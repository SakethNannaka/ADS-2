import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import java.io.InputStreamReader;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.HashMap;

/**
 * Class PickTextFiles
 * @author : Saketh Nannaka
 * This class implements two methods parseSynsets and parseHypernms
 */
public class WordNet {
    /**
     * 
     */
    HashMap <Integer ,ArrayList<String>>SynsetsMap = new HashMap<>();
    HashMap <Integer ,ArrayList<Integer>>HypernymsMap = new HashMap<>();
    // String[] HypernymsMap ;
    
    /**
     * This methods reads the text files and puts the key value pairs into the hashMap
     * @param FileName
     * @throws IOException
     */
        public void parseSynsets() throws IOException{
            BufferedReader in = new BufferedReader(new FileReader("synsets.txt"));
        //Assuming you have a text file in D drive
        String strCurrentLine;
        
        while ((strCurrentLine = in.readLine()) != null) {
            String []temp = strCurrentLine.split(",");
            ArrayList<String> vArrayList = new ArrayList<>();
            int key = Integer.parseInt(temp[0]);
            String [] temp1 =temp[1].split(" ");
            for (int i = 0; i < temp1.length; i++) {
                vArrayList.add(temp1[i]);
            }
            vArrayList.add(temp[2]);
            SynsetsMap.put(key, vArrayList);
        }
        // System.out.println("synsets : "+SynsetsMap.size());
       
    }
    /**
     * This methods reads the text files and puts the key value pair into the hashMap
     * @param FileName1
     * @throws IOException
     */
   public void parseHypernyms() throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("hypernyms.txt"));
        String strCurrentLine;
        while ((strCurrentLine = in.readLine()) != null) {
            String []temp = strCurrentLine.split(",",2);
            int key = Integer.parseInt(temp[0]);
            if (temp.length>1) {
                String [] temp1 = temp[1].split(",");
                ArrayList<Integer> vArrayList = new ArrayList<>();
                for (int i = 0; i < temp1.length; i++) {
                    int Values =Integer.parseInt(temp1[i]);
                vArrayList.add(Values);
                }
            HypernymsMap.put(key,vArrayList);
        }
            
        }
    }

}


