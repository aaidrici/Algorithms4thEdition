package Chapter_2_4.CreativeProblems;

import java.util.Timer;
import edu.princeton.cs.algs4.StdRandom;
import java.lang.System;

public class HeapConstructionTime {



    public static boolean less(Comparable a, Comparable b){ return a.compareTo(b) < 0; }

    public static void Swap(Comparable[] a, int i, int j){Comparable t = a[i]; a[i] = a[j]; a[j] = t; }

    private static void sink(Comparable[] a, int k, int N){

        while (k*2 <= N){
            int j = k*2;
            if (j+1 <= N && less(a[j],a[j+1])) j++;
            if (less(a[k], a[j])){
                Swap(a,j,k);
                k = j;
            }
            else break;
        }
    }


    private static void swim(Comparable[] a, int k){
        while (k/2 > 0){
            if (less(a[k/2], a[k])){
                Swap(a,k,k/2);
                k /= 2;
            }
            else break;
        }
    }



    public static double HeapSort(Comparable[] a ){


        int N = a.length - 1;

        long t0 = System.currentTimeMillis();

        // heap construction
        for (int i = N/2; i > 0;i--)
            sink(a,i, a.length-1);

        long t1 = System.currentTimeMillis();

        // removeMin(){
        for (int i = N; i > 0; i--){
            Swap(a,i,1);
            sink(a,1, --N);
        }

        long t2 = System.currentTimeMillis();


        if (t2 == t0)
            return -1;
        else
            return ((double)(t1 - t0))/(t2 - t0);

    }


    public static double mean(double[] a){
        // discard all negative values;
        int N = a.length;
        double sum = 0;
        for (int i = 0; i<a.length; i++)
            if (a[i] < 0)
                N--;
            else
                sum+= a[i];
        return sum / a.length;
    }

    public static double stdVar(double[] a, double mean){
        double sum = 0;
        for (int i = 0; i<a.length; i++)
            sum += (a[i] - mean)*(a[i] - mean);
        return Math.sqrt(sum / (a.length));
    }

    public static void main(String[] args){


        int sampleSize = 100;
        double[] timeRatio = new double[sampleSize];

        System.out.printf("Based on sample size of %d\n", sampleSize);

        int [] Nvalues = {10000, 100000, 1000000};

        for (int N : Nvalues){
            Integer[] a = new Integer[N];

            for (int s = 0; s < sampleSize; s++){

                for (int i = 1; i<N; i++)
                    a[i] = StdRandom.uniform(0,100);
                timeRatio[s] = HeapSort(a);

            }

            double meanProportion = mean(timeRatio);
            System.out.printf("N = %d\n", N);
            System.out.printf("Mean proportion of time allocated to heap construction = %.4f (variance = %.4f)\n", meanProportion, stdVar(timeRatio,meanProportion) );

        }




    }
}
