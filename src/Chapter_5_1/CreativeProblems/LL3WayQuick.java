package Chapter_5_1.CreativeProblems;


import Chapter_1_3.LinkedList;
import edu.princeton.cs.algs4.In;


public class LL3WayQuick {

    public LL3WayQuick(LinkedList<String> ll){


        for (String x : ll) System.out.println(x);

    }

    private void sort (LinkedList<String> a, int lo, int hi, int d){

    }

    public static void main(String[] args){

        String filename = "Chapter_5_1\\CreativeProblems\\Q5_1_16_input.txt";
        In in = new In(filename);
        LinkedList<String> ll = new LinkedList<String>();
        while (!in.isEmpty()) ll.push(in.readString());
        LL3WayQuick ll3 = new LL3WayQuick(ll);


    }

}
