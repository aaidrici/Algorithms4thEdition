package Chapter_2_1.Exercises;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ShellSortWorstCase {

    private static int comparesCount;

    // This version of Shell sort uses the specific h_values arrays =  {40, 13, 4, 1};
    // and outputs the total number of compares used.
    public static int sort(Comparable[] a)
    { // Sort a[] into increasing order.
        int N = a.length;
        comparesCount = 0;

        int[] h_values = {40, 13, 4, 1};


        for (int h : h_values){
            // h-sort the array.
            for (int i = h; i < N; i++)
            { // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                    exch(a, j, j-h);
            }
        }
        return comparesCount;
    }

    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }

    private static boolean less(Comparable v, Comparable w) {
        comparesCount++ ;
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a)
    { // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }



    public static void main(String[] args){

        int N = 100;
        Integer a[] = new Integer[N];
        int maxCount = 0;
        int currentCount = 0;



        for (int i = 1; i < 10000001; i++){

            // generate random array
            for ( int j = 0; j<N; j++){
                a[j] = StdRandom.uniform(1,101);
            }
            // determine max numbers of compares obtained so far
            currentCount = sort(a);
            if (currentCount > maxCount) maxCount = currentCount;

            // display max count at every 100 attempt increment
            if (i % 100000 == 0){
                System.out.println("Sort attempt number: " + i + ", max number of compares so far: " +  maxCount);
            }

        }
    }
}

