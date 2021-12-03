package Chapter_2_4.CreativeProblems;
import edu.princeton.cs.algs4.StdRandom;

import static edu.princeton.cs.algs4.StdOut.print;



public class IndexPQ<Key extends Comparable<Key>> {

    private Integer[] pq; // heap-ordered complete binary tree

    private Key[] keys; // content of the PQ

    private Integer[] qp; // keeps track of the position of each keys within pq.

    // i'th key (or keys[i]) is located in position qp[i] on the heap.

    private int maxN;

    private int N = 0; // in pq[1..N] with pq[0] unused

    public IndexPQ(int maxN) {
        this.maxN = maxN;
        pq = new Integer[maxN+1];
        qp = new Integer[maxN+1];
        keys = (Key[]) new Comparable[maxN+1];

        for (int i = 0; i<maxN+1; i++) qp[i] = -1;
    }

    public boolean isEmpty()
    { return N == 0; }

    public int size()
    { return N; }

    public void insert(Key key, Integer index) {

        if (index > maxN) print("max index exceeded");
        keys[++N] = key;
        pq[N] = index;
        qp[index] = N;
        swim(N);
    }

    public Key delMin() {
        Integer max = pq[1]; // Retrieve max index from top.
        Key maxKey = keys[1]; // Retrieve max key from top.

        exch(1, N--); // Exchange with last item.

        qp[pq[1]] = -1; // Avoid loitering of qp.
        pq[N+1] = null; // Avoid loitering of pq.

        sink(1); // Restore heap property.
        return maxKey;
    }


    public Integer delMinId() {
        Integer max = pq[1]; // Retrieve max index from top.
        Key maxKey = keys[1]; // Retrieve max key from top.

        exch(1, N--); // Exchange with last item.

        qp[pq[1]] = -1; // Avoid loitering of qp.
        pq[N+1] = null; // Avoid loitering of pq.

        sink(1); // Restore heap property.
        return max;
    }

    // See pages 145-147 for implementations of these helper methods.

    private boolean less(int i, int j) {
        return keys[i].compareTo(keys[j]) > 0;
    }

    private void exch(int i, int j) {

        // update array qp first
        qp[pq[i]] = j;
        qp[pq[j]] = i;

        // swap indices in pq
        Integer t = pq[i]; pq[i] = pq[j]; pq[j] = t;

        // swap keys in keys[]
        Key temp = keys[i]; keys[i] = keys[j]; keys[j] = temp;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2*k <= N)
        {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public boolean contains(Integer k){
        return qp[k] != -1;
    }

    public Key minIndex(){
        // returns the keys with the highest index value
        return keys[1];
    }
    public void Change(int id, Key newKey){
        // associate an id with a different key.
        keys[qp[id]] = newKey;
        swim(qp[id]);
        sink(qp[id]);
    }
    public void delete(int id2delete){

        Integer id2fix = pq[N];

        exch(N--,qp[id2delete]);

        swim(qp[id2fix]);
        sink(qp[id2fix]);

        keys[N+1] = null;
        pq[N+1] = 0;
        qp[id2delete] = -1;
    }



public static void main(String[] args){

    int N = 10;
    Integer[] keys = new Integer[N];
    Integer[] ids = new Integer[N];

    for (Integer i = 0; i<N; i++){
        keys[i] = StdRandom.uniform(0,10*N);
        //ids[i] = StdRandom.uniform(0,10*N);
        ids[i] = i;

    }

    print("list of keys: ");
    for (Integer x : keys) print(x + " ");
    print("\n");
    print("list of ids: ");
    for (Integer x : ids) print(x + " ");
    print("\n");

    IndexPQ pq = new IndexPQ(10);

    for (int i = 0; i<N; i++)
        pq.insert(keys[i], ids[i]);

    print("\n PQ:");
    for (int i = 0; i<N; i++)
        print(" " + pq.keys[pq.qp[i]]);

    print("\n suppress element with id 4\n");
    pq.delete(4);
    for (int i = 0; i<N; i++)
        print(" " + pq.pq[i]);




}

}
