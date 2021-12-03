package Chapter_3_1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class ArrayST<Key extends Comparable<Key>, Value> implements Iterable<Key>{

    protected Value[] values;
    protected Key[] keys;
    protected int n;

    public ArrayST(){}

    public ArrayST(int size){

        values = (Value[]) new Object[size];
        keys = (Key[]) new Comparable[size];

        n = 0;
    }

    public void put(Key key, Value value){
        for (int i = 0; i<n; i++) {
            if (keys[i].compareTo(key) == 0) {
                values[i] = value;
                return;
            }
        }
        keys[n] = key;
        values[n++] = value;
        updateArraySize();
    }

    public Value get(Key key){
        for (int i = 0; i<n ; i++) {
            if (keys[i].compareTo(key) == 0) return values[i];
        }
        return null;
    }

    public boolean contains(Key key){
        for (int i = 0; i<n ; i++) {
            if (keys[i].compareTo(key) == 0) return true;
        }
        return false;
    }

    public void delete(Key key){
        for (int i = 0; i<n; i++){
            if (keys[i].compareTo(key) == 0) {
                // target hit
                for (int j = i; j < n-1; j++){
                    keys[j] = keys[j + 1];
                    values[j] = values[j + 1];
                }
                n--;
                return;
            }
        }
    }

    public boolean isEmpty(){return n==0;}

    public int size(){return n;}

    void ResizeArray(int newSize){
        Key[] keyTemp = (Key[]) new Comparable[newSize];
        Value[] valueTemp = (Value[]) new Object[newSize];

        for (int i = 0; i<n; i++){
            keyTemp[i] = keys[i];
            valueTemp[i] = values[i];
        }
        keys = keyTemp;
        values = valueTemp;

    }

    void updateArraySize(){
        int newSize;
        if (n == keys.length) newSize = keys.length*2;
        else if(n < keys.length/4) newSize = keys.length/4;
        else return;
        ResizeArray(newSize);
    }

    public Iterator<Key> iterator(){
        return new ArraySTIterator();
    }

    class ArraySTIterator implements Iterator<Key>{
        int i = 0;
        public boolean hasNext(){return i != n; }
        public Key next(){return keys[i++]; }

    }

    public void display(){
        for (int i = 0; i<n; i++) System.out.println(keys[i]  + " : " + values[i]);
    }


    public static void main(String[] args){

        Class<Integer> k;

        ArrayST<Integer, String> st = new ArrayST<Integer, String>(10);

        st.put(33, "hello");
        st.put(14,"OtherMessage");
        st.put(10,"test");
        st.put(23, "none");
        st.delete(10);
        st.display();
        System.out.println();

        for (Integer x : st) System.out.println(x);
    }
}
