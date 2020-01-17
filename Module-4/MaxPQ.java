/**
 * Class MaxPQ
 * Complexity :
 * @author SakethNannaka
 *
 */
public class MaxPQ<Key extends Comparable <Key>>{
        private Key[] pq;
        private int N;
        /**
         * Constructor MaxPQ.
         * @param capacity.
         */
        public MaxPQ(int capacity){
            pq = (Key[]) new Comparable[capacity+1];
        }

        /**
         * This method checks and returns boolean Whether the array isempty or not.
         * @return boolean.
         */
        public boolean isEmpty(){
            return N==0;                           //returns true if N equals 0
        }
        /**
         * This method inserts new elements.
         * Swim up :compares the inserted children to it's parent and replaces the element accordingly.
         * @param key.
         */
        public void insert(Key key){
            pq[++N] = key;
            swim(N);                              //Swim up
        }
        /**
         *deletes the top most element and replaces it with bottom value.
         *sink down : compare parent to children and replace it accordingly.
         * @return Key.
         */
        public Key delMax(){
            Key max = pq[1];
            exch(1, N--);
            sink(1);                            //Sink down
            return max;

        }

        /**
         *This method compares children to it's parent and replaces accordingly.
         * @param k.
         * enter the loop if parent is lesser than child
         * exchanges parent position value with children's value if child's value is greater
         */
        private void swim(int k)
        {
            while (k > 1 && less(k/2, k))
            {
            exch(k, k/2);
            k = k/2;
            }
        }

        /**
         * This method compares parent to it's child and replaces it accordingly.
         * @param k.
         */
        private void sink(int k){
            while (2*k <= N)
            {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
            }
        }
        /**
         *This method returns a boolean on comparing i and j.
         * @param i
         * @param j
         * @return boolean.
         */
        public boolean less(int i , int j){
            return pq[i].compareTo(pq[j])<0;
        }

        /**
         * This method exchanges values of i and j
         * @param i
         * @param j
         */
        public void exch(int i , int j){
            Key temp = pq[i];
            pq[i]=pq[j];
            pq[j]=temp;
        }
        public int size(){
            return N;
        }
        public Key getMaxKey(){
            return pq[1];
        }

}
