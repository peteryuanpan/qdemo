package test;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class FormUpload {

	public static void main(String[] args) {
		
		Zone zone = new Zone.Builder(Zone.autoZone())
				.upHttp("http://upload.qiniu.com")
				.upBackupHttp("http://upload.qiniu.com")
				.upHttps("https://upload.qiniup.com")
				.upBackupHttps("https://upload.qiniup.com")
				.build();
		Configuration cfg = new Configuration(zone);
		UploadManager uploadManager = new UploadManager(cfg);
		
		String accessKey = "<ak>";
		String secretKey = "<sk>";
		String bucket = "<bucket>";
		String localFilePath = "<path>";
		String key = "<key>";
		Auth auth = Auth.create(accessKey, secretKey);
		
		StringMap policy = new StringMap();
		//policy.put("insertOnly", 1);
		
		String upToken = auth.uploadToken(bucket, key, 3600, policy);
		System.out.println(upToken);
		
		try {
		    Response response = uploadManager.put(localFilePath, key, upToken);
		    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		    
		    System.out.println(putRet.key);
		    System.out.println(putRet.hash);
		    
		} catch (QiniuException ex) {
			
		    Response r = ex.response;
		    System.err.println(r.toString());
		    
		    try {
		        System.err.println(r.bodyString());
		    } catch (QiniuException ex2) {
		    	// DO NOTHING
		    }
		}
	}
}
