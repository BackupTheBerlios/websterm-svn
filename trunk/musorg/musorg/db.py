#!/usr/bin/env python
# -*- coding: utf-8 -*-

# db.py
# author: rshen
# date: 2009-03-18

import os
from PyQt4.QtGui import QStandardItem
from PyQt4.QtCore import QSize, QVariant, Qt
from sqlalchemy import *
from sqlalchemy import ForeignKey
from sqlalchemy.orm import relation, backref, sessionmaker
from sqlalchemy.ext.declarative import declarative_base
from tagpy import FileRef

UNKNOWN_ALBUM = ("Unknown Album@@", ":/icons/icons/empty_cd.png",
        0)
UNKNOWN_ARTIST = "Unknown Artist@@"

db = create_engine("mysql://root:jackshen@localhost:3306/musorg")
Base = declarative_base()
favourite_tracks = Table('favourite_tracks', Base.metadata,
        Column('favourite_id', Integer, ForeignKey('favourites.id')),
        Column('track_id', Integer, ForeignKey('tracks.id'))
)
Session = sessionmaker(bind=db)
session = Session()

class ArtistItem(Base):
    __tablename__ = 'artists'

    id = Column(Integer, primary_key=True)
    name = Column(String(100))

    albums = relation('AlbumItem', backref=backref('artist'),
            cascade='all')
    tracks = relation('TrackItem', backref=backref('artist'),
            cascade='all')

    def __init__(self, name):
        self.name = name

    @staticmethod
    def get_unknown_artist(s):
        new_artist = None
        artist = s.query(ArtistItem, ArtistItem.name).filter(
                ArtistItem.name == UNKNOWN_ARTIST).all()
        if len(artist) == 0:
            new_artist = ArtistItem(UNKNOWN_ARTIST)
            s.add(new_artist)
        else:
            new_artist = artist[0].ArtistItem

        return new_artist

class AlbumItem(Base):
    __tablename__ = 'albums'

    id = Column(Integer, primary_key=True)
    name = Column(String(100))
    coverart = Column(String(255))
    rating = Column(Integer)
    artist_id = Column(Integer, ForeignKey('artists.id',
        onupdate='cascade'))

    tracks = relation('TrackItem', backref=backref('album'),
            cascade='all')

    def __init__(self, name, coverart, rating):
        self.name = name
        self.coverart = coverart
        self.rating = rating

    @staticmethod
    def get_unknown_album(s):
        new_album = None
        album = s.query(AlbumItem, AlbumItem.name).filter(
                AlbumItem.name == UNKNOWN_ALBUM[0]).all()
        if len(album) == 0:
            new_album = AlbumItem(UNKNOWN_ALBUM[0], UNKNOWN_ALBUM[1],
                    UNKNOWN_ALBUM[2])
            s.add(new_album)
        else:
            new_album = album[0].AlbumItem

        return new_album

    def to_std_items(self):
        cover_art_item = QStandardItem(self.coverart)
        cover_art_item.setSizeHint(QSize(50, 50))
        cover_art_item.setData(QVariant(self.id), Qt.UserRole)
        album_name_item = QStandardItem(self.name)
        album_name_item.setData(QVariant([self.name.decode("utf-8").strip(),
            self.rating]), Qt.UserRole)

        items = [cover_art_item, album_name_item]
        return items

    @staticmethod
    def init_std_items(aid, name, cover_art, year):
        cover_art_item = QStandardItem(cover_art)
        cover_art_item.setSizeHint(QSize(50, 50))
        cover_art_item.setData(QVariant(aid), Qt.UserRole)
        album_name_item = QStandardItem(name)
        album_name_item.setData(QVariant([name, year]),
                Qt.UserRole)

        items = [cover_art_item, album_name_item]
        return items

class TrackItem(Base):
    __tablename__ = 'tracks'

    id = Column(Integer, primary_key=True)
    name = Column(String(100))
    position = Column(Integer)
    path = Column(String(255))
    rating = Column(Integer)
    artist_id = Column(Integer, ForeignKey('artists.id',
        onupdate='cascade'))
    album_id = Column(Integer, ForeignKey('albums.id',
        onupdate='cascade'))

    def __init__(self, name, position, path, rating):
        self.name = name
        self.position = position
        self.path = path
        self.rating = rating

    @staticmethod
    def get_info(path):
        format = os.path.splitext(path)[1]
        tagger = FileRef(path)

        tags = tagger.tag()
        title = tags.title
        album = tags.album
        artist = tags.artist
        position = tags.track
        year = tags.year

        return (title.encode("utf-8"), album.encode("utf-8"),
                artist.encode("utf-8"), str(year),
                position, path)

    def to_std_item(self):
        name_item = QStandardItem(self.name.decode("utf-8"))
        name_item.setData(QVariant(self.id), Qt.UserRole)
        position_item = QStandardItem(str(self.position))
        rating_item = QStandardItem(str(self.rating))
        artist_item = QStandardItem(self.artist.name.decode("utf-8"))
        file_item = QStandardItem(os.path.split(self.path)[1]\
                .decode("utf-8"))

        return [name_item, position_item, artist_item, rating_item,
                file_item]

class FavouriteItem(Base):
    __tablename__ = 'favourites'

    id = Column(Integer, primary_key=True)
    name = Column(String(100))
    icon = Column(String(255))
    rating = Column(Integer)

    tracks = relation(TrackItem, secondary=favourite_tracks,
            backref=backref('favourites'))

    def __init__(self, name, icon, rating):
        self.name = name
        self.icon = icon
        self.rating = rating

Base.metadata.create_all(db)
