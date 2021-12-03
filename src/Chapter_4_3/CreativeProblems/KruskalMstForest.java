package Chapter_4_3.CreativeProblems;

import Chapter_1_3.Queue;
import Chapter_4_1.CC;
import Chapter_4_1.Digraph;
import Chapter_4_3.Edge;
import Chapter_4_3.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.UF;

public class KruskalMstForest {
    private Queue<Edge> mst;
    public KruskalMstForest(EdgeWeightedGraph G)
    {
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges())
            pq.insert(e);

        // added part: determine how many component separate component there are.
        Digraph g_t = new Digraph(G.V());
        for (Edge e : G.edges()) g_t.addEdge(e.either(), e.other(e.either()));
        CC cc = new CC(g_t);

        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - cc.count())
        {
            Edge e = pq.delMin(); // Get min weight edge on pq
            int v = e.either(), w = e.other(v); // and its vertices.
            if (uf.connected(v, w)) continue; // Ignore ineligible edges.
            uf.union(v, w); // Merge components.
            mst.enqueue(e); // Add edge to mst.
        }
    }
    public Iterable<Edge> edges()
    { return mst; }
    //public double weight(){} // See Exercise 4.3.31.


    public static void main(String[] args){
        String filename = "Chapter_4_3\\CreativeProblems\\graph_test.txt";
        In in = new In(filename);
        EdgeWeightedGraph g = new EdgeWeightedGraph(in);

        KruskalMstForest mst = new KruskalMstForest(g);
        for (Edge e : mst.edges()){
            System.out.println(e.toString());
        }
    }

}


