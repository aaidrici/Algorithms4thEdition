package Chapter_3_4;

import Chapter_1_3.Queue;

import java.util.Arrays;

public class SeparateChainingHashST<Key, Value>
{
    public int N; // number of key-value pairs

    public int M; // hash table size

    public SequentialSearchST<Key, Value>[] st; // array of ST objects

    public SeparateChainingHashST()
    { this(997); }

    public SeparateChainingHashST(int M)
    { // Create M linked lists.
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST();
    }

    protected int hash(Key key)
    { return (key.hashCode() & 0x7fffffff) % M; }

    public Value get(Key key)
    { return (Value) st[hash(key)].get(key); }

    public void put(Key key, Value val)
    {
        st[hash(key)].put(key, val);
        updateSize();
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


    // Added methods
    public void displayKey(){
        updateSize();
        System.out.printf("M = %d, N = %d\n", M, N);
        for (int i = 0; i<M ;i++){
            System.out.printf("%3d: ",i);
            int sc_size = st[i].size();
            for (int j = 0; j<sc_size; j++) {System.out.printf("%4d ",st[i].getKeyFromId(j));}
            System.out.println();
        }
    }
    public void displayVals(){
        updateSize();
        System.out.printf("M = %d, N = %d\n", M, N);
        for (int i = 0; i<M ;i++){
            System.out.printf("%3d: ",i);
            int sc_size = st[i].size();
            for (int j = 0; j<sc_size; j++) {
                System.out.printf("%4c ", st[i].getValFromId(j));
            }
            System.out.println();
        }
    }
    public void updateSize(){
        N = 0;
        for (int i = 0; i<M;i++){ N += st[i].size(); }
    }

    // 3.4.30
    public double chi2Stats(){
        updateSize();
        double sum = 0;
        double alpha = (double)(N)/M;
        for (int i = 0; i<M; i++){
            sum += Math.pow((st[i].size() - alpha),2);
        }
        return (sum * M) / N;
    }

    public int maxListLength(){
        if (M == 0) return -1;
        int result = st[0].size();
        for (int i = 1; i<M; i++){
            int seq_length = st[i].size();
            if (seq_length > result) result = seq_length;
        }
        return result;
    }

    public int minListLength(){
        if (M==0) return -1;
        int result = st[0].size();
        for (int i = 1; i<M; i++){
            int seq_length = st[i].size();
            if (seq_length < result) result = seq_length;
        }
        return result;
    }


}

