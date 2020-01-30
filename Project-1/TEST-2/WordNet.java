/*
*Class : WordNet
*@author : Saketh Nannaka
*/
// import edu.princeton.cs.algs4.Digraph;
// import edu.princeton.cs.algs4.In;
import java.lang.IllegalArgumentException;
import java.io.IOException;
import java.util.ArrayList;
// import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 * Class : WordNet
 * @author Saketh Nannaka
 */
public class WordNet{
    private final Digraph G;
    private final HashMap <Integer ,ArrayList<String>>SynsetsMap = new HashMap<>();
    private final HashMap <String,Integer>nounIndex = new HashMap<>();
    // private final HashMap <Integer ,ArrayList<Integer>>HypernymsMap = new HashMap<>();
    // private ArrayList<synsets> synsetsLog  = new ArrayList<>();
    private final ArrayList<String> nouns = new ArrayList<>();
    private final SAP sap ;

    //Constructor takes the name of the two input files

    public WordNet(String F1 , String F2){
        parseSynsets(F1);
        // parseHypernyms(F2);
        G = new Digraph(buildDigraph(F2,SynsetsMap.size()));
        // loadDiGraph();
        sap = new SAP(G);
    }

     /**
     * This methods reads the text files and puts the key value pairs into the hashMap
     * @param FileName
     * @throws IOException
     */
        private void parseSynsets(String fileString){
        In in = new In(fileString);
        
        while (in.hasNextLine()) {
            String []temp = in.readLine().split(",");
            ArrayList<String> vArrayList = new ArrayList<>();
            int key = Integer.parseInt(temp[0]);
            String [] temp1 =temp[1].split(" ");
            for (int i = 0; i < temp1.length; i++) {
                vArrayList.add(temp1[i]);
                nounIndex.put(temp1[i],key);
                nouns.add(temp1[i]);
            }

            // vArrayList.add(temp[2]);
            SynsetsMap.put(key, vArrayList);
            // synsetsLog.add(new synsets(key,vArrayList,temp[2]));
        }
        
        }

        /**
     * This methods reads the text files and puts the key value pair into the hashMap
     * @param FileName1
     * @throws IOException
     */
//    private void parseHypernyms(String fStringS){
//             In in = new In(fStringS);
//         while (in.hasNextLine()) {
//             String []temp = in.readLine().split(",",2);
//             int key = Integer.parseInt(temp[0]);
//             if (temp.length>1) {
//                 String [] temp1 = temp[1].split(",");
//                 ArrayList<Integer> vArrayList = new ArrayList<>();
//                 for (int i = 0; i < temp1.length; i++) {
//                     int Values =Integer.parseInt(temp1[i]);
//                 vArrayList.add(Values);
//                 }
//             HypernymsMap.put(key,vArrayList);
//         }
            
//         }
        
//     }
    /**
     * This method loads the hypernyms into the diGraph.
     */
    // private void loadDiGraph(){
          
    //     for (int v = 0;v<HypernymsMap.size();v++ ) {

    //             for(int w : HypernymsMap.get(v))
    //         {
    //             G.addEdge(v,w);
    //         }

    //     }
    // }

    //returns all WordNet nouns
    public Iterable<String> nouns(){
        return this.nouns;
    }


    ///is the word a wordNet Noun
    public boolean isNoun(String word){
        return nounIndex.containsKey(word);

    }

    // Distance b/w nounA and nounB
    public int distance(String nounA, String nounB) {
        int a = -1;
        int b = -1;

        if (isNoun(nounA)&&isNoun(nounB)) {
        a=nounIndex.get(nounA);
        b=nounIndex.get(nounB);
        }

        // for (synsets s : synsetsLog) {
        //     if (s.ContainsNoun(nounA)) {
        //            a = s.n;
        //     }
        // }
        // for (synsets s : synsetsLog) {
        //     if (s.ContainsNoun(nounB)) {
        //             b = s.n;
        //             }
        //         }
            
            return sap.length(a,b);
        }

    /**
     * This method returns the Shortest ancestor name b/w to wordnet nouns.
     * @param nounA
     * @param nounB
     * @return
     */
    public String sap(String nounA ,String nounB){
        int a = -1;
        int b = -1;
        if (isNoun(nounA)&&isNoun(nounB)) {
            a=nounIndex.get(nounA);
            b=nounIndex.get(nounB);
            }
        int ancestor = sap.ancestor(a,b);
        String temp = "";
        for(String string :SynsetsMap.get(ancestor)){
            temp = temp+" "+string;
        }
        return temp;

    }
    private Digraph buildDigraph(String file, int size) {

        In input = new In(file);
        Digraph g = new Digraph(size);
        while (input.hasNextLine()) {
            String[] split = input.readLine().split(",");
            int id = Integer.parseInt(split[0]);
            for (int i = 1; i < split.length; i++) {
                g.addEdge(id, Integer.parseInt(split[i]));
            }
        }
        return g;
    }


    //do testing of this class
    public static void main(String[] args) throws IOException{
        WordNet wordnet = new WordNet("synsets.txt","hypernyms.txt");
        System.out.println(wordnet.distance("1530s","1900s"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(wordnet.SynsetsMap.size());
    }
    //     System.out.println(wordnet.sap("1530s","1900s"));
    //     System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    //     System.out.println(wordnet.sap("24/7","9/11"));
    //     System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    //     System.out.println(wordnet.sap("1860s","1900s"));
    //     System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    //     System.out.println(wordnet.sap("blastocyst","blastodermic_vessicle"));
    //     System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    //     System.out.println(wordnet.distance("1530s","decennary"));
    //     System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    //     System.out.println(wordnet.sap("apple_juice","orange_juice"));
    //     System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    //     // System.out.println(wordnet.nouns);
    //     // System.out.println(wordnet.G.toString());
    //     // System.out.println(wordnet.synsetsLog.get(0));

    
    // }
}