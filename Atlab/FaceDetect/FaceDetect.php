<?php
require '<sdkfolder>/autoload.php';
use Qiniu\Auth;
use Qiniu\Http\Client;
 
$accessKey = '<ak>';
$secretKey = '<sk>';
$auth = new Auth($accessKey, $secretKey);
 
$data = array(
    'data' => array (
    	'uri' => 'http://tswork.peterpy.cn/1.png'
    ),
);
 
$url = 'http://ai.qiniuapi.com/v1/face/detect';
$token = $auth->authorizationV2($url, 'POST', json_encode($data), 'application/json')['Authorization'];
#echo $token."\n";
 
$client = new Client();
$headers = array(
    'Authorization' => $token,
    'Content-Type' => 'application/json'
);
$res = $client->post($url, json_encode($data), $headers);
echo $res->statusCode."\n";
echo print_r($res->headers)."\n";
echo $res->body."\n";
?>
