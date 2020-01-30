/**
 * Class :SAP
 * @author Saketh Nannaka
 * This class is to implement the methods which find shortest commmon ancestor and SAP length
 */
import java.io.IOException;
import java.util.ArrayList;

public class SAP {

	//Fields
	Digraph di;      //Variable di of type Digraph
	boolean [] b1 ;
	boolean [] b2 ;
	int minIndex = -1;
	int [] sapVertices = new int[2] ;
	
	//Methods

	/**
	 * Constructor :SAP
	 */
	public SAP(Digraph G){
		di = G;
	}

	/**
	 * This method returns the SAP length b/w @param v and @param w
	 * @param v
	 * @param w
	 * @return
	 */
	public int length(int v , int w){
		int minLength = Integer.MAX_VALUE;
		BreadthFirstPaths v1  = new BreadthFirstPaths(di,v);
		BreadthFirstPaths v2  = new BreadthFirstPaths(di,w);
		b1 = v1.marked;
		b2 = v2.marked;
		for(int i = 0 ; i<di.vertices ; i++){
				if(b1[i]==true && b2[i]==true){
					int length = v1.distTo(i)+v2.distTo(i);
				if(length < minLength){
					minLength = length;
					// System.out.println("HI : ------"+i);
						minIndex=i;
				}
			}
		}
		// System.out.println(minIndex+"----------------");
		return minLength;
	}
	 /**
	  * 
	  * @param v
	  * @param w
	  * @return
	  */
	public int ancestor(int v , int w){
		int a = length(v,w);
	 return this.minIndex ;
	}
	/**
	 * 
	 * @param v
	 * @param w
	 * @return
	 */
	public int length(Iterable<Integer> v,Iterable<Integer> w){
		int minLength = Integer.MAX_VALUE;
		for (int i : v) {
			for (int j : w) {
				int length = length(i,j);
				if (minLength > length) {
					minLength = length;
					sapVertices[0]=i;
					sapVertices[1]=j;
				}
				
			}
			return minLength;

		}
		 
		return 0;
	}
/**
 * 
 * @param v
 * @param w
 * @return
 */
	public int ancestor(Iterable<Integer> v,Iterable<Integer> w){
		int a = length(v,w);
		return ancestor(sapVertices[0],sapVertices[1]);
	}
	  public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }



	public static void main(String[] args) throws IOException{
		WordNet wordnet = new WordNet();
        wordnet.parseHypernyms();
        Digraph  di       = new Digraph(wordnet.HypernymsMap.size());
        for (int v = 0;v<wordnet.HypernymsMap.size();v++ ) {
        	try{
        		for(int w : wordnet.HypernymsMap.get(v))
        	{
				di.addEdge(v,w);
        	}

        	}catch(Exception NullPointerException){
        	}
        }
        // System.out.println(di.toString());
		SAP sap = new SAP(di);
		System.out.println("1 : "+sap.length(3,1000));
		System.out.println("2 : "+sap.ancestor(3,123));
		ArrayList<Integer> v = new ArrayList<>();
		ArrayList<Integer> w = new ArrayList<>();
		v.add(1);
		v.add(2);
		v.add(3);
		v.add(4);

		w.add(987);
		w.add(978);
		w.add(879);
		w.add(9);




		System.out.println("3 : "+sap.length(v,w));
		System.out.println("4 : "+sap.ancestor(v,w));

	}
}