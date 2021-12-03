package Chapter_2_3.creativeProblems;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import javax.lang.model.element.VariableElement;

public class RecursionDepth {


    static int recursionDepth;

    static int M;

    // this version of quick sort computes the number of recursion depth
    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }
    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }

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


    public static int sort(Comparable[] a, int sizeCutoff)
    {
        M = sizeCutoff;
        recursionDepth = 0;
//        StdRandom.shuffle(a); // Eliminate dependence on input.
        sort(a, 0, a.length - 1, 1);
        return recursionDepth;
    }
    private static void sort(Comparable[] a, int lo, int hi, int depth)
    {
        if (hi <= lo) return;

        if (hi - lo + 1 < M){
            selectionSort(a,lo,hi);
            return;
        }


        if (depth > recursionDepth) recursionDepth = depth;

        int j = partition(a, lo, hi); // Partition (see page 291).
        sort(a, lo, j-1, depth+1); // Sort left part a[lo .. j-1].
        sort(a, j+1, hi, depth+1); // Sort right part a[j+1 .. hi].
    }

    private static int partition(Comparable[] a, int lo, int hi)
    { // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi+1; // left and right scan indices
        Comparable v = a[lo]; // partitioning item
        while (true)
        { // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); // Put v = a[j] into position
        return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
    }


    public static double MeanOfDoubleArray(double[] a){
        double sum = 0;
        for (int i = 0; i<a.length; i++) sum += a[i];
        return sum / a.length;
    }
    public static double VarianceOfDoubleArray(double[] a, double arrayMean){
        double sum = 0;
        for (int i = 0; i<a.length; i++)
            sum += (a[i] - arrayMean) * (a[i] - arrayMean);
        return sum / (a.length - 1);
    }



    public static void main(String[] args){



        int[] M_values = {10,20,50};
        int[] N_values = {1000,10000,100000};

        double depthMean;
        double depthStdDev;
        int sampleSize = 300;
        double[] empiricalDepths = new double[sampleSize];


        Integer[] a;
        for (int m : M_values){
            System.out.println("        For M = " + m);
            for (int n : N_values){

                a = new Integer[n];

                for (int i = 0 ; i < sampleSize; i++){

                    // generate randomized array
                    for (int j = 0; j < n ; j++) a[j] = j;
                    StdRandom.shuffle(a);
                    empiricalDepths[i] = sort(a, m);
                }
                depthMean = MeanOfDoubleArray(empiricalDepths);
                depthStdDev = Math.sqrt(VarianceOfDoubleArray(empiricalDepths, depthMean));

                StdOut.printf("N = %d, mu_depth = %.3f, mu_stddev = %.3f\n", n, depthMean, depthStdDev);


            }
        }
    }
}
