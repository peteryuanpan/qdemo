# -*- coding: utf-8 -*-
from qiniu import Auth, QiniuMacAuth
import requests, json, sys
  
access_key = '<ak>'
secret_key = '<sk>'
  
auth = Auth(access_key, secret_key)
  
url = '<url>'
body = {
}
  
token = 'QBox ' + auth.token_of_request(url, body=json.dumps(body), content_type='application/json')
print token
  
r = requests.post(url, data=json.dumps(body), headers={'Authorization': token, 'Content-Type': 'application/json'})
  
print r
print r.text
