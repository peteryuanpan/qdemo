import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class GetS3Domain {

    static final String accessKey = "<accessKey>";
    static final String secretKey = "<secretKey>";
    static final String bucket = "<bucket>";
    static final String url = "http://uc.qbox.me/s3domain?tbl=" + bucket;

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
        String authorization = (String) auth.authorization(url).get("Authorization");
        System.out.println(authorization);
        return authorization;
    }

}
