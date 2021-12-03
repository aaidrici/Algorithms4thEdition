package Chapter_3_1.CreativeProblems;

import Chapter_3_1.ArrayST;

public class SelforganizingSearch<Key extends Comparable<Key>, Value> extends ArrayST<Key, Value> {
//
//    private ArrayST st;
//
//    public SelforganizingSearch(){
//        st = new ArrayST<>();
//    }


    public SelforganizingSearch(int size){
        values = (Value[]) new Object[size];
        keys = (Key[]) new Comparable[size];
        n = 0;
    }

    public Value get(Key key){
        for (int i = 0; i<n ; i++) {
            if (keys[i].compareTo(key) == 0){
                putToFront(i);
                return values[0];
            }
        }
        return null;
    }

    public boolean contains(Key key){
        for (int i = 0; i<n ; i++) {
            if (keys[i].compareTo(key) == 0){
                putToFront(i);
                return true;
            }
        }
        return false;
    }

    private void putToFront(int id){
        Value tempval = values[id];
        Key tempkey = keys[id];

        for (int i = id; i > 0; i--){
            values[i] = values[i-1];
            keys[i] = keys[i-1];
        }
        values[0] = tempval;
        keys[0] = tempkey;
    }

    public static void main(String[] args){

        SelforganizingSearch<Integer, String> st = new SelforganizingSearch<>(10);

        st.put(10,"word1");
        st.put(22,"word2");
        st.put(59,"word3");
        st.put(24,"word4");
        st.put(25,"word5");
        st.display();
        System.out.println();

        st.contains(24);
        st.display();



    }

}
