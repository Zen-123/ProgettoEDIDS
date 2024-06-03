package UI;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class uploadFile {

    private String bucketName = "test-dungeonunipd";
    private Regions region = Regions.EU_WEST_3;

    public uploadFile(String filename) throws IOException {
        InputStream object =  new FileInputStream("FileLoad/"+filename);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(region).build();

        ObjectMetadata metadata  = new ObjectMetadata();
        metadata.setContentLength(object.available());
        metadata.setContentType("file.txt");
        try{
            PutObjectRequest request = new PutObjectRequest(bucketName, filename, object, metadata);
            s3Client.putObject(request);
            System.out.println("Salvataggio avvenuto con successo!");
        }catch (AmazonS3Exception e){
            e.printStackTrace();
        } finally {
            object.close();
        }
    }


}
