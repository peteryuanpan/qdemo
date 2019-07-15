# -*- encoding:utf-8 -*-

from qiniu import Auth, http, utils

ak = '<ak>'
sk = '<sk>'

auth = Auth(ak, sk)

bucket = '<bucket>'
old_key = '<old_key>'
new_key = '<new_key>'
force = False # True or False

url = 'http://rs.qiniu.com/move/'
url = url + utils.urlsafe_base64_encode(bucket+':'+old_key) + '/'
url = url + utils.urlsafe_base64_encode(bucket+':'+new_key)
url = url + '/force/' + ('True' if force else 'False')
print(url)

ret, info = http._post_with_auth(url, None, auth)

print(ret)
print(info)
