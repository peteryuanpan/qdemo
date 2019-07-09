package test;
 
import java.io.File;
import java.io.FileInputStream;
 
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
 
public class Put64
{
    static final String accessKey = "ak";
    static final String secretKey = "sk";
    static final String bucket = "aaaaa";
    static final String key = "002.txt";
     
    static final String localFilePath = "/Users/peteryuanpan/Desktop/002.txt";
    static Integer fsize;
    static byte[] fbyte;
    static String file64;
     
    public static void main(String[] args)
    {
        Put64 put64 = new Put64();
        String upToken = put64.upToken();
        System.out.println(upToken);
        try
        {
            put64.initFile();
            String url = "http://upload.qiniup.com/putb64/" + Integer.toString(fsize) + "/key/" + UrlSafeBase64.encodeToString(key);
            StringMap headers = new StringMap();
            headers.put("Authorization", "UpToken " + upToken);
            headers.put("Content-Type", "application/octet-stream");
            Client client = new Client();
            Response resp = client.post(url, file64.getBytes(), headers, Client.JsonMime);
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
        fsize = (int) (file.length());
        fbyte = new byte[fsize];
        FileInputStream fis = new FileInputStream(file);
        fis.read(fbyte);
        file64 = Base64.encodeToString(fbyte, 0);
    }
}
