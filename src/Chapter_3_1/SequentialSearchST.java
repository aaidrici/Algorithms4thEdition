package Chapter_3_1;

import java.util.Iterator;

public class SequentialSearchST<Key, Value>
{
    int N = 0;
    private Node first; // first node in the linked list
    private class Node
    { // linked-list node
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next)
        {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    public Value get(Key key)
    { // Search for key, return associated value.
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val; // search hit
        return null; // search miss
    }
    public void put(Key key, Value val)
    { // Search for key. Update value if found; grow table if new.
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
            { x.val = val; return; } // Search hit: update val.
        first = new Node(key, val, first); // Search miss: add new node.
        N++;
    }



    // Methods below this line were implemented as exercises.

    public int size(){ return N; }

    public void delete(Key key){

        Node tempFirst = new Node(null, null, first);

        for (Node x = tempFirst; x.next != null; x = x.next)
            if (key.equals(x.next.key)){
                x.next = x.next.next;  // search hit: delete val.
                N--;
                return;
            }


    }

    public Iterator<Key> iterator(){
        return new iteratorForSequentialSearch();
    }

    class iteratorForSequentialSearch implements Iterator<Key>{
        Node newFirst = new Node(null, null, first);
        Node x = newFirst;
        public boolean hasNext(){return x.next != null;}
        public Key next(){x = x.next; return x.key;}
    }

    public void display(){
        for (Node x = first; x != null; x = x.next)
            System.out.println(x.key  + ": " + x.val);
    }


    public static void main(String[] args){

        SequentialSearchST<Integer, String> st = new SequentialSearchST<Integer,String>();
        st.put(10,"Yacine" );
        st.put(12, "Yousra");
        st.put(23, "Imane");
        st.put(14, "Imane");
        st.put(17, "Imane");

        st.display();
    }


}