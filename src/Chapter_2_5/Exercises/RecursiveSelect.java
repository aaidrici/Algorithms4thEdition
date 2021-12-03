package Chapter_2_5.Exercises;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class RecursiveSelect{

    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }
    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }



    private static int partition(Comparable[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi + 1; // left and right scan indices
        Comparable v = a[lo]; // partitioning item
        while (true) { // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); // Put v = a[j] into position
        return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
    }

    public static Comparable select (Comparable[] a, int k, int lo, int hi){

        if (hi > lo){
            int j = partition(a, lo, hi);
            if (j == k) return a[k];
            else if (j > k) return select (a, k, lo, j-1);
            else if (j < k) return select (a, k, j + 1, hi);
            return a[k]; // this is a dummy line, not sure why the compiler forces me to put this...
        }
        else
            return a[k];

    }


    public static Comparable select (Comparable[] a, int k){
        StdRandom.shuffle(a);
        return select (a, k, 0, a.length -1);
    }



    public static void main(String[] args){
        String[] a = {"lolita", "ayy", "drugs", "shall", "reconsider", "alpha", "pleasant"};
        Arrays.sort(a);
        for (String x : a) System.out.print(x + " ");
        System.out.println();

        for (int i = 0; i<a.length; i++)
            System.out.print(select(a, i) + " ");

    }

}
