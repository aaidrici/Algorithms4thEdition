package Chapter_3_1.CreativeProblems;

import Chapter_3_1.BinarySearchST;
import Chapter_3_1.Entry;

public class OrderedInsertion<Key extends Comparable<Key>, Value> extends BinarySearchST<Key, Value> {

    // No modification

    // The order of growth for the creation of a symbolic table from a sorted
    // array should be linearithmic, and not linear.

    // It could be make linear if rank() was changed such that it looks for keys
    // sequentially starting from the last key.

    public OrderedInsertion(int size){
        // See Algorithm 1.1 for standard array-resizing code.
        item = new Entry[size];
        for (int i = 0; i<size; i++) item[i] = new Entry(null, null);
        N = 0;
    }

    @Override
    public void put(Key key, Value val){
        // Search for key. Update value if found; grow table if new.
        int i = rank(key);
        if (i < N && item[i].key.compareTo(key) == 0)
        { item[i].value = val; return; }
        for (int j = N; j > i; j--)
        { item[j].key = item[j-1].key; item[j].value = item[j-1].value; }
        item[i].key = key; item[i].value = val;
        N++;
    }


    public static void main(String[] args){

        OrderedInsertion<Integer, String> st = new OrderedInsertion<>(10);
        st.put(1,"word1");
        st.put(2,"word2");
        st.put(3,"word3");
        st.put(4,"word4");
        st.put(5,"word5");

        st.display();
    }

}
