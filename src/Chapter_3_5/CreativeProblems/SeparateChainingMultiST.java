package Chapter_3_5.CreativeProblems;


import Chapter_3_3.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.util.ArrayList;

public class SeparateChainingMultiST<Key extends Comparable<Key>, Value> {


    public SeparateChainingMultiST(){ st = new SeparateChainingHashST<>();}

    SeparateChainingHashST<Key, ArrayList<Value>> st;

     public void put(Key key, Value val){
        if (!st.contains(key)){
            ArrayList<Value> t = new ArrayList<Value>();
            t.add(val);
            st.put(key, t);
        }
        else{
            ArrayList<Value> t = getValList(key);
            t.add(val);
            st.put(key,t);
        }
    }

    public Value get(Key key){
        ArrayList<Value> vals = getValList(key);
        if (vals == null) return null;
        // id zero could be replaced with a random number within [0, vals.size() - 1]
        return vals.get(0);
    }

    void delete(Key key){st.delete(key);}

    public boolean contains (Key key){return st.contains(key);}

    public boolean isEmpty(Key key){return st.isEmpty();}

    public int size(Key key){return st.size();}

    private ArrayList<Value> getValList(Key key){return st.get(key);}


    public Iterable<Value> getAll(Key key){
        ArrayList<Value> a = getValList(key);
        if (a != null) return a;
        else return new ArrayList<Value>();
    }

    public Iterable<Key> keys(){return st.keys();}


    // added method for debugging:
    public void display(){
        for (Key x : st.keys()){
            System.out.print(x + " : ");
            for(Value y : st.get(x)){
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args){


        SeparateChainingMultiST<Integer, String> st = new SeparateChainingMultiST<Integer, String>();

        st.put(1, "Catherine");
        st.put(1, "Imane");
        st.put(1, "Sophie");
        st.put(3, "Iris");
        st.delete(3);
        st.put(4, "Cassandre");

        for (String x : st.getAll(3)){System.out.print(x + " "); }



    }

}
