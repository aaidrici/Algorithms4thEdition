package Chapter_2_3.creativeProblems;
import Chapter_2_1.Exercises.Insertion;
import Chapter_2_3.Quick;
import edu.princeton.cs.algs4.StdRandom;
import Chapter_2_1.Exercises.Certification;
public class SampleSort {

    static int k = 3; // sample sort parameter


    public static void selectionSort(Comparable[] a, int lo, int hi){

        int tempMin = 0;

        for (int i = lo; i< hi; i++){
            tempMin = i;
            for (int j = i+1; j<=hi; j++){
                if (less(a[j],a[tempMin]))
                    tempMin = j;
            }
            exch(a,i,tempMin);
        }
    }

    public static int pow(int a, int b){
        // returns a^b (assuming both a and b are positive)
        int c = 1;
        for (int i = 0; i < b; i++)
            c *=a ;
        return c;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort(Comparable[] a) {
//        StdRandom.shuffle(a); // Eliminate dependence on input.
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi); // Partition (see page 291).
        sort(a, lo, j - 1); // Sort left part a[lo .. j-1].
        sort(a, j + 1, hi); // Sort right part a[j+1 .. hi].
    }

    // the sample sort variant of quick sort only affect the partition() method
    private static int partition(Comparable[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi + 1; // left and right scan indices

        Comparable v;

        // determine if the array is large enough to apply sample sort:
        if (hi - lo + 1 > pow(2,k)-1){
            // arbitrarily take the first k^2 - 1 elements of the array
            // assume k^2-1 is small enough so that sample is sorted using insertion sort
            selectionSort(a,lo, lo + pow(2,k));
            int sampleHalfSize = (pow(2,k)-1) / 2;
            lo += sampleHalfSize;
            hi -= sampleHalfSize;
            v = a[lo];

            // everything on the left of v must remain there. Everything on the right must change place
            for (i = 0; i < sampleHalfSize; i++)
                exch(a,lo+1+i,hi-i-1 );
        }
        else
            v = a[lo]; // partitioning item


        while (true) { // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); // Put v = a[j] into position
        return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].

    }



    public static void main(String[] args) {

        int N = 20;
        Integer[] a = new Integer[N];
        Integer[] a_copy = new Integer[N];
        for (int i = 0; i<N; i++){
            a[i] = StdRandom.uniform(0,N);
            a_copy[i] = a[i];
        }

        for (Integer x : a) System.out.print(x + " ");
        System.out.println();

        selectionSort(a,0,a.length-1);

        for (Integer x : a) System.out.print(x + " ");
        System.out.println();

        if (Certification.check(a,a_copy))
            System.out.print("sorting keeps values intact");


    }

}



