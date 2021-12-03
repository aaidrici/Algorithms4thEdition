package Chapter_3_4.Exercises;

import Chapter_3_1.SequentialSearchST;
import com.sun.javaws.jnl.RContentDesc;

public class SeparateChainingHashST<Key, Value>
    {

        @Override
        public int hashCode(){
            return 0;
        }

        public class SequentialSearchST<Key, Value> {
            private Node first; // first node in the linked list

            private class Node
            { // linked-list node
                Key key;
                Value val;
                Node next;
                int numberOfKeysAddedPrior;
                public Node(Key key, Value val, Node next, int numberOfKeysAddedPrior)
                {
                    this.key = key;
                    this.val = val;
                    this.next = next;
                    this.numberOfKeysAddedPrior = numberOfKeysAddedPrior;
                }
            }
            public Value get(Key key)
            { // Search for key, return associated value.
                for (Node x = first; x != null; x = x.next)
                    if (key.equals(x.key))
                        return x.val; // search hit
                return null; // search miss
            }
            public void put(Key key, Value val, int numberOfKeysAddedPrior)
            { // Search for key. Update value if found; grow table if new.
                for (Node x = first; x != null; x = x.next)
                    if (key.equals(x.key))
                    { x.val = val; return; } // Search hit: update val.
                first = new Node(key, val, first, numberOfKeysAddedPrior); // Search miss: add new node.
            }
//            public void delete(Key key){
//
//                Node tempFirst = new Node(null, null, first, -1);
//                for (Node x = tempFirst; x.next != null; x = x.next)
//                    if (key.equals(x.next.key)){
//                        x.next = x.next.next;  // search hit: delete val.
//                        N--;
//                        return;
//                    }
//            }
            public void deleteBasedOnNumOfKeysPutPrior(int k){
                Node x = new Node(null, null, first, -1);
                while (x.next != null){
                    if (x.next.numberOfKeysAddedPrior > k ){
                        if (x.next == first) first = first.next; // ensure node `first` is shifted if needed./
                        x.next = x.next.next;
                        continue;
                    }
                    x = x.next;
                }
            }

            public void display(){
                // used mainly for debugging purposes
                for (Node x = first; x != null; x = x.next) System.out.print(x.key + "");
            }

        }




        private int N; // number of key-value pairs
        private int M; // hash table size
        private SequentialSearchST<Key, Value>[] st; // array of ST objects
        public SeparateChainingHashST()
        { this(997); }
        public SeparateChainingHashST(int M)
        { // Create M linked lists.
            this.M = M;
            st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
            for (int i = 0; i < M; i++)
                st[i] = new SequentialSearchST();
        }
        private int hash(Key key)
        { return (key.hashCode() & 0x7fffffff) % M; }
        public Value get(Key key)
        { return (Value) st[hash(key)].get(key); }
        public void put(Key key, Value val)
        { st[hash(key)].put(key, val, N); }
        //public Iterable<Key> keys()
// See Exercise 3.4.19.



        public static void main(String[] args){



        }

    }
