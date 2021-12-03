package Chapter_2_3.exercises;
import edu.princeton.cs.algs4.StdOut;
import Chapter_2_3.QuickNumberOfCompare;
import edu.princeton.cs.algs4.StdRandom;

public class CN_values {


    public static double c_n(int N){


        double c_n_minus_one = 0;
        double c_n = -1;
        for (int n = 2; n <= N; n++){
            c_n = (n+1) * (c_n_minus_one/n + 2.0/(n+1));
            c_n_minus_one = c_n;
        }
        return c_n;

    }

    public static void main(String[] args){


        int[] N = {100,1000,1000};

        for (int n : N) {
            double approxValue = 2 * n * Math.log(n) / Math.log(2.71828);
            double exactValue = c_n(n);
            StdOut.printf("C_%d = %.4f whereas 2n*ln(n) = %.3f, the ratio gives %.4f\n", n, exactValue, approxValue, exactValue / approxValue);
        }

        // empirically validate the number of exchanges performed in quicksort;
        int n = 10000;
        Integer[] a = new Integer[n];

        int trial_number = 10;
        double meanNumberOfExchange = 0;

        for (int j = 0; j < trial_number; j++){
            for (int i = 0; i<n; i++)
                a[i] = StdRandom.uniform(0,n);

            meanNumberOfExchange += QuickNumberOfCompare.sort(a) / trial_number;
        }


        StdOut.printf("Number of comparison performed on average: %.3f, total number of comparison expected: %.4f\n",meanNumberOfExchange,c_n(n) );

        StdOut.printf("Mean number of compares for C_1000000 = %.4f", c_n(1000000));


    }

}
