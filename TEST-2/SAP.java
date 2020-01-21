/**
 * Class :SAP
 * @author Saketh Nannaka
 * This class is to implement the methods which find shortest commmon ancestor and SAP length
//  */
// import edu.princeton.cs.algs4.Digraph;
// import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
// import java.io.IOException;
// import java.util.ArrayList;
import java.lang.IllegalArgumentException;




public class SAP {

	//Fields
	private Digraph di;      //Variable di of type Digraph
	// private boolean [] b1 ;
	// private boolean [] b2 ;
	private int minIndex = -1;
	private int [] sapVertices = new int[2] ;
	
	//Methods

	/**
	 * Constructor :SAP
	 */


	public SAP(Digraph G){
		if (G==null) {
			throw new IllegalArgumentException();
		}
		di = G;
	}

	// public SAP(edu.princeton.cs.algs4.Digraph G){
	// 	di = G;
	// }

	/**
	 * This method returns the SAP length b/w @param v and @param w
	 * @param v
	 * @param w
	 * @return
	 */
	public int length(int v , int w){
		if (v < 0 || v > di.V() || w < 0 || w > di.V())
            throw new IllegalArgumentException();

		int minLength = Integer.MAX_VALUE;
		BreadthFirstDirectedPaths v1  = new BreadthFirstDirectedPaths(di,v);
		BreadthFirstDirectedPaths v2  = new BreadthFirstDirectedPaths(di,w);
		// b1 = v1.marked;
		// b2 = v2.marked;
		for(int i = 0 ; i<di.V() ; i++){
				if(v1.hasPathTo(i)==true && v2.hasPathTo(i)==true){
					int length = v1.distTo(i)+v2.distTo(i);
				if(length < minLength){
					minLength = length;
					// System.out.println("HI : ------"+i);
						// minIndex=i;
				}
			}
		}
		// System.out.println(minIndex+"----------------");
		if (minLength == Integer.MAX_VALUE) {
			return -1;
		 }else{
			 return minLength;
		 }
	}
	 /**
	  * 
	  * @param v
	  * @param w
	  * @return
	  */
	public int ancestor(int v , int w){
		if (v < 0 || v > di.V() || w < 0 || w > di.V())
            throw new IllegalArgumentException();

		int minLength = Integer.MAX_VALUE;
		int ans = -1;
		BreadthFirstDirectedPaths v1  = new BreadthFirstDirectedPaths(di,v);
		BreadthFirstDirectedPaths v2  = new BreadthFirstDirectedPaths(di,w);
		// b1 = v1.marked;
		// b2 = v2.marked;
		for(int i = 0 ; i<di.V() ; i++){
				if(v1.hasPathTo(i)==true && v2.hasPathTo(i)==true){
					int length = v1.distTo(i)+v2.distTo(i);
					if(length < minLength){
						minLength = length;
					// System.out.println("HI : ------"+i);
						ans = i;
				}
			}
		}
		// System.out.println(minIndex+"----------------");
		if (minLength == Integer.MAX_VALUE) {
			return -1;
		 }else{
			 return ans;
		 }
	}
	/**
	 * 
	 * @param v
	 * @param w
	 * @return
	 */
	public int length(Iterable<Integer> v,Iterable<Integer> w){
		int minLength = Integer.MAX_VALUE;
		checkIndex(v);
		checkIndex(w);
		for (int i : v) {
			for (int j : w) {
				if (i < 0 || i > di.V() || j < 0 || j > di.V())
           			 throw new IllegalArgumentException();

				int length = length(i,j);
				if (minLength > length) {
					minLength = length;
					sapVertices[0]=i;
					sapVertices[1]=j;
				}
				
			}
		}
		 if (minLength == Integer.MAX_VALUE) {
			return -1;
		 }else{
			 return minLength;
		 }
	}
/**
 * 
 * @param v
 * @param w
 * @return
 */
	public int ancestor(Iterable<Integer> v,Iterable<Integer> w){
		int minLength = Integer.MAX_VALUE;
		checkIndex(v);
		checkIndex(w);
		int ans = -1;
		for (int i : v) {
			for (int j : w) {
				if (i < 0 || i > di.V() || j < 0 || j > di.V())
           			 throw new IllegalArgumentException();

				int length = length(i,j);
				if (minLength > length) {
					minLength = length;
					sapVertices[0]=i;
					sapVertices[1]=j;
				}
			}
		}
		ans = ancestor(sapVertices[0],sapVertices[1]);
		 if (minLength == Integer.MAX_VALUE) {
			return -1;
		 }else{
			 return ans;
		 }
	
		}
	
		private void checkIndex(Iterable<Integer> v) {
		if (v == null) {
			throw new IllegalArgumentException("v cannot be null");
		}
		for (int vv : v) {
			if (vv < 0 || vv >= di.V()) {
				throw new IllegalArgumentException("out of bound index " + vv);
			}
		}
	}
	
}
	




	// public static void main(String[] args) throws IOException{
	// 	WordNet wordnet = new WordNet();
 //        wordnet.parseHypernyms();
 //        Digraph  di       = new Digraph(wordnet.HypernymsMap.size());
 //        for (int v = 0;v<wordnet.HypernymsMap.size();v++ ) {
 //        	try{
 //        		for(int w : wordnet.HypernymsMap.get(v))
 //        	{
	// 			di.addEdge(v,w);
 //        	}

 //        	}catch(Exception NullPointerException){
 //        	}
 //        }
 //        // System.out.println(di.toString());
	// 	SAP sap = new SAP(di);
	// 	System.out.println("1 : "+sap.length(3,1000));
	// 	System.out.println("2 : "+sap.ancestor(3,123));
	// 	ArrayList<Integer> v = new ArrayList<>();
	// 	ArrayList<Integer> w = new ArrayList<>();
	// 	v.add(1);
	// 	v.add(2);
	// 	v.add(3);
	// 	v.add(4);

	// 	w.add(987);
	// 	w.add(978);
	// 	w.add(879);
	// 	w.add(9);




	// 	System.out.println("3 : "+sap.length(v,w));
	// 	System.out.println("4 : "+sap.ancestor(v,w));

	// }
