package useful;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.qiniu.http.Client;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class AsyncFetch
{	
	public static void main(String[] args) throws IOException
	{
		Gson gson = new Gson();
        	Map<String, String> m = new HashMap<>();
        	m.put("url", "<url>");
        	m.put("bucket", "<bucket>");
        	m.put("key", "<key>");
        	
        	String paraR = gson.toJson(m);
        	byte[] bodyByte = paraR.getBytes();
        
        	String url = "http://api-z0.qiniu.com/sisyphus/fetch";
        
		String accessKey = "<ak>";
		String secretKey = "<sk>";
		Auth auth = Auth.create(accessKey, secretKey);
        
        	String accessToken = (String) auth.authorizationV2(url, "POST", bodyByte, Client.JsonMime).get("Authorization");
        
        	System.out.println(accessToken);
        
        	Client client = new Client();
        	StringMap headers = new StringMap();
        	headers.put("Authorization", accessToken);
        
        	try
		{
            		com.qiniu.http.Response resp = client.post(url, bodyByte, headers, Client.JsonMime);
            		System.out.println(resp.toString());
            		System.out.println(resp.bodyString());
        	} 
        	catch (Exception e)
		{
            		e.printStackTrace();
        	}
	}
}
 
