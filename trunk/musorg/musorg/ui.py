#!/usr/bin/env python
# -*- coding: utf-8 -*-

# ui.py
# author: rshen
# date: 2009-03-04

from ui_api import *
from PyQt4.QtGui import *
from PyQt4.QtCore import *
from musorg.db import *
from musorg.search_model import *
from musorg.searcher import *
from musorg.downloader import *
from musorg.organizer import *

class MusorgMainWindow(QMainWindow, Ui_MainWindow):
    def __init__(self, config):
        QMainWindow.__init__(self)

        self.__STATUS_ICONS__ = {
                IDLE : QIcon(QPixmap(":/icons/icons/status_idle.png")),
                DOWNLOADING : QIcon(QPixmap(":/icons/icons/down.png")),
                PAUSE : QIcon(QPixmap(":/icons/icons/pause.png")),
                COMPLETE : QIcon(QPixmap(":/icons/icons/status_complete.png")),
                FAILED : QIcon(QPixmap(":/icons/icons/status_failed.png"))
        }
        self.setupUi(self)

        # Some initializations
        self.change_tab(0)
        self.downloader = DownloadManager(self)
        self.downloader.start()

        # ========================
        # Load configurations
        self.config = config
        self._load_config(config)

        # ========================
        # Initialize preferences page elements
        for k, v in ENGINES.items():
            self.cb_engines.addItem(v, QVariant(k))

        # ========================
        # Add search status
        self.lb_search = QLabel()
        self.lb_search.setText("Search Status")
        self.statusbar.addPermanentWidget(self.lb_search, 1)

        # ========================
        # Add download status
        self.lb_download = QLabel()
        self.lb_download.setText(
                "Number of downloads: %d | Download speed: %.2f" % (0, 0.0))
        self.statusbar.addPermanentWidget(self.lb_download, 1)

        # ========================
        # Search Model initialization
        sr_headers = ["Title", "Artist", "Album", "Format", "Size"]
        self.sr_model = QStandardItemModel()
        self.trw_results.setModel(self.sr_model)
        self.trw_results.addAction(self.actionStart_Download)
        self.sr_model.setColumnCount(5)
        self.sr_model.setHorizontalHeaderLabels(sr_headers)

        # =========================
        # Download Model initialization
        dn_headers = ["Status", "File", "Progress", "Speed"]
        self.dn_model = QStandardItemModel()
        self.prog_delegate = ProgressBarDelegate(self)
        self.trw_downloads.setModel(self.dn_model)
        self.trw_downloads.setItemDelegate(self.prog_delegate)
        self.trw_downloads.addAction(self.actionPause_Download)
        self.trw_downloads.addAction(self.actionResume_Download)
        self.trw_downloads.addAction(self.actionRemove_Download)
        self.dn_model.setHorizontalHeaderLabels(dn_headers)

        # ==========================
        # Albums view initialization
        menu = QMenu(self)
        menu.addAction(self.actionImport_Files)
        menu.addAction(self.actionImport_Directory)
        self.tb_import_albums.setMenu(menu)

        self.pb_reload.setVisible(False)

        # ==========================
        # Albums list model initialization
        self.albums_model = QStandardItemModel()
#        items = AlbumItem.init_std_items(":/icons/icons/empty_cd.png",
#                "Meteora", 10)
#        self.albums_model.appendRow(items)
        albums = session.query(AlbumItem).all()
        for album in albums:
            self.albums_model.appendRow(album.to_std_items())
        self.trw_albums.setItemDelegate(AlbumItemDelegate(self))
        self.trw_albums.setModel(self.albums_model)
        self.trw_albums.setRootIsDecorated(False)
        self.trw_albums.setColumnWidth(0, 50)

        # ==========================
        # System tray icon
        self.sys_icon = QSystemTrayIcon(QIcon(QPixmap(
            ":/icons/icons/filesave.png")))
        self.sys_icon.setToolTip("A tooltip\nAnother line")
        self.sys_icon.show()

        # ==========================
        # Signals connection
        self.connect(self.btn_search, SIGNAL('clicked()'), self.search)
        # ACTIONS SIGNALS
        self.connect(self.actionStart_Download, SIGNAL("triggered()"),
                self.add_download)
        self.connect(self.actionRemove_Download, SIGNAL("triggered()"),
                self.remove_download)
        self.connect(self.actionPause_Download, SIGNAL("triggered()"),
                self.pause_download)
        self.connect(self.actionResume_Download, SIGNAL("triggered()"),
                self.resume_download)
        self.connect(self.actionReload_Collection, SIGNAL("triggered()"),
                self.reload_collection)
        self.connect(self.actionCorrect_Encodings, SIGNAL("triggered()"),
                self.correct_encodings)
        self.connect(self.actionSave_Preferences, SIGNAL("triggered()"),
                self.save_config)

        self.connect(self.trw_albums, SIGNAL("clicked(const QModelIndex)"),
                self.show_tracks)

        self.connect(self.main_tab, SIGNAL("currentChanged(int)"),
                self.change_tab)
        self.connect(self.btn_browse_down_dir, SIGNAL("clicked()"),
                self.set_download_dir)
        self.connect(self, SIGNAL("downloadStatusChanged(int,float)"),
                self.set_download_status)
        self.connect(self.sys_icon, SIGNAL("activated(QSystemTrayIcon::ActivationReason)"),
                self.sys_icon_activated)

        # Downloader SIGNALS
        self.connect(self.downloader, SIGNAL("downloadStatus(QString, int)"),
                self.update_download_status)
        self.connect(self.downloader, SIGNAL("progressUpdated(QString, int)"),
                self.update_progress)

    def sys_icon_activated(self, reason):
        if reason & QSystemTrayIcon.Trigger:
            self.setVisible(not self.isVisible())

    def show_tracks(self, index):
        print "here"

    def set_download_status(self, num_downloads, speed):
        self.lb_download.setText(
                "Number of downloads: %d | Download speed: %.2f" % (
                    num_downloads, speed))

    def update_download_status(self, down_id, status):
        download_task = self.downloader.downloads[str(down_id)]
        if download_task != None:
            download = download_task.download_item
            download.set_icon(COL_STATUS, self.__STATUS_ICONS__[int(status)])
            download.set_data(COL_STATUS, str(status))

    def change_tab(self, index):
        self.actionStart_Download.setEnabled(index == 0)
        self.actionPause_Download.setEnabled(index == 0)
        self.actionResume_Download.setEnabled(index == 0)
        self.actionRemove_Download.setEnabled(index == 0)

        self.actionReload_Collection.setEnabled(index == 1)
        self.actionAuto_Organize.setEnabled(index == 1)
        self.actionCorrect_Encodings.setEnabled(index == 1)

        self.actionSave_Preferences.setEnabled(index == 2)

    def search(self):
        sr_contents = self.lne_search.text()
        sr_thread = YahooSearcher(self, self.sr_model,
                unicode(sr_contents))
        self.connect(sr_thread, SIGNAL("searchStatusChanged(QString)"),
                self.update_search_status)
        sr_thread.start()

    def update_search_status(self, status):
        self.lb_search.setText(status)

    def add_download(self):
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

    def remove_download(self):
        selection = self.trw_downloads.selectionModel().selectedRows()
        for i, s in enumerate(selection):
            row = self.dn_model.takeRow(s.row())
            down_id = str(row[COL_ID].data(Qt.UserRole).toString())
            self.downloader.remove_download(down_id)

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

    def reload_collection(self):
        self.pb_reload.setVisible(True)
        reload_thread = CollectionLoader(self,
                self.config.get("Downloader", "download.dir"))
        self.connect(reload_thread, SIGNAL("trackAdded(int, QString)"),
                self.update_loading_status)
        self.connect(reload_thread, SIGNAL("loadingComplete()"),
                self.complete_loading)
        reload_thread.start()

    def update_loading_status(self, percent, track_name):
        self.pb_reload.setValue(percent)

    def complete_loading(self):
        self.albums_model.removeRows(0, self.albums_model.rowCount())
        self.pb_reload.setVisible(False)
        albums = session.query(AlbumItem).all()
        for album in albums:
            std_items = album.to_std_items()
            self.albums_model.appendRow(std_items)

    def correct_encodings(self):
        encoding_thread = EncodingCorrector(self)
        self.connect(encoding_thread, SIGNAL("encodingComplete()"),
                self.complete_encoding)
        encoding_thread.start()

    def complete_encoding(self):
        self.albums_model.removeRows(0, self.albums_model.rowCount())
        albums = session.query(AlbumItem).all()
        for album in albums:
            print album.name
            std_items = album.to_std_items()
            self.albums_model.appendRow(std_items)

    def update_progress(self, down_id, prog):
        if self.downloader.downloads.has_key(str(down_id)):
            download_task = self.downloader.downloads[str(down_id)]
            if download_task != None:
                download = download_task.download_item
                download.set_text(COL_PROGRESS, str(prog))

    def set_download_dir(self):
        cur_down_dir = self.config.get("Downloader", "download.dir")
        cur_down_dir = os.path.abspath(os.path.expanduser(cur_down_dir))
        down_dir = QFileDialog.getExistingDirectory(self,
                "Browse Download Directory", cur_down_dir)
        if down_dir != None and down_dir != '':
            self.config.set("Downloader", "download.dir", unicode(down_dir))
            self.lne_download_dir.setText(down_dir)

    def save_config(self):
        self.config.save()

    def _load_config(self, config):
        down_dir = self.config.get("Downloader", "download.dir")
        down_dir = os.path.abspath(os.path.expanduser(down_dir))
        self.lne_download_dir.setText(down_dir)
