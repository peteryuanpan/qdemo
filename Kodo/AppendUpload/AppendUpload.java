package test;
 
import java.io.File;
import java.io.FileInputStream;
 
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
 
public class AppendObject
{
    static final String accessKey = "ak";
    static final String secretKey = "sk";
    static final String bucket = "aaaaa";
    static final String key = "113";
    static final String localFilePath = "/Users/peteryuanpan/Desktop/vlc-3.0.4.dmg";
    static Integer fsize = 20;
    static byte[] fbyte;
     
    public static void main(String[] args)
    {
        AppendObject appendObject = new AppendObject();
        String upToken = appendObject.upToken();
        System.out.println(upToken);
        try
        {
            appendObject.initFile();
            String url = "http://upload.qiniup.com/append/0/fsize/" + Integer.toString(fsize) + "/key/" + UrlSafeBase64.encodeToString(key);
            System.out.println(url);
            StringMap headers = new StringMap();
            headers.put("Host", "upload.qiniup.com");
            headers.put("Authorization", "UpToken " + upToken);
            headers.put("Content-Type", "application/octet-stream");
            Client client = new Client();
            Response resp = client.post(url, fbyte, headers, Client.JsonMime);
            System.out.println(resp.statusCode);
            System.out.println(resp.bodyString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
     
    String upToken()
    {
        Auth auth = Auth.create(accessKey, secretKey);
        StringMap policy = new StringMap();
        String upToken = auth.uploadToken(bucket, key, 3600, policy);
        return upToken;
    }
     
    @SuppressWarnings("resource")
    void initFile() throws Exception
    {
        File file = new File(localFilePath);
        if (fsize == null)
        {
            fsize = (int) file.length();
        }
        fbyte = new byte[fsize];
        FileInputStream fis = new FileInputStream(file);
        fis.read(fbyte);
    }
}
