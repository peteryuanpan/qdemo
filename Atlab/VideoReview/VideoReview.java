package ai;

import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
 
public class VideoReview {
     
    static final String accessKey = "<ak>";
    static final String secretKey = "<sk>";
    static final String url = "http://ai.qiniuapi.com/v3/video/censor";
    static final String video_url = "https://mars-assets.qnssl.com/scene.mp4";
    
    public static void main(String[] args) {
        StringMap headers = new StringMap();
        headers.put("Authorization", qiniuToken());
        try {
            Client client = new Client();
            Response resp = client.post(url, json().getBytes(), headers, Client.JsonMime);
            System.out.println(resp.toString());
            System.out.println(resp.bodyString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static String qiniuToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        String authorization = (String) auth.authorizationV2(url, "POST", json().getBytes(), Client.JsonMime).get("Authorization");
        System.out.println(authorization);
        return authorization;
    }
     
    static String json() {
        return "{\"data\": {\"uri\": \"" + video_url  + "\"},\"params\": {\"scenes\": [\"pulp\"]}}";
    }
}
