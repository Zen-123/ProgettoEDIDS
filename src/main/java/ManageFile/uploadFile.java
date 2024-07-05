package ManageFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

public class uploadFile {
    private final String bucketName = "test-dungeonunipd";
    private final Region region = Region.EU_WEST_3;

    public uploadFile(String repositoryname, String filename) throws IOException {
        try (InputStream file = new FileInputStream("FileLoad/" + repositoryname + "/" + filename);
             S3Client s3Client = S3Client.builder().region(region).build()) {

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(repositoryname + "/" + filename)
                    .build();

            s3Client.putObject(request, RequestBody.fromInputStream(file, file.available()));
            System.out.println("File " + filename + " caricato.");
            System.out.println("Salvataggio avvenuto con successo!");
        } catch (IOException e) {
            System.out.println("File non valido!");
            throw e;
        }
    }
}