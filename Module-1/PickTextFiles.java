import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PickTextFiles {
    public void parseSynsets(String FileName) throws IOException{
            BufferedReader in = new BufferedReader(new FileReader(FileName));
        //Assuming you have a text file in D drive
        String strCurrentLine;
        HashMap <Integer ,ArrayList<String>>map = new HashMap<>();
        while ((strCurrentLine = in.readLine()) != null) {
            String []temp = strCurrentLine.split(",");
            ArrayList<String> vArrayList = new ArrayList<>();
    
            int key = Integer.parseInt(temp[0]);
            vArrayList.add(temp[1]);
            vArrayList.add(temp[2]);
            map.put(key, vArrayList);
        }
        System.out.println("synsets : "+map.get(1));
       
    }
   public void parseHypernyms(String FileName1) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("hypernyms.txt"));
        String strCurrentLine;
        HashMap <Integer ,ArrayList<Integer>>map = new HashMap<>();
    while ((strCurrentLine = in.readLine()) != null) {
        String []temp = strCurrentLine.split(",",2);
        int key = Integer.parseInt(temp[0]);
        if (temp.length>1) {
            String [] temp1 = temp[1].split(",");
            ArrayList<Integer> vArrayList = new ArrayList<>();
            for (int i = 0; i < temp1.length; i++) {
                vArrayList.add(Integer.parseInt(temp1[i]));
            }
            map.put(key, vArrayList);
        }
    }
    System.out.println("hypernyms :"+map);
}

    public static void main(String[] args) throws IOException {
            PickTextFiles obj = new PickTextFiles();
            obj.parseSynsets("synsets.txt");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            obj.parseHypernyms("hypernyms.txt");
}
}