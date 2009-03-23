# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'favourites_dialog.ui'
#
# Created: Tue Mar 17 15:17:00 2009
#      by: PyQt4 UI code generator 4.4.4
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

class Ui_FavouritesEditDialog(object):
    def setupUi(self, FavouritesEditDialog):
        FavouritesEditDialog.setObjectName("FavouritesEditDialog")
        FavouritesEditDialog.resize(639, 467)
        FavouritesEditDialog.setAcceptDrops(False)
        self.verticalLayout = QtGui.QVBoxLayout(FavouritesEditDialog)
        self.verticalLayout.setMargin(0)
        self.verticalLayout.setObjectName("verticalLayout")
        self.verticalLayout_2 = QtGui.QVBoxLayout()
        self.verticalLayout_2.setObjectName("verticalLayout_2")
        self.trw_favourites = QtGui.QTreeView(FavouritesEditDialog)
        self.trw_favourites.setAcceptDrops(True)
        self.trw_favourites.setObjectName("trw_favourites")
        self.verticalLayout_2.addWidget(self.trw_favourites)
        self.horizontalLayout = QtGui.QHBoxLayout()
        self.horizontalLayout.setObjectName("horizontalLayout")
        spacerItem = QtGui.QSpacerItem(40, 20, QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Minimum)
        self.horizontalLayout.addItem(spacerItem)
        self.tb_add = QtGui.QToolButton(FavouritesEditDialog)
        icon = QtGui.QIcon()
        icon.addPixmap(QtGui.QPixmap(":/icons/icons/add.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        self.tb_add.setIcon(icon)
        self.tb_add.setPopupMode(QtGui.QToolButton.MenuButtonPopup)
        self.tb_add.setArrowType(QtCore.Qt.NoArrow)
        self.tb_add.setObjectName("tb_add")
        self.horizontalLayout.addWidget(self.tb_add)
        self.toolButton = QtGui.QToolButton(FavouritesEditDialog)
        icon1 = QtGui.QIcon()
        icon1.addPixmap(QtGui.QPixmap(":/icons/icons/remove.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        self.toolButton.setIcon(icon1)
        self.toolButton.setObjectName("toolButton")
        self.horizontalLayout.addWidget(self.toolButton)
        self.toolButton_3 = QtGui.QToolButton(FavouritesEditDialog)
        icon2 = QtGui.QIcon()
        icon2.addPixmap(QtGui.QPixmap(":/icons/icons/organize.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        self.toolButton_3.setIcon(icon2)
        self.toolButton_3.setObjectName("toolButton_3")
        self.horizontalLayout.addWidget(self.toolButton_3)
        self.toolButton_2 = QtGui.QToolButton(FavouritesEditDialog)
        icon3 = QtGui.QIcon()
        icon3.addPixmap(QtGui.QPixmap(":/icons/icons/filesave.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        self.toolButton_2.setIcon(icon3)
        self.toolButton_2.setObjectName("toolButton_2")
        self.horizontalLayout.addWidget(self.toolButton_2)
        self.verticalLayout_2.addLayout(self.horizontalLayout)
        self.verticalLayout.addLayout(self.verticalLayout_2)
        self.actionAdd_Albums = QtGui.QAction(FavouritesEditDialog)
        self.actionAdd_Albums.setIcon(icon)
        self.actionAdd_Albums.setObjectName("actionAdd_Albums")
        self.actionAdd_Tracks = QtGui.QAction(FavouritesEditDialog)
        self.actionAdd_Tracks.setIcon(icon)
        self.actionAdd_Tracks.setObjectName("actionAdd_Tracks")

        self.retranslateUi(FavouritesEditDialog)
        QtCore.QMetaObject.connectSlotsByName(FavouritesEditDialog)

    def retranslateUi(self, FavouritesEditDialog):
        FavouritesEditDialog.setWindowTitle(QtGui.QApplication.translate("FavouritesEditDialog", "Edit Favourite Tracks", None, QtGui.QApplication.UnicodeUTF8))
        self.tb_add.setToolTip(QtGui.QApplication.translate("FavouritesEditDialog", "Add Albums/Tracks", None, QtGui.QApplication.UnicodeUTF8))
        self.tb_add.setText(QtGui.QApplication.translate("FavouritesEditDialog", "Add Track", None, QtGui.QApplication.UnicodeUTF8))
        self.toolButton.setToolTip(QtGui.QApplication.translate("FavouritesEditDialog", "Remove Tracks", None, QtGui.QApplication.UnicodeUTF8))
        self.toolButton.setText(QtGui.QApplication.translate("FavouritesEditDialog", "...", None, QtGui.QApplication.UnicodeUTF8))
        self.toolButton_3.setToolTip(QtGui.QApplication.translate("FavouritesEditDialog", "Clear Tracks", None, QtGui.QApplication.UnicodeUTF8))
        self.toolButton_3.setText(QtGui.QApplication.translate("FavouritesEditDialog", "...", None, QtGui.QApplication.UnicodeUTF8))
        self.toolButton_2.setToolTip(QtGui.QApplication.translate("FavouritesEditDialog", "Save Favourites", None, QtGui.QApplication.UnicodeUTF8))
        self.actionAdd_Albums.setText(QtGui.QApplication.translate("FavouritesEditDialog", "Add Albums", None, QtGui.QApplication.UnicodeUTF8))
        self.actionAdd_Tracks.setText(QtGui.QApplication.translate("FavouritesEditDialog", "Add Tracks", None, QtGui.QApplication.UnicodeUTF8))

import musorg_rc
