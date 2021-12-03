package Chapter_3_4.Experiments;

import Chapter_3_4.SeparateChainingHashST;

public class ListLengthRange {



    public static void main(String[] args){

        SeparateChainingHashST<Integer, Integer> st;

        int RANDBOUND = 10000000;
        int trials = 1;
        int[] N_list = {1000, 10000, 100000, 1000000};

        for (int N : N_list) {
            for (int i = 0; i < trials; i++) {

                st = new SeparateChainingHashST<>(N/100);

                for (int k = 0; k<N; k++) st.put((int)(Math.random()*RANDBOUND), 0);

                System.out.printf("N = %d, max list size = %d, min list size = %d\n", N, st.maxListLength(), st.minListLength());
            }

        }


    }





}

