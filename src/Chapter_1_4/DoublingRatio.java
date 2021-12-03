package Chapter_1_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Timer;

public class DoublingRatio
{

    public static double timeTrial(int N, int occurence)
    { // Time ThreeSum.count() for N random 6-digit ints.
        int MAX = 1000000;
        int[] a = new int[N];

        double result = 0;

        for (int c = 0 ; c < occurence; c++){
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform(-MAX, MAX);

            long startTime = System.currentTimeMillis();
            //Stopwatch timer = new Stopwatch();

            // -> time-tested function / statement goes here:
            //int cnt = ThreeSum.count(a);
            FixedCapacityStack<Integer> stackItem = new FixedCapacityStack<Integer>(N);
            for (int i = 0; i<N; i++) stackItem.push(i);
            for (int i = 0; i<N; i++) stackItem.pop();

//        FixedCapacityStackOfInts stackItem = new FixedCapacityStackOfInts(N);
//        for (int i = 0; i<N; i++) stackItem.push(i);
//        for (int i = 0; i<N; i++) stackItem.pop();
            double delta_time = (double)(System.currentTimeMillis() - startTime)/1000;
            result += delta_time;
        }

        return result / occurence;

    }

    public static void main(String[] args)
    {

        double prev = timeTrial(125, 10);
        StdOut.printf("%6d %7.4f\n ", 125, prev);
        for (int N = 250; true; N += N)
        {
            double time = timeTrial(N,10);
            StdOut.printf("%6d %7.4f ", N, time);
            StdOut.printf("%5.4f\n", time/prev);
            prev = time;
        }
    }
}