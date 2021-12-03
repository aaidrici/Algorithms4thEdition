package Chapter_4_4;


import edu.princeton.cs.algs4.In;

public class Q4_4_4 {



    public static void main(String[] args){


        // part one, SP with v7 removed

        String filename = "Chapter_4_4\\tinyEWD.txt";
        In in = new In(filename);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(7);
        in.readLine();
        in.readLine();
        while (!in.isEmpty()){
            String[] a = in.readLine().split(" ");
            DirectedEdge e = new DirectedEdge(Integer.parseInt(a[0]), Integer.parseInt(a[1]), Double.parseDouble(a[2]));
            G.addEdge(e);
        }


        // part two, SP reversed



    }

}
