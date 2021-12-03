package Chapter_2_2.CreativeProblems;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;


public class sublinearExtraSpace {

    public static void sort(Comparable[] a)
    {
        Comparable[] aux = new Comparable[a.length]; // Allocate space just once.
        sort(a,aux, 0, a.length - 1);
    }

    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }

    public static void merge(Comparable[] a, int lo, int mid, int hi)
    { // Merge a[lo..mid] with a[mid+1..hi].


        int M = 4;
        int segmentSize = (hi-lo+1)/M;
        Comparable[] aux = new Comparable[M];

        int i = 0;// keep track of the beginning of each of two sorted parts
        int j = mid+1;

        while (i < j && j <= hi){
            if (less(a[i], a[j])) // no transformation to make
                i += segmentSize;
            else{
                // copy block [j, j + segmentSize - 1]
                int L = 0;
                for (int k = 0; k < segmentSize && (j+k) <= hi ; k++){
                    aux[k] = a[j+k];
                    L++;
                }
                // shift block [i, j-1] by 'segmentSize' times to the right
                for (int k = j-1;  k >= i; k--)
                    a[k + L] = a[k];
                // copy element
                for (int k = 0; k < L; k++)
                    a[k+i] = aux[k];
                j += L;
                i += L; 

                // sort what was added to the remaining value
                PartialInsertionSort(a, minOf(i - 1 - 2*L,lo), i-1);

            }
        }
    }
    private static int minOf(int a, int b){
        if (a < b) return a;
        else return b;
    }

    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
    { // Sort a[lo..hi].

        if (hi-lo+1 < 15){
            PartialInsertionSort(a, lo, hi);
            return;
        }


        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, aux, lo, mid); // Sort left half.
        sort(a, aux, mid+1, hi); // Sort right half.
        merge(a, lo, mid, hi); // Merge results (code on page 271).
    }

    public static void PartialInsertionSort(Comparable[] a, int l_id, int r_id){
        for (int i = l_id; i <= r_id-1; i++)
        { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
            int minId = i;
            for (int j = i; j <= r_id; j++) {
                if (less(a[j], a[minId]))
                    minId = j;
            }
            exch(a, minId, i);
        }
    }



    public static void main(String[] args){

        int N = 35;
        Integer[] a = new Integer[N];
        for (int i = 0; i<N; i++){
            a[i] = StdRandom.uniform(0,N);
        }

        sort(a);

    }




}


