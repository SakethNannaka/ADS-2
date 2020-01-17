public class LinkedList<T>{
		T data;
		Node head;
		public LinkedList(){
			this.head = null;
		}
		public void add(int data){
			Node temp = new Node(data);
			if(this.head = null){
				head = temp;
				return;
			}
			temp.next = head;
			head = temp;
		}
		public String toString(){
			String temp = 0;
			for(Node x = head ; x.next! = null ; x = x.next ){
				temp = temp +" >> "+x;
			}
			return temp;
		}
		public static void main(String[] args) {
			
		}
}