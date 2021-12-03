package Chapter_3_4;


import Chapter_1_3.Queue;

import java.util.Arrays;

public class SeparateChainingHashST_upgraded<Key, Value>
{
    private int N; // number of key-value pairs
    private int M; // hash table size
    private double alpha_max;
    private SequentialSearchST<Key, Value>[] st; // array of ST objects
    public SeparateChainingHashST_upgraded()
    { this(997); }



    private int hash(Key key)
    { return (key.hashCode() & 0x7fffffff) % M; }
    public Value get(Key key)
    { return (Value) st[hash(key)].get(key); }

    public void put(Key key, Value val) {
        if (N >= alpha_max * M){
            increaseM();
        }
        int hashVal = hash(key);
        st[hashVal].put(key, val);
        UpdateSize();
    }
    public void UpdateSize(){
        N = 0;
        for (int i = 0; i<st.length; i++) N += st[i].N;
    }




    // See Exercise 3.4.19.
    public Iterable<Key> keys(){

        Key[] key_array = (Key[]) new Object[N];

        int k, j = 0;
        for (int i = 0; i<M; i++) {
            k = 0;
            while (k < st[i].size()) {
                key_array[j++] = st[i].getKeyFromId(k++);
            }
        }
        Arrays.sort(key_array);
        Queue<Key> keys = new Queue<Key>();
        for (int i = 0; i<N; i++) {
            keys.enqueue(key_array[i]);
        }
        return keys;
    }


    // added for 3.4.17
    private int k;
    private static final int[] delta = {1,3,1,5,3,3,9,3,1,3,19,15,1,5,1,3,9,3,15,3,39,5,39,57,3,35,1};
    public SeparateChainingHashST_upgraded(double alpha_max){
         // alpha = N / M;
        // -> M must constantly be re-adjusted s.t. N / M <= alpha_max
        k = 5;
        M = (int) Math.pow(2, k) - delta[k - 5];
        st = new SequentialSearchST[M];
        for (int i = 0; i<M; i++) st[i] = new SequentialSearchST<Key, Value>();

        this.alpha_max = alpha_max;

    }
    void increaseM(){
        if (k < 31) k++;
        updateMbasedOnK();
    }
    void decreaseM(){
        if (k > 5) k--;
        updateMbasedOnK();
    }
    void updateMbasedOnK(){
        M = (int) Math.pow(2, k) - delta[k - 5];

        SequentialSearchST<Key, Value>[] st_temp;
        st_temp = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i<M; i++) st_temp[i] = new SequentialSearchST<Key, Value>();


        for (int i = 0; i<st.length; i++){
            while (!st[i].isEmpty()){
                Key key = st[i].first();
                Value val = st[i].get(key);
                st[i].delete(key);
                st_temp[hash(key)].put(key,val);
            }
        }
        UpdateSize();
        st = st_temp;
    }


    public static void main(String[] args){

        SeparateChainingHashST_upgraded<Integer, Integer> sc = new SeparateChainingHashST_upgraded<Integer, Integer>(3);

        // generate random key entries
        final int KEY_MAX = 100000;
        for (int i = 0; i < 150; i++){
            int rand = (int)(Math.random() * KEY_MAX);
            sc.put(rand, 0);
            System.out.printf("M = %d, N = %d, alpha = %.4f, X_{added} = %d \n", sc.M, sc.N, (double)(sc.N)/sc.M, rand);
        }

        for (Integer x : sc.keys()) System.out.print(x + " ");


    }


}



