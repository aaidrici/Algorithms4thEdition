package Chapter_2_1.CreativeProblems;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class PrimitiveTypeComparison {


    public static double timeSortComparables (Comparable[] a){
        Stopwatch timer = new Stopwatch();
        InsertionSort.sort(a);
        return timer.elapsedTime();
    }

    public static double timeSortInts (int[] a){
        Stopwatch timer = new Stopwatch();
        InsertionSortofInts.sort(a);
        return timer.elapsedTime();
    }


    public static void main(String[] args){

        double totalTimeForInt = 0;
        double totalTimeForIntegers = 0;

        int T = 100;
        int N = 2000;
        int attempt = 1000;

        for (int k = 0; k < attempt; k++){
            // generate int[] array
            int[] a = new int[N];
            for (int i = 0; i < N; i++) a[i] = StdRandom.uniform(-T,T);

            // convert int[] to Integer[]
            int i  = 0;
            Integer[]  a2 = new Integer[N];
            for (int x : a){ a2[i++] = Integer.valueOf(x); }

//            for (Integer x: a2) System.out.print(x + " ");
//            System.out.println();
//            for (int x: a) System.out.print(x + " ");
//            System.out.println();



            totalTimeForInt += timeSortInts(a);
            totalTimeForIntegers += timeSortComparables(a2);

        }

        double timeRatio = totalTimeForInt / totalTimeForIntegers;

        StdOut.printf("%.4f", timeRatio);


    }


}


