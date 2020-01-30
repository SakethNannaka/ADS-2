// import edu.princeton.cs.algs4.*;
// import java.io.IOException;
public class Outcast {

    private WordNet wordnet;
	
    public Outcast(WordNet wordnet) {
    	this.wordnet = wordnet;
    }
    
    public String outcast(String[] nouns) {
        
    	String outcast = "";
    	int max = 0;
    	
    	for(String temp : nouns) {
    		int distance = 0;
    		for(String temp2 : nouns) {
    			if(temp != temp2) {
    				distance += this.wordnet.distance(temp, temp2);
    			}
    		}
    		
    		if(distance > max) {
    			max = distance;
    			outcast = temp;
    		}
    	}
  
        return outcast;
    }
    
    // public static void main(String[] args) {
		
	// 	try {
	// 	Outcast out = new Outcast(new WordNet("synsets.txt", "hypernyms.txt"));
    // 	String[] nouns = new String[]{"water","soda","milk","orange_juice","apple_juice","tea","coffee","bed"};
    // 	System.out.println(out.outcast(nouns));
    //     System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    //     String[] nouns1 = new String[]{"cat","cheetah","dog","wolf","albatross","horse","zebra","lemur","orangutan","chimpanzee"};
    //     System.out.println(out.outcast(nouns1));

	// 	} catch (Exception e) {
	// 		//TODO: handle exception
	// 	}
    	
    // }
}
