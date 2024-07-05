package ManageFile;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import UI.UI;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

public class DownloadFile {
    private final String bucketName = "test-dungeonunipd";
    private final String downloadDir = "FileDownload/";
    private UI uiManager;

    public DownloadFile(String directory, UI ui) {
        Region region = Region.EU_WEST_3;
        try (S3Client s3 = S3Client.builder().region(region).build()) {
            uiManager = ui;

            ListObjectsV2Request listRequest = ListObjectsV2Request.builder()
                    .bucket(bucketName)
                    .prefix(directory + "/")
                    .build();

            ListObjectsV2Response listResponse = s3.listObjectsV2(listRequest);
            List<S3Object> contents = listResponse.contents();

            if (contents.isEmpty()) {
                System.out.println("Directory vuota o inesistente: " + directory);
                updateUILabel(directory, false);
                return;
            }

            for (S3Object s3Object : contents) {
                String key = s3Object.key();
                if (!key.endsWith("/")) {  // Ignora le directory vuote
                    File destinationFile = new File(downloadDir + key);

                    try {
                        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                                .bucket(bucketName)
                                .key(key)
                                .build();

                        s3.getObject(getObjectRequest, ResponseTransformer.toFile(destinationFile));
                        System.out.println("File scaricato: " + key);
                    } catch (S3Exception e) {
                        System.err.println("Errore nel download del file " + key);
                    }
                }
            }

            updateUILabel(directory, true);
            System.out.println("Directory scaricata con successo: " + directory);
        } catch (S3Exception e) {
            System.err.println("Errore S3: " + e.awsErrorDetails().errorMessage());
            updateUILabel(directory, true);
        } catch (Exception e) {
            System.err.println("Errore generico: " + e.getMessage());
            e.printStackTrace();
            updateUILabel(directory, true);
        }
    }

    private void updateUILabel(String directory, boolean success) {
        String labelText = success ? "Save slot " : "-";
        switch (directory) {
            case "Filesave1" -> uiManager.loadLabel1.setText(labelText + "1");
            case "Filesave2" -> uiManager.loadLabel2.setText(labelText + "2");
            case "Filesave3" -> uiManager.loadLabel3.setText(labelText + "3");
            case "Filesave4" -> uiManager.loadLabel4.setText(labelText + "4");
            default -> throw new IllegalStateException("Unexpected directory: " + directory);
        }
    }
}