#!/usr/bin/env python
# -*- coding: utf-8 -*-

# organizer.encoding
# author: rshen
# date: 2009-03-23

import chardet
from PyQt4.QtCore import QThread, SIGNAL
from musorg.db import *

class EncodingCorrector(QThread):
    def __init__(self, parent, config):
        QThread.__init__(self, parent)

        self.config = config

    def run(self):
        tracks = session.query(TrackItem).all()
        size, i = len(tracks), 0
        for track in tracks:
            f = track.path
            title, album, artist = self.correct_tags_encoding(f)
            track.name = title.encode("utf-8")
            track.album.name = album.encode("utf-8") if\
                    (album != None and album != "") else\
                    UNKNOWN_ALBUM[0]
            track.artist.name = artist.encode("utf-8") if\
                    (artist != None and artist != "") else\
                    UNKNOWN_ARTIST
            session.merge(track)
            i += 1
            self.emit(SIGNAL("encoding(int,QString)"), int(float(i)/size*100),
                    "")

        session.commit()
        self.emit(SIGNAL("encodingComplete()"))

    def correct_tags_encoding(self, f):
        tagger = FileRef(f)

        tags = tagger.tag()
        title_encoding = self.get_encoding(tags.title)
        album_encoding = self.get_encoding(tags.album)
        artist_encoding = self.get_encoding(tags.artist)
        best_encoding = self._highest_conf([title_encoding, album_encoding,
            artist_encoding])

        changed, title = self.correct_encoding(tags.title, best_encoding)
        tags.title = title if changed else tags.title
        changed, album = self.correct_encoding(tags.album, best_encoding)
        tags.album = album if changed else tags.album
        changed, artist = self.correct_encoding(tags.artist, best_encoding)
        tags.artist = artist if changed else tags.artist

        tagger.save()

        return (title, album, artist)

    def _highest_conf(self, enc_list):
        highest_conf, index = 0.0, -1
        for i in range(len(enc_list)):
            enc = enc_list[i]
            if enc["confidence"] > highest_conf and enc["encoding"] != None\
                    and enc["encoding"] != "ascii":
                highest_conf = enc["confidence"]
                index = i

        if highest_conf < 0.6 or index < 0:
            encoding = self.config.get("Organizer", "default.enc")
        else:
            encoding = enc_list[index]["encoding"]

        return encoding

    def get_encoding(self, text):
        latin_text = text.encode("latin1", "replace")
        enc = chardet.detect(latin_text)
        return enc

    def correct_encoding(self, text, encoding):
        encoded_text, changed = text, False
        latin_text = text.encode("latin1", "replace")
        enc = chardet.detect(latin_text)["encoding"]
        if enc != None and enc != "ascii" and encoding != None:
            encoded_text = latin_text.decode(encoding, "replace")
            changed = True

        return (changed, encoded_text)
