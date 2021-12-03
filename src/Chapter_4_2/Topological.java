package Chapter_4_2;

import Chapter_4_1.Digraph;
import edu.princeton.cs.algs4.StdOut;

import Chapter_4_4.DirectedEdge;
import Chapter_4_4.EdgeWeightedDigraph;


public class Topological
{
    private Iterable<Integer> order; // topological order
    public Topological(Digraph G)
    {
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if (!cyclefinder.hasCycle())
        {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order()
    { return order; }
    public boolean isDAG()
    { return order == null; }



    // code added for 4.4.12
    public Topological(EdgeWeightedDigraph G){
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if (!cyclefinder.hasCycle())
        {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }


    public static void main(String[] args)
    {
        String filename = args[0];
        String separator = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, separator);
        Topological top = new Topological(sg.G());
        for (int v : top.order())
            StdOut.println(sg.nameOf(v));
    }
}