package Chapter_4_3;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;

public class PrimMST
{
    private Edge[] edgeTo; // shortest edge from tree vertex
    private double[] distTo; // distTo[w] = edgeTo[w].weight()
    private boolean[] marked; // true if v on tree
    private IndexMinPQ<Double> pq; // eligible crossing edges
    double totalWeight;


    public PrimMST(EdgeWeightedGraph G)
    {
        totalWeight = 0;
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        pq = new IndexMinPQ<Double>(G.V());
        distTo[0] = 0.0;
        pq.insert(0, 0.0); // Initialize pq with 0, weight 0.
        while (!pq.isEmpty())
            visit(G, pq.delMin()); // Add closest vertex to tree.
    }
    private void visit(EdgeWeightedGraph G, int v)
    { // Add v to tree; update data structures.
        marked[v] = true;
        totalWeight += distTo[v];
        for (Edge e : G.adj(v))
        {
            int w = e.other(v);
            if (marked[w]) continue; // v-w is ineligible.
            if (e.weight() < distTo[w])
            { // Edge e is new best connection from tree to w.
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }
    // See Exercise 4.3.21.
    public Iterable<Edge> edges(){
        Queue<Edge> mst = new Queue<Edge>();
        for (int v = 1; v < edgeTo.length; v++){
            mst.enqueue(edgeTo[v]);
        }
        return mst;
    }


    public double weight(){
        return totalWeight;
    } // See Exercise 4.3.31.
}