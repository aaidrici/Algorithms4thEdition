package Chapter_3_4;


import Chapter_1_3.Queue;

import javax.swing.text.html.HTMLDocument;
import java.util.Arrays;
import java.util.Iterator;

public class LinearProbingHashST<Key, Value>
{
    protected int N; // number of key-value pairs in the table
    public int M = 16; // size of linear-probing table
    public Key[] keys; // the keys
    public Value[] vals; // the values
    public LinearProbingHashST()
    {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }
    public LinearProbingHashST(int M)
    {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
        this.M = M;
    }
    protected int hash(Key key)
    { return (key.hashCode() & 0x7fffffff) % M; }
    protected void resize(int cap)
    {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(cap);
        for (int i = 0; i < M; i++)
            if (keys[i] != null)
                t.put(keys[i], vals[i]);
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }
    public void put(Key key, Value val)
    {
        if (N >= M/2) resize(2*M); // double M (see text)
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key)) { vals[i] = val; return; }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    public Value get(Key key)
    {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    public double MeanCostForSearchHit(){
        double alpha = (double)(N) / M;
        return 0.5 * (1 + 1 /(1 - alpha));
    }
    public double MeanCostForSearchMiss(){
        double alpha = (double)(N) / M;
        return 0.5 * (1 + 1 /((1 - alpha)*(1 - alpha)));
    }

    public Iterable<Key> keys(){

        Key[] keys_array = (Key[]) new Object[N];
        int k = 0;
        for (int i = 0; i<M; i++){
            if (keys[i] != null) keys_array[k++] = keys[i];
        }

        Arrays.sort(keys_array);

        Queue<Key> keys = new Queue<Key>();
        for (int i = 0; i<N; i++) keys.enqueue(keys_array[i]);
        return keys;

    }


    public void display(){
        System.out.printf("M = %d \nN = %d\n",M,N);
        System.out.printf("%6s","Keys :");
        for (int k = 0; k<M; k++){
            if (keys[k] == null) System.out.printf("%4s","-");
            else System.out.printf("%4d",keys[k]);
        }
        System.out.printf("\n%6s", "Vals :");
        for (int k = 0; k<M; k++){
            if (vals[k] == null) System.out.printf("%4s","-");
            else System.out.printf("%4d",vals[k]);
        }
        System.out.print("\n\n");

    }

    public boolean contains(Key key){
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i] == key) return true;
            i = (i+1)%8;
        }
        return false;
    }

    public void delete(Key key)
    {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % M;
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null)
        {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M/8) resize(M/2);
    }

    public int size(){return N;}


    // altered version of put() where the number of probe is output to the user.
    public int putWithProbeCount(Key key, Value val)
    {
        int probeCount = 0;
        if (N >= M/2) resize(2*M); // double M (see text)
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            probeCount++;
            if (keys[i].equals(key)) {
                vals[i] = val;
                return probeCount;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
        return probeCount;
    }


    public static void main(String[] args){
        LinearProbingHashST<Integer, Integer> table = new LinearProbingHashST<Integer, Integer>();

        for (int i = 0; i<10; i++){ table.put((int)(Math.random() * 1000), 0);

        table.display();
        System.out.println();
        System.out.println();

        for (Integer x : table.keys()) System.out.print(x + " ");


        System.out.print(table.MeanCostForSearchHit());
    }
    }

}