package Chapter_2_1.CreativeProblems;

import Chapter_2_1.Exercises.Certification;
import Chapter_2_1.Exercises.ShellWithArray;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class GeometricIncrement {


    public static void main(String[] args){



        int N = 100000;
        Integer[] a = new Integer[N];

        int[] t_values = {2,3,4,5,6,7,8};
        double[] timeValues = new double[t_values.length];
        int attemptNo = 1;
        long startTime;
        double totalTime;
        int t_count = 0;





        for (int i = 0; i < attemptNo; i++){

            // generated a random array to be tested against & create its copy
            for (int k = 0; k<N ; k++){a[k] = StdRandom.uniform(0,N); }
            Integer[] a_origin = Arrays.copyOf(a,a.length);

            t_count = 0;
            for (int t : t_values){

                startTime = System.currentTimeMillis();
                ShellWithArray.sort(a, t);
                totalTime = (double)(System.currentTimeMillis() - startTime);
                timeValues[t_count++] = totalTime;

                //StdOut.printf("t = %d, mean time [ms] = %.1f\n",t, totalTime / attemptNo);
                a = Arrays.copyOf(a_origin,a_origin.length);
            }
        }

        t_count = 0;
        for (int t : t_values){
            StdOut.printf("t = %d, mean time [ms] = %.1f\n",t, timeValues[t_count++] / attemptNo);
        }



    }
}
