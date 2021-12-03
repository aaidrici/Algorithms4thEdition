package Chapter_4_2;

        import Chapter_1_3.Stack;
        import Chapter_4_1.Digraph;
        import Chapter_4_4.DirectedEdge;
        import Chapter_4_4.EdgeWeightedDigraph;
        import edu.princeton.cs.algs4.In;

public class DirectedWeightedCycle
{
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private Stack<DirectedEdge> cycle; // vertices on a cycle (if one exists)
    private boolean[] onStack; // vertices on recursive call stack


    // adaptation for EdgeWeightedDigraph (Q4.4.12)
    public DirectedWeightedCycle(EdgeWeightedDigraph G){
        onStack = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }
    private void dfs(EdgeWeightedDigraph G, int v)
    {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (this.hasCycle()) return;
            else if (!marked[w])
            { edgeTo[w] = e; dfs(G, w); }
            else if (onStack[w])
            {
                cycle = new Stack<DirectedEdge>();
                for (DirectedEdge edg = e; edg.from() != w; edg = edgeTo[edg.from()])
                    cycle.push(edg);
                cycle.push(edgeTo[cycle.peek().from()]);
            }
        }


        onStack[v] = false;
    }



    public boolean hasCycle()
    { return cycle != null; }
    public Iterable<DirectedEdge> cycle()
    { return cycle; }


    public static void main(String[] args){

        String filename = "Chapter_4_2\\DirectedWeightedCycleTest.txt";
        In in = new In(filename);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        DirectedWeightedCycle dwc = new DirectedWeightedCycle(G);

        for (DirectedEdge e : dwc.cycle) System.out.println(e.from() + "->" + e.to());


    }



}
