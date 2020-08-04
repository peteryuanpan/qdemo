import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;

import java.io.IOException;


public class UploadDemo {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "<ACCESS_KEY>";
    String SECRET_KEY = "<SECRET_KEY>";
    //要上传的空间
    String bucketname = "<bucketname>";
    //上传到七牛后保存的文件名
    String key = "<key>";
    //上传文件的路径
    String FilePath = "<FilePath>";

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    ///////////////////////指定上传的Zone的信息//////////////////
    //第一种方式: 指定具体的要上传的zone
    //注：该具体指定的方式和以下自动识别的方式选择其一即可
    //要上传的空间(bucket)的存储区域为华东时
    // Zone z = Zone.zone0();
    //要上传的空间(bucket)的存储区域为华北时
    // Zone z = Zone.zone1();
    //要上传的空间(bucket)的存储区域为华南时
    // Zone z = Zone.zone2();

    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
    //Zone z = Zone.autoZone();

    Zone z = new Zone.Builder(Zone.zone0())
				.upHttp("http://upload.qiniu.com")
				.upBackupHttp("http://upload.qiniu.com")
				.upHttps("https://upload.qiniup.com")
				.upBackupHttps("https://upload.qiniup.com")
				.build();

    Configuration c = new Configuration(z);

    public static void main(String args[]) throws IOException {
        new UploadDemo().upload();
    }

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public void upload() throws IOException {
        c.useHttpsDomains = false;
        UploadManager uploadManager = new UploadManager(c);
        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            System.out.println(e.code());
            System.out.println(e.error());
        }
    }

}
