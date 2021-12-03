package Chapter_3_5.CreativeProblems;

import Chapter_3_4.LinearProbingHashST;

import java.util.ArrayList;

public class LRUcache<Key> {


    LinearProbingHashST<Key, Integer> st;

    DoubleLL<Key> ll = new DoubleLL<Key>();

    class Node{
        public Key key;
        public int val;
        public Node(Key key, int val){this.key = key; this.val = val;}
    }

    public LRUcache(){
        st = new LinearProbingHashST<>();
    }


    public void access(Key key){

        if (st.contains(key)){
            int pos = st.get(key); // pos = position on ll of key to access
            for (int i = ll.size()-1; i > pos; i--){
                st.put(ll.get(i),i-1);
            }
            st.put(key, ll.size() - 1);
            ll.delete(pos);
        }
        else{ st.put(key, ll.size()); }

        ll.insertOnTop(key, -1);

    }

    public Key delete(){
        if (ll.size() == 0) return null;
        Key t = ll.head.key;
        ll.delete(ll.size()-1);
        st.delete(t);
        return t;
    }


    public void displayll(){
        ll.display();
    }

    public static void main(String[] args){

        LRUcache<Integer> lru = new LRUcache<Integer>();


        int[] access = {2,4,2,1,5,3,0};
        for (int x : access) lru.access(x);

        lru.delete();
        lru.delete();

        int[] access2 = {8,7,1,0};
        for (int x : access2) lru.access(x);

        lru.delete();
        lru.delete();
        lru.delete();

        lru.displayll();

    }

}


class DoubleLL<Key>{

    public Node head;
    public Node tail;
    int N = 0;

    // convention: tail (left) has pos = 0
    //             head (left) has pos = size() - 1

    class Node{
        Node up;
        Node down;
        Key key;
        int val;
        public Node(Node up, Node down, Key key, int val){this.up = up; this.down = down; this.key = key; this.val = val;}
    }

    public DoubleLL(){
        N = 0;
        head = null;
        tail = null;
    }
    public void insertOnTop(Key key, int val){
        if (head == null){
            head =new Node(null, null, key, val);
            tail = head;
        }
        else{
            Node t = head;
            head = new Node(null, t,key, val);
            t.up = head;
        }
        N++;
    }

    public int size(){return N;}

    public void delete(){ delete(size() -1);}
    public void delete(int pos){ // pos = 0 means delete the tail
        Node x = tail;
        for (int i = 0; i < pos; i++){ x = x.up;}
        if (x.up != null) x.up.down = x.down;
        else{head = x.down;}

        if (x.down != null) x.down.up = x.up;
        else{tail = x.up;}

        N--;
    }

    public Key get(int pos){
        Node x = tail;
        for (int i = 0; i < pos; i++){ x = x.up;}
        return x.key;
    }

    public void display(){
        Node x = head;
        while (x != null){
            System.out.print(x.key + " ");
            x = x.down;
        }
    }


}