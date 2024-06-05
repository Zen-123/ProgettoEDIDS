package ManageFile;

import UI.UI;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.nio.file.Paths;

public class DownloadFile {
    private String bucketName = "test-dungeonunipd";
    private String downloadDir = "FileDownload/";
    UI uiManager = new UI();
    public DownloadFile(String fileName, UI ui) {
        Region region = Region.EU_WEST_3;
        S3Client s3 = S3Client.builder().region(region).build();
        uiManager = ui;

        GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(fileName).build();

        try {
            s3.getObject(getObjectRequest, Paths.get(downloadDir, fileName));
            switch (fileName) {
                case "Filesave 1.txt":
                    uiManager.loadLabel1.setText("Save slot 1");
                    break;
                case "Filesave 2.txt":
                    uiManager.loadLabel2.setText("Save slot 2");
                    break;

                case "Filesave 3.txt":
                    uiManager.loadLabel3.setText("Save slot 3");
                    break;
                case "Filesave 4.txt":
                    uiManager.loadLabel4.setText("Save slot 4");
                    break;
            }
            System.out.println("File scaricato!");
        } catch (Exception e) {
            switch (fileName) {
                case "Filesave 1.txt":
                    uiManager.loadLabel1.setText("-");
                    break;
                case "Filesave 2.txt":
                    uiManager.loadLabel2.setText("-");
                    break;

                case "Filesave 3.txt":
                    uiManager.loadLabel3.setText("-");
                    break;
                case "Filesave 4.txt":
                    uiManager.loadLabel4.setText("-");
                    break;
            }
        }finally {
            s3.close();

        }

    }
}