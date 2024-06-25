package Board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

//gestisce lettura e scrittura del file e li salva nella cartella Stanze & Stanzeold
public class Readfile {

    private static BufferedReader reader;
    FileWriter writer;
    public ArrayList<String> fileToRead(String filename){
        
        String str="";
        ArrayList<String> strings = new ArrayList <String>();
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
    public void fileToWrite(ArrayList<ArrayList<Cell>> stanza, String filename){
        try{
            writer = new FileWriter(filename);
            for (int i = 0; i <= stanza.size()-1; i++) {
                for (int j = 0; j < stanza.get(0).size(); j++) {
                    if(i == (stanza.size()-1))
                        writer.write(reference.currentStanza.ss.get(i).charAt(j));
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
    }
}
