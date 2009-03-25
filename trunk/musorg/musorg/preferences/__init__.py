#!/usr/bin/env python
# -*- coding: utf-8 -*-

# preferences.__init__
# author: rshen
# date: 2009-03-21

import os
import sys
from PyQt4.QtGui import *
from PyQt4.QtCore import *
from musorg.preferences.ui import *
from musorg.downloader.search import *


class PreferencesWidget(Ui_Preferences, QWidget):

    def __init__(self, parent=None, config=None, **actions):
        QWidget.__init__(self, parent)
        self.setupUi(self)

        self.config = config

        # Setup elements
        for k, v in ENGINES.items():
            self.cb_engines.addItem(v, QVariant(k))

        self.load_config(config)

        self.actionSave_Preferences = actions['actionSave_Preferences']

        self.connect(self.btn_browse_down_dir, SIGNAL("clicked()"),
                self.set_download_dir)
        self.connect(self.sb_max_downloads, SIGNAL("valueChanged(int)"),
                self.set_max_threads)
        self.connect(self.cb_engines, SIGNAL("currentIndexChanged(int)"),
                self.set_search_engine)
        self.connect(self.lne_encoding, SIGNAL("textChanged(QString)"),
                self.set_default_encoding)
        self.connect(self.actionSave_Preferences, SIGNAL("triggered()"),
                self.save_preferences)

    def load_config(self, config):
        down_dir = self.config.get("Downloader", "download.dir")
        down_dir = os.path.abspath(os.path.expanduser(down_dir))
        self.lne_download_dir.setText(down_dir)

        max_download = self.config.get("Downloader", "num.threads")
        self.sb_max_downloads.setValue(int(max_download))

        engine = self.config.get("Downloader", "search.engine")
        index = self.cb_engines.findData(QVariant(int(engine)))
        self.cb_engines.setCurrentIndex(index)

        encoding = self.config.get("Organizer", "default.enc")
        self.lne_encoding.setText(encoding)

    def set_download_dir(self):
        cur_down_dir = self.config.get("Downloader", "download.dir")
        cur_down_dir = os.path.abspath(os.path.expanduser(cur_down_dir))
        down_dir = QFileDialog.getExistingDirectory(self,
                "Browse Download Directory", cur_down_dir)
        if down_dir != None and down_dir != '':
            print down_dir
            self.config.set("Downloader", "download.dir", unicode(down_dir))
            self.lne_download_dir.setText(down_dir)

    def set_max_threads(self, num_threads):
        self.config.set("Downloader", "num.threads", str(num_threads))

    def set_search_engine(self, index):
        engine = self.cb_engines.itemData(index)
        self.config.set("Downloader", "search.engine", str(engine.toInt()[0]))

    def set_default_encoding(self, text):
        self.config.set("Organizer", "default.enc", unicode(text))

    def save_preferences(self):
        self.config.save()

def main():
    app = QApplication(sys.argv)
    frame = PreferencesWidget()
    frame.show()

    sys.exit(app.exec_())
