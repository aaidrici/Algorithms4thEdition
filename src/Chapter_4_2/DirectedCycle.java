package Chapter_4_2;

import Chapter_1_3.Stack;
import Chapter_4_1.Digraph;
import Chapter_4_4.DirectedEdge;
import Chapter_4_4.EdgeWeightedDigraph;

public class DirectedCycle
{
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle; // vertices on a cycle (if one exists)
    private boolean[] onStack; // vertices on recursive call stack
    public DirectedCycle(Digraph G)
    {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    private void dfs(Digraph G, int v)
    {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v))
            if (this.hasCycle()) return;
            else if (!marked[w])
            { edgeTo[w] = v; dfs(G, w); }
            else if (onStack[w])
            {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
        onStack[v] = false;
    }


    // adaptation for EdgeWeightedDigraph (Q4.4.12)
    public DirectedCycle(EdgeWeightedDigraph G){
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
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
            { edgeTo[w] = v; dfs(G, w); }
            else if (onStack[w])
            {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }}

        onStack[v] = false;
    }



    public boolean hasCycle()
    { return cycle != null; }
    public Iterable<Integer> cycle()
    { return cycle; }
}
