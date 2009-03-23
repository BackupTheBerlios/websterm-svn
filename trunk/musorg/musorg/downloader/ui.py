# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'downloader.ui'
#
# Created: Sat Mar 21 22:55:30 2009
#      by: PyQt4 UI code generator 4.4.4
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

class Ui_Downloader(object):
    def setupUi(self, Downloader):
        Downloader.setObjectName("Downloader")
        Downloader.resize(614, 466)
        self.verticalLayout_2 = QtGui.QVBoxLayout(Downloader)
        self.verticalLayout_2.setSpacing(6)
        self.verticalLayout_2.setMargin(0)
        self.verticalLayout_2.setObjectName("verticalLayout_2")
        self.verticalLayout = QtGui.QVBoxLayout()
        self.verticalLayout.setObjectName("verticalLayout")
        self.horizontalLayout = QtGui.QHBoxLayout()
        self.horizontalLayout.setObjectName("horizontalLayout")
        self.btn_search = QtGui.QPushButton(Downloader)
        self.btn_search.setObjectName("btn_search")
        self.horizontalLayout.addWidget(self.btn_search)
        self.lne_search = QtGui.QLineEdit(Downloader)
        self.lne_search.setObjectName("lne_search")
        self.horizontalLayout.addWidget(self.lne_search)
        self.verticalLayout.addLayout(self.horizontalLayout)
        self.label = QtGui.QLabel(Downloader)
        font = QtGui.QFont()
        font.setWeight(75)
        font.setBold(True)
        self.label.setFont(font)
        self.label.setObjectName("label")
        self.verticalLayout.addWidget(self.label)
        self.trw_results = QtGui.QTreeView(Downloader)
        self.trw_results.setContextMenuPolicy(QtCore.Qt.ActionsContextMenu)
        self.trw_results.setObjectName("trw_results")
        self.verticalLayout.addWidget(self.trw_results)
        self.label_2 = QtGui.QLabel(Downloader)
        font = QtGui.QFont()
        font.setWeight(75)
        font.setBold(True)
        self.label_2.setFont(font)
        self.label_2.setObjectName("label_2")
        self.verticalLayout.addWidget(self.label_2)
        self.trw_downloads = QtGui.QTreeView(Downloader)
        self.trw_downloads.setContextMenuPolicy(QtCore.Qt.ActionsContextMenu)
        self.trw_downloads.setObjectName("trw_downloads")
        self.verticalLayout.addWidget(self.trw_downloads)
        self.verticalLayout_2.addLayout(self.verticalLayout)

        self.retranslateUi(Downloader)
        QtCore.QMetaObject.connectSlotsByName(Downloader)

    def retranslateUi(self, Downloader):
        Downloader.setWindowTitle(QtGui.QApplication.translate("Downloader", "Downloader", None, QtGui.QApplication.UnicodeUTF8))
        self.btn_search.setText(QtGui.QApplication.translate("Downloader", "Search", None, QtGui.QApplication.UnicodeUTF8))
        self.label.setText(QtGui.QApplication.translate("Downloader", "Search Results", None, QtGui.QApplication.UnicodeUTF8))
        self.label_2.setText(QtGui.QApplication.translate("Downloader", "Downloads", None, QtGui.QApplication.UnicodeUTF8))

