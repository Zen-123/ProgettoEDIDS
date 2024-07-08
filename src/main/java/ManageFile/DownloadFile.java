package ManageFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import UI.UI;
import UI.choiceHandler;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;

/**
 * Classe per gestire il download di file da un bucket Amazon S3.
 * Questa classe si occupa di scaricare una directory da un bucket S3 specificato
 * e aggiorna poi l'interfaccia utente in base al risultato dell'operazione.
 */
public class DownloadFile {
    private final String bucketName = "test-dungeonunipd";
    private final String downloadDir = "FileDownload/";
    private UI uiManager;
    private static final String AWS_ACCESS_KEY_ID = "AKIAXYKJU6P4MEEC5C76";
    private static final String AWS_SECRET_ACCESS_KEY = "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";

    /**
     * Costruttore della classe DownloadFile.
     * Inizializza il download dei file dalla directory specificata nel bucket S3.
     *
     * @param directory La directory nel bucket S3 da cui scaricare i file.
     * @param ui L'oggetto UI per gestire l'interfaccia utente.
     */
    public DownloadFile(String directory, UI ui) {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY);
        Region region = Region.EU_WEST_3;
        try (S3Client s3 = S3Client.builder().region(region).credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build()) {
            uiManager = ui;
            ListObjectsV2Request listRequest = ListObjectsV2Request.builder()
                    .bucket(bucketName)
                    .prefix(directory + "/")
                    .build();

            ListObjectsV2Response listResponse = s3.listObjectsV2(listRequest);
            List<S3Object> contents = listResponse.contents();

            if (contents.isEmpty() || allDirectories(contents)) {
                System.out.println("Directory vuota o inesistente: " + directory);
                updateUILabel(directory, false);
                return;
            }

            boolean anyFileDownloaded = false;
            for (S3Object s3Object : contents) {
                String key = s3Object.key();
                if (!key.endsWith("/")) {  // Ignora le directory vuote
                    File destinationFile = new File(downloadDir + key);

                    if (!destinationFile.exists()) {
                        try {
                            Files.createDirectories(Paths.get(destinationFile.getParent()));

                            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                                    .bucket(bucketName)
                                    .key(key)
                                    .build();

                            s3.getObject(getObjectRequest, ResponseTransformer.toFile(destinationFile));
                            System.out.println("File scaricato: " + key);
                            anyFileDownloaded = true;
                        } catch (Exception e) {
                            System.err.println("Errore nel download del file " + key + ": " + e.getMessage());
                        }
                    } else {
                        System.out.println("File già esistente: " + key);
                    }
                }
            }

            if (anyFileDownloaded) {
                System.out.println("Directory scaricata con successo: " + directory);
            } else {
                System.out.println("Nessun nuovo file da scaricare nella directory: " + directory);
            }
            updateUILabel(directory, true);
        } catch (S3Exception e) {
            System.err.println("Errore S3: " + e.awsErrorDetails().errorMessage());
            updateUILabel(directory, false);
        } catch (Exception e) {
            System.err.println("Errore generico: " + e.getMessage());
            e.printStackTrace();
            updateUILabel(directory, false);
        }
    }
    /**
     * Aggiorna le label nella schermata Load in base al risultato del download.
     *
     * @param directory La directory di cui si sta aggiornando lo stato.
     * @param success Indica se il download è avvenuto con successo.
     */

    private void updateUILabel(String directory, boolean success) {
        String labelText = success ? "Save slot " : "-";
        switch (directory) {
            case "Filesave1" -> uiManager.loadLabel1.setText(success ? labelText + "1" : labelText);
            case "Filesave2" -> uiManager.loadLabel2.setText(success ? labelText + "2" : labelText);
            case "Filesave3" -> uiManager.loadLabel3.setText(success ? labelText + "3" : labelText);
            case "Filesave4" -> uiManager.loadLabel4.setText(success ? labelText + "4" : labelText);
            default -> throw new IllegalStateException("Unexpected directory: " + directory);
        }
    }
    /**
     * Verifica se tutti gli oggetti nella lista sono directory.
     *
     * @param contents Lista di oggetti S3 da controllare.
     * @return true se tutti gli oggetti sono directory, false altrimenti.
     */
    private boolean allDirectories(List<S3Object> contents) {
        return contents.stream().allMatch(obj -> obj.key().endsWith("/"));
    }
}