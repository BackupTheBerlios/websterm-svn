#!/usr/bin/env python
# -*- coding: utf-8 -*-

# organizer.collection
# author: rshen
# date: 2009-03-23

import os
from musorg.db import *
from PyQt4.QtCore import QThread, SIGNAL

COL_COVER_ART = 0

FILE_TYPES = [".mp3", ".flac", ".mpc"]

class CollectionLoader(QThread):

    def __init__(self, parent):
        QThread.__init__(self, parent)

    def run(self):
        albums = session.query(AlbumItem)
        for album in albums:
            self.emit(SIGNAL("albumLoaded(int, QString, QString, int)"),
                    album.id, album.coverart.decode("utf-8"),
                    album.name.decode("utf-8"), album.rating)

class CollectionReloader(QThread):

    def __init__(self, parent, path):
        QThread.__init__(self, parent)

        self.path = path

    def run(self):
        session.query(ArtistItem).delete()
        session.query(AlbumItem).delete()
        session.query(TrackItem).delete()

        file_list = []
        for root, dirs, files in os.walk(os.path.abspath(
            os.path.expanduser(self.path))):
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
