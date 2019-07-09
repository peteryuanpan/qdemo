import com.qiniu.http.Client;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class AsyncResult {

	public static void main(String[] agrs) {

		String jobid = "<jobid>";
		String url = "http://ai.qiniuapi.com/v1/jobs/video/" + jobid;

		String accessKey = "<ak>";
		String secretKey = "<sk>";
		Auth auth = Auth.create(accessKey, secretKey);

        	String token = (String) auth.authorizationV2(url).get("Authorization");
        	System.out.println(token);

        	Client client = new Client();
        	StringMap headers = new StringMap();
	        headers.put("Authorization", token);

        	try {
            		com.qiniu.http.Response resp = client.get(url, headers);
            		System.out.println(resp.toString());
            		System.out.println(resp.bodyString());
        	}
        	catch (Exception e) {
            		e.printStackTrace();
        	}
	}
}
