package Chapter_2_4.CreativeProblems;

import Chapter_2_4.MaxPQ;
import MiscTools.Plots;
import edu.princeton.cs.algs4.StdRandom;

public class PerformanceDriver_II {


    public static void main(String[] args){



        int N = 1000000;

        MaxPQ pq = new MaxPQ(N);


        int n_trials = 100;
        double[] trials = new double[n_trials];
        double[] timeVals = new double[n_trials];

        for (int j = 0; j<n_trials; j++){

            long t0 = System.currentTimeMillis();

            for (int i = 0; i<N; i++) pq.insert(StdRandom.uniform(0,10*N));
            for (int i = 0; i<N/1; i++) pq.delMax();
            for (int i = 0; i<N/1; i++) pq.insert(StdRandom.uniform(0,10*N));
            while (!pq.isEmpty()) pq.delMax();

            timeVals[j] = (double)(System.currentTimeMillis() - t0);
            trials[j] = (double)j;

        }


        Plots.PlotOfDouble(trials, timeVals);

        for (int i = 0; i < n_trials; i++)
            System.out.printf(trials[i] + " ");
        System.out.println();

        for (int i = 0; i < n_trials; i++)
            System.out.printf(timeVals[i] + " ");
        System.out.println();

    }

}
