package Chapter_4_4;

import Chapter_4_3.Edge;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph
{
    private final int V; // number of vertices
    private int E; // number of edges
    private Bag<DirectedEdge>[] adj; // adjacency lists
    public EdgeWeightedDigraph(int V)
    {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }

    // See Exercise 4.4.2.
    public EdgeWeightedDigraph(In in){
        this(in.readInt());
        int expectedLines = in.readInt(); in.readLine();
        for (int i = 0; i<expectedLines; i++){
            String[] a = in.readLine().split(" ");
            int from = Integer.parseInt(a[0]);
            int to  = Integer.parseInt(a[1]);
            double weight  = Double.parseDouble(a[2]);
            DirectedEdge e = new DirectedEdge(from, to, weight);
            addEdge(e);
        }
    }

    public int V() { return V; }
    public int E() { return E; }

    public void addEdge(DirectedEdge e)
    {
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v)
    { return adj[v]; }

    public Iterable<DirectedEdge> edges()
    {
        Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++)
            for (DirectedEdge e : adj[v])
                bag.add(e);
        return bag;
    }


    public static void main(String[] args){
        String filename = "src\\Chapter_4_4\\tinyEWD.txt";
        In in = new In(filename);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        for (DirectedEdge e : G.edges())
            System.out.printf(e.toString() + "\n");
    }
}