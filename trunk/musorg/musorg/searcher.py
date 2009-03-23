#!/usr/bin/env python
# -*- coding: utf-8 -*-

# searcher.py
# author: rshen
# date: 2009-03-05

from PyQt4.QtCore import QThread, SIGNAL
from BeautifulSoup import BeautifulSoup, NavigableString
from search_model import SearchResultItem
from urllib2 import urlopen

YAHOO_ENGINE = 0
SOGOU_ENGINE = 1
BAIDU_ENGINE = 2

ENGINES = {
        YAHOO_ENGINE : "Yahoo Music",
        SOGOU_ENGINE : "Sogou",
        BAIDU_ENGINE : "Baidu MP3"
}

YAHOO_URL = "http://one.cn.yahoo.com/s?p=%s&pid=hp&v=music&b=0"

class YahooSearcher(QThread):

    def __init__(self, parent, trv_results, content):
        QThread.__init__(self, parent)
        self._results = trv_results
        self._results.removeRows(0, self._results.rowCount())
        self._content = content
        pass

    def run(self):
        self.search(self._content)

    def search(self, content):
        self.emit(SIGNAL("searchStatusChanged(QString)"),
                "Searching for %s" % content)
        search_url = str(YAHOO_URL % content.encode("utf-8"))
        print search_url
        try:
            result = urlopen(search_url)
        except Exception, exc:
            print exc
        else:
            self.parse_page(result)

    def parse_page(self, html):
        self.emit(SIGNAL("searchStatusChanged(QString)"), "Parsing Web Page")
        num_results = 0
        parsed = BeautifulSoup(html)

        div = parsed.find("div", {"class" : "yst-music"})
        if div != None:
            table = div.find("table")
            rows = table.findAll("tr")
            for row in rows[1:]:
                item = self.get_result_item(row)
                tr_item = item.to_tree_item(self._results)
                num_results += 1

        self.emit(SIGNAL("searchStatusChanged(QString)"),
                "Number of results: %d" % num_results)

    def get_result_item(self, row):
        title = self._text(row, cl="m_song")
        artist = self._text(row, cl="m_singer")
        album = self._text(row, cl="m_album")
        format = self._text(row, index=5)
        size = self._text(row, index=6)
        link = self._link(row, index=4)

        item = SearchResultItem([title, artist, album,
            format, size, link])
        return item

    def _text(self, row, cl=None, index=0):
        if cl != None:
            part = row.find("td", {"class" : cl})
        else:
            part = row.findAll("td")[index]

        tlist = []
        self._extract(part, tlist)
        text = ''.join(tlist)

        return text

    def _extract(self, part, text):
        contents = part.contents
        for c in contents:
            if type(c) == NavigableString:
                c_str = c
                c_str.replace("\n\r\t", "")
                text.append(c_str)
            else:
                self._extract(c, text)

    def _link(self, row, cl=None, index=0):
        if cl != None:
            part = row.find("td", {"class" : cl})
        else:
            part = row.findAll("td")[index]

        link = None
        ahref = part.find("a")
        if ahref != None:
            link = ahref["href"]

        return link
