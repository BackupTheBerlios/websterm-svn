# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'preferences.ui'
#
# Created: Tue Mar 24 17:37:57 2009
#      by: PyQt4 UI code generator 4.4.4
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

class Ui_Preferences(object):
    def setupUi(self, Preferences):
        Preferences.setObjectName("Preferences")
        Preferences.resize(720, 567)
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Preferred, QtGui.QSizePolicy.Preferred)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(Preferences.sizePolicy().hasHeightForWidth())
        Preferences.setSizePolicy(sizePolicy)
        self.horizontalLayout = QtGui.QHBoxLayout(Preferences)
        self.horizontalLayout.setMargin(0)
        self.horizontalLayout.setObjectName("horizontalLayout")
        self.verticalLayout = QtGui.QVBoxLayout()
        self.verticalLayout.setObjectName("verticalLayout")
        self.groupBox_2 = QtGui.QGroupBox(Preferences)
        self.groupBox_2.setObjectName("groupBox_2")
        self.verticalLayout_2 = QtGui.QVBoxLayout(self.groupBox_2)
        self.verticalLayout_2.setMargin(0)
        self.verticalLayout_2.setObjectName("verticalLayout_2")
        self.formLayout = QtGui.QFormLayout()
        self.formLayout.setObjectName("formLayout")
        self.label = QtGui.QLabel(self.groupBox_2)
        self.label.setObjectName("label")
        self.formLayout.setWidget(0, QtGui.QFormLayout.LabelRole, self.label)
        self.horizontalLayout_2 = QtGui.QHBoxLayout()
        self.horizontalLayout_2.setObjectName("horizontalLayout_2")
        self.lne_download_dir = QtGui.QLineEdit(self.groupBox_2)
        self.lne_download_dir.setObjectName("lne_download_dir")
        self.horizontalLayout_2.addWidget(self.lne_download_dir)
        self.btn_browse_down_dir = QtGui.QPushButton(self.groupBox_2)
        icon = QtGui.QIcon()
        icon.addPixmap(QtGui.QPixmap(":/icons/icons/open.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        self.btn_browse_down_dir.setIcon(icon)
        self.btn_browse_down_dir.setObjectName("btn_browse_down_dir")
        self.horizontalLayout_2.addWidget(self.btn_browse_down_dir)
        self.formLayout.setLayout(0, QtGui.QFormLayout.FieldRole, self.horizontalLayout_2)
        self.label_3 = QtGui.QLabel(self.groupBox_2)
        self.label_3.setObjectName("label_3")
        self.formLayout.setWidget(1, QtGui.QFormLayout.LabelRole, self.label_3)
        self.sb_max_downloads = QtGui.QSpinBox(self.groupBox_2)
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Fixed, QtGui.QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.sb_max_downloads.sizePolicy().hasHeightForWidth())
        self.sb_max_downloads.setSizePolicy(sizePolicy)
        self.sb_max_downloads.setAccelerated(True)
        self.sb_max_downloads.setMinimum(-1)
        self.sb_max_downloads.setProperty("value", QtCore.QVariant(-1))
        self.sb_max_downloads.setObjectName("sb_max_downloads")
        self.formLayout.setWidget(1, QtGui.QFormLayout.FieldRole, self.sb_max_downloads)
        self.label_4 = QtGui.QLabel(self.groupBox_2)
        self.label_4.setFrameShape(QtGui.QFrame.NoFrame)
        self.label_4.setObjectName("label_4")
        self.formLayout.setWidget(2, QtGui.QFormLayout.LabelRole, self.label_4)
        self.cb_engines = QtGui.QComboBox(self.groupBox_2)
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Fixed, QtGui.QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.cb_engines.sizePolicy().hasHeightForWidth())
        self.cb_engines.setSizePolicy(sizePolicy)
        self.cb_engines.setMinimumSize(QtCore.QSize(200, 0))
        self.cb_engines.setObjectName("cb_engines")
        self.formLayout.setWidget(2, QtGui.QFormLayout.FieldRole, self.cb_engines)
        self.verticalLayout_2.addLayout(self.formLayout)
        self.verticalLayout.addWidget(self.groupBox_2)
        self.groupBox = QtGui.QGroupBox(Preferences)
        self.groupBox.setObjectName("groupBox")
        self.verticalLayout_3 = QtGui.QVBoxLayout(self.groupBox)
        self.verticalLayout_3.setMargin(0)
        self.verticalLayout_3.setObjectName("verticalLayout_3")
        self.formLayout_2 = QtGui.QFormLayout()
        self.formLayout_2.setObjectName("formLayout_2")
        self.label_2 = QtGui.QLabel(self.groupBox)
        self.label_2.setObjectName("label_2")
        self.formLayout_2.setWidget(0, QtGui.QFormLayout.LabelRole, self.label_2)
        self.lne_encoding = QtGui.QLineEdit(self.groupBox)
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Fixed, QtGui.QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.lne_encoding.sizePolicy().hasHeightForWidth())
        self.lne_encoding.setSizePolicy(sizePolicy)
        self.lne_encoding.setMinimumSize(QtCore.QSize(150, 0))
        self.lne_encoding.setObjectName("lne_encoding")
        self.formLayout_2.setWidget(0, QtGui.QFormLayout.FieldRole, self.lne_encoding)
        self.verticalLayout_3.addLayout(self.formLayout_2)
        self.verticalLayout.addWidget(self.groupBox)
        self.horizontalLayout.addLayout(self.verticalLayout)

        self.retranslateUi(Preferences)
        QtCore.QMetaObject.connectSlotsByName(Preferences)

    def retranslateUi(self, Preferences):
        Preferences.setWindowTitle(QtGui.QApplication.translate("Preferences", "Form", None, QtGui.QApplication.UnicodeUTF8))
        self.groupBox_2.setTitle(QtGui.QApplication.translate("Preferences", "Downloader Preferences", None, QtGui.QApplication.UnicodeUTF8))
        self.label.setText(QtGui.QApplication.translate("Preferences", "Download Directory", None, QtGui.QApplication.UnicodeUTF8))
        self.btn_browse_down_dir.setText(QtGui.QApplication.translate("Preferences", "Browse", None, QtGui.QApplication.UnicodeUTF8))
        self.label_3.setText(QtGui.QApplication.translate("Preferences", "Maximum Downloads", None, QtGui.QApplication.UnicodeUTF8))
        self.label_4.setText(QtGui.QApplication.translate("Preferences", "Search Engine", None, QtGui.QApplication.UnicodeUTF8))
        self.groupBox.setTitle(QtGui.QApplication.translate("Preferences", "Organizer Preferences", None, QtGui.QApplication.UnicodeUTF8))
        self.label_2.setText(QtGui.QApplication.translate("Preferences", "Default Encoding", None, QtGui.QApplication.UnicodeUTF8))

import musorg_rc
