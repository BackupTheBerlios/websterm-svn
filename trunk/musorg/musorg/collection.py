#!/usr/bin/env python
# -*- coding: utf-8 -*-

# collection.py
# author: rshen
# date: 2009-03-18

from PyQt4.QtCore import QRunnable

class CollectionLoader(QRunnable):

    def __init__(self, parent):
        QRunnable.__init__(self, parent)

        self.setAutoDelete(True)

    def run(self):
        pass
