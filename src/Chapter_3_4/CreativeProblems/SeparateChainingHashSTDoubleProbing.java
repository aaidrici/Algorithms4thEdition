package Chapter_3_4.CreativeProblems;

import Chapter_3_4.SeparateChainingHashST;
import Chapter_3_4.SequentialSearchST;


// Notes:
//  1. Assume the key type of DoubleProbing is always integer.
//  2. The result may differ based on which hash method is picked when two lists have identical size.
//  3.


public class SeparateChainingHashSTDoubleProbing<Value> extends SeparateChainingHashST<Integer, Value> {

    public SeparateChainingHashSTDoubleProbing(int M){
        this.M = M;
        st = (SequentialSearchST<Integer, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST();
    }

    private int hash01(int key){ return (11*key) % M; }
    private int hash02(int key){ return (17*key) % M; }
    protected int hash_int(int key){
            int id_list1 = hash01(key);
            int id_list2 = hash02(key);
            if (st[id_list2].size() > st[id_list1].size()){ return id_list1; }
            else {return id_list2;}
    }


    public void put(Integer key, Value val)
    { st[hash_int(key)].put(key, val); }



    public static void main(String[] args){

        SeparateChainingHashSTDoubleProbing<Character> st = new SeparateChainingHashSTDoubleProbing<>(3);

        char[] char_list = {'E','A','S', 'Y', 'Q','U','T','I', 'O', 'N'};

        for (char x : char_list){
            int key = (int)x - 64;
            st.put(key,x);
        }


        st.displayKey();
        st.displayVals();

        // Mean search miss: 3.33
        // Mean search hit: 2.20
        // Trace can be visualized from the illustrated table

        System.out.print((int)"BB".charAt(1));
    }
}
