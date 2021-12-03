package Chapter_4_1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import static java.lang.Integer.parseInt;

public class Digraph
{

    // Note: Parallel edges and self-loops are prevented (Q4.1.5)

    private final int V; // number of vertices
    private int E; // number of edges
    private Bag<Integer>[] adj; // adjacency lists
    public Digraph(int V)
    {
        this.V = V; this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V]; // Create array of lists.
        for (int v = 0; v < V; v++) // Initialize all lists
            adj[v] = new Bag<Integer>(); // to empty.
    }
    public Digraph(In in)
    {
        this(in.readInt()); // Read V and construct this graph.
        int E = in.readInt(); // Read E.
        for (int i = 0; i < E; i++)
        { // Add an edge.
            int v = in.readInt(); // Read a vertex,
            int w = in.readInt(); // read another vertex,
            addEdge(v, w); // and add edge connecting them.
        }
    }

    public int V() { return V; }
    public int E() { return E; }
    public void addEdge(int v, int w)
    {
        if (!hasEdge(v,w)){                             // Q4.1.5 check whether v,w are already edges
            adj[v].add(w); // Add w to v’s list.
            adj[w].add(v); // Add v to w’s list.
            E++;
        }
    }

    public Iterable<Integer> adj(int v)
    { return adj[v]; }


    // added methods from exercises

    // 4.1.3 - CopyConstructor
    public Digraph(Digraph G){
        this.V = G.V();
        this.E = G.E();
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0 ; i < this.V; i++){this.adj[i] = G.adj[i];}
    }

    // 4.1.15
    // alternative constructor where an adjacency table is provided ahead.
//    public Graph(In in){
//
//        this(in.readInt());
//        this.E = in.readInt();
//        int i = 0;
//        in.readLine();
//        while (!in.isEmpty()){
//            String line = in.readLine();
//            String[] a = line.split(" ");
//            for (String x : a)
//                adj[i].add(parseInt(x));
//            i++;
//        }
//    }

    public boolean hasEdge(int v, int w){
        for (int p : adj[v]){if (p == w) return true; }
        return false;
    }


    public String toString()
    {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++)
        {
            s += v + ": ";
            for (int w : this.adj(v))
                s += w + " ";
            s += "\n";
        }
        return s;
    }



    public static void main(String[] args){
        String filename = args[0];
        System.out.println(filename);
        In str = new In(filename);
        Digraph g = new Digraph(str);
        System.out.print(g.toString());

    }

}