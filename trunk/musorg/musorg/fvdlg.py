#!/usr/bin/env python
# -*- coding: utf-8 -*-

# fvdlg.py
# author: rshen
# date: 2009-03-17

import sys
from fvdlg_api import *
from PyQt4.QtGui import *
from PyQt4.QtCore import *

class FavouritesEditDialog(QDialog, Ui_FavouritesEditDialog):
    def __init__(self):
        QDialog.__init__(self)

        self.setupUi(self)

        menu = QMenu(self)
        menu.addAction(self.actionAdd_Albums)
        menu.addAction(self.actionAdd_Tracks)
        self.tb_add.setMenu(menu)

app = QApplication(sys.argv)

frame = FavouritesEditDialog()
frame.show()
sys.exit(app.exec_())
