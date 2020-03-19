import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
 
public class FaceGroup {
     
    static final String accessKey = "<accessKey>";
    static final String secretKey = "<secretKey>";
    
    static String group_id = "<group_id>";
    static String url = "http://ai.qiniuapi.com/v1/face/group/" + group_id + "/new";
    static String resouce_url = "<resouce_url>";
    
    public static void main(String[] args) {
        StringMap headers = new StringMap();
        headers.put("Authorization", qiniuToken());
        try {
            Client client = new Client();
            Response resp = client.post(url, json().getBytes(), headers, Client.JsonMime);
            System.out.println(resp.statusCode);
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
        return "{\"data\": [{\"uri\": \"" + resouce_url  + "\"}]}";
    }
}
