import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class GET_HUB_INFO {

    static final String accessKey = "<accessKey>";
    static final String secretKey = "<secretKey>";
    static String hub = "<hub>";
    static String url = "http://pili.qiniuapi.com/v2/hubs/" + hub;

    static Client client = new Client();
    static Auth auth = Auth.create(accessKey, secretKey);

    public static void main(String[] args) {
        StringMap headers = new StringMap();
        headers.put("Authorization", qiniuToken());
        try {
            Response resp = client.get(url, headers);
            System.out.println(resp.toString());
            System.out.println(resp.bodyString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String qiniuToken() {
        String authorization = (String) auth.authorizationV2(url).get("Authorization");
        System.out.println(authorization);
        return authorization;
    }
}
