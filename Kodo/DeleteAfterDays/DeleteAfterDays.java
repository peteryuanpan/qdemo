package kodo;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;

public class DeleteAfterDays {
    
    static final String accessKey = "<accessKey>";
    static final String secretKey = "<secretKey>";
    static String bucket = "<bucket>";
    static String key = "<key>";
    static int days = 1;
    
    static Auth auth = Auth.create(accessKey, secretKey);
    static Client client = new Client();

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        BucketManager bucketManager = new BucketManager(auth, configuration);
        try {
            Response response = bucketManager.deleteAfterDays(bucket, key, days);
            System.out.println(response.statusCode);
            System.out.println(response.bodyString());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }
    
}
