package Chapter_3_4.Exercises;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class NumberOfEmptyList {






    public static void main(String[] args){

//        double[] N_list = {10,100, 1000, 10000, 100000, 1000000};
//        double M;
//        for (double N : N_list){
//            M = N/2;
//            double emptyListNumber = M - N*Math.exp(-N/M);
//            System.out.println("N = " + N + ", E[EmptyListNumber] " + emptyListNumber);
//        }
//
//
//




        int N = 100;
        int M = 10;
        int duplicateCount = 1;
        int[] hash_output = new int[N];
        for (int i = 0; i<N; i++){
            int a = StdRandom.uniform(0,M);
            hash_output[i] = a;
            System.out.print(a + "\n");
        }
        Arrays.sort(hash_output);
        for (int i = 0; i<N-1; i++){
            if (hash_output[i] == hash_output[i+1])
                duplicateCount++;
        }

        System.out.println("Theory: " + (1-Math.exp(-(double)(N)/M)));
        System.out.println("Experimental: "+ (double)(duplicateCount) / (double)N);



    }

}
