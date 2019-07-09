#!/usr/bin/python
# -*- coding:utf-8 -*-
from hashlib import sha1
from base64 import urlsafe_b64encode, urlsafe_b64decode
import hmac, time

def __token(sk, data):
    hashed = hmac.new(sk, bytes(data), sha1)
    return urlsafe_b64encode(bytes(hashed.digest()))

def privateBucketDownloadUrl(ak, sk, url, expires=3600):
    deadline = int(time.time()) + expires
    url += '&' if '?' in url else '?'
    url = '{0}e={1}'.format(url, str(deadline))
    token = '{0}:{1}'.format(ak, __token(sk, url))
    return '{0}&token={1}'.format(url, token)

print privateBucketDownloadUrl('ak', 'sk', 'url')
