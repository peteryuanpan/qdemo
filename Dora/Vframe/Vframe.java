import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.processing.OperationManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
 
public class Vframe {
 
    public static void main(String[] args) {
         
        String accessKey = "ak";
        String secretKey = "sk";
        Auth auth = Auth.create(accessKey, secretKey);
         
        String url = "http://api.qiniu.com/pfop/";
        String token = (String) auth.authorization(url).get("Authorization");
        System.out.println(token);
         
        Configuration cfg = new Configuration(Zone.autoZone());
        OperationManager operationManager = new OperationManager(auth, cfg);
         
        String fops = "vframe/jpg/offset/1";
         
        try {
            String persistentId = operationManager.pfop("aaaaa", "1.mp4", fops, "aaaaa", null);
            System.out.println(persistentId);
             
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }
}
