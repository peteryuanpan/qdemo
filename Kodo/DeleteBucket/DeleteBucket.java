import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;


public class DeleteBucket {

    static final String accessKey = "<ak>";
    static final String secretKey = "<sk>";
    static final String bucket = "<bucket>";
    static final String url = "/drop/" + bucket;
    
    public static void main(String[] args) {
        StringMap headers = new StringMap();
        headers.put("Authorization", token());
        try {
            Client client = new Client();
            Response resp = client.post(url, json().getBytes(), headers, Client.JsonMime);
            System.out.println(resp.toString());
            System.out.println(resp.bodyString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    static String token() {
        Auth auth = Auth.create(accessKey, secretKey);
        String authorization = (String) auth.authorization(url, json().getBytes(), Client.JsonMime).get("Authorization");
        System.out.println(authorization);
        return authorization;
    }
    
    static String json() {
        return "";
    }
}
