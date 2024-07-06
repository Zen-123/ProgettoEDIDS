package ManageFile;


import java.lang.reflect.Field;

import javax.swing.JLabel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import UI.UI;

class DownloadFileTest {

    private UI ui;

    @BeforeEach
    //creazione di oggetti necessari per testare la classe in modo da evitare lancio di eccezioni
    void setUp() {
        ui = new UI();

        //setting delle label per evitare lancio NullPointerException
        ui.loadLabel1 = new JLabel();
        ui.loadLabel2 = new JLabel();
        ui.loadLabel3 = new JLabel();
        ui.loadLabel4 = new JLabel();


    }

    @Test
    //test costruttore parametrizzato, solo nome del file
    void testConstructorWithValidFileName() {
        String dirname = "Filesave1";
        String fileName = "Filesave1.txt";
        DownloadFile downloadFile = new DownloadFile(dirname, ui);

        // verifica di corrette creazione di oggetto di tipo DownloadFile
        assertNotNull(downloadFile);
    }

    @Test
    //test costruttore parametrizzato solo con nome del file, e nome del file illecito
    void testConstructorWithInvalidFileName() {
        String fileName = "InvalidFile.txt";
        String dirname = "InvaliddirFilesave1";
        // ci aspettiamo che venga lanciata una eccezione
        assertThrows(IllegalStateException.class, () -> new DownloadFile(dirname, ui));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Filesave1", "Filesave2", "Filesave3", "Filesave4"})
    //test con costruttore parametrizzato e tutti i nomi del file leciti
    void testConstructorWithAllValidFileNames(String dirname) {
        DownloadFile downloadFile = new DownloadFile(dirname, ui);
        // verifica della creazione di ogetto senza lancio di eccezioni
        assertNotNull(downloadFile);
    }

    @Test
    //test per verificare se file scaricato dal bucket di aws viene messo nella giusta directory
    void testBucketNameAndDownloadDir() throws NoSuchFieldException, IllegalAccessException {
        DownloadFile downloadFile = new DownloadFile("Filesave1", ui);
        Field bucketNameField = DownloadFile.class.getDeclaredField("bucketName");
        bucketNameField.setAccessible(true);
        //nome del bucket aws
        assertEquals("test-dungeonunipd", bucketNameField.get(downloadFile));

        Field downloadDirField = DownloadFile.class.getDeclaredField("downloadDir");
        downloadDirField.setAccessible(true);
        //verifica che file venga scaricato nella giusta directory
        assertEquals("FileDownload/", downloadDirField.get(downloadFile));
    }
}