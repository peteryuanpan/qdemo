import java.util.Arrays;
import java.util.List;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class Toptrafficurl {
    
    static final String ak = "<ak>";
    static final String sk = "<sk>";
    static Auth auth = Auth.create(ak, sk);
    static Client client = new Client();

    public static void main(String[] args) {
        String url = "http://fusion.qiniuapi.com/v2/tune/loganalyze/toptrafficurl";
        
        StringMap params =  new StringMap();
        List<String> domainList = Arrays.asList("<domain>");
        
        params.put("domains", domainList);
        params.put("region", "global");
        params.put("startDate", "2020-01-01");
        params.put("endDate", "2020-01-02");
        
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
