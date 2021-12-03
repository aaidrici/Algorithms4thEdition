package Chapter_2_1.CreativeProblems;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ShellSortWithSelection {

    public static void sort(Comparable[] a)
    { // Sort a[] into increasing order.
        int N = a.length;
        int h = 1;
        while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
        while (h >= 1)
        { // h-sort the array.
            for (int startId = 0; startId < h; startId++){
                for (int k = startId; k < N ; k+= h){
                    int minId = k;
                    // find min value
                    for (int i = k + h; i < N; i += h){ if (less(a[i],a[minId])) minId = i; }
                    exch(a,k,minId);
                }
            }
            h = h/3;
        }
    }
// See page 245 for less(), exch(), isSorted(), and main().


    private static void show(Comparable[] a)
    { // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }

    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }






    public static void main(String[] args){

        double timeRatio;



        int arraySize = 128;
        while(true){
            StdOut.printf("array size: %d", arraySize);
            timeRatio = SortCompare.SpeedRatio("ShellSortWithSelection", "ShellSort", arraySize, 1000 );
            StdOut.printf(", time ratio: %.5f\n", timeRatio);
            arraySize *= 2;
        }


    }



}
