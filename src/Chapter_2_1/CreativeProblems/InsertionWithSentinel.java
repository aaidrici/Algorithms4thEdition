package Chapter_2_1.CreativeProblems;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class InsertionWithSentinel
{


    public static void sort(Comparable[] a) { // Sort a[] into increasing order.
        int N = a.length;

        // modification to InsertionSort: put smallest item first
        int smallestValueId = 0;
        Comparable smallest = a[0];
        for (int i = 1; i < N; i++){
            if (less(a[i], smallest)) {
                smallestValueId = i;
                smallest = a[i];
            }
        }
        exch(a, 0,smallestValueId);

        for (int i = 1; i < N; i++)
        { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
            for (int j = i; less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
        }
    }
    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }


    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }


    public static void main(String[] args) {

        int N = 20;
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(0, 10);
        }

        for (int x : a) System.out.print(x + " ");
        System.out.println();

        sort(a);
        for (int x : a) System.out.print(x + " ");


    }
}

