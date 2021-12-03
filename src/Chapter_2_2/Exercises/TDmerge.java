package Chapter_2_2.Exercises;

import edu.princeton.cs.algs4.StdRandom;
import MiscTools.Plots;


public class TDmerge {

    private static Comparable[] aux;

    private static int arrayAccessCount;

    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }

    public static void sort(Comparable[] a)
    {
        arrayAccessCount = 0;
        aux = new Comparable[a.length]; // Allocate space just once.
        sort(a, 0, a.length - 1);
    }


    public static void merge(Comparable[] a, int lo, int mid, int hi)
    { // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++){
            aux[k] = a[k];
            arrayAccessCount += 2;
        } // Copy a[lo..hi] to aux[lo..hi].


        for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
            if (i > mid){
                a[k] = aux[j++];
                arrayAccessCount += 2;
            }
            else if (j > hi ){
                a[k] = aux[i++];
                arrayAccessCount += 2;
            }
            else if (less(aux[j], aux[i])){
                a[k] = aux[j++];
                arrayAccessCount += 4;
            }
            else{
                a[k] = aux[i++];
                arrayAccessCount += 2;
            }
    }

    public static int getArrayAccessCount(){
        return arrayAccessCount;
    }


    private static void sort(Comparable[] a, int lo, int hi)
    { // Sort a[lo..hi].
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid); // Sort left half.
        sort(a, mid+1, hi); // Sort right half.
        merge(a, lo, mid, hi); // Merge results (code on page 271).
    }



    public static void main(String[] args) {

        double[] N_values = new double[512];
        double[] arrayAccessNormalized = new double[512];
        int count = 0;
        int maxArrayCount = 0;

        int m = 100; // sample size

        for (int N = 5; N <= 512 ; N++){



            // compute max number of array access for a given value of N
            maxArrayCount = 0;
            for (int j = 0; j < m; j++) {
                Integer[] a = new Integer[N];
                for (int i = 0; i < N; i++) {
                    a[i] = StdRandom.uniform(0, N);
                }
                TDmerge.sort(a);
                int arrCount = getArrayAccessCount();
                if (arrCount > maxArrayCount){
                    maxArrayCount = arrCount;
                }
            }


            N_values[count] = N;
            arrayAccessNormalized[count++] = maxArrayCount / (6 * N*Math.log(N)/Math.log(2));
        }
        Plots.PlotOfDouble(N_values, arrayAccessNormalized);

        // It is possible to see on this graph that at most the 6N*logN value is reach at 84% in this case
        // It expected that this value approaches 1.0  as 'm' increase.

    }







}
