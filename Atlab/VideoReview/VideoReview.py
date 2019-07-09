# -*- coding: utf-8 -*-
from qiniu import Auth, QiniuMacAuth, http
import requests, json, sys

access_key = '<ak>'
secret_key = '<sk>'
 
auth = QiniuMacAuth(access_key, secret_key)
 
url = 'http://ai.qiniuapi.com/v3/video/censor'
 
body = {
	"data": {
		"uri": "<uri>"
	},
	"params": {
        "scenes":[],
        "cut_param": {
            "interval_msecs": 5000
        }
    }
}

ops = {
	"censor": ["pulp","terror","politician"],
	"pulp": ["pulp"],
	"terror": ["terror"],
	"politician": ["politician"],
}

body["params"]["scenes"] = ops[sys.argv[1]]
body["data"]["uri"] = sys.argv[2]

ret, res = http._post_with_qiniu_mac(url, body, auth)

headers = {"code": res.status_code, "reqid": res.req_id, "xlog": res.x_log}

print json.dumps(headers, indent=4, ensure_ascii=False)
print json.dumps(ret, indent=4, ensure_ascii=False)
