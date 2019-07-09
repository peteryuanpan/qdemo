# -*- coding: utf-8 -*-
from qiniu import Auth, QiniuMacAuth, urlsafe_base64_encode, urlsafe_base64_decode
import requests, json
   
access_key = 'ak'
secret_key = 'sk'
   
auth = Auth(access_key, secret_key)
   
EncodedURL = urlsafe_base64_encode("http://img.k3cdn.com/k/2034558/2018091215412581666494_750x750.jpg")
EncodedEntryURI = urlsafe_base64_encode("aaaaa:fetch-test")
url = 'http://iovip.qbox.me/fetch/' + EncodedURL + "/to/" + EncodedEntryURI
   
token = "QBox " + auth.token_of_request(url)
   
print token
 
r = requests.post(url, headers={'Authorization': token})
 
print r.text
print r
