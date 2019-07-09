# -*- coding: utf-8 -*-
# flake8: noqa
from qiniu import Auth, PersistentFop, urlsafe_base64_encode
 
access_key = 'ak'
secret_key = 'sk'
q = Auth(access_key, secret_key)
bucket_name = 'aaaaa'
key = '1.mp4'
pipeline = 'aaaaa'
fops = 'avthumb/mp4/ss/0/t/10'
saveas_key = urlsafe_base64_encode('aaaaa:2.mp4')
fops = fops + '|saveas/' + saveas_key
print fops
ops = []
pfop = PersistentFop(q, bucket_name, pipeline)
ops.append(fops)
ret, info = pfop.execute(key, ops, 1)
print(info)
assert ret['persistentId'] is not None
