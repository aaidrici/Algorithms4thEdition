package Chapter_3_5.Exercises;

import Chapter_3_3.RedBlackBST;


// Strategy: use the value field as field to count the number of redundant keys
// Assumption: not all instances of key are deleted if delete(key) is called.

public class MultiSET<Key extends Comparable<Key>> {

    RedBlackBST<Key, Integer> st;

    public MultiSET(){st = new RedBlackBST<Key, Integer>(); }

    public void add(Key key) {
        if (!st.contains(key)){ st.put(key, 1);}
        else{
            int instances = st.get(key) + 1;
            st.put(key, instances);
        }
    }


    public void delete(Key key){
        int inst_nmb = st.get(key);
        if (inst_nmb > 1){ st.put(key, inst_nmb - 1);  }
        else if (inst_nmb == 1) { st.delete(key);}
    }

    public boolean contains(Key key){return st.contains(key);}

    boolean isEmpty(){return st.isEmpty();}

    public int size(){return st.size(); }

    // added methods for debugging
    public Iterable<Key> keys(){return st.keys(); }

    public void display(){
        for (Key x : keys()){
            int n = st.get(x);
            for (int i = 0; i<n;i++)
                System.out.print(x + " ");
        }
    }

    public static void main(String[] args){

        MultiSET<String> set = new MultiSET<String>();

        set.add("word 1");
        set.add("word 1");
        set.add("word 3");
        set.add("word 3");

        set.display();

    }
}


