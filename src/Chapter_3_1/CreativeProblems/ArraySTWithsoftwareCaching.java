package Chapter_3_1.CreativeProblems;

import Chapter_3_1.ArrayST;

import java.lang.reflect.Array;

public class ArraySTWithsoftwareCaching<Key extends Comparable<Key>, Value> extends ArrayST<Key, Value> {

    private int lastAccessedArrayInd;

    public ArraySTWithsoftwareCaching(int size){
        n = 0;
        keys = (Key[]) new Comparable[size];
        values = (Value[]) new Object[size];
        lastAccessedArrayInd = 0;
    }

    @Override
    public boolean contains(Key key) {
        // last array accessed precheck
        if (!isEmpty() && keys[lastAccessedArrayInd].compareTo(key) == 0)
            return true;

        for (int i = 0; i<n ; i++) {
            if (keys[i].compareTo(key) == 0){
                lastAccessedArrayInd = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public Value get(Key key){
        // last array accessed precheck
        if (!isEmpty() && keys[lastAccessedArrayInd].compareTo(key) == 0)
            return values[lastAccessedArrayInd];

        for (int i = 0; i<n ; i++) {
            if (keys[i].compareTo(key) == 0) {
                lastAccessedArrayInd = i;
                return values[i];
            }
        }
        return null;
    }


    public static void main(String[] args){

        ArraySTWithsoftwareCaching<Integer, String> st = new ArraySTWithsoftwareCaching<>(10);

        st.put(33,"word1");
        st.put(14,"word2");
        st.put(10,"word3");
        st.put(23,"word4");
        st.contains(10);
        st.contains(20);
        st.contains(10);
        st.display();
        System.out.println();

    }

}
