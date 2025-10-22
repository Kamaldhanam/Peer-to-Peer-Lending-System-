package p2p;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;

public class S3Manager {
    private static final String BUCKET_NAME = "kamal-bucket";

    public static void uploadDocument(String filePath, String userEmail) {
        try {
            S3Client s3 = S3Client.builder()
                    .region(Region.US_EAST_1)
                    .credentialsProvider(ProfileCredentialsProvider.create())
                    .build();

            File file = new File(filePath);

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(userEmail + "/" + file.getName())
                    .build();

            s3.putObject(request, file.toPath());
            System.out.println("Uploaded document to S3: " + file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
