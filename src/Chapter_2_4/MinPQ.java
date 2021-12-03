package Chapter_2_4;

public class MinPQ<Key extends Comparable<Key>>

{
    private Key[] pq; // heap-ordered complete binary tree
    private int N = 0; // in pq[1..N] with pq[0] unused

    public MinPQ(int maxN)
    { pq = (Key[]) new Comparable[maxN+1]; }

    public MinPQ()
    { pq = (Key[]) new Comparable[10]; }


    // added for Q2.4.19
    public MinPQ(Key[] a){

        N = a.length;
        pq = (Key[]) new Comparable[N + 1];
        for (int i = 1; i < N+1; i++)
            pq[i] = a[i-1];
        for (int i = N/2; i > 0; i--)
            sink(i);

    }
    public void display(){
        for (int i = 1; i<N+1; i++)
            System.out.print(pq[i] + " ");
    }

    // added for Q2.4.22
    private void CheckForArrayResize(){
        Key[] temp;
        if (N > pq.length * 4){
            temp = (Key[]) new Comparable[(N+1)/2];
            for (int i = 1; i<N; i++)
                temp[i] = pq[i];
            pq = temp;
        }
        else if (pq.length == N+1){
            temp = (Key[]) new Comparable[(N+1)*2];
            for (int i = 1; i<N; i++)
                temp[i] = pq[i];
            pq = temp;
        }
    }

    public boolean isEmpty()
    { return N == 0; }

    public int size()
    { return N; }

    public void insert(Key v)
    {
        pq[++N] = v;
        swim(N);
    }
    public Key delMin()
    {
        Key max = pq[1]; // Retrieve max key from top.
        exch(1, N--); // Exchange with last item.
        pq[N+1] = null; // Avoid loitering.
        sink(1); // Restore heap property.
        return max;
    }
    // See pages 145-147 for implementations of these helper methods.
    private boolean less(int j, int i) { // <- swapped argument from MaxPQ
        return pq[i].compareTo(pq[j]) < 0;
    }
    private static boolean less(Comparable w, Comparable v)
    { return v.compareTo(w) < 0; }

    private void exch(int i, int j) {
        Key t = pq[i]; pq[i] = pq[j]; pq[j] = t;
    }

    // implemented for Q2.4.26
    private void SwimFast(int k){
        Key val2swim = pq[k];
        while (k > 1 && less(pq[k/2], val2swim))
        {
            pq[k] = pq[k/2];
            k = k/2;
        }
        pq[k] = val2swim;
    }

    private void SwimRegular(int k){
        while (k > 1 && less(k/2, k))
        {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void swim(int k) {
        SwimFast(k);
        // SwimRegular(k);
    }



    private void sink(int k){
        SinkRegular(k);
        // SinkFast(k);
    }

    private void SinkRegular(int k) {

        while (2*k <= N)
        {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    // implemented for Q2.4.26
    private void SinkFast(int k){

        Key val2Sink = pq[k];

        while (2*k <= N)
        {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(val2Sink, pq[j])){
                // k is the final destination of Val2sink
                break;
            }
            pq[k] = pq[j];
            k = j;

        }
        pq[k] = val2Sink;

    }

    public void Display(){
        for (int i = 1; i<N+1; i++)
            System.out.printf("%d ",pq[i]);
    }

    public static void main(String[] args){
        Integer[] a = {1,4,5,3,6,5,2,76,34,52,-60};
        MinPQ maxpq = new MinPQ(a);
        maxpq.display();

    }

}
