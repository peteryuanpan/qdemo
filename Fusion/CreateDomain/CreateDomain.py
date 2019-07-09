# -*- coding: utf-8 -*-
from qiniu import Auth, QiniuMacAuth
import requests, json, sys
  
access_key = 'ak'
secret_key = 'sk'
  
auth = Auth(access_key, secret_key)
  
url = 'http://api.qiniu.com/domain/just-test-001.qiniuts.com'
body = {
    "type": "normal",
    "platform": "web",
    "geoCover": "china",
    "protocol": "http",
    "source": {
        "sourceType": "qiniuBucket",
        "SourceQiniuBucket": "aaaaa"
    },
    "cacheControls": [
        {
            "time": 1,
            "timeunit": 5,
            "type": "all",
            "rule": "*"
        }
    ],
    "ignoreParam": False
}
  
token = 'QBox ' + auth.token_of_request(url, json.dumps(body), 'application/json')
print token
  
r = requests.post(url, data=json.dumps(body), headers={'Authorization': token, 'Content-Type': 'application/json'})
  
print r
print r.text
