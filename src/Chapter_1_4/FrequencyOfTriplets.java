package Chapter_1_4;

import edu.princeton.cs.algs4.StdRandom;


public class FrequencyOfTriplets {


    public static void main(String[] args){

        int NoOfExperiment = 10;
        double[] frequencies = new double[NoOfExperiment];
        ThreeSumFaster threeSumFaster;
        ThreeSum threeSum = new ThreeSum();
        int M = 100;
        int N = 100;
        double Frequency;
        int numberOfTriplets;


        for (int count = 0; count < NoOfExperiment; count ++ ){

            // generate array of N random number between -M and M
            int[] a = new int[N];
            for(int i = 0; i<N ; i++)
                a[i] = StdRandom.uniform(-M,M+1);

            // determine number of triplets with zero sum
//            threeSumFaster= new ThreeSumFaster(a);
//            numberOfTriplets = threeSumFaster.applyThreeSumFaster();
            threeSum = new ThreeSum();
            numberOfTriplets = threeSum.count(a);

            frequencies[count] = (double)numberOfTriplets / (double)N;

        }

        double mean_frequency = 0;
        for (double freq : frequencies) mean_frequency += freq;
        mean_frequency /= frequencies.length; // mean number of zero-sum in the array

        System.out.println("Expected result: " +  (double)(N*(N-1)*(N-2)/6) *  (double)(3*M*M+5*M+1) / (double)((2*M + 1)*(2*M + 1)*(2*M + 1)));
        System.out.println("Obtained result: " +  mean_frequency);



    }
}


