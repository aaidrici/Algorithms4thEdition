package Chapter_1_4;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class DoublingTest
{
    public static double timeTrial(int N)
    { // Time ThreeSum.count() for N random 6-digit ints.
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-MAX, MAX);
        Stopwatch timer = new Stopwatch();
        int cnt = ThreeSum.count(a);
        return timer.elapsedTime();
    }
    public static void main(String[] args)
    { // Print table of running times.

        int n_pts = 6;

        int[] x_values = new int[n_pts];
        double[] y_values = new double[n_pts];

        x_values[0] = 100;
        for (int i = 1; i < x_values.length; i++)  x_values[i] = x_values[i-1]*2;

        int ind = 0;
        for (int N : x_values){
            double time = timeTrial(N);
            y_values[ind++] = time;
            StdOut.printf("%7d %5.4f\n", N, time);
        }

        // illustrate plot
        StdDraw.setPenRadius(0.005);


        double X_axis_min = Math.log10(x_values[0]);
        double X_axis_max = Math.log10(x_values[n_pts -1]);
        double Y_axis_min = Math.log10(y_values[0]);
        double Y_axis_max = Math.log10(y_values[n_pts -1]);

        StdDraw.setYscale(Y_axis_min, Y_axis_max);
        StdDraw.setXscale(X_axis_min, X_axis_max);

        for (int i = 0; i<n_pts; i++){
            double x = Math.log10((double)x_values[i]);
            double y = Math.log10((double)y_values[i]);
            StdDraw.point(x, y);
        }




    }
}