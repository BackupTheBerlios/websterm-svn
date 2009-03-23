#!/usr/bin/env python
# -*- coding: utf-8 -*-

# test_encoding.py
# author: rshen
# date: 2009-03-20

import os
from musorg.encoding import *
from tagpy import FileRef

def test_detect_enc():
    data_dir = os.path.dirname(__file__) + "/data/music"
    for root, dirs, files in os.walk(data_dir):
        for f in files:
            if os.path.splitext(f)[1] in [".mp3"]:
                tags = _get_tags(os.path.join(root, f))
                if tags != None and tags.title.strip() != "":
                    print tags.album.encode("utf-8")
                    print detect_enc(tags.album)

def _get_tags(f):
    ref = FileRef(f)
    tags = ref.tag()
    return tags
