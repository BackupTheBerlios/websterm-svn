#!/usr/bin/env python
# -*- coding: utf-8 -*-

# search_model.py
# author: rshen
# date: 2009-03-05

from PyQt4.QtGui import *
from PyQt4.QtCore import *

class SearchResultItem(object):
    def __init__(self, data):
        self._data = data
        self.title = data[0]
        self.artist = data[1]
        self.album = data[2]
        self.format = data[3]
        self.size = data[4]
        self.url = data[5]

    def data(self, index):
        return self._data[index.column()]

    def to_tree_item(self, model):
        row = []
        for i, d in enumerate(self._data[0:5]):
            item = QStandardItem(unicode(d))
            item.setEditable(False)
            row.append(item)
        title_item = row[0]
        title_item.setData(QVariant(self.url), Qt.UserRole)
        model.appendRow(row)
        return item
