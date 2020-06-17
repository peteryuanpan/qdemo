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

/**
> Task :GetS3Domain.main()
QBox bjtWBQXrcxgo7HWwlC_bgHg81j352_GhgBGZPeOW:MEpWFCzfTUAy8IgDHVwpRVHbsHc=
{ResponseInfo:com.qiniu.http.Response@7cd62f43,status:200, reqId:ajAAAK9suWNTQxkW, xlog:BUCKET:14;BUCKET:15, xvia:, adress:uc.qbox.me/218.98.28.19:80, duration:0.196000 s, error:null}
{"domain":"panyuan.s3-cn-east-1.qiniucs.com","tbl":"panyuan","owner":1380432151,"antileech":{"no_refer":false,"anti_leech_mode":0,"anti_leech_used":false,"source_enabled":false},"domaintype":1,"s3endpoint":"s3-cn-east-1.qiniucs.com"}
*/
