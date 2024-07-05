package ManageFile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import software.amazon.awssdk.regions.Region;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class UploadFileTest {

    @TempDir
    Path tempDir;

    private Path fileLoadDir;

    @BeforeEach
    //creazione di oggetti per gestire la path del file da caricare nel bucket aws
    void setUp() throws IOException {
        fileLoadDir = tempDir.resolve("FileLoad");
        Files.createDirectories(fileLoadDir);
    }

    @Test
    //test del costruttore con nome del file
    void testUploadFileConstructor() throws IOException {
        //file .txt usato per testing
        String directory = "testDirUpload";
        String fileName = "testFileUpload.txt";
        Path filePath = fileLoadDir.resolve(fileName);
        Files.write(filePath, "Test content".getBytes());

        uploadFile uploader = new uploadFile(directory,fileName);
        //verifica che oggetto sia creato correttamente
        assertNotNull(uploader);
    }

    @Test
    //test per verificare lancio di eccezione in caso di caricamento di file con nome illecito
    void testUploadFileIOException() {
        String nonExistentFile = "nonexistent.txt";
        String nonExistentDir = "nonexistentdir";
        // ci aspettiamo una eccezione
        assertThrows(IOException.class, () -> new uploadFile(nonExistentDir,nonExistentFile));
    }

    @Test
    //test di caricamento del file nel bucket aws
    void testBucketNameAndRegion() throws Exception {
        String directory = "testDirUpload";
        String fileName = "testFileUpload.txt";
        Path filePath = fileLoadDir.resolve(fileName);
        Files.write(filePath, "Test content".getBytes());

        uploadFile uploader = new uploadFile(directory,fileName);

        Field bucketNameField = uploadFile.class.getDeclaredField("bucketName");
        bucketNameField.setAccessible(true);
        //verifica se file caricato nel giusto bucket aws
        assertEquals("test-dungeonunipd", bucketNameField.get(uploader));

        Field regionField = uploadFile.class.getDeclaredField("region");
        regionField.setAccessible(true);
        assertEquals(Region.EU_WEST_3, regionField.get(uploader));
    }


}