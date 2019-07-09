# -*- coding: utf-8 -*-
from qiniu import Auth, QiniuMacAuth
import requests, json, sys
 
access_key = 'ak'
secret_key = 'sk'
 
auth = Auth(access_key, secret_key)
 
url = 'http://fusion.qiniuapi.com/v2/tune/refresh'
body = {
    "urls": [
        "https://aaaaa.qiniuts.com/1.mp4"
    ]
}
 
token = 'QBox ' + auth.token_of_request(url, json.dumps(body), 'application/json')
print token
 
r = requests.post(url, data=json.dumps(body), headers={'Authorization': token, 'Content-Type': 'application/json'})
 
print r
print r.text
