package Chapter_4_2;

import Chapter_1_3.Queue;
import Chapter_1_3.Stack;
import Chapter_4_1.Digraph;
import Chapter_4_4.DirectedEdge;
import Chapter_4_4.EdgeWeightedDigraph;

public class DepthFirstOrder
{
    private boolean[] marked;
    private Queue<Integer> pre; // vertices in preorder
    private Queue<Integer> post; // vertices in postorder
    private Stack<Integer> reversePost; // vertices in reverse postorder
    public DepthFirstOrder(Digraph G)
    {
        pre = new Queue<Integer>();
        post = new Queue<Integer>();
        reversePost = new Stack<Integer>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }
    private void dfs(Digraph G, int v)
    {
        pre.enqueue(v);
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
        post.enqueue(v);
        reversePost.push(v);
    }




    // added for 4.4.12
    public DepthFirstOrder(EdgeWeightedDigraph G){
        pre = new Queue<Integer>();
        post = new Queue<Integer>();
        reversePost = new Stack<Integer>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }
    private void dfs(EdgeWeightedDigraph G, int v)
    {
        pre.enqueue(v);
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (!marked[w])
                dfs(G, w);
        }
        post.enqueue(v);
        reversePost.push(v);
    }


    public Iterable<Integer> pre()
    { return pre; }
    public Iterable<Integer> post()
    { return post; }
    public Iterable<Integer> reversePost()
    { return reversePost; }
}