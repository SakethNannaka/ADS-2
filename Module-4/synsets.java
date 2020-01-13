import java.util.ArrayList;

public class synsets{
	int n;
	ArrayList<String> words;
	String Meaning;

	public synsets(int n , ArrayList<String> words,String Meaning){
		this.words   = words;
		this.n 		 = n;
		this.Meaning = Meaning;

	}
	public boolean ContainsNoun(String word){
		if(words.contains(word)){
			return true;
		}
		return false;
	}
	public int Vertice(String word){
		if(words.contains(word)){
			return this.n;
		}
		return -1;
	}
	public String toString(){
		return "n       :"+n+"\n"+ "words   :"+words+"\n"+"Meaning :"+Meaning;
	}
	public static void main(String[] args) {
		ArrayList<String> words =  new ArrayList<>();
		words.add("Saketh");
		words.add("Jayanth");
		int n = 1;
		synsets obj = new synsets(n,words,"Brothers");
		System.out.println(obj);
	}
}