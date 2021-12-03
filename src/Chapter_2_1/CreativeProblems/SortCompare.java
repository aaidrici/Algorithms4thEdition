package Chapter_2_1.CreativeProblems;

import Chapter_2_1.Exercises.ShellWithArray;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import Chapter_2_1.Exercises.Selection;

public class SortCompare
{




    public static double time(String alg, Double[] a)
    { Stopwatch timer = new Stopwatch();
        if (alg.equals("InsertionSort")) InsertionSort.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("InsertionWithSentinel")) InsertionWithSentinel.sort(a);
        if (alg.equals("InsertionSortWithoutExchanges")) InsertionSortWithoutExchanges.sort(a);
        if (alg.equals("ShellSortWithSelection")) ShellSortWithSelection.sort(a);
        //if (alg.equals("ShellSort")) ShellSort.sort(a);
        if (alg.equals("ShellSortDefaultArray")) {
            ShellWithArray.sort(a, ShellWithArray.arrayMethod.Exercise2_1_11_array);
        }
        if (alg.equals("ShellSort_Q2_1_29_Array")) {
            ShellWithArray.sort(a, ShellWithArray.arrayMethod.Exercise2_1_29_array);
        }
//        if (alg.equals("Merge")) Merge.sort(a);
//        if (alg.equals("Quick")) Quick.sort(a);
//        if (alg.equals("Heap")) Heap.sort(a);
        return timer.elapsedTime(); }



//    public static double timeRandomInput(String alg, int N, int T, Type[] a){
//
//    }

    public static double timeRandomInput(String alg, int N, int T)
    { // Use alg to sort T random arrays of length N.
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++)
        { // Perform one experiment (generate and sort an array).
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }


    public static double SpeedRatio(String alg2, String alg1,int arraySize, int attemptNumber){
        int N = arraySize; // array size
        int T = attemptNumber; // number of attempt

        double t1 = timeRandomInput(alg1, arraySize, attemptNumber); // total for alg1
        double t2 = timeRandomInput(alg2, arraySize, attemptNumber); // total for alg2
//        StdOut.printf("%.4f\n", t2);
//        StdOut.printf("%.4f\n", t1);


        return t2/t1;
    }


    public static void main(String[] args)
    {
//        String alg1 = args[0];
//        String alg2 = args[1];
//        int N = Integer.parseInt(args[2]);
//        int T = Integer.parseInt(args[3]);

        String alg1 = "InsertionSort";
        String alg2 = "InsertionWithSentinel";
        int N = 500; // array size
        int T = 1000; // number of attempt


        double t1 = timeRandomInput(alg1, N, T); // total for alg1
        double t2 = timeRandomInput(alg2, N, T); // total for alg2
        StdOut.printf("For %d random Doubles\n %s is", N, alg1);
        StdOut.printf(" %.3f times faster than %s\n", t2/t1, alg2);
    }
}