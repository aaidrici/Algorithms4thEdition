package Chapter_4_4;


import edu.princeton.cs.algs4.Stack;

public class AcyclicLP {


    AcyclicSP sp;

    public AcyclicLP(EdgeWeightedDigraph G, int s){

        // create identical EdgeWeightedGraph but with opposite weights.
        EdgeWeightedDigraph G_n = new EdgeWeightedDigraph(G.V());
        for (DirectedEdge e : G.edges()){
            DirectedEdge e_n = new DirectedEdge(e.from(), e.to(), e.weight() * -1);
            G_n.addEdge(e_n);
        }

        sp = new AcyclicSP(G_n, s);
    }

    public double distTo(int v)
    { return  sp.distTo(v) * -1; }

    public boolean hasPathTo(int v)
    { return sp.hasPathTo(v); }

    public Iterable<DirectedEdge> pathTo(int v)
    {
        return sp.pathTo(v);
    }

}
