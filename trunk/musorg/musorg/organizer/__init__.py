#!/usr/bin/env python
# -*- coding: utf-8 -*-

# organizer.__init__
# author: rshen
# date: 2009-03-21

import sys
from PyQt4.QtGui import *
from PyQt4.QtCore import *
from musorg.organizer.ui import *
from musorg.organizer.encoding import *
from musorg.organizer.collection import *
from musorg.organizer.edit_album import *
from musorg.qtdb import *

class OrganizerWidget(Ui_Organizer, QWidget):

    def __init__(self, parent=None, config=None, **actions):
        QWidget.__init__(self, parent)
        self.setupUi(self)

        self.config = config
        self.splitter.setSizes([200, 600])

        self.tracks_headers = ["Title", "Track Number", "Artist", "Rating",
                "File"]
        self.tracks_model = QStandardItemModel()
        self.trw_tracks.setModel(self.tracks_model)
        self.trw_tracks.setItemDelegate(TrackItemDelegate(self.trw_tracks))
        self.tracks_model.setColumnCount(5)
        self.tracks_model.setHorizontalHeaderLabels(self.tracks_headers)

        self.albums_model = QStandardItemModel()
        self.trw_albums.setItemDelegate(AlbumItemDelegate(self.trw_albums))
        self.trw_albums.setModel(self.albums_model)
        self.trw_albums.setRootIsDecorated(False)
        self.trw_albums.setColumnWidth(0, 50)
        self.trw_albums.addAction(self.actionEdit_Album)
        self.trw_albums.addAction(self.actionRemove_Album)
        self.trw_albums.addAction(self.actionChange_Encoding)
        loader = CollectionLoader(self)
        self.connect(loader, SIGNAL("albumLoaded(int,QString,QString,int)"),
                self.load_album)
        loader.start()

        self.actionReload_Collection = actions['actionReload_Collection']
        self.actionCorrect_Encodings = actions['actionCorrect_Encodings']

        self.pb_reload.setVisible(False)

        menu = QMenu(self)
        menu.addAction(self.actionImport_Files)
        menu.addAction(self.actionImport_Directory)
        self.tb_import_albums.setMenu(menu)

        self.tb_reload_collection.setDefaultAction(
                self.actionReload_Collection)

        self.connect(self.trw_albums, SIGNAL("doubleClicked(QModelIndex)"),
                self.edit_album)
        self.connect(self.trw_albums, SIGNAL("pressed(QModelIndex)"),
                self.list_tracks)
        self.connect(self.actionReload_Collection, SIGNAL("triggered()"),
                self.reload_collection)
        self.connect(self.actionCorrect_Encodings, SIGNAL("triggered()"),
                self.correct_encodings)

    def reload_collection(self):
        self.pb_reload.setVisible(True)
        reload_thread = CollectionReloader(self,
                self.config.get("Downloader", "download.dir"))
        self.connect(reload_thread, SIGNAL("trackAdded(int, QString)"),
                self.update_loading_status)
        self.connect(reload_thread, SIGNAL("loadingComplete()"),
                self.complete_loading)
        reload_thread.start()

    def correct_encodings(self):
        self.pb_reload.setVisible(True)
        encoding_thread = EncodingCorrector(self, self.config)
        self.connect(encoding_thread, SIGNAL("encodingComplete()"),
                self.complete_encoding)
        self.connect(encoding_thread, SIGNAL("encoding(int,QString)"),
                self.update_loading_status)
        encoding_thread.start()

    def complete_encoding(self):
        self.pb_reload.setVisible(False)
        self.albums_model.removeRows(0, self.albums_model.rowCount())
        albums = session.query(AlbumItem).all()
        for album in albums:
            std_items = album.to_std_items()
            self.albums_model.appendRow(std_items)

    def load_album(self, aid, coverart, name, rating):
        album = AlbumItem.init_std_items(aid, name, coverart, rating)
        self.albums_model.appendRow(album)

    def edit_album(self, index):
        row = 0
        if index == None:
            selection = self.trw_albums.selectionModel().selectedRows()
            row = selection[0].row()
        else:
            row = index.row()

        id_item = self.albums_model.item(row, 0)
        name_item = self.albums_model.item(row, 1)

        edit_dialog = EditAlbumDialog(self, id_item, name_item)
        edit_dialog.show()

    def list_tracks(self, index):
        album_item = self.albums_model.item(index.row(), 0)
        album_id = album_item.data(Qt.UserRole).toInt()[0]
        album = session.query(AlbumItem).filter(AlbumItem.id==int(album_id))\
                .one()
        tracks = album.tracks
        self.tracks_model.removeRows(0, self.tracks_model.rowCount())
        for track in tracks:
            item = track.to_std_item()
            self.tracks_model.appendRow(item)

    def update_loading_status(self, percent, track_name):
        self.pb_reload.setValue(percent)

    def complete_loading(self):
        self.albums_model.removeRows(0, self.albums_model.rowCount())
        self.pb_reload.setVisible(False)
        albums = session.query(AlbumItem).all()
        for album in albums:
            std_items = album.to_std_items()
            self.albums_model.appendRow(std_items)

class EditAlbumDialog(QDialog, Ui_EditDialog):

    def __init__(self, parent=None, id_item=None, name_item=None):
        QDialog.__init__(self, parent)
        self.setupUi(self)

        self.id_item = id_item
        self.name_item = name_item

        cover = id_item.text()
        self.aid = id_item.data(Qt.UserRole).toInt()[0]
        l = name_item.data(Qt.UserRole).toStringList()
        name, rating = l

        self.lne_name.setText(name)
        self.lb_cover.setPixmap(QPixmap(cover))

        self.cb_rating.addItems([str(i) for i in range(6)])

        artists = session.query(ArtistItem).all()
        for artist in artists:
            self.cb_artist.addItem(artist.name.decode("utf-8"),
                    QVariant(artist.id))

class TrackItemDelegate(QItemDelegate):

    def __init__(self, parent):
        QItemDelegate.__init__(self, parent)

        icon = QIcon(":/icons/icons/star.png")
        self.star = icon.pixmap(QSize(15, 15), QIcon.Normal,
                QIcon.On)

    def createEditor(self, parent, option, index):
        if index.column() == 3:
            combobox = QComboBox(parent)
            combobox.addItems(["0", "1", "2", "3", "4", "5"])
            return combobox
        elif index.column() == 1:
            combobox = QComboBox(parent)
            combobox.addItems([str(i) for i in range(1, 50)])
            return combobox
        else:
            return QItemDelegate.createEditor(self, parent, option, index)

    def setEditorData(self, editor, index):
        if index.column() == 3:
            editor.setCurrentIndex(index.data().toInt()[0])
        elif index.column() == 1:
            track_num = index.data().toInt()[0]
            if track_num > 0:
                editor.setCurrentIndex(track_num - 1)
        else:
            QItemDelegate.setEditorData(self, editor, index)

    def setModelData(self, editor, model, index):
        if index.column() == 3:
            rating = editor.currentText()
            item = model.itemFromIndex(index)
            item.setText(rating)
        elif index.column() == 1:
            track_num = editor.currentText()
            item = model.itemFromIndex(index)
            item.setText(track_num)

    def paint(self, painter, option, index):
        if option.state & QStyle.State_Selected:
            painter.fillRect(option.rect, option.palette.highlight())
            painter.setBrush(option.palette.highlightedText())
        if index.column() != 3:
            QItemDelegate.paint(self, painter, option, index)
        else:
            rating = index.data().toInt()[0]
            width, height = self.star.width(), self.star.height()
            x, y = option.rect.x(), option.rect.y() +\
                    (option.rect.height() / 2.0) - (height / 2.0)

            for i in range(rating):
                painter.drawPixmap(x, y, self.star)
                x += width + 2

class AlbumItemDelegate(QItemDelegate):

    def __init__(self, parent):
        QItemDelegate.__init__(self, parent)

        icon = QIcon(":/icons/icons/star.png")
        self.star = icon.pixmap(QSize(15, 15), QIcon.Normal,
                QIcon.On)

    def paint(self, painter, option, index):
        if option.state & QStyle.State_Selected:
            painter.fillRect(option.rect, option.palette.highlight())
            painter.setBrush(option.palette.highlightedText())
        if index.column() == COL_COVER_ART:
            data = index.data()
            path = data.toString()
            icon = QIcon(path)
            pixmap = icon.pixmap(QSize(48, 48),
                    QIcon.Normal, QIcon.On)
            painter.drawPixmap(option.rect.topLeft(), pixmap)
        else:
            text = index.data(Qt.UserRole).toStringList()
            topLeft = option.rect.topLeft()
            cur_font = painter.font()
            cur_font_metrics = painter.fontMetrics()
            new_font = QFont(cur_font.family(), 12,
                    QFont.Bold)
            new_small_font = QFont(cur_font.family(),
                    cur_font.pointSize(), QFont.Normal)
            painter.setFont(new_font)
            font_metrics = painter.fontMetrics()
            painter.drawText(QPoint(topLeft.x(),
                topLeft.y() + font_metrics.height()),
                text[0])
            painter.setFont(new_small_font)
            painter.drawText(QPoint(topLeft.x(),
                topLeft.y() + font_metrics.height() +
                cur_font_metrics.height()), str(text[1]))

def main():
    app = QApplication(sys.argv)
    frame = OrganizerWidget()
    frame.show()

    sys.exit(app.exec_())
