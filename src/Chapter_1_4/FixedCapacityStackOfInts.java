package Chapter_1_4;

public class FixedCapacityStackOfInts {
    private int[] a; // stack entries
    private int N; // size

    public FixedCapacityStackOfInts(int cap)
    { a = new int[cap]; }

    public boolean isEmpty() { return N == 0; }

    public int size() { return N; }

    public void push(int item)
    { a[N++] = item; }

    public int pop()
    { return a[--N]; }
}


