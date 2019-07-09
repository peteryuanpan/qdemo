package test;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;

import java.io.IOException;

public class FileRecoderUpload
{
    public static void main(String args[])
    {
        String bucket = "aaaaa";
        String key = "ttt001";
        String domain = "upload.qiniup.com";
        Zone zone = (new Zone.Builder())
                .upHttp("http://" + domain)
                .upHttps("https://up.qbox.me")
                .upBackupHttp("http://up.qiniu.com")
                .upBackupHttps("https://upload.qbox.me")
                .iovipHttp("http://iovip.qbox.me")
                .iovipHttps("https://iovip.qbox.me")
                .rsHttp("http://rs.qiniu.com")
                .rsHttps("https://rs.qbox.me")
                .rsfHttp("http://rsf.qiniu.com")
                .rsfHttps("https://rsf.qbox.me")
                .apiHttp("http://api.qiniu.com")
                .apiHttps("https://api.qiniu.com")
                .build();
        Configuration cfg = new Configuration(zone);
        String accessKey = "ak";
        String secretKey = "sk";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        String localFilePath = "/Users/peteryuanpan/qiniu/api/data/1.pkg";
        String localTempDir = "/Users/peteryuanpan/qiniu/api/data";
        try
        {
            FileRecorder fileRecorder = new FileRecorder(localTempDir);
            UploadManager uploadManager = new UploadManager(cfg, fileRecorder);
            Response response = null;
            try
            {
                response = uploadManager.put(localFilePath, key, upToken);
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(response);
                System.out.println(response.bodyString());
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } 
            catch (QiniuException ex)
            {
                Response r = ex.response;
                System.err.println(r.toString());
            } 
            finally
            {
                if (response != null)
                {
                    response.close();
                }
            }
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
