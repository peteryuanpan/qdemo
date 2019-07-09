import com.qiniu.common.QiniuException;
import com.qiniu.streaming.StreamingManager;
import com.qiniu.streaming.model.StreamListing;
import com.qiniu.util.Auth;

public class ListStream {

	public static void main(String[] args) {
		
		String ak = "<ak>";
		String sk = "<sk>";
		Auth auth = Auth.create(ak, sk);
		String hub = "<hub>";
		StreamingManager streamingManager = new StreamingManager(auth, hub);
		try {
			StreamListing streamListing = streamingManager.listStreams(false, null, null);
			String[] items = streamListing.keys();
			if (items != null) {
				for (int i = 0; i < items.length; i ++) {
					System.out.println(items[i]);
				}
			}
			System.out.println(streamListing.marker);
		} catch (QiniuException e) {
			e.printStackTrace();
		}
	}
	
}
