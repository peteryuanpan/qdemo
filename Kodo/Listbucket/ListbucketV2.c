#include "../qiniu/http.h"
#include "../qiniu/base.h"
#include <curl/curl.h>
#include <stdio.h>
#include <stdlib.h>

const char *Qiniu_Int2Str(int val) {
    static char str[30];
    sprintf(str, "%d", val);
    return str;
}

int main(int argc, char **argv) {
    Qiniu_Client client;
    Qiniu_Mac mac;
    Qiniu_Header *headers = NULL;
    CURL *curl;
    CURLcode curlCode;
    char *url = NULL;

    mac.accessKey = "<ak>";
    mac.secretKey = "<sk>";
    char *bucket = "<bucket>";
    char *prefix = "";
    char *delimiter = "";
    char *marker = "";
    int limit = "<limit>";

    Qiniu_Client_InitMacAuth(&client, 1024, &mac);
    url = Qiniu_String_Concat(QINIU_RSF_HOST, "/v2/list?", "bucket=", bucket, "&prefix=", prefix, "&delimiter=", delimiter, "&marker=", marker, "&limit=", Qiniu_Int2Str(limit), NULL);
    printf("%s\n", url);

    client.auth.itbl->Auth(client.auth.self, &headers, url, NULL, 0);
    printf("%s\n", headers->data);

    curl_global_init(CURL_GLOBAL_ALL);
    curl = curl_easy_init();

    if (curl) {
        struct curl_slist *chunk = NULL;
        chunk = curl_slist_append(chunk, headers->data);
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, chunk);

        curl_easy_setopt(curl, CURLOPT_URL, url);
        curlCode = curl_easy_perform(curl);

        curl_easy_cleanup(curl);
    }

    curl_global_cleanup();
    return 0;
}
