<?php
require '<srcfolder>/autoload.php';
use Qiniu\Auth;
use Qiniu\Http\Client;
 
$accessKey = '<ak>';
$secretKey = '<sk>';
$auth = new Auth($accessKey, $secretKey);
 
$data = array(
    'url' => '<url>',
    'bucket' => '<bucket>'
);
 
$url = 'http://api-z0.qiniu.com/sisyphus/fetch';
$token = $auth->authorizationV2($url, 'POST', json_encode($data), 'application/json')['Authorization'];
echo $token."\n";
 
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
