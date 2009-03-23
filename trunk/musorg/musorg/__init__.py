#!/usr/bin/env python
# -*- coding: utf-8 -*-

# __init__.py
# author: rshen
# date: 2009-03-19

import sys
from PyQt4.QtGui import *
from PyQt4.QtCore import *
from musorg.window import *
from musorg.downloader import *
from musorg.organizer import *
from musorg.preferences import *

class MusorgMainWindow(Ui_MainWindow, QMainWindow):

    def __init__(self, config=None):
        QMainWindow.__init__(self)
        self.setupUi(self)

        self.config = config
        #self.change_tab(0)

        self.lb_search = QLabel()
        self.lb_search.setText("Search status")
        self.statusbar.addPermanentWidget(self.lb_search, 1)

        self.lb_download = QLabel()
        self.lb_download.setText(
                "Number of downloads: %d | Download speed: %.2f" % (0, 0.0))
        self.statusbar.addPermanentWidget(self.lb_download, 1)

        self.downloader = DownloaderWidget(self.tab_downloader, config,
                actionStart_Download=self.actionStart_Download,
                actionPause_Download=self.actionPause_Download,
                actionResume_Download=self.actionResume_Download,
                actionRemove_Download=self.actionRemove_Download)
        self.verticalLayout_2.addWidget(self.downloader)
        self.organizer = OrganizerWidget(self.tab_organizer, config,
                actionImport_Files=self.actionImport_Files,
                actionImport_Directory=self.actionImport_Directory,
                actionReload_Collection=self.actionReload_Collection,
                actionCorrect_Encodings=self.actionCorrect_Encodings)
        self.horizontalLayout_8.addWidget(self.organizer)
        self.preferences = PreferencesWidget(self.tab_pref, config,
                actionSave_Preferences=self.actionSave_Preferences)
        self.verticalLayout_5.addWidget(self.preferences)

        self.connect(self.downloader, SIGNAL("searchStatusChanged(QString)"),
                self.update_search_status)
        self.connect(self.downloader, SIGNAL("downloadStatusChanged(int,float)"),
                self.update_download_status)

    def change_tab(self, index):
        self.actionStart_Download.setEnabled(index == 0)
        self.actionPause_Download.setEnabled(index == 0)
        self.actionResume_Download.setEnabled(index == 0)
        self.actionRemove_Download.setEnabled(index == 0)

        self.actionReload_Collection.setEnabled(index == 1)
        self.actionAuto_Organize.setEnabled(index == 1)
        self.actionCorrect_Encodings.setEnabled(index == 1)

        self.actionSave_Preferences.setEnabled(index == 2)

    def update_download_status(self, num_downloads, speed):
        self.lb_download.setText(
                "Number of downloads: %d | Download speed: %.2f" % (num_downloads, speed))

    def update_search_status(self, message):
        self.lb_search.setText(message)

def main(config):
    app = QApplication(sys.argv)
    frame = MusorgMainWindow(config)
    frame.show()

    sys.exit(app.exec_())
