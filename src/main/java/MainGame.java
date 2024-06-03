import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import UI.UI;
import UI.visibilityManager;
import UI.choiceHandler;

public class MainGame {
    UI ui = new UI();
    visibilityManager manager = new visibilityManager(ui);

    public static void main(String[] args) throws Exception {
        new MainGame();
        //upload();
    }

    public MainGame(){
        ui.createUI();
        manager.showMenuScreen();
    }


}

