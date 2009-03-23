#!/usr/bin/env python
# -*- coding: utf-8 -*-

# organizer.py
# author: rshen
# date: 2009-03-16

import os
import chardet
from PyQt4.QtGui import *
from PyQt4.QtCore import *
from musorg.db import *

COL_COVER_ART = 0

FILE_TYPES = [".mp3", ".flac", ".mpc"]

class Organizer(QThread):
    def __init__(self, parent):
        QThread.__init__(self, parent)

    def run(self):
        pass

class EncodingCorrector(QThread):
    def __init__(self, parent):
        QThread.__init__(self, parent)

    def run(self):
        tracks = session.query(TrackItem).all()
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
            encoding = "GB2312"
        else:
            encoding = enc_list[index]["encoding"]

        return encoding

    def get_encoding(self, text):
        latin_text = text.encode("latin1", "replace")
        enc = chardet.detect(latin_text)
        print text, enc
        return enc

    def correct_encoding(self, text, encoding):
        encoded_text, changed = text, False
        latin_text = text.encode("latin1", "replace")
        enc = chardet.detect(latin_text)["encoding"]
        if enc != None and enc != "ascii" and encoding != None:
            encoded_text = latin_text.decode(encoding, "replace")
            changed = True

        return (changed, encoded_text)

class CollectionLoader(QThread):

    def __init__(self, parent, path):
        QThread.__init__(self, parent)

        self.path = path

    def run(self):
        session.query(ArtistItem).delete()
        session.query(AlbumItem).delete()
        session.query(TrackItem).delete()

        file_list = []
        for root, dirs, files in os.walk(self.path):
            for f in files:
                if os.path.splitext(f)[1] in FILE_TYPES:
                    file_list.append(TrackItem.get_info(os.path.join(root, f)))

        num_files, index = len(file_list), 1
        for f in file_list:
            track, artist, album = TrackItem(f[0], f[4], f[5], 0), None, None
            if f[2] == None or f[2] == '':
                artist = ArtistItem.get_unknown_artist(session)
            else:
                artist_query = session.query(ArtistItem, ArtistItem.name)\
                        .filter(ArtistItem.name == f[2]).all()
                if len(artist_query) == 0:
                    new_artist = ArtistItem(f[2])
                    session.add(new_artist)
                    artist = new_artist
                else:
                    artist = artist_query[0].ArtistItem
            track.artist = artist

            if f[1] == None or f[1] == '':
                album = AlbumItem.get_unknown_album(session)
            else:
                album_query = session.query(AlbumItem, AlbumItem.name)\
                        .filter(AlbumItem.name == f[1]).all()
                if len(album_query) == 0:
                    new_album = AlbumItem(f[1], ":/icons/icons/empty_cd.png",
                            0)
                    session.add(new_album)
                    album = new_album
                else:
                    album = album_query[0].AlbumItem
            album.artist = artist
            track.album = album

            session.add(track)
            percent, index = (float(index) / num_files) * 100, index + 1
            self.emit(SIGNAL("trackAdded(int, QString)"), int(percent),
                    f[0])

        session.commit()
        self.emit(SIGNAL("loadingComplete()"))

class AlbumItemDelegate(QItemDelegate):

    def __init__(self, parent):
        QItemDelegate.__init__(self, parent)

    def paint(self, painter, option, index):
        if option.state & QStyle.State_Selected:
            painter.fillRect(option.rect, option.palette.highlight())
            painter.setBrush(option.palette.highlightedText())
        if index.column() == COL_COVER_ART:
            data = index.data()
            path = data.toString()
            icon = QIcon(path)
            pixmap = icon.pixmap(QSize(48, 48),
                    QIcon.Normal, QIcon.On)
            painter.drawPixmap(option.rect.topLeft(), pixmap)
        else:
            text = index.data(Qt.UserRole).toStringList()
            topLeft = option.rect.topLeft()
            cur_font = painter.font()
            cur_font_metrics = painter.fontMetrics()
            new_font = QFont(cur_font.family(), 12,
                    QFont.Bold)
            new_small_font = QFont(cur_font.family(),
                    cur_font.pointSize(), QFont.Normal)
            painter.setFont(new_font)
            font_metrics = painter.fontMetrics()
            painter.drawText(QPoint(topLeft.x(),
                topLeft.y() + font_metrics.height()),
                text[0])
            painter.setFont(new_small_font)
            painter.drawText(QPoint(topLeft.x(),
                topLeft.y() + font_metrics.height() +
                cur_font_metrics.height()), str(text[1]))
