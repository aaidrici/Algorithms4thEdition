package Chapter_1_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import Chapter_1_4.ThreeSum;

import static Chapter_1_4.ThreeSum.count;

public class ThreeSumVsNaiveThreeSum {



    private static int NaiveThreeSum(int[] a){
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    if (i < j && j < k)
                        if (a[i] + a[j] + a[k] == 0)
                            cnt++;
        return cnt;
    }

    private static int ThreeSum(int [] a){
        int N = a.length;
        long summation = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                for (int k = j+1; k < N; k++){
                    if(a[i] + a[j] + a[k] == 0)
                        if (summation == 0)
                            cnt++;
                }

        return cnt;
    }


    public static double TimeRatio(int N)
    { // Time ThreeSum.count() for N random 6-digit ints.
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-MAX, MAX);


        long timeStamp01 = System.currentTimeMillis();
        ThreeSum(a);
        long timeStamp02 = System.currentTimeMillis();
        NaiveThreeSum(a);
        long timeStamp03 = System.currentTimeMillis();

        double deltaT01 = (double)(timeStamp02 - timeStamp01);
        double deltaT02 = (double)(timeStamp03 - timeStamp02);
//        System.out.print(deltaT01 + " ");
//        System.out.println(deltaT02 + " ");

        return deltaT02 / deltaT01;
    }




    public static void main(String[] args)
    {
        // - Problem size increase by approximately 20% at each iteration
        // - ThreeSum performed on arrays with less than 500 elements are difficult to time and result in unstable results
        // -



        double prev = TimeRatio(500);
        StdOut.printf("%6d %7.4f\n ", 100, prev);
        for (double N = 500 * 1.2; true; N *= 1.2)
        {
            double ratio  = TimeRatio((int)Math.round(N));
            StdOut.printf("%6d %8.4f \n", Math.round(N), ratio);

        }
    }


}
