package Chapter_1_4;

import Chapter_1_4.CreativeProblems.ThreeSumFastt;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.ThreeSumFast;
import java.lang.Math;

public class TimeEstimation {



    // this program is meant to estimate the amount of time it would take to ru
    //   1. TwoSumFast, TwoSum, ThreeSumFast and ThreeSum for one million elements

    public static double timeTrial(int N, int occurence)
    {  // return times in take to run a function in ms
        int MAX = 1000000;
        int[] a = new int[N];

        double result = 0;

        for (int c = 0 ; c < occurence; c++){
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform(-MAX, MAX);

            long startTime = System.currentTimeMillis();

            // apply following command here
//
//            TwoSumFast.count(a);
//            TwoSum.count(a);
//            ThreeSum.count(a);
            ThreeSumFastt.count(a);

            long endTime = System.currentTimeMillis();

           double delta_time = (double)(System.currentTimeMillis() - startTime)/1000;
            result += delta_time;
        }

        return result / occurence;

    }


    public static void main(String[] args){

        double initialArraySize = 500;
        int occurrence = 5;
        double expFact = 1.5;
        double Time;
        double prevTime;

        prevTime = timeTrial((int)Math.round(initialArraySize), occurrence);
        System.out.print("Elapsed Time = " + prevTime);

        for (double N = 500 * expFact; true; N *= expFact){
            Time = timeTrial((int)Math.round(N), occurrence);
            double ratio = Time/prevTime;

            double b_approx = Math.log(ratio)/Math.log(expFact);
            double c_approx = Time/Math.pow(N,b_approx);
            double time_approx = (c_approx * Math.pow(1000000, b_approx));

            String timeString = String.format("%.4f", Time);
            String TimeToOneMillionApprox = String.format("%.2f", time_approx);

            String timeEstimateToOneMillion = String.format("%.1f",Time/1000);
            System.out.println("fct eval time [ms] = " + timeString + ", approx. time to one million [s] = " + TimeToOneMillionApprox);
            prevTime = Time;
        }


    }



}
