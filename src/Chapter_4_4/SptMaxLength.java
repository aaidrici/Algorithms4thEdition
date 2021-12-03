package Chapter_4_4;

import edu.princeton.cs.algs4.In;

public class SptMaxLength {

    public static void main(String[] args){

        String filename = "Chapter_4_4\\tinyEWD.txt";
        In in = new In(filename);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        int sf = -1;
        int vf = -1;
        Iterable<DirectedEdge> path = null;
        double LongestPath = 0;
        for (int s = 0; s < G.V(); s++){
            DijkstraSP sp = new DijkstraSP(G, s);
            for (int v = 0; v < G.V(); v++){
                if(!sp.hasPathTo(s)) continue;
                if (LongestPath < sp.distTo(v)){
                    LongestPath = sp.distTo(v);
                    vf = v;
                    sf = s;
                    path = sp.pathTo(v);
                }
            }
        }

        System.out.printf("Longest shortest path from %d to %d with a weight of %.4f. \nPath taken: \n", sf, vf, LongestPath);

        for (DirectedEdge e : path) System.out.println(e.toString() + "");




    }

}
