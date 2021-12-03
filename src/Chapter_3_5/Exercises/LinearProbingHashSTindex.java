package Chapter_3_5.Exercises;

import Chapter_1_3.Queue;
import Chapter_3_4.LinearProbingHashST;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;


public class LinearProbingHashSTindex<Key, Value>
{
    protected int N; // number of key-value pairs in the table
    public int M = 16; // size of linear-probing table
    public Key[] keys; // the keys

    public ArrayList<Value>[] vals;

    //public Value[] vals; // the values
    public LinearProbingHashSTindex()
    {
        keys = (Key[]) new Object[M];
        vals = new ArrayList[M];
    }
    public LinearProbingHashSTindex(int M)
    {
        keys = (Key[]) new Object[M];
        vals = (ArrayList[]) new Object[M];
        this.M = M;
    }
    protected int hash(Key key)
    { return (key.hashCode() & 0x7fffffff) % M; }
    protected void resize(int cap)
    {
        LinearProbingHashSTindex<Key, Value> t;
        t = new LinearProbingHashSTindex<Key, Value>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value val) // add single value
    {
        if (N >= M/2) resize(2*M); // double M (see text)
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key)) {
                for (Value x : vals[i]) {if (x == val) return;}
                vals[i].add(val); return;
            }
        keys[i] = key;
        vals[i] = new ArrayList<Value>();
        vals[i].add(val);
        N++;
    }
    public void put(Key key, ArrayList<Value> val) // add the whole arrayList when resizing
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
                return vals[i].get(0);
        return null;
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

//
//    public void display(){
//        System.out.printf("M = %d \nN = %d\n",M,N);
//        System.out.printf("%6s","Keys :");
//        for (int k = 0; k<M; k++){
//            if (keys[k] == null) System.out.printf("%4s","-");
//            else System.out.printf("%4d",keys[k]);
//        }
//        System.out.printf("\n%6s", "Vals :");
//        for (int k = 0; k<M; k++){
//            if (vals[k] == null) System.out.printf("%4s","-");
//            else System.out.printf("%4d",vals[k]);
//        }
//        System.out.print("\n\n");
//
//    }

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
            ArrayList<Value> valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M/8) resize(M/2);

    }

    public void display(){
        for (int i = 0; i<M;i++){
            if (keys[i] == null) {
                continue;
            }
            System.out.print(keys[i] + " ");
            for (Value x : vals[i]) System.out.print(x + " ");
            System.out.println();
        }
    }

    public int size(){return N;}

    public boolean isEmpty(){return (size() == 0); }

    public static void main(String[] args){


        LinearProbingHashSTindex<String,Integer> set = new LinearProbingHashSTindex<>();

        set.put("hay",0);
        set.put("word",0);
        set.put("Needless",0);
        set.put("to",0);
        set.put("say",0);
        set.delete("Needless");
        set.put("word",2);
        set.put("word",0);

        set.display();

        System.out.printf("size = %d\n", set.size());
        System.out.printf("contains the word `word` = %b\n", set.contains("word"));
        System.out.printf("contains the word `Needless` = %b\n", set.contains("Needless"));
        System.out.printf("is the set empty? = %b \n", set.isEmpty());





    }

}