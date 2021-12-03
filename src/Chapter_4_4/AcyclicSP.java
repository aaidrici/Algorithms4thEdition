package Chapter_4_4;

import Chapter_4_3.Edge;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;
import Chapter_4_2.Topological;
import Chapter_1_3.Queue;

public class AcyclicSP
{
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    // added variable tE_Io keep track of all element an vertex could edge to.
        private Bag<Integer>[] edgesTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s)
        {
            edgesTo = (Bag<Integer>[]) new Bag[G.V()];

            edgeTo = new DirectedEdge[G.V()];
            distTo = new double[G.V()];
            for (int v = 0; v < G.V(); v++)
                distTo[v] = Double.POSITIVE_INFINITY;
            distTo[s] = 0.0;
            Topological top = new Topological(G);
            for (int v : top.order())
                relax(G, v);
        }

        private void relax(EdgeWeightedDigraph G, int v)
        {
            for (DirectedEdge e : G.adj(v))
            {
                int w = e.to();
                if (distTo[w] > distTo[v] + e.weight())
                {
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w] = e;
                }
            }
        }
        // See page 648.
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

//     added code for 4.4.18
//    public Queue<Iterable<DirectedEdge>> allPathTo(int v){
//
//    }





}