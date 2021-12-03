package Chapter_3_1.CreativeProblems;

import Chapter_3_1.BinarySearchST;
import Chapter_3_1.Entry;

public class BinarySearchSTwithCertification<Key extends Comparable<Key>, Value> extends BinarySearchST<Key, Value> {


    public BinarySearchSTwithCertification(int size){
        // See Algorithm 1.1 for standard array-resizing code.
        item = new Entry[size];
        for (int i = 0; i<size; i++) item[i] = new Entry(null, null);
        N = 0;

    }

    public boolean Certification(){
        // check rank
        for (int i = 0; i<N; i++) {
            if (rank(item[i].key) != i) {
                return false;
            }
        }

        // check order
        for (int i = 0; i<N-1; i++) {
            if (item[i + 1].key.compareTo(item[i].key) < 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args){

        BinarySearchSTwithCertification<Integer, String> st = new BinarySearchSTwithCertification<Integer, String>(10);

        st.put(10,"word1");
        st.put(98,"word2");
        st.put(54,"word3");
        st.put(76,"word4");
        st.put(12,"word5");
        st.display();
        if (st.Certification()) System.out.print("It's legit.");


    }


}
