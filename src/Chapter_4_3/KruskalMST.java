package Chapter_4_3;

import Chapter_1_3.Queue;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.UF;

public class KruskalMST
{
    private Queue<Edge> mst;
    double totalWeight;

    public KruskalMST(EdgeWeightedGraph G)
    {
        mst = new Queue<Edge>();
        totalWeight = 0;
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges())
            pq.insert(e);
        UF uf = new UF(G.V());

        while (!pq.isEmpty() && mst.size() < G.V()-1)
        {
            Edge e = pq.delMin(); // Get min weight edge on pq
            int v = e.either(), w = e.other(v); // and its vertices.
            if (uf.connected(v, w)) continue; // Ignore ineligible edges.
            uf.union(v, w); // Merge components.
            mst.enqueue(e); // Add edge to mst.
            totalWeight += e.weight();
        }
    }
    public Iterable<Edge> edges()
    { return mst;

    }


    public double weight(){
        return totalWeight;
    } // See Exercise 4.3.31.



}

