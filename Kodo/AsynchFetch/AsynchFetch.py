# -*- coding: utf-8 -*-
from qiniu import Auth, QiniuMacAuth, urlsafe_base64_encode, urlsafe_base64_decode
import requests, json
    
access_key = 'ak'
secret_key = 'sk'
    
auth = QiniuMacAuth(access_key, secret_key)
 
url = 'http://api-z0.qiniu.com/sisyphus/fetch'
 
callbackbody = {
 
}
 
body = {
    "url" : "https://img.alicdn.com/imgextra/i2/3478075143/O1CN011nrXQMyQDzX36I3_!!3478075143.jpg",
    "bucket": "aaaaa",
    "key": "fetch-test11",
    "callbackurl": "http://requestbin.fullcontact.com/165vkyy1",
    "callbackbody": json.dumps(callbackbody),
    "callbackbodytype": "application/json"
}
    
token = "Qiniu " + auth.token_of_request("POST", "api-z0.qiniu.com", url, "", content_type='application/json', body=json.dumps(body))
    
print token
  
r = requests.post(url, data=json.dumps(body), headers={'Authorization': token, 'Content-Type': 'application/json'})
  
print r.text
print r
