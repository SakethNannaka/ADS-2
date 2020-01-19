// import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Class : DiGraph
 * @author : Saketh Nannaka
 * 
 */
public class Digraph{
    final int vertices;
    final ArrayList<Integer>[] adj ;

    /**
     * 
     */
    public Digraph(int vertices){
        this.vertices = vertices;
        adj = (ArrayList<Integer>[]) new ArrayList[vertices];
        for(int i = 0 ; i < vertices ; i++){
            adj[i] = new ArrayList<Integer>();
        }
    }
    public void addEdge(int  v , int w)
    {
        adj[v].add(w);
    }

    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }
    public String toString(){
    	String temp = "";
    	for(int i = 0 ; i < adj.length ; i++){
    		temp = temp + i +" : "+adj[i]+"\n";
    	}
    	return temp;
    }


    // public static void main(String[] args) throws IOException{
    //     WordNet wordnet = new WordNet();
    //     // wordnet.parseHypernyms();
    //     Digraph  di       = new Digraph(wordnet.HypernymsMap.size());
    //     for (int v = 0;v<wordnet.HypernymsMap.size();v++ ) {
    //     	try{
    //     		for(int w : wordnet.HypernymsMap.get(v))
    //     	{
				// di.addEdge(v,w);
    //     	}

    //     	}catch(Exception NullPointerException){
    //     	}
    //     }
    //     System.out.println(di.toString());		
    // }
}
