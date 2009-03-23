#!/usr/bin/env python
# -*- coding: utf-8 -*-

# encoding.py
# author: rshen
# date: 2009-03-20

import chardet

def detect_enc(text):
    latin_text = text.encode("latin1", "replace")
    enc = chardet.detect(latin_text)
    return enc

