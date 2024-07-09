package ManageFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

/**
 * Classe per gestire il caricamento di una directory e di un file su un bucket Amazon S3.
 * Questa classe si occupa di caricare un file specifico e una directory locale
 * ad un bucket S3 predefinito.
 */
public class uploadFile {
    /** Nome del bucket S3 su cui caricare i file. */
    private final String bucketName = "test-dungeonunipd";

    /** Regione AWS in cui si trova il bucket S3. */
    private final Region region = Region.EU_WEST_3;
    /**Credenziali per accesso al bucket aws*/
    private static final String AWS_ACCESS_KEY_ID = "YourKey";
    private static final String AWS_SECRET_ACCESS_KEY = "YourSecretKey";

    public uploadFile(String repositoryname, String filename) throws IOException {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY);
        try (InputStream file = new FileInputStream("FileLoad/" + repositoryname + "/" + filename);
             S3Client s3Client = S3Client.builder().region(region).credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build()) {

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
