#!/usr/bin/env python
# -*- coding: utf-8 -*-

# config.py
# author: rshen
# date: 2009-03-08

from ConfigParser import RawConfigParser

class Configuration(object):

    def __init__(self, config_file):
        self.config_file = config_file
        self.config = RawConfigParser()
        self.config.read([config_file])

    def get(self, sec, opt):
        value = ""
        if self.config.has_section(sec) and\
                self.config.has_option(sec, opt):
            value = self.config.get(sec, opt)

        return value

    def set(self, sec, opt, value):
        if not self.config.has_section(sec):
            self.config.add_section(sec)
        self.config.set(sec, opt, value)

    def save(self):
        self.config.write(open(self.config_file, 'w'))
