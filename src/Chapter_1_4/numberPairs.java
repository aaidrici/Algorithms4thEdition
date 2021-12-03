package Chapter_1_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdRandom;


import java.util.Arrays;

public class numberPairs {


    // returns the number of pairs (each entry can only contribute to a single pair)
    public static int numberPairsInList(int [] listOfInts){
        Arrays.sort(listOfInts);
        int pairsCount = 0;
        for (int i = 0; i<listOfInts.length - 1; i++)
            if (listOfInts[i] == listOfInts[i+1]){
                pairsCount++;
                i++;
            }
        return pairsCount;
    }


    public static void main(String[] args){

        String filename = "src/Chapter_1_4/inputFiles/Q8input.txt";

        // generate input file based on
        int BOUND = 25;
        int numberOfEntries = 100;
        Out file = new Out(filename);
        for (int j = 0; j < numberOfEntries; j++ )
            file.println(StdRandom.uniform(-BOUND, BOUND));
        file.close();
        file = null;


        In intputFile = new In(filename);
        int[] listOfIntegers = intputFile.readAllInts();
        intputFile.close();
        int n = numberPairsInList(listOfIntegers);
        System.out.print("This file contains " + n + " non-overlapping pairs of integers");
    }

}
