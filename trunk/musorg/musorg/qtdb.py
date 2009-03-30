#!/usr/bin/env python
# -*- coding: utf-8 -*-

# qtdb.py
# author: rshen
# date: 2009-03-24

from PyQt4.QtSql import *

Q_TRACKS_ALBUM = QSqlQuery()

def prepare_statements():
    global Q_TRACKS_ALBUM
    Q_TRACKS_ALBUM.prepare("select name from tracks where tracks.album_id = ?")

def init_db(host, username, password, dbname):
    db = QSqlDatabase.addDatabase("QMYSQL")
    db.setHostName(host)
    db.setUserName(username)
    db.setPassword(password)
    db.setDatabaseName(dbname)

    if not db.open():
        return db.lastError()

    return None
