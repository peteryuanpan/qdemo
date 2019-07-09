# -*- coding: utf-8 -*-
# flake8: noqa
from qiniu import Auth
from qiniu import BucketManager
access_key = 'ak'
secret_key = 'sk'
q = Auth(access_key, secret_key)
bucket = BucketManager(q)
bucket_name = 'aaaaa'
prefix = None
limit = 100
delimiter = None
marker = None
ret, eof, info = bucket.list(bucket_name, prefix, marker, limit, delimiter)
for item in ret["items"]:
    print item
