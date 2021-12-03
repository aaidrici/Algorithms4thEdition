package Chapter_2_3.creativeProblems;

import Chapter_2_3.Quick;
import Chapter_2_3.creativeProblems.HistogramGenerator;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class SubarraySizeForDifferentCutoff {

    static double[] bin;
    static int M;

    // the use of selectionSort as opposed to InsertionSort will not impact the result of this problem
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



    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort(Comparable[] a, Dummy dummy) {
        M = dummy.a.length;
        bin = new double[M];
//        StdRandom.shuffle(a); // Eliminate dependence on input.
        sort(a, 0, a.length - 1);
        dummy.a = Arrays.copyOf(bin, bin.length);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        if (hi - lo + 1 < M){
            selectionSort(a,lo,hi);
            bin[hi - lo +1] ++;
            return;
        }

        int j = partition(a, lo, hi); // Partition (see page 291).
        sort(a, lo, j - 1); // Sort left part a[lo .. j-1].
        sort(a, j + 1, hi); // Sort right part a[j+1 .. hi].
    }

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


    public static void main(String[] args) {

        int N = 100000;
        Comparable[] a = new Comparable[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0,N);

        // generate empty bin to be returned by quick sort
        int M = 30;
        double[] binContent = new double[M];
        double[] binCategory = new double[M];

        for (int i = 0; i<M; i++) {
            binContent[i] = 0.0;
            binCategory[i] = i;
        }


        Dummy dummy = new Dummy();
        dummy.a = new double[M];


        sort(a,dummy);

        HistogramGenerator.PlotHistogram(binCategory, dummy.a);

        // result short discussion:
        // it seems the distribution in array size when the remaining array doesn't pass the cut off is
        // uniformly distributed



    }
}

class Dummy{
    public double[] a;
}