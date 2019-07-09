#include "../qiniu/io.h"
#include "../qiniu/rs.h"
#include "../qiniu/resumable_io.h"
#include <stdlib.h>

int main(int argc, char **argv) {
    Qiniu_Global_Init(-1);

    Qiniu_Io_PutRet putRet;
    Qiniu_Client client;
    Qiniu_RS_PutPolicy putPolicy;
    Qiniu_Rio_PutExtra putExtra;

    char *accessKey = "<ak>";
    char *secretKey = "<sk>";
    char *bucket = "<bucket>";
    char *key = "<key>";
    char *localFile = "<localfilepath>";

    Qiniu_Mac mac;
    mac.accessKey = accessKey;
    mac.secretKey = secretKey;

    Qiniu_Zero(putPolicy);
    Qiniu_Zero(putExtra);

    putPolicy.scope = bucket;
    char *uptoken = Qiniu_RS_PutPolicy_Token(&putPolicy, &mac);

    Qiniu_Use_Zone_Huadong(Qiniu_True);

    putExtra.upHost="https://upload.qiniup.com";

    Qiniu_Client_InitMacAuth(&client, 1024, &mac);
    Qiniu_Error error = Qiniu_Rio_PutFile(&client, &putRet, uptoken, key, localFile, &putExtra);
    if (error.code != 200) {
        printf("%d\n", error.code);
        printf("%s\n", error.message);
        printf("upload file %s:%s error.\n", bucket, key);
    } else {
        printf("upload file %s:%s success.\n\n", bucket, key);
        printf("key:\t%s\n",putRet.key);
        printf("hash:\t%s\n", putRet.hash);
    }

    Qiniu_Free(uptoken);
    Qiniu_Client_Cleanup(&client);
}
