/*
Trying 124.160.115.104...\r\n"
TCP_NODELAY set\r\n"
Connected to rs.qiniu.com (124.160.115.104) port 80 (#0)\r\n"
> GET /stat/cGFueXVhbjoxLnBuZw== HTTP/1.1\r\r\n"
Host: rs.qiniu.com\r\r\n"
Authorization: QBox bjtWBQXrcxgo7HWwlC_bgHg81j352_GhgBGZPeOW:88TmLNwvfOhghi9vbvE71EfkRGs=\r\r\n"
< HTTP/1.1 200 OK\r\r\n"
< Server: openresty\r\r\n"
< Date: Tue, 14 Apr 2020 11:44:42 GMT\r\r\n"
< Content-Type: application/json\r\r\n"
< Content-Length: 155\r\r\n"
< Connection: keep-alive\r\r\n"
< Cache-Control: no-store\r\r\n"
< X-Reqid: 0GQAANUA0V0FrQUW\r\r\n"
{\"fsize\":972418,\"hash\":\"FqaucE4DDYYnbmNoH9B_8FPvynyJ\",\"md5\":\"b4dd11f09a02a994c832d7a3d885255d\",\"mimeType\":\"image/png\",\"putTime\":15724431254751708,\"type\":0}"
*/

#include <stdio.h>
#include <stdlib.h>
#include <curl/curl.h>
#include "../qiniu/conf.h"

void http_post(const char * url, const char * token, const char * body, const char * content_type) {
    CURL * curl;
    CURLcode res;
    curl = curl_easy_init();
    if (curl) {
        struct curl_slist * chunk = NULL;
        if (token != NULL) chunk = curl_slist_append(chunk, Qiniu_String_Concat2("Authorization: ", token));
        if (content_type != NULL) chunk = curl_slist_append(chunk, Qiniu_String_Concat2("Content-Type: ", content_type));
        if (chunk != NULL) curl_easy_setopt(curl, CURLOPT_HTTPHEADER, chunk);
        if (body != NULL) curl_easy_setopt(curl, CURLOPT_POSTFIELDS, body);
        curl_easy_setopt(curl, CURLOPT_URL, url);
        curl_easy_setopt(curl, CURLOPT_VERBOSE, 1L);
        res = curl_easy_perform(curl);
        if(res != CURLE_OK) {
            printf("curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
        }
        curl_easy_cleanup(curl);
        curl_slist_free_all(chunk);
    }
}

void rs_stat_with_token(char * token, char * bucket, char * key) {
    char *entryURI = Qiniu_String_Concat3(bucket, ":", key);
    char *entryURIEncoded = Qiniu_String_Encode(entryURI);
    char *url = Qiniu_String_Concat3(QINIU_RS_HOST, "/stat/", entryURIEncoded);
    Qiniu_Free(entryURI);
    Qiniu_Free(entryURIEncoded);
    http_post(url, token, NULL, NULL);
}

int main() {
    char * token = "QBox bjtWBQXrcxgo7HWwlC_bgHg81j352_GhgBGZPeOW:88TmLNwvfOhghi9vbvE71EfkRGs=";
    char * bucket = "panyuan";
    char * key = "1.png";
    rs_stat_with_token(token, bucket ,key);
    return 0;
}
