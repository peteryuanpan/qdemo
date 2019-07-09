# -*- coding: utf-8 -*-
from qiniu import Auth, QiniuMacAuth
import requests
import json
  
access_key = 'ak'
secret_key = 'sk'
  
auth = QiniuMacAuth(access_key, secret_key)
  
_id = 'eyJ6b25lIjoiejAiLCJxdWV1ZSI6IlNJU1lQSFVTLUpPQlMtVjItVEVNUCIsInBhcnRfaWQiOjI1LCJvZmZzZXQiOjEzNDY1NjV9'
url = 'http://api-z0.qiniu.com/sisyphus/fetch?id=' + _id
 
token = "Qiniu " + auth.token_of_request("GET", "api-z0.qiniu.com", url, "")
  
print token
  
r = requests.get(url, headers={'Authorization': token})
  
print r.text
print r
