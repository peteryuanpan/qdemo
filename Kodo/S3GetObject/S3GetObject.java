import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import com.amazonaws.Protocol;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Get an object within an Amazon S3 bucket.
 *
 * This code expects that you have AWS credentials set up per:
 * http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html
 */
public class GetObjectTest {

    static final String accessKeyId = "";
    static final String accessKeySecret = "";

    public static void main(String[] args) {
        String bucket_name = "";
        String file_name = "";
        String download_file_path = "";
        System.out.format("Downloading %s from S3 bucket %s to %s...\n", file_name, bucket_name, download_file_path);
        try {
            AmazonS3 AmazonS3Client = new AmazonS3Client(new BasicAWSCredentials(accessKeyId, accessKeySecret),
                    new ClientConfiguration().withProtocol(Protocol.HTTP));
            S3Object o = Constants.AmazonS3Client.getObject(bucket_name, file_name);
            S3ObjectInputStream s3is = o.getObjectContent();
            FileOutputStream fos = new FileOutputStream(new File(download_file_path));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("Done!");
    }
}
