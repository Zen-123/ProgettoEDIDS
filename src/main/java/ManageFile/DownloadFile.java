package ManageFile;
import java.nio.file.Paths;

import UI.UI;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

/**
 * Classe che gestisce il download di file dal bucket di aws S3
 */
public class DownloadFile {
    //variabili private della classe
    private final String bucketName = "test-dungeonunipd";
    private final String downloadDir = "FileDownload/";
    private UI uiManager;

    /**
     *Costruttore parametrizzato della classe DownloadFile
     * @param fileName  il nome del file da scaricare dal bucket di aws
     * @param ui  oggetto della classe UI che permette di gestire alcune label grafiche
     */
    public DownloadFile(String directory,String name, UI ui) {
        //Imposta la regione del bucket
        Region region = Region.EU_WEST_3;
        try (S3Client s3 = S3Client.builder().region(region).build()) {
            uiManager = ui;

            //Permette di scaricare degli oggetti tra aws S3
            GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(directory+"/"+name).build();

            //Service client per accedere ad aws s3
            s3.getObject(getObjectRequest, Paths.get(directory+"/"+name));

            /*
            Switch usato per la gestione dei file scaricati da aws s3.
            Il limite di salvataggi possibili è impostato a 4.
            Si utilizza oggetto uiManager della classe UI per scrivere nella label corrispettiva lo slot occupato.
             */
            switch (directory) {
                case "Filesave1":
                    uiManager.loadLabel1.setText("Save slot 1");
                    break;
                case "Filesave2":
                    uiManager.loadLabel2.setText("Save slot 2");
                    break;
                case "Filesave3":
                    uiManager.loadLabel3.setText("Save slot 3");
                    break;
                case "Filesave4":
                    uiManager.loadLabel4.setText("Save slot 4");
                    break;
            }
            System.out.println("File scaricato!");
        } catch (Exception e) {  //Gestione delle eccezioni,ad esempio la mancanza del file che si vuole scaricare
            switch (directory) {
                case "Filesave1":
                    uiManager.loadLabel1.setText("-");
                    break;
                case "Filesave2":
                    uiManager.loadLabel2.setText("-");
                    break;
                case "Filesave3":
                    uiManager.loadLabel3.setText("-");
                    break;
                case "Filesave4":
                    uiManager.loadLabel4.setText("-");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: ");
            }
        }

    }
}