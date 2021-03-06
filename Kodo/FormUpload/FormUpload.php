<?php
require_once __DIR__ . '/../autoload.php';

// 引入鉴权类
use Qiniu\Auth;

// 引入上传类
use Qiniu\Storage\UploadManager;

// 引入Config、Zone
use Qiniu\Config;
use Qiniu\Zone;

// 需要填写你的 Access Key 和 Secret Key
$accessKey = getenv('QINIU_ACCESS_KEY');
$secretKey = getenv('QINIU_SECRET_KEY');
$bucket = getenv('QINIU_TEST_BUCKET');

// 构建鉴权对象
$auth = new Auth($accessKey, $secretKey);

// 生成上传 Token
$token = $auth->uploadToken($bucket);

// 要上传文件的本地路径
$filePath = './php-logo.png';

// 上传到七牛后保存的文件名
$key = 'my-php-logo.png';

$zone = new Zone(
            array("up.qiniup.com", 'up-jjh.qiniup.com', 'up-xs.qiniup.com'),
            array('upload.qiniup.com', 'upload-jjh.qiniup.com', 'upload-xs.qiniup.com'),
            'rs.qbox.me',
            'rsf.qbox.me',
            'api.qiniu.com',
            'iovip.qbox.me'
        );
$config = new Config($zone);

$config->useCdnDomains = true;

// 初始化 UploadManager 对象并进行文件的上传。
$uploadMgr = new UploadManager($config);

// 调用 UploadManager 的 putFile 方法进行文件的上传。
list($ret, $err) = $uploadMgr->putFile($token, $key, $filePath);
echo "\n====> putFile result: \n";
if ($err !== null) {
    var_dump($err);
} else {
    var_dump($ret);
}
?>
