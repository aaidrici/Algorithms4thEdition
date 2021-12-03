package Chapter_1_4;

import edu.princeton.cs.algs4.StdRandom;

import javax.swing.text.StyledEditorKit;

public class BirthdayProblem {





    public static void main(String[] args){

        // let's not take inputs from the command line, life is too short

        int N = 1000;
        int trialNo = 100000;

        double overallCount = 0;
        for (int trial = 0 ; trial < trialNo; trial ++ ){

            Boolean[] a = new Boolean[N];
            for (int i = 0; i<N; i++) a[i] = false;

            double count = 0;
            int ind = 0;
            while(true){
                ind = StdRandom.uniform(N);
                if (a[ind] == true) break;
                else a[ind] = true;
                count++;
            }

            overallCount += count;
        }


        String expectedString = String.format("%.4f", Math.sqrt(Math.PI * N / 2));
        String obtainedResult = String.format("%.4f", overallCount/trialNo);

        System.out.println("expected: " + expectedString + ", obtained: " + obtainedResult);


    }
}
