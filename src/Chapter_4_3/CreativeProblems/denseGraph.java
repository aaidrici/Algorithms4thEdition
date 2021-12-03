package Chapter_4_3.CreativeProblems;

import Chapter_1_3.Queue;
import Chapter_4_3.Edge;
import Chapter_4_3.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;

public class denseGraph {

    boolean[] marked;
    Queue<Edge> mst;

    public denseGraph(EdgeWeightedGraph G){

        mst = new Queue<Edge>();
        marked = new boolean[G.V()];
        int s = 0;
        marked[s] = true;
        int markedCount = 0;
        Edge nextEdge;

        while(markedCount < G.V() - 1){
            nextEdge = new Edge(-1,-1,Double.POSITIVE_INFINITY);
            for (int v = 0; v < G.V(); v++){
                if (!marked[v]) continue;
                for (Edge e : G.adj(v)){
                    if (marked[e.other(v)]) continue;
                    if (e.weight() < nextEdge.weight()){
                        nextEdge = e;
                    }
                }
            }
            mst.enqueue(nextEdge);
            int w = nextEdge.either();
            if (marked[w]) s = nextEdge.other(w);
            else s = w;
            marked[s] = true;
            markedCount++;
        }
    }


    public static void main(String[] args){

        String filename = "Chapter_4_3\\CreativeProblems\\graph_test2.txt";
        In in = new In(filename);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        denseGraph dg = new denseGraph(G);
        for (Edge e : dg.mst) System.out.println(e.toString());
    }


}
