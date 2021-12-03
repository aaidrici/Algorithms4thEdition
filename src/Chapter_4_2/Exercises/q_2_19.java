package Chapter_4_2.Exercises;

import Chapter_4_2.Digraph;
import edu.princeton.cs.algs4.In;
import Chapter_1_3.Stack;

public class q_2_19 {


    public static boolean ValidSequence(Integer[] sequence, Digraph d){
        boolean[] checked = new boolean[d.V()];
        for (int s : sequence){
            checked[s] = true;
            for (int x : d.adj(s)) if (checked[x]) return false;
        }
        return true;
    }


    public static void main(String[] args){


        // assume the graph is DAG in the first place
        int V = 8;
        Digraph d = new Digraph(V);

        int[] vs = {0,1,2,3,4,5,6};
        int[] ws = {1,2,3,4,5,6,7};
        for (int i = 0 ; i< vs.length; i++) d.addEdge(vs[i], ws[i]);
        Integer[] sequence = {0,1,2,3,4,5,7,6};

        System.out.println(ValidSequence(sequence, d));

    }
}
