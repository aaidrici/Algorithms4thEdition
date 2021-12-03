package Chapter_4_4.CreativeProblem;

import Chapter_2_4.MinPQ;
import Chapter_4_4.DirectedEdge;
import Chapter_4_4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;


public class MonotonicSP {


    double distTo[];
    DirectedEdge[] edgeTo;
    IndexMinPQ<Double> pq;
    int source;

    enum order {ASCENDING, DESCENDING};

    // Strategy: Apply Dijkstra twice with two different constraints:
    //  I. edges must have a weight smaller than their parent edge to be eligible.
    // II. edges must have a weight larger than their parent edge to be eligible.

    public MonotonicSP(EdgeWeightedDigraph G, int s){
        pq = new IndexMinPQ<>(G.V());
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int i = 0; i < G.V(); i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        source = s;
        distTo[s] = 0.0;


        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()){
            relax(G, pq.delMin(), order.ASCENDING);
        }


        pq = new IndexMinPQ<>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()){
            relax(G, pq.delMin(), order.DESCENDING);
        }
    }


    private void relax(EdgeWeightedDigraph G, int v, order dir)
    {
        for(DirectedEdge e : G.adj(v))
        {
            if (v != source && dir == order.ASCENDING && e.weight() < edgeTo[v].weight()) continue;
            else if (v != source && dir == order.DESCENDING && e.weight() > edgeTo[v].weight()) continue;

            int w = e.to();
            if (distTo[w] >= distTo[v] + e.weight())
            {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public double distTo(int v)
    { return distTo[v]; }
    public boolean hasPathTo(int v)
    { return distTo[v] < Double.POSITIVE_INFINITY; }
    public Iterable<DirectedEdge> pathTo(int v)
    {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    public static void main(String[] args){


        String filename = "Chapter_4_4\\Q4_4_34_input.txt";
        In in = new In(filename);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        MonotonicSP msp = new MonotonicSP(G, 0);

        for (int v = 0; v < G.V(); v++){
            System.out.printf("distTo(%d), %.4f\n", v, msp.distTo(v));
        }

    }

}
