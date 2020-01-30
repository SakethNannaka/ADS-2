/**
 * Class :SAP
 * @author Saketh Nannaka
 * This class is to implement the methods which find shortest commmon ancestor and SAP length
 */
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
// import java.io.IOException;
// import java.util.ArrayList;
import java.lang.IllegalArgumentException;




public class SAP {

	private final Digraph graph;
	
	/**
	 * constructor takes a digraph (not necessarily a DAG)
	 * 
	 * @param G
	 */
	public SAP(Digraph G) {
		assert G != null;
		this.graph = new Digraph(G);
	}

	/**
	 * length of shortest ancestral path between v and w; -1 if no such path
	 * 
	 * @param v
	 * @param w	
	 * @return
	 */
	public int length(int v, int w) {
		if (v < 0 || v >= graph.V()) throw new IndexOutOfBoundsException("index v out of bounds");
		if (w < 0 || w >= graph.V()) throw new IndexOutOfBoundsException("index w out of bounds");
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
		
		int minDist = -1;
		for (int i = 0; i < graph.V(); i++) {
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
				int dist = bfsV.distTo(i) + bfsW.distTo(i);
				if (minDist == -1 || dist < minDist) {
					minDist = dist;
				}
			}
		}
		return minDist;
	}

	/**
	 * a common ancestor of v and w that participates in a shortest ancestral
	 * path; -1 if no such path
	 * 
	 * @param v
	 * @param w
	 * @return
	 */
	public int ancestor(int v, int w) {
		if (v < 0 || v >= graph.V()) throw new IndexOutOfBoundsException("index v out of bounds");
		if (w < 0 || w >= graph.V()) throw new IndexOutOfBoundsException("index w out of bounds");
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
		
		int minDist = Integer.MAX_VALUE;
		int argMinDist = -1;
		for (int i = 0; i < graph.V(); i++) {
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
				int dist = bfsV.distTo(i) + bfsW.distTo(i);
				if (dist < minDist) {
					minDist = dist;
					argMinDist = i;
				}
			}
		}

		return argMinDist;
	}


	/**
	 * length of shortest ancestral path between any vertex in v and any vertex
	 * in w; -1 if no such path
	 * 
	 * @param v a vertex index
	 * @param w a vertex index
	 * @return
	 */
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		checkIndex(v);
		checkIndex(w);
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);

		int minDist = -1;
		for (int i = 0; i < graph.V(); i++) {
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
				int dist = bfsV.distTo(i) + bfsW.distTo(i);
				if (minDist == -1 || dist < minDist) {
					minDist = dist;
				}
			}
		}
		return minDist;
	}

	private void checkIndex(Iterable<Integer> v) {
		if (v == null) {
			throw new IllegalArgumentException("v cannot be null");
		}
		for (int vv : v) {
			if (vv < 0 || vv >= graph.V()) {
				throw new IllegalArgumentException("out of bound index " + vv);
			}
		}

	}

	/**
	 * a common ancestor that participates in shortest ancestral path; -1 if no
	 * such path
	 * 
	 * @param v
	 * @param w
	 * @return
	 */
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		checkIndex(v);
		checkIndex(w);
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
		
		int minDist = Integer.MAX_VALUE;
		int argMinDist = -1;
		for (int i = 0; i < graph.V(); i++) {
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
				int dist = bfsV.distTo(i) + bfsW.distTo(i);
				if (dist < minDist) {
					minDist = dist;
					argMinDist = i;
				}
			}
		}

		return argMinDist;

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
