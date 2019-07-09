<?php
require '<srcfolderpath>/autoload.php';
use Qiniu\Auth;
use Qiniu\Http\Client;
 
$accessKey = '<ak>';
$secretKey = '<sk>';
$auth = new Auth($accessKey, $secretKey);
 
$jobid = "<jobid>";
$url = "http://ai.qiniuapi.com/v3/jobs/video/".$jobid;
$token = $auth->authorizationV2($url, 'GET')['Authorization'];
echo $token."\n";
 
$client = new Client();
$headers = array(
    'Authorization' => $token,
);
$res = $client->get($url, $headers);
echo $res->statusCode."\n";
echo $res->body."\n";
?>
