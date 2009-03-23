#!/usr/bin/env python
# -*- coding: utf-8 -*-

# downloader.model
# author: rshen
# date: 2009-03-05

from musorg.downloader.util import *
from PyQt4.QtCore import *
from PyQt4.QtGui import *
import BeautifulSoup as bs
import urllib

class DownloadManager(QThread):
    def __init__(self, parent, max_download=-1):
        QThread.__init__(self, parent)

        self.parent = parent
        self.max_download = max_download
        self.downloads = {}
        self.download_semaphore = QSemaphore(max_download)
        self.download_queue = []
        self.wait_semaphore = QSemaphore(0)
        self.wait_queue = []

    def queue_download(self, download):
        download_task = DownloadTask(self.parent, download)
        self.wait_queue.append(download_task)
        self.downloads[download.data(COL_ID)] = download_task
        self.connect(download_task, SIGNAL("downloadComplete()"),
                self.download_complete)
        self.connect(download_task, SIGNAL("downloadFailed()"),
                self.download_failed)
        self.connect(download_task, SIGNAL("progressUpdated(int)"),
                self.progress_updated)
        self.wait_semaphore.release()

    def remove_download(self, down_id):
        download_task = self.downloads[down_id]
        download = download_task.download_item
        status = int(download.data(COL_STATUS))
        if status == IDLE:
            self.wait_semaphore.acquire()
            self._remove_item(down_id, self.wait_queue)
        elif status == DOWNLOADING:
            self.download_semaphore.release()
            self._remove_item(down_id, self.download_queue)
        else:
            self._remove_item(down_id, self.download_queue)

        self.downloads.pop(down_id)

    def pause_download(self, down_id):
        download_task = self.downloads[down_id]
        download = download_task.download_item
        status = int(download.data(COL_STATUS))
        if status == DOWNLOADING:
            download_task.pause()
            self.emit(SIGNAL("downloadStatus(QString, int)"),
                    download.data(COL_ID), PAUSE)

    def resume_download(self, down_id):
        download_task = self.downloads[down_id]
        download = download_task.download_item
        status = int(download.data(COL_STATUS))
        if status == PAUSE:
            download_task.resume()
            self.emit(SIGNAL("downloadStatus(QString, int)"),
                    download.data(COL_ID), DOWNLOADING)

    def download_complete(self):
        self.download_semaphore.release()
        download_task = self.sender()
        if download_task != None:
            download = download_task.download_item
            self.emit(SIGNAL("downloadStatus(QString, int)"),
                    download.data(COL_ID), COMPLETE)

    def download_failed(self):
        self.download_semaphore.release()
        download_task = self.sender()
        if download_task != None:
            download = download_task.download_item
            self.emit(SIGNAL("downloadStatus(QString, int)"),
                    download.data(COL_ID), FAILED)

    def progress_updated(self, progress):
        download_task = self.sender()
        if download_task != None:
            download = download_task.download_item
            self.emit(SIGNAL("progressUpdated(QString, int)"),
                    download.data(COL_ID), progress)

    def run(self):
        while True:
            self.wait_semaphore.acquire()
            next_down = self.wait_queue.pop(0)

            if self.max_download > 0:
                self.download_semaphore.acquire()
            self.download_queue.append(next_down)
            next_down.start()
            download = next_down.download_item
            self.emit(SIGNAL("downloadStatus(QString, int)"),
                    download.data(COL_ID), DOWNLOADING)

    def _get_item(self, item_id, queue):
        item = None
        for i in queue:
            if item_id == i.download_item.data(COL_ID):
                item = i
                break
        return item

    def _remove_item(self, item_id, queue):
        item = None
        for i in queue:
            if item_id == i.download_item.data(COL_ID):
                item = i
                break

        if item != None:
            item.stop()
            queue.remove(item)

class DownloadItem(QObject):
    def __init__(self, parent, std_items):
        QObject.__init__(self, parent)
        self.std_items = std_items

    def text(self, column):
        text = unicode(self.std_items[column].text())
        return text

    def data(self, column):
        data = unicode(self.std_items[column].data(Qt.UserRole).toString())
        return data

    def icon(self, column):
        icon = self.std_items[column].icon()
        return icon

    def set_icon(self, column, icon):
        self.std_items[column].setIcon(icon)

    def set_text(self, column, value):
        self.std_items[column].setText(value)

    def set_data(self, column, value):
        self.std_items[column].setData(QVariant(value), Qt.UserRole)

    @staticmethod
    def to_std_text(text):
        item = QStandardItem(text)
        item.setEditable(False)
        return item

    @staticmethod
    def to_std_icon(text, icon):
        item = QStandardItem(icon, text)
        item.setEditable(False)
        return item

class DownloadTask(QThread):
    def __init__(self, parent, download_item):
        QThread.__init__(self, parent)
        self.download_item = download_item
        self.pause_condition = QWaitCondition()
        self.pause_mutex = QMutex()
        self.pause_semaphore = QSemaphore(1)
        self.stop_semaphore = QSemaphore(1)

    def run(self):
        down_file = unicode(self.download_item.text(COL_FILE))
        down_path = unicode(self.download_item.data(COL_PATH))
        print "Downloading %s to %s" % (down_file, down_path)
        down_link = unicode(self.download_item.data(COL_LINK))

        try:
            conn = urllib.urlopen(down_link)
            html_contents = conn.read()
            conn.close()

            tree = bs.BeautifulSoup(html_contents)
            table_cell = tree.find("td", {"colspan" : "2"})
            ahref = table_cell.find("a")
            music_link = ahref["href"]

            music_file = urllib.urlopen(music_link)
            if music_file != None:
                meta = music_file.info()
                size = meta.getheaders("Content-Length")[0]
                size = float(size)
                print "%s : %s" % (down_file, size)

                i, out_file = 1, open(unicode(down_path), 'wb')
                while True:
                    if self.pause_semaphore.available() < 1:
                        self.pause_condition.wait(self.pause_mutex)
                    if self.stop_semaphore.available() < 1:
                        break
                    bytes = music_file.read(256)
                    if bytes == None or bytes == "":
                        self.emit(SIGNAL("downloadComplete()"))
                        break
                    out_file.write(bytes)
                    i, percent = i + 1, i*256/size * 100
                    self.emit(SIGNAL("progressUpdated(int)"), int(percent))
                out_file.close()
                music_file.close()
            else:
                self.emit(SIGNAL("downloadFailed()"))
        except Exception, e:
            print e
            self.emit(SIGNAL("downloadFailed()"))

    def pause(self):
        if self.pause_semaphore.available() > 0:
            self.pause_semaphore.acquire()
            self.pause_mutex.lock()

    def resume(self):
        if self.pause_semaphore.available() < 1:
            self.pause_semaphore.release()
            self.pause_condition.wakeAll()
            self.pause_mutex.unlock()

    def stop(self):
        if self.stop_semaphore.available() > 0:
            self.stop_semaphore.acquire()
