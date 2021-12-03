package Chapter_4_3;

import Chapter_1_3.Queue;
import edu.princeton.cs.algs4.MinPQ;

public class PrimDenseGraph {


    Queue<Edge> mst;
    int[] distTo;
    boolean[] marked;
    MinPQ<Edge> pq;

    public PrimDenseGraph(EdgeWeightedGraph G){
        distTo = new int[G.V()];
        marked = new boolean[G.V()];


//        Edge e =


    }


    public static void main(String[] args){


    }

}
