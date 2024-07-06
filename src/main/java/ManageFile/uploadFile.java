package ManageFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

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
    /**
     * Costruttore della classe uploadFile.
     * Inizializza il caricamento di un file e di una directory specifica su Amazon S3.
     *
     * @param repositoryname Il nome della directory da caricare sul bucket aws
     * @param filename Il nome del file da caricare sul bucket aws
     * @throws IOException Se si verifica un errore durante la lettura o il caricamento del file.
     */
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