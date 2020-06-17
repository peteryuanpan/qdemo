import com.qiniu.common.AutoZone;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;

public class GetBucketInfo {

    static final String accessKey = "<accessKey>";
    static final String secretKey = "<secretKey>";
    static Auth auth = Auth.create(accessKey, secretKey);
    static String bucket = "<bucket>";

    public static void main(String[] args) {
        AutoZone autoZone = new AutoZone();
        Configuration cfg = new Configuration(autoZone);
        BucketManager bucketManager = new BucketManager(auth, cfg);

        try {
            Response response = bucketManager.getBucketInfoResponse(bucket);
            System.out.println(response.bodyString());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }
}
