# -*- coding: utf-8 -*-
from qiniu import Auth, QiniuMacAuth
import requests, json, sys

access_key = '<ak>'
secret_key = '<sk>'
 
auth = QiniuMacAuth(access_key, secret_key)

url = 'http://ai.qiniuapi.com/v3/jobs/video/' + sys.argv[1]

token = "Qiniu " + auth.token_of_request("GET", "ai.qiniuapi.com", url, "")

r = requests.get(url, headers={'Authorization': token})
print json.dumps(json.loads(r.text), indent=4, ensure_ascii=False)
