# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'musorg_main.ui'
#
# Created: Wed Mar 25 11:47:18 2009
#      by: PyQt4 UI code generator 4.4.4
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(949, 682)
        icon = QtGui.QIcon()
        icon.addPixmap(QtGui.QPixmap(":/icons/icons/filesave.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        MainWindow.setWindowIcon(icon)
        MainWindow.setToolButtonStyle(QtCore.Qt.ToolButtonIconOnly)
        self.centralwidget = QtGui.QWidget(MainWindow)
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Preferred, QtGui.QSizePolicy.Preferred)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.centralwidget.sizePolicy().hasHeightForWidth())
        self.centralwidget.setSizePolicy(sizePolicy)
        self.centralwidget.setObjectName("centralwidget")
        self.horizontalLayout_7 = QtGui.QHBoxLayout(self.centralwidget)
        self.horizontalLayout_7.setMargin(0)
        self.horizontalLayout_7.setObjectName("horizontalLayout_7")
        self.main_tab = QtGui.QTabWidget(self.centralwidget)
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.main_tab.sizePolicy().hasHeightForWidth())
        self.main_tab.setSizePolicy(sizePolicy)
        self.main_tab.setContextMenuPolicy(QtCore.Qt.DefaultContextMenu)
        self.main_tab.setLayoutDirection(QtCore.Qt.LeftToRight)
        self.main_tab.setTabShape(QtGui.QTabWidget.Rounded)
        self.main_tab.setObjectName("main_tab")
        self.tab_downloader = QtGui.QWidget()
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Preferred, QtGui.QSizePolicy.Preferred)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.tab_downloader.sizePolicy().hasHeightForWidth())
        self.tab_downloader.setSizePolicy(sizePolicy)
        self.tab_downloader.setLayoutDirection(QtCore.Qt.LeftToRight)
        self.tab_downloader.setObjectName("tab_downloader")
        self.verticalLayout_2 = QtGui.QVBoxLayout(self.tab_downloader)
        self.verticalLayout_2.setMargin(0)
        self.verticalLayout_2.setObjectName("verticalLayout_2")
        icon1 = QtGui.QIcon()
        icon1.addPixmap(QtGui.QPixmap(":/icons/icons/downloader.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        self.main_tab.addTab(self.tab_downloader, icon1, "")
        self.tab_organizer = QtGui.QWidget()
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Preferred, QtGui.QSizePolicy.Preferred)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.tab_organizer.sizePolicy().hasHeightForWidth())
        self.tab_organizer.setSizePolicy(sizePolicy)
        self.tab_organizer.setObjectName("tab_organizer")
        self.horizontalLayout_8 = QtGui.QHBoxLayout(self.tab_organizer)
        self.horizontalLayout_8.setMargin(0)
        self.horizontalLayout_8.setObjectName("horizontalLayout_8")
        self.main_tab.addTab(self.tab_organizer, "")
        self.tab_pref = QtGui.QWidget()
        self.tab_pref.setObjectName("tab_pref")
        self.verticalLayout_5 = QtGui.QVBoxLayout(self.tab_pref)
        self.verticalLayout_5.setMargin(0)
        self.verticalLayout_5.setObjectName("verticalLayout_5")
        self.main_tab.addTab(self.tab_pref, "")
        self.horizontalLayout_7.addWidget(self.main_tab)
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QtGui.QMenuBar(MainWindow)
        self.menubar.setGeometry(QtCore.QRect(0, 0, 949, 23))
        self.menubar.setObjectName("menubar")
        self.menu_File = QtGui.QMenu(self.menubar)
        self.menu_File.setObjectName("menu_File")
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QtGui.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)
        self.toolBar = QtGui.QToolBar(MainWindow)
        self.toolBar.setToolButtonStyle(QtCore.Qt.ToolButtonTextUnderIcon)
        self.toolBar.setObjectName("toolBar")
        MainWindow.addToolBar(QtCore.Qt.TopToolBarArea, self.toolBar)
        self.actionSave_Preferences = QtGui.QAction(MainWindow)
        self.actionSave_Preferences.setIcon(icon)
        self.actionSave_Preferences.setObjectName("actionSave_Preferences")
        self.actionPause_Download = QtGui.QAction(MainWindow)
        icon2 = QtGui.QIcon()
        icon2.addPixmap(QtGui.QPixmap(":/icons/icons/pause.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        self.actionPause_Download.setIcon(icon2)
        self.actionPause_Download.setObjectName("actionPause_Download")
        self.actionResume_Download = QtGui.QAction(MainWindow)
        icon3 = QtGui.QIcon()
        icon3.addPixmap(QtGui.QPixmap(":/icons/icons/player_play.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        self.actionResume_Download.setIcon(icon3)
        self.actionResume_Download.setObjectName("actionResume_Download")
        self.actionStart_Download = QtGui.QAction(MainWindow)
        icon4 = QtGui.QIcon()
        icon4.addPixmap(QtGui.QPixmap(":/icons/icons/down.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        self.actionStart_Download.setIcon(icon4)
        self.actionStart_Download.setObjectName("actionStart_Download")
        self.actionRemove_Download = QtGui.QAction(MainWindow)
        icon5 = QtGui.QIcon()
        icon5.addPixmap(QtGui.QPixmap(":/icons/icons/remove.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        self.actionRemove_Download.setIcon(icon5)
        self.actionRemove_Download.setObjectName("actionRemove_Download")
        self.actionCorrect_Encodings = QtGui.QAction(MainWindow)
        icon6 = QtGui.QIcon()
        icon6.addPixmap(QtGui.QPixmap(":/icons/icons/encoding.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        self.actionCorrect_Encodings.setIcon(icon6)
        self.actionCorrect_Encodings.setObjectName("actionCorrect_Encodings")
        self.actionReload_Collection = QtGui.QAction(MainWindow)
        icon7 = QtGui.QIcon()
        icon7.addPixmap(QtGui.QPixmap(":/icons/icons/reload.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        self.actionReload_Collection.setIcon(icon7)
        self.actionReload_Collection.setObjectName("actionReload_Collection")
        self.actionAuto_Organize = QtGui.QAction(MainWindow)
        icon8 = QtGui.QIcon()
        icon8.addPixmap(QtGui.QPixmap(":/icons/icons/organize.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        self.actionAuto_Organize.setIcon(icon8)
        self.actionAuto_Organize.setObjectName("actionAuto_Organize")
        self.menubar.addAction(self.menu_File.menuAction())
        self.toolBar.addAction(self.actionStart_Download)
        self.toolBar.addAction(self.actionRemove_Download)
        self.toolBar.addAction(self.actionResume_Download)
        self.toolBar.addAction(self.actionPause_Download)
        self.toolBar.addSeparator()
        self.toolBar.addAction(self.actionReload_Collection)
        self.toolBar.addAction(self.actionCorrect_Encodings)
        self.toolBar.addAction(self.actionAuto_Organize)
        self.toolBar.addSeparator()
        self.toolBar.addAction(self.actionSave_Preferences)

        self.retranslateUi(MainWindow)
        self.main_tab.setCurrentIndex(1)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(QtGui.QApplication.translate("MainWindow", "MainWindow", None, QtGui.QApplication.UnicodeUTF8))
        self.main_tab.setTabText(self.main_tab.indexOf(self.tab_downloader), QtGui.QApplication.translate("MainWindow", "Downloader", None, QtGui.QApplication.UnicodeUTF8))
        self.main_tab.setTabText(self.main_tab.indexOf(self.tab_organizer), QtGui.QApplication.translate("MainWindow", "Organizer", None, QtGui.QApplication.UnicodeUTF8))
        self.main_tab.setTabText(self.main_tab.indexOf(self.tab_pref), QtGui.QApplication.translate("MainWindow", "Preferences", None, QtGui.QApplication.UnicodeUTF8))
        self.menu_File.setTitle(QtGui.QApplication.translate("MainWindow", "&File", None, QtGui.QApplication.UnicodeUTF8))
        self.toolBar.setWindowTitle(QtGui.QApplication.translate("MainWindow", "toolBar", None, QtGui.QApplication.UnicodeUTF8))
        self.actionSave_Preferences.setText(QtGui.QApplication.translate("MainWindow", "Save", None, QtGui.QApplication.UnicodeUTF8))
        self.actionPause_Download.setText(QtGui.QApplication.translate("MainWindow", "Pause", None, QtGui.QApplication.UnicodeUTF8))
        self.actionResume_Download.setText(QtGui.QApplication.translate("MainWindow", "Resume", None, QtGui.QApplication.UnicodeUTF8))
        self.actionStart_Download.setText(QtGui.QApplication.translate("MainWindow", "Start", None, QtGui.QApplication.UnicodeUTF8))
        self.actionRemove_Download.setText(QtGui.QApplication.translate("MainWindow", "Remove", None, QtGui.QApplication.UnicodeUTF8))
        self.actionCorrect_Encodings.setText(QtGui.QApplication.translate("MainWindow", "Correct Encodings", None, QtGui.QApplication.UnicodeUTF8))
        self.actionReload_Collection.setText(QtGui.QApplication.translate("MainWindow", "Reload Collection", None, QtGui.QApplication.UnicodeUTF8))
        self.actionAuto_Organize.setText(QtGui.QApplication.translate("MainWindow", "Auto-Organize", None, QtGui.QApplication.UnicodeUTF8))

import musorg_rc
