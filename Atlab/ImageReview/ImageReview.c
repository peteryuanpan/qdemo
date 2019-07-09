/*
POST /v3/image/censor
Host: ai.qiniuapi.com
Content-Type: application/json

{"data":{"uri":"https://mars-assets.qnssl.com/resource/gogopher.jpg"},"params":{"scenes":["pulp"]}}
Qiniu bjtWBQXrcxgo7HWwlC_bgHg81j352_GhgBGZPeOW:R7X8PSeYGKYQYvONHZ-DRVh88qE=
{"code":200,"message":"OK","result":{"suggestion":"pass","scenes":{"pulp":{"suggestion":"pass","details":[{"suggestion":"pass","label":"normal","score":0.97864}]}}}}
*/

#include <stdio.h>
#include <stdlib.h>
#include "../qiniu/base.h"
#include "../qiniu/http.h"
#include <curl/curl.h>
#include <openssl/hmac.h>
#include <openssl/engine.h>

static void hmac_update(HMAC_CTX ctx, char *s) {
    HMAC_Update(&ctx, s, strlen(s));
}

char* qiniu_token(const char *ak, const char *sk, const char *method, const char *path, const char *host, const char *content_type, const char *body) {
    char* token;
    char* enc_digest;
    char digest[EVP_MAX_MD_SIZE + 1];
    unsigned int digest_len = sizeof(digest);

    Qiniu_Mac mac;
    mac.accessKey = ak;
    mac.secretKey = sk;

    HMAC_CTX ctx;
    HMAC_CTX_init(&ctx);
    HMAC_Init_ex(&ctx, mac.secretKey, strlen(mac.secretKey), EVP_sha1(), NULL);
    hmac_update(ctx, Qiniu_String_Concat(method, " ", path, NULL));
    hmac_update(ctx, Qiniu_String_Concat("\nHost: ", host, NULL));
    hmac_update(ctx, Qiniu_String_Concat("\nContent-Type: ", content_type, NULL));
    hmac_update(ctx, "\n\n");
    hmac_update(ctx, body);
    HMAC_Final(&ctx, digest, &digest_len);
    HMAC_cleanup(&ctx);

    enc_digest = Qiniu_Memory_Encode(digest, digest_len);
    token = Qiniu_String_Concat("Qiniu ", mac.accessKey, ":", enc_digest, NULL);

    return token;
}

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
        curl_easy_setopt(curl, CURLOPT_VERBOSE, 1L);
        res = curl_easy_perform(curl);
        if(res != CURLE_OK) {
            printf("curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
        }
        curl_easy_cleanup(curl);
        curl_slist_free_all(chunk);
    }
}

int main() {
    char *ak = "<ak>";
    char *sk = "<sk>";
    char *method = "POST";
    char *path = "/v3/image/censor";
    char *host = "ai.qiniuapi.com";
    char *content_type = "application/json";
    char *body = "{\"data\":{\"uri\":\"https://mars-assets.qnssl.com/resource/gogopher.jpg\"},\"params\":{\"scenes\":[\"pulp\"]}}";
    char *token = qiniu_token(ak, sk, method, path, host, content_type, body);
    printf("%s\n", token);
    
    http_post("http://ai.qiniuapi.com/v3/image/censor", token, body, content_type);
}
