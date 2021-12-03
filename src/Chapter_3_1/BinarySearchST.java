package Chapter_3_1;

import edu.princeton.cs.algs4.BinarySearch;

import static edu.princeton.cs.algs4.BinaryStdIn.isEmpty;

import Chapter_2_2.CodeProvidedInBook.TopDownMergeSort;

import java.util.Comparator;

public class BinarySearchST<Key extends Comparable<Key>, Value>
{


//    class ValueComparator <Value extends Comparable<Value>> implements Comparator<Value>{
//        public int compare(Value v, Value v2){
//            return v.compareTo(v2);
//        }
//    }


    protected Entry<Key, Value>[] item;

    protected int N;

    public BinarySearchST(){}

    public BinarySearchST(int capacity) {
        // See Algorithm 1.1 for standard array-resizing code.
        item = new Entry[capacity];
        for (int i = 0; i<capacity; i++) item[i] = new Entry(null, null);
        N = 0;
    }

    public void sort(){
        TopDownMergeSort.intervalSort(item, 0, N-1);
    }

    public int size() {
        return N;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && item[i].key.compareTo(key) == 0) return item[i].value;
        else return null;
    }

    public int rank(Key key) {
        int lo = 0, hi = N-1;
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo((Key)item[mid].key);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val) {
        // Search for key. Update value if found; grow table if new.
        int i = rank(key);
        if (i < N && item[i].key.compareTo(key) == 0)
        { item[i].value = val; return; }
        for (int j = N; j > i; j--)
        { item[j].key = item[j-1].key; item[j].value = item[j-1].value; }
        item[i].key = key; item[i].value = val;
        N++;
    }

    public void delete(Key key){
        for (int i = 0; i<N; i++)
            if (item[i].key.compareTo(key) == 0){
                // target hit
                for (int j = i; j<N-1; j++)
                    item[j] = item[j+1];
                N--;
                return;
            }
    }

    public Key floor(Key key){
        for (int i = N-1; i >= 0; i--)
            if (item[i].key.compareTo(key) <= 0)
                return item[i].key;

        return null;
    }

    public void display(){
        for (int i = 0; i<N; i++)
            System.out.println(item[i].key + " - " + item[i].value);
        System.out.println();
    }

    public Key min() { return item[0].key; }

    public Key max() { return item[N-1].key; }

    public Key select(int k) { return item[k].key; }

    public static void main(String[] args){
        BinarySearchST<Integer, String> a = new BinarySearchST<Integer, String>(10);
        a.put(33,"sophie");
        a.put(27, "Julia");
        a.put(25, "Cassandre");
        a.put(13, "Iris");

        a.display();

        a.sort();

        a.display();

        a.delete(-5);

        a.display();

        System.out.print(a.floor(25));
    }
}

