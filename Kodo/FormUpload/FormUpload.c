#include "qiniu/io.h"
#include "qiniu/rs.h"
#include "qiniu/http.h"
#include <stdlib.h>
 
int main(int argc, char **argv) {
 
    Qiniu_Global_Init(-1);
 
    Qiniu_Io_PutRet putRet;
    Qiniu_Client client;
    Qiniu_RS_PutPolicy putPolicy;
    Qiniu_Io_PutExtra putExtra;
 
    char *accessKey = "";
    char *secretKey = "";
    char *bucket = "";
    char *key = "";
    char *localFile = "";
 
    Qiniu_Mac mac;
    mac.accessKey = accessKey;
    mac.secretKey = secretKey;
 
    Qiniu_Zero(putPolicy);
    Qiniu_Zero(putExtra);
 
    putPolicy.scope = bucket;
    char *uptoken = Qiniu_RS_PutPolicy_Token(&putPolicy, &mac);
 
    Qiniu_Use_Zone_Huadong(Qiniu_True);
 
    Qiniu_Client_InitMacAuth(&client, 1024, &mac);
    Qiniu_Error error = Qiniu_Io_PutFile(&client, &putRet, uptoken, key, localFile, &putExtra);
    if (error.code != 200) {
        printf("%d\n", error.code);
        printf("%s\n", error.message);
    } else {
        printf("key:\t%s\n",putRet.key);
        printf("hash:\t%s\n", putRet.hash);
    }
 
    Qiniu_Free(uptoken);
    Qiniu_Client_Cleanup(&client);
}
