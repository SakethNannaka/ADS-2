class T implements Comparable<T>{
    int id;
    int indegree;
    public T(int i, int indegree2) {
        id = i;
        indegree = indegree2;
	}
	public String toString() {
        return ""+id+" : "+indegree;
    }


    public int compareTo(T that){
       return that.indegree-this.indegree;
   }
  }
  
    
        

