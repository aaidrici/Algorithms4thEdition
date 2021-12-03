package Chapter_4_4.CreativeProblem;

import Chapter_4_4.BellmanFordSP;
import Chapter_4_4.DirectedEdge;
import Chapter_4_4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.In;

public class sensitivityAnalysis {


    boolean[][] stvt_matrix;


    public sensitivityAnalysis(EdgeWeightedDigraph G){
        stvt_matrix = new boolean[G.V()][G.V()];


        for (int s = 0; s < G.V(); s++){

            BellmanFordSP bfsp = new BellmanFordSP(G, s);

            for (int t = 0; t < G.V(); t++){
                if (t == s) continue;
                if (!bfsp.hasPathTo(t)) continue;
                int count = 0;
                for (DirectedEdge e : bfsp.pathTo(t)){ count++; }
                if (count > 1) continue;
                stvt_matrix[s][t] = true;
            }
        }
    }

    public void printMatrix(){

        System.out.printf("%6s", "s\\t");
        for (int j = 0; j < stvt_matrix.length; j++){System.out.printf("%6d", j);}

        System.out.println();
        for (int i = 0; i < stvt_matrix.length; i++) {
            System.out.printf("%6d", i);
            for (int j = 0; j < stvt_matrix.length; j++) {
                if (!stvt_matrix[i][j]) System.out.printf("%6s", "-");
                else System.out.printf("%6b", stvt_matrix[i][j]);
            }
            System.out.println();
        }
    }

    public boolean booleanEdge(int s, int t){return stvt_matrix[s][t]; }
    public static void main(String[] args){
        String filename = "Chapter_4_4\\Q4_4_38_input.txt";
        In in = new In(filename);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        sensitivityAnalysis sdsa = new sensitivityAnalysis(G);
        sdsa.printMatrix();
    }

}
