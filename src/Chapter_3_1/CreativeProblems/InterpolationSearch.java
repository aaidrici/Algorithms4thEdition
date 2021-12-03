package Chapter_3_1.CreativeProblems;

import Chapter_1_4.DoublingTest;
import Chapter_3_1.BinarySearchST;
import Chapter_3_1.Entry;

public class InterpolationSearch<Key extends Comparable<Key>, Value> extends BinarySearchST<Key, Value>{



    public InterpolationSearch(int capacity){
        // See Algorithm 1.1 for standard array-resizing code.
        item = new Entry[capacity];
        for (int i = 0; i<capacity; i++) item[i] = new Entry(null, null);
        N = 0;
    }







    public int rankWithInterpolation(Key key){

        // rankWithInterpolation takes a key and returns its position on the ST


        double target = -1.0;

        if (key instanceof Integer)
            target = (double)(int)(Integer)key;
        else if(key instanceof Double)
            target = (double)(Double)key;

        int lo = 0;
        int hi = size() - 1;
        int mid;

        while (lo <= hi)
        {
            mid = lo + (hi - lo) * (int)Math.floor(((target - (Double)select(lo)) / ((Double)select(hi) - (Double)select(lo))));
            // int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo((Key)item[mid].key);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;


    }

    public static void main(String[] args) {


        InterpolationSearch<Integer, String> st = new InterpolationSearch<>(10);

        //st.rankWithInterpolation(12);

        st.put(12,"sandra");
        st.put(10,"Jennifer");
        st.put(24,"Jeffrey");
        st.put(13,"Eleonora");
        st.put(1,"Edward");
        st.put(12,"Justine");
        st.put(33,"Amir");
        st.put(37,"Eliot");

        st.display();
        Integer a = 1;
        System.out.print(st.rankWithInterpolation(a));
//
//        Integer a = 401;
//
//        System.out.print((double)(int)a);



    }


}

class wrongType extends Exception{
    public wrongType(String message){
        super(message);
    }
    public wrongType(String message, Throwable throwable){
        super(message,throwable);
    }
}