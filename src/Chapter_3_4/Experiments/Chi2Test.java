package Chapter_3_4.Experiments;

import Chapter_3_4.SeparateChainingHashST;

public class Chi2Test {


    public static void main(String[] args) {

        int M = 31;
        final int MAXRAND = 1000;
        SeparateChainingHashST<Integer, Integer> st = new SeparateChainingHashST<>(M);
        for (int i = 0; i<100; i++){
            int randVal = (int)(MAXRAND * Math.random());
            st.put(randVal, 0);
        }
        st.displayKey();

        double chi2 = st.chi2Stats();

        System.out.printf("Chi2 = %.4f\n", chi2);
        double val = st.M - 1;


        System.out.printf("Theoretical Mean = %.4f\n", val);
        System.out.printf("Theoretical std.dev. = %.4f\n", Math.sqrt(2*(st.M-1)));

        double c = (double)(st.N)/st.M;
        double lowBound = st.M - Math.sqrt(st.M);
        double highBound = st.M + Math.sqrt(st.M);
        System.out.printf("Chi2 should be between %.4f and %.4f with probability %.4f\n", lowBound, highBound, 1-(1/c));

        // The result seem reasonable.



    }

}


