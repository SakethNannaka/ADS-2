public class BoggleSolver{
	TrieST st;
	public BoggleSolver(){
		st = new TrieST();
		}
	public static void main(String[] args) {
				BoggleSolver s = new BoggleSolver();
				In in =  new In("words.txt");
				while(in.hasNextLine()){
							String input = in.readLine();
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
							s.st.put(input,wordScore);
						}
						System.out.println(s.st.get("LONGEST"));
				}
			}		
