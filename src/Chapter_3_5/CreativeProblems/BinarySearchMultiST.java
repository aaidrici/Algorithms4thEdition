package Chapter_3_5.CreativeProblems;

import Chapter_3_2.BST;

import java.util.ArrayList;

public class BinarySearchMultiST<Key extends Comparable<Key>, Value> {

    BST<Key, ArrayList<Value>> bst = new BST<Key, ArrayList<Value>>();

    public BinarySearchMultiST(){ bst = new BST<Key, ArrayList<Value>>(); }

    public boolean isEmpty(){return bst.size() == 0; }

    public boolean contains(Key key){return (bst.get(key) != null); }

    public Value get(Key key){
        ArrayList<Value> a = bst.get(key);
        if (a != null) return a.get(0);
        else return null;
    }

    public void put(Key key, Value value){
        if (!contains(key)){
            ArrayList<Value> t = new ArrayList<Value>();
            t.add(value);
            bst.put(key, t);
        }
        else{
            ArrayList<Value> t = bst.get(key);
            t.add(value);
            bst.put(key, t);
        }
    }

    public int size(){return bst.size();}

    public void delete(Key key){bst.delete(key);}

    public Iterable<Key> keys(){ return bst.keys();}


    public static void main(String[] args){

    }
}
