import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

public class DisabledStream {

    static final String accessKey = "<accessKey>";
    static final String secretKey = "<secretKey>";
    static String hub = "<hub>";
    static String stream = "<stream>";
    static String url = "http://pili.qiniuapi.com/v2/hubs/" + hub + "/streams/" + UrlSafeBase64.encodeToString(stream) + "/disabled";

    static Client client = new Client();
    static Auth auth = Auth.create(accessKey, secretKey);

    public static void main(String[] args) {
        StringMap headers = new StringMap();
        headers.put("Authorization", qiniuToken());
        try {
            Response resp = client.post(url, json().getBytes(), headers, Client.JsonMime);
            System.out.println(resp.toString());
            System.out.println(resp.bodyString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String qiniuToken() {
        String authorization = (String) auth.authorizationV2(url, "POST", json().getBytes(), Client.JsonMime).get("Authorization");
        System.out.println(authorization);
        return authorization;
    }

    static String json() {
        return "{\"disabledTill\": 0}";
    }
}
