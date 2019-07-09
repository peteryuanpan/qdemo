import com.qiniu.common.AutoZone;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;

public class MoveObject {
	
	static final String accessKey = "<ak>";
	static final String secretKey = "<sk>";

	public static void main(String[] args) {
		
		Auth auth = Auth.create(accessKey, secretKey);
		AutoZone autoZone = new AutoZone();
		Configuration cfg = new Configuration(autoZone);
		BucketManager bucketManager = new BucketManager(auth, cfg);
		
		try {
			Response response = bucketManager.move("<fromBucket>", "<fromFileKey>", "<toBucket>", "<toFileKey>");
			System.out.println(response.statusCode);
			System.out.println(response.reqId);
			System.out.println(response.bodyString());
		} catch (QiniuException e) {
			e.printStackTrace();
		}
	}
}
