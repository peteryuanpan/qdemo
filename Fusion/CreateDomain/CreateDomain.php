<?php
require '<srcfolderpath>/autoload.php';
use Qiniu\Auth;
use Qiniu\Http\Client;
 
$accessKey = 'ak';
$secretKey = 'sk';
$auth = new Auth($accessKey, $secretKey);
 
$domain='just-test-003.qiniuts.com';
$data=array(
    'type' => 'normal',
    'platform' => 'web',
    'geoCover' => 'china',
    'protocol' => 'http',
    'source' => array (
        'sourceType' => 'qiniuBucket',
        'SourceQiniuBucket' => 'aaaaa'
    ),
    'cacheControls' => array (
        array(
            'time' => 1,
            'timeunit' => 5,
            'type' => 'all',
            'rule' => '*'
        )
    ),
    'ignoreParam' => FALSE
);
//echo json_encode($data)."\n";
 
$url='http://api.qiniu.com/domain/'.$domain;
$token=$auth->authorization($url, json_encode($data), 'application/json')['Authorization'];
echo $token."\n";
 
$client = new Client();
$headers=array(
    'Authorization' => $token,
    'Content-Type' => 'application/json'
);
$res=$client->post($url, json_encode($data), $headers);
echo $res->statusCode."\n";
echo print_r($res->headers)."\n";
echo $res->body."\n";
?>
