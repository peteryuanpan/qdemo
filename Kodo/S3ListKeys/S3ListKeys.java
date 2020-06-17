package example.test;

/**
 * 参考：https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/java/example_code/s3/src/main/java/aws/example/s3/ListKeys.java
 */

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.IOException;

public class ListKeys {

    /**
     * accessKeyId、accessKeySecret 从 https://portal.qiniu.com/user/key 中可获取
     * bucketName、s3endpoint 从 https://portal.qiniu.com/kodo/bucket 可找到具体空间名，点具体具体空间名查看配置
     * 比如 空间域名：<s3bucketname>.s3-cn-east-1.qiniucs.com
     * bucketName = <s3bucketname>
     * s3endpoint = s3-cn-east-1.qiniucs.com
     * 注意，bucketName不一定是空间名，而是s3endpoint的中的<s3bucketname>
     */
    static String accessKeyId = "<accessKeyId>";
    static String accessKeySecret = "<accessKeySecret>";
    static String bucketName = "<bucketName>";
    static String s3endpoint = "<s3endpoint>";
    static AmazonS3 AmazonS3Client = new AmazonS3Client(
            new BasicAWSCredentials(accessKeyId, accessKeySecret),
            new ClientConfiguration().withProtocol(Protocol.HTTP)
    );
    static {
        AmazonS3Client.setEndpoint(s3endpoint);
    }

    public static void main(String[] args) throws IOException {
        try {
            System.out.println("Listing objects");

            // maxKeys is set to 2 to demonstrate the use of
            // ListObjectsV2Result.getNextContinuationToken()
            ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withMaxKeys(2);
            ListObjectsV2Result result;

            do {
                result = AmazonS3Client.listObjectsV2(req);

                for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                    System.out.printf(" - %s (size: %d)\n", objectSummary.getKey(), objectSummary.getSize());
                }
                // If there are more than maxKeys keys in the bucket, get a continuation token
                // and list the next objects.
                String token = result.getNextContinuationToken();
                System.out.println("Next Continuation Token: " + token);
                req.setContinuationToken(token);
            } while (result.isTruncated());
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
    }
}
