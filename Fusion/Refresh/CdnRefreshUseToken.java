package cdn;

import java.util.Arrays;
import java.util.List;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class CdnRefreshUseToken {
    
    static final String access_key = "<access_key>";
    static final String secret_key = "<secret_key>";
    static Auth auth = Auth.create(access_key, secret_key);
    static final String url = "http://fusion.qiniuapi.com/v2/tune/refresh";
    static Client client = new Client();
    
    public static void main(String[] args) {
        
        StringMap params =  new StringMap();
        
        List<String> urlsList = Arrays.asList("<url>");
        params.put("urls", urlsList);
        
        //List<String> dirsList = Arrays.asList("");
        //params.put("dirs", dirsList);
        
        String authorization = (String) auth.authorization(url, params.jsonString().getBytes(), "application/json").get("Authorization");
        
        StringMap headers = new StringMap();
        headers.put("Authorization", authorization);
        
        try {
            Response response = client.post(url, params.jsonString().getBytes(), headers, "application/json");
            System.out.println(response.bodyString());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        
    }

}
