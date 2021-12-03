package Chapter_2_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QuickNumberOfCompare
{

    private static int numberOfCompare;

    // Note: this version of quicksort counts the number of exchanges


    private static boolean less(Comparable v, Comparable w)
    { numberOfCompare++; return v.compareTo(w) < 0; }
    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t;  }

    public static int sort(Comparable[] a)
    {
        numberOfCompare = 0;
        //StdRandom.shuffle(a); // Eliminate dependence on input.
        sort(a, 0, a.length - 1);
        return numberOfCompare;
    }
    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo) return;
        int j = partition(a, lo, hi); // Partition (see page 291).
        sort(a, lo,  -1); // Sort left part a[lo .. j-1].
        sort(a, j+1, hi); // Sort right part a[j+1 .. hi].
    }

    private static int partition(Comparable[] a, int lo, int hi)
    { // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi+1; // left and right scan indices
        Comparable v = a[lo]; // partitioning item
        while (true)
        { // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], v))
                if (i == hi)
                    break;
            while (less(v, a[--j]))
                if (j == lo)
                    break;
            if (i >= j) break;
            exch(a, i, j);
        }
//        numberOfCompare++; // it takes exactly one compare operation to exit the loop

        exch(a, lo, j); // Put v = a[j] into position
        return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
    }


    public static void main(String[] args){

        // Check whether these arrays require the maximal number of comparison in quick sort


//        Integer[] a = {12,11,10,9,8,7,6,5,4,3,2,1};
        //Integer[] a = {7,6,5,4,3,2,1};
        Integer[] a = {5,4,3,2,1};

        int n = a.length;
        int abc = QuickNumberOfCompare.sort(a);

        StdOut.printf("Expected number of compares = %d, Empirical number of compares = %d\n", (n+1)*n/2,abc);

        

    }



}


