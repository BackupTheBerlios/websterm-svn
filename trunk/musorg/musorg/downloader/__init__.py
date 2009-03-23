#!/usr/bin/env python
# -*- coding: utf-8 -*-

# downloader.__init__
# author: rshen
# date: 2009-03-21

import os
import sys
from PyQt4.QtGui import *
from PyQt4.QtCore import *
from musorg.downloader.ui import *
from musorg.downloader.model import *
from musorg.downloader.util import *
from musorg.downloader.search import *

class DownloaderWidget(Ui_Downloader, QWidget):

    def __init__(self, parent=None, config=None, **actions):
        QWidget.__init__(self, parent)
        self.setupUi(self)

        self.__STATUS_ICONS__ = {
                IDLE : QIcon(QPixmap(":/icons/icons/status_idle.png")),
                DOWNLOADING : QIcon(QPixmap(":/icons/icons/down.png")),
                PAUSE : QIcon(QPixmap(":/icons/icons/pause.png")),
                COMPLETE : QIcon(QPixmap(":/icons/icons/status_complete.png")),
                FAILED : QIcon(QPixmap(":/icons/icons/status_failed.png"))
        }

        self.config = config
        self.downloader = DownloadManager(self)
        self.downloader.start()

        self.actionStart_Download = actions["actionStart_Download"]
        self.actionPause_Download = actions["actionPause_Download"]
        self.actionResume_Download = actions["actionResume_Download"]
        self.actionRemove_Download = actions["actionRemove_Download"]

        sr_headers = ["Title", "Artist", "Album", "Format", "Size"]
        self.sr_model = QStandardItemModel()
        self.trw_results.setModel(self.sr_model)
        self.trw_results.addAction(self.actionStart_Download)
        self.sr_model.setColumnCount(5)
        self.sr_model.setHorizontalHeaderLabels(sr_headers)

        dn_headers = ["Status", "File", "Progress", "Speed"]
        self.dn_model = QStandardItemModel()
        self.trw_downloads.setModel(self.dn_model)
        self.trw_downloads.setItemDelegate(ProgressBarDelegate(
            self.trw_downloads))
        self.trw_downloads.addAction(self.actionPause_Download)
        self.trw_downloads.addAction(self.actionResume_Download)
        self.trw_downloads.addAction(self.actionRemove_Download)
        self.dn_model.setHorizontalHeaderLabels(dn_headers)

        self.connect(self.btn_search, SIGNAL("clicked()"), self.search)
        self.connect(self.actionStart_Download, SIGNAL("triggered()"),
                self.start_download)
        self.connect(self.actionPause_Download, SIGNAL("triggered()"),
                self.pause_download)
        self.connect(self.actionResume_Download, SIGNAL("triggered()"),
                self.resume_download)
        self.connect(self.actionRemove_Download, SIGNAL("triggered()"),
                self.remove_download)
        self.connect(self.downloader, SIGNAL("downloadStatus(QString, int)"),
                self.update_download_status)
        self.connect(self.downloader, SIGNAL("progressUpdated(QString, int)"),
                self.update_progress)

    def search(self):
        sr_contents = self.lne_search.text()
        self.emit(SIGNAL("searchStatusChanged(QString)"),
                "Searching for %s" % unicode(sr_contents))
        sr_thread = YahooSearcher(self, self.sr_model, unicode(sr_contents))
        sr_thread.start()

    def update_download_status(self, down_id, status):
        download_task = self.downloader.downloads[str(down_id)]
        if download_task != None:
            download = download_task.download_item
            download.set_icon(COL_STATUS, self.__STATUS_ICONS__[int(status)])
            download.set_data(COL_STATUS, str(status))

    def update_progress(self, down_id, prog):
        if self.downloader.downloads.has_key(str(down_id)):
            download_task = self.downloader.downloads[str(down_id)]
            if download_task != None:
                download = download_task.download_item
                download.set_text(COL_PROGRESS, str(prog))

    def start_download(self):
        selection = self.trw_results.selectionModel().selectedRows()
        for i, s in enumerate(selection):
            dn_status_item = DownloadItem.to_std_icon("",
                    self.__STATUS_ICONS__[IDLE])

            dn_progress_item = DownloadItem.to_std_text("0")
            dn_link = str(self.sr_model.item(s.row(), 0).data(Qt.UserRole)
                    .toString())
            dn_progress_item.setData(QVariant(dn_link), Qt.UserRole)

            format = str(self.sr_model.item(s.row(), 3).text())
            if format == '':
                format = "mp3"
            title = unicode(self.sr_model.item(s.row(), 0).text())
            dn_file = title + "." + format
            dn_file_item = DownloadItem.to_std_text(dn_file)
            dn_path = self.config.get("Downloader", "download.dir") + "/" +\
                    dn_file
            dn_path = os.path.abspath(os.path.expanduser(dn_path))
            dn_file_item.setData(QVariant(dn_path), Qt.UserRole)

            dn_speed_item = DownloadItem.to_std_text("0.0")

            dn_item = DownloadItem(self, [dn_status_item, dn_progress_item,
                dn_file_item, dn_speed_item])
            dn_id = id(dn_item)
            dn_item.set_data(COL_ID, dn_id)

            self.dn_model.appendRow([dn_status_item, dn_progress_item,
                dn_file_item, dn_speed_item])
            self.downloader.queue_download(dn_item)

            self.emit(SIGNAL("downloadStatusChanged(int,float)"),
                    len(self.downloader.downloads), 0.0)

    def pause_download(self):
        selection = self.trw_downloads.selectionModel().selectedRows()
        for i, s in enumerate(selection):
            down_item = self.dn_model.item(s.row(), COL_ID)
            down_id = str(down_item.data(Qt.UserRole).toString())
            self.downloader.pause_download(down_id)

    def resume_download(self):
        selection = self.trw_downloads.selectionModel().selectedRows()
        for i, s in enumerate(selection):
            down_item = self.dn_model.item(s.row(), COL_ID)
            down_id = str(down_item.data(Qt.UserRole).toString())
            self.downloader.resume_download(down_id)

    def remove_download(self):
        selection = self.trw_downloads.selectionModel().selectedRows()
        for i, s in enumerate(selection):
            row = self.dn_model.takeRow(s.row())
            down_id = str(row[COL_ID].data(Qt.UserRole).toString())
            self.downloader.remove_download(down_id)

# For testing purposes
def main():
    app = QApplication(sys.argv)
    frame = DownloaderWidget()
    frame.show()

    sys.exit(app.exec_())
