package useful;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
 
import com.qiniu.cdn.CdnManager;
import com.qiniu.cdn.CdnResult.PrefetchResult;
import com.qiniu.util.Auth;
 
public class PrefetchUrls {
 
    public static void main(String[] args) {
         
        String accessKey = "<ak>";
        String secretKey = "<sk>";
         
        Auth auth = Auth.create(accessKey, secretKey);
        CdnManager cndManager = new CdnManager(auth);
         
        try {
            File file = new File("<filepath>");
            List<String> list = read(file);
 
            for (int i = 0; i < list.size(); i += 100) {
                int limit = Math.min(list.size(), i + 100);
                System.out.printf("%d, %d\n", i, limit);
                String[] urls = new String[limit - i];
                urls = list.subList(i, limit).toArray(urls);
                PrefetchResult result = cndManager.prefetchUrls(urls);
                System.out.println(result.code);
            }
             
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     
    @SuppressWarnings("resource")
    static List<String> read(File file) {
        List<String> urls = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                urls.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urls;
    }
}
