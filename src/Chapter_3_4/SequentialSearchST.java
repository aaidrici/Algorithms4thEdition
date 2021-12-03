package Chapter_3_4;

public class SequentialSearchST<Key, Value> {
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




    // added for 3.14.7
    int N;
    public boolean isEmpty(){return first == null;}
    public Key first(){
        if (first != null) return first.key;
        return null;
    }
    public void delete(Key key){
        if (!isEmpty()){
            if (first.key == key) {
                first = first.next;
                N--;
            }
            else{
                Node x = first;
                while (x.next != null){
                    if (x.next.key == key){
                        x.next = x.next.next;
                        N--;
                        return;
                    }
                }
            }
        }
    }

    public int size(){return N;}

    public Key getKeyFromId(int k){ // starting with id 0 convention.
        if (first == null) return null;
        Node x = first;
        for (int i = 0; i<k; i++){
            if (x.next == null) return null;
            x = x.next;
        }
        return x.key;
    }
    public Value getValFromId(int k){ // starting with id 0 convention.
        if (first == null) return null;
        Node x = first;
        for (int i = 0; i<k; i++){
            if (x.next == null) return null;
            x = x.next;
        }
        return x.val;
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



}