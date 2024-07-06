package Board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Classe che gestisce la lettura e la scrittura dei file per le stanze del gioco.
 * I file vengono salvati nelle cartelle Stanze e Stanzeold.
 */
public class Readfile {

    private static BufferedReader reader;
    FileWriter writer;

    /**
     * Legge il contenuto di un file e lo restituisce come lista di stringhe.
     * @param filename Il nome del file da leggere.
     * @return Una lista di stringhe contenente le righe del file.
     */
    public ArrayList<String> fileToRead(String filename){
        
        String str="";
        ArrayList<String> strings = new ArrayList<String>();
        try{
            if(filename.length() > 0){      
            FileReader fr = new FileReader(filename);
            reader = new BufferedReader(fr);
            if(reader.ready()){
                while(str != null){
                    str = reader.readLine();
                    if(str != null){
                        strings.add(str);                
                    }
                }     
            }
        }
        }catch(Exception FileNotFoundException){
            System.out.println("\n Oooops qualcosa è andato storto con il file!\n");
        }
        return strings;
    }
    /**
     * Scrive il contenuto di una stanza su un file.
     * @param ss Lista di stringhe contenente informazioni aggiuntive sulla stanza.
     * @param stanza Matrice di celle rappresentante la stanza.
     * @param filename Il nome del file su cui scrivere.
     */
    public void fileToWrite(ArrayList<String> ss, ArrayList<ArrayList<Cell>> stanza, String filename){
        try{
            writer = new FileWriter(filename);
            for (int i = 0; i <= stanza.size()-1; i++) {
                for (int j = 0; j < stanza.get(0).size(); j++) {
                    if(i == (stanza.size()-1))
                        writer.write(ss.get(i).charAt(j));
                    else if(stanza.get(i).get(j).getSymbol() == 'A'){
                        writer.write('.');
                    }else{
                        writer.write(stanza.get(i).get(j).getSymbol());
                    }
                }
                writer.write("\n");
            }
            writer.close();
        }catch(Exception e){
            e.getStackTrace();
        }
    }  /**
     * Scrive il contenuto di una stanza su un file per AWS.
     * Simile a fileToWrite, ma gestisce diversamente il simbolo 'A'.
     * @param ss Lista di stringhe contenente informazioni aggiuntive sulla stanza.
     * @param stanza Matrice di celle rappresentante la stanza.
     * @param filename Il nome del file su cui scrivere.
     */
    public void fileToWriteAWS(ArrayList<String> ss, ArrayList<ArrayList<Cell>> stanza, String filename){
        try{
            writer = new FileWriter(filename);
            for (int i = 0; i <= stanza.size()-1; i++) {
                for (int j = 0; j < stanza.get(0).size(); j++) {
                    if(i == (stanza.size()-1))
                        writer.write(ss.get(i).charAt(j));
                    else if(stanza.get(i).get(j).getSymbol() == 'A'){
                        writer.write('A');
                    }else{
                        writer.write(stanza.get(i).get(j).getSymbol());
                    }
                }
                writer.write("\n");
            }
            writer.close();
        }catch(Exception e){
            e.getStackTrace();
        }
    }
    /**
     * Resetta la directory Stanzeold eliminando tutti i file al suo interno.
     * Questo metodo viene chiamato all'avvio del gioco per pulire i dati precedenti.
     * @throws IllegalArgumentException se viene trovata una directory illegale.
     */    public void ResetDirectory(){

        File path = new File("src/main/java/Board/Stanzeold/");
        if (!path.exists()){
            File folder = new File("src/main/java/Board/Stanzeold");
            folder.mkdir();
        }
        File []allfiles = path.listFiles();
        if(allfiles.length > 0){
            for(int i = 0; i < allfiles.length; i++) {
                if(allfiles[i].isDirectory()){
                    throw new IllegalArgumentException("E presente una directory illegale: ");
                }else{
                    allfiles[i].delete();
                }    
            }
        }
    }
}
