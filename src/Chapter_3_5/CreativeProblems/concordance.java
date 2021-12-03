package Chapter_3_5.CreativeProblems;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.util.ArrayList;

public class concordance {


    public static String invertConcordance(SeparateChainingMultiST<String, Integer> a){

        // step one: convert index
        RedBlackBST<Integer, String> t = new RedBlackBST<>();
        for (String x : a.keys()){
            for(Integer y : a.getAll(x)){
                t.put(y, x);
            }
        }

        String originalString = "";
        int i = 0;
        while (i <= t.max()){
            if (t.contains(i)){ originalString = originalString + t.get(i++) + " "; }
        }

        return originalString;
    }



    public static void main(String[] args){

        String filename = args[0];
        In in = new In(filename);

        SeparateChainingMultiST<String, Integer> st = new SeparateChainingMultiST<>();


        int i = 0;
        while (!in.isEmpty()){
            String line = in.readLine();
            String[] tokens = line.split(" ");
            for (String x : tokens) st.put(x, i++);
        }

        System.out.print("==== Concordance Table: ==== \n");
        st.display();

        System.out.print("==== Original String: ===== \n");
        String originalString = invertConcordance(st);
        System.out.print(originalString);

    }

}

