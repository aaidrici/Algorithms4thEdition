package Chapter_3_1;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class DistinctKeysInFrequencyCounter {


public static void main(String[] args){

    boolean[] table = new boolean[1000];
    Arrays.fill(table, false);

    int[] Nvalues = {10,100,1000, 10000, 100000, 1000000};
    int numberOfTrial = 100;

    for (int N : Nvalues){

        double totalCount = 0.0;

        for (int j = 0; j < numberOfTrial; j++) {

            Arrays.fill(table, false);

            for (int i = 0; i < N; i++)
                table[StdRandom.uniform(0, 1000)] = true;
            int count = 0;
            for (int i = 0; i < 1000; i++)
                if (table[i]) count++;
            totalCount += count;
        }
        double meanCount = totalCount/numberOfTrial;

            System.out.printf("N = %d, number of distinct values = %.4f\n", N, meanCount);
    }

}


}
