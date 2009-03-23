#!/usr/bin/env python
# -*- coding: utf-8 -*-

# downloads_model.py
# author: rshen
# date: 2009-03-05

from PyQt4.QtGui import *
from PyQt4.QtCore import *

IDLE = 0
DOWNLOADING = 1
PAUSE = 2
COMPLETE = 3
FAILED = 4

COL_STATUS = 0 # Display
COL_PROGRESS = 1 # Display
COL_FILE = 2 # Display
COL_SPEED = 3 # Display
COL_ID = 3 # Data
COL_LINK = 1 # Data
COL_PATH = 2 # Data

class ProgressBarDelegate(QItemDelegate):

    def __init__(self, main_window):
        QItemDelegate.__init__(self, main_window)
        pass

    def paint(self, painter, option, index):
        if index.column() == COL_PROGRESS:
            data = index.data()

            style_pb = QStyleOptionProgressBar()
            style_pb.state = QStyle.State_Enabled
            style_pb.direction = QApplication.layoutDirection()
            style_pb.rect = option.rect
            style_pb.fontMetrics = QApplication.fontMetrics()
            style_pb.minimum = 0
            style_pb.maximum = 100
            style_pb.textAlignment = Qt.AlignCenter
            style_pb.textVisible = True

            progress = data.toInt()[0]
            style_pb.progress = progress
            style_pb.text = QString("%1%").arg(style_pb.progress)

            QApplication.style().drawControl(QStyle.CE_ProgressBar, style_pb, painter)
        else:
            QItemDelegate.paint(self, painter, option, index)
