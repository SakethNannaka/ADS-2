public class BoggleSolver{
	TrieST st;
	public BoggleSolver(){
		st = new TrieST();
		}
		    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary){

    				for (String istring : dictionary) {
    					String input = istring;
    					 int wordScore = 0;
							int length = input.length(); 
							if (length <= 4) {
								wordScore += 1;
							}
							if (length == 5) {
							wordScore += 2;
							}
							if (length == 6) {
							wordScore += 3;
							}
							if (length == 7) {
							wordScore += 5;
							}
							if (length >= 8) {
							wordScore += 11;
							}
						    st.put(input,wordScore);
    				}

    	                   
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board){

    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word){
    	
    }
	public static void main(String[] args) {
				BoggleSolver s = new BoggleSolver();
				In in =  new In("words.txt");
				String [] dictionary = new String[3152];
				int j = 0;
				while(in.hasNextLine()){
							String input = in.readLine();
							dictionary[j++] = input;
						}
						System.out.println(s.st.get("LONGEST"));
				}
			}		

