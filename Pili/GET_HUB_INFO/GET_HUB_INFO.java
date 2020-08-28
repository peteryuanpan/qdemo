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

/**
Qiniu Yagw3OmzvYGwfB6cQ0gGcZxr_rskhp3EjhkE9uCx:4ideFqAGZpnkWVsfrB5i73uBQgI=
{ResponseInfo:com.qiniu.http.Response@4883b407,status:200, reqId:3hMAALR40xKiXy8W, xlog:PILI;PILI-LINA:2, xvia:jjh1446, adress:pili.qiniuapi.com/180.101.136.120:80, duration:1.151000 s, error:null}
{"createdAt":1542611519,"updatedAt":1542611521,"domainIndex":"1000d5a","domains":[{"type":"publishRtmp","domain":"pili-publish.tt.test.cloudvdn.com","cname":"pili-publish.tt.test.cloudvdn.com.pubv3.pilidns.com"},{"type":"liveRtmp","domain":"pili-live-rtmp.tt.test.cloudvdn.com","cname":"pili-live-rtmp.tt.test.cloudvdn.com.rtmpv3.pilidns.com"},{"type":"liveHls","domain":"pili-live-hls.tt.test.cloudvdn.com","cname":"pili-live-hls.tt.test.cloudvdn.com.hlsv3.pilidns.com"},{"type":"liveHdl","domain":"pili-live-hdl.tt.test.cloudvdn.com","cname":"pili-live-hdl.tt.test.cloudvdn.com.hdlv3.pilidns.com"}],"defaultDomains":[{"type":"liveHdl","domain":"pili-live-hdl.tt.test.cloudvdn.com"},{"type":"liveHls","domain":"pili-live-hls.tt.test.cloudvdn.com"},{"type":"liveRtmp","domain":"pili-live-rtmp.tt.test.cloudvdn.com"},{"type":"publishRtmp","domain":"pili-publish.tt.test.cloudvdn.com"}],"storageBucket":"demo-test-1","liveDataExpireDays":60,"publishSecurity":"expiry_sk","callbackURL":"","callbackLatency":30,"authCallbackURL":"","authCallbackCheckCode":200,"authCallbackCheckBody":"","snapshotInterval":60,"SnapshotInterval":60,"nrop":{"enable":false,"interval":0,"notifyURL":"","notifyRate":0,"saveRate":0},"group":"","edgeNodeForward":true,"converts":[],"transform":"","watermark":false,"hlsPlus":false,"vodDomain":"pili-vod.tt.test.cloudvdn.com","accesslog":{}}
*/
