package Chapter_4_4;

import Chapter_1_3.Stack;
import Chapter_4_2.DirectedWeightedCycle;
import Chapter_4_3.Edge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class BellmanFordSP
{
    private double[] distTo; // length of path to v
    private DirectedEdge[] edgeTo; // last edge on path to v
    private boolean[] onQ; // Is this vertex on the queue?
    private Queue<Integer> queue; // vertices being relaxed
    private int cost; // number of calls to relax()
    private Iterable<DirectedEdge> cycle; // negative cycle in edgeTo[]?
    public BellmanFordSP(EdgeWeightedDigraph G, int s)
    {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !this.hasNegativeCycle())
        {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v)
    {
        for (DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight())
            {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w])
                {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            if (cost++ % G.V() == 0)
                findNegativeCycle();
        }
    }
    // See page 673.
    public double distTo(int v){return distTo[v]; } // standard client query methods
    public boolean hasPathTo(int v){return edgeTo[v] != null; } // for SPT implementations
    public Iterable<DirectedEdge> pathTo(int v)
    {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }
    private void findNegativeCycle()
    {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt;
        spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++)
            if (edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);
        DirectedWeightedCycle cf;                    // `EdgeWeightedCycleFinder` replaced with `DirectedWeightedCycle`
        cf = new DirectedWeightedCycle(spt);
        cycle = cf.cycle();
    }
    public boolean hasNegativeCycle()
    { return cycle != null; }
    public Iterable<DirectedEdge> negativeCycle()
    { return cycle; }
// See page 677.


    public static void main(String[] args){
        String filename = "Chapter_4_4\\BellmanFordSP_test.txt";
        In in = new In(filename);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        BellmanFordSP bfsp = new BellmanFordSP(G, 0);
        for (int v = 1; v < G.V(); v++){
            System.out.print("\n" + v + ": ");
            for (DirectedEdge e : bfsp.pathTo(v)) {
                System.out.print(e.from() + "->" + e.to() + ", ");
            }
        }
    }


}