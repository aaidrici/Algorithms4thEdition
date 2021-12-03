package Chapter_5_1;

import Chapter_1_3.Queue;
import Chapter_3_5.CreativeProblems.SeparateChainingMultiST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinearProbingHashST;

public class q5_1_1 {


    public static void main(String[] args){


        String filename = "Chapter_5_1\\q5_1_1_input.txt";
        In in = new In(filename);
        Queue<String> sortedList = new Queue<String>();
        Queue<Integer> sortedkeys = new Queue<Integer>();

        SeparateChainingMultiST<Integer, String> st = new SeparateChainingMultiST<Integer, String>();

        int key_max = 0;
        while (!in.isEmpty()){
            String s = in.readString();
            int d = in.readInt();
            if (d > key_max) key_max = d;
            st.put(d, s);
        }

        for (int i = 0; i <= key_max; i++){
            if (!st.contains(i)) continue;
            for (String s : st.getAll(i)){
                sortedList.enqueue(s);
                sortedkeys.enqueue(i);
            }
        }


        while (!sortedList.isEmpty()){
            System.out.println(Integer.toString(sortedkeys.dequeue()) + " " + sortedList.dequeue());
        }





    }

}
