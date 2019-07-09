#include <stdio.h>
#include <stdlib.h>
#include <curl/curl.h>
#include "../qiniu/base.h"
#include "../qiniu/http.h"
#include "../qiniu/rs.h"

void http_post(const char *url, const char *token, const char *body, const char *content_type) {
    CURL *curl;
    CURLcode res;
    curl = curl_easy_init();
    if (curl) {
        struct curl_slist *chunk = NULL;
        chunk = curl_slist_append(chunk, Qiniu_String_Concat2("Authorization: ", token));
        chunk = curl_slist_append(chunk, Qiniu_String_Concat2("Content-Type: ", content_type));
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, chunk);
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, body);
        curl_easy_setopt(curl, CURLOPT_URL, url);
        //curl_easy_setopt(curl, CURLOPT_VERBOSE, 1L);
        res = curl_easy_perform(curl);
        if(res != CURLE_OK) {
            printf("curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
        }
        curl_easy_cleanup(curl);
        curl_slist_free_all(chunk);
    }
}

int main() {
    Qiniu_Global_Init(-1);

    const char *accessKey = "<ak>";
    const char *secretKey = "<sk>";
    const char *bucket = "<bucket>";
    const char *key = "<key>";
    const char *body = "<body>"; // "ABCDE";
    const char *content_type = "application/octet-stream";
    const char *upload_url = "<endpiont>"; // "https://upload.qiniup.com/";

    Qiniu_Mac mac;
    mac.accessKey = accessKey;
    mac.secretKey = secretKey;
    Qiniu_Client client;
    Qiniu_Client_InitMacAuth(&client, 1024, &mac);

    Qiniu_RS_PutPolicy putPolicy;
    Qiniu_Zero(putPolicy);
    putPolicy.scope = bucket;
    char *uptoken = Qiniu_String_Concat2("UpToken ", Qiniu_RS_PutPolicy_Token(&putPolicy, &mac));
    printf("%s\n", uptoken);

    int len_body = strlen(body);
    for (int i = 0; i < len_body; i ++) {
        char i_s[] = {i + '0', '\0'};
        char sub_body[] = {body[i], '\0'};
        char *url = Qiniu_String_Concat(upload_url, "append/", i_s, "/key/", Qiniu_String_Encode(key), NULL);
        printf("%s\n", url);
        http_post(url, uptoken, sub_body, content_type);
    }

    Qiniu_Client_Cleanup(&client);
    return 0;
}
