package ManageFile;


import UI.UI;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import javax.swing.*;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

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
        String fileName = "Filesave1";
        DownloadFile downloadFile = new DownloadFile(fileName, ui);

        // verifica di corrette creazione di oggetto di tipo DownloadFile
        assertNotNull(downloadFile);
    }

    @Test
    //test costruttore parametrizzato solo con nome del file, e nome del file illecito
    void testConstructorWithInvalidFileName() {
        String fileName = "InvalidFile.txt";

        // ci aspettiamo che venga lanciata una eccezione
        assertThrows(IllegalStateException.class, () -> new DownloadFile(fileName, ui));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Filesave1", "Filesave2", "Filesave3", "Filesave4"})
    //test con costruttore parametrizzato e tutti i nomi del file leciti
    void testConstructorWithAllValidFileNames(String fileName) {
        DownloadFile downloadFile = new DownloadFile(fileName, ui);
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