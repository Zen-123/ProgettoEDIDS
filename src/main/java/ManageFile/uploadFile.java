package ManageFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * Classe utilizzata per la gestione del caricamento di file di salvataggio sul bucket di aws s3
 */
public class uploadFile {
    //variabili private della classe
    private final String bucketName = "test-dungeonunipd";
    private final Regions region = Regions.EU_WEST_3;

    /**
     * Costruttore della classe
     * Il caricamento sul bucket aws dei file di salvataggio viene gestito con una cartella di supporto FileLoad in cui
     * sono contenuti i dati in locale prima di essere caricati.
     * @param filename nome del file da scaricare
     * @throws IOException
     */
    public uploadFile(String filename) throws IOException {
        //oggetto che prende i dati contenuti da un file nel file system
        System.out.print(filename);
        InputStream file =  new FileInputStream("FileLoad/"+filename);
        //Interfaccia che permette di accedere ai web service di aws s3
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(region).build();

        //Rappresenta i metadata che sono salvati con aws s3
        ObjectMetadata metadata  = new ObjectMetadata();
        metadata.setContentLength(file.available());
        metadata.setContentType("file.txt");

        /*
        Gestione delle eccezioni che possono essere lanciate da s3Client
         */
        try{
            //Carica il file fileName.txt nel bucket aws
            PutObjectRequest request = new PutObjectRequest(bucketName, filename, file, metadata);
            s3Client.putObject(request);

            System.out.println("Salvataggio avvenuto con successo!");
        }catch (AmazonS3Exception e){
            e.printStackTrace();
        } finally {
            file.close();
        }
    }


}