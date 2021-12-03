package Chapter_4_3;


import edu.princeton.cs.algs4.*;
import Chapter_4_3.Edge;


public class EdgeWeightedGraph
{
    private final int V; // number of vertices
    private int E; // number of edges
    private Bag<Edge>[] adj; // adjacency lists
    public EdgeWeightedGraph(int V)
    {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Edge>();
    }

    // See Exercise 4.3.9.
    public EdgeWeightedGraph(In in){
        this(in.readInt());
        this.E = in.readInt();
        in.readLine();
        while (!in.isEmpty()){
            String[] line = in.readLine().split(" ");
            int v = Integer.parseInt(line[0]);
            int w = Integer.parseInt(line[1]);
            double weight = Double.parseDouble(line[2]);
            Edge e = new Edge(w,v,weight);
            adj[w].add(e);
            adj[v].add(e);
        }
    }


    public Graph toGraph(){
        Graph G = new Graph(V());
        for (Edge e : edges()) G.addEdge(e.either(), e.other(e.either()));
        return G;
    }

    public int V() { return V; }
    public int E() { return E; }
    public void addEdge(Edge e)
    {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }
    public Iterable<Edge> adj(int v)
    { return adj[v]; }

    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<Edge>();
        for (int v = 0; v < V; v++)
            for (Edge e : adj[v])
                if (e.other(v) > v) b.add(e);
        return b;
    }
// See page 609.


    public String toString(){
        String content = V + " vertices, " + E + " edges\n";
        for (Bag<Edge> edgeBag : adj){
            for (Edge e : edgeBag){ content += e.toString() + " "; }
            content += "\n";
        }
        return content;
    }

    public boolean check(Iterable<Edge> mst){

        // Note:
        // implementation could be improved:
        //  I. get() operators of symbol tables require the edges to have the exact address, i.e.
        //     copies of the same MST would probably not work.
        //  II. To much redundancy in all the hashTable, although it may be faster.
        //  III. toHash() was not overrode.

        // symbol table to check whether an edge is part of the mst (true) or just part of the graph (false)
        LinearProbingHashST<Edge, Boolean> inMstlist = new LinearProbingHashST<>();
        for (Edge e : edges()) inMstlist.put(e, false);
        for (Edge e : mst) inMstlist.put(e, true);


        //  symbol table to check whether an edge was added or not to the queue (faster than linear search)
        LinearProbingHashST<Edge, Boolean> onQueue = new LinearProbingHashST<>();
        for (Edge e : edges()) onQueue.put(e, false);


        // list of the edges added to the mst being built.
        Queue<Edge> bfs = new Queue<Edge>();
        Edge init_e = mst.iterator().next();
        bfs.enqueue(init_e);
        onQueue.put(init_e, true);

        // boolean array to keep track of vertices crossed by the mst
        boolean[] mst_marked = new boolean[V()];
        Queue<Integer> marked_v = new Queue<Integer>();
        int v = init_e.either();
        int w = init_e.other(v);
        mst_marked[v] = true;
        mst_marked[w] = true;
        marked_v.enqueue(v);
        marked_v.enqueue(w);

        while (bfs.size() < V() - 1){



            Edge e_shortest = new Edge(0,0, Double.POSITIVE_INFINITY);
            for (int ver : marked_v){
                for (Edge edg : adj(ver)){
                    if (onQueue.get(edg) == false){
                        if (e_shortest.compareTo(edg) > 0)
                            e_shortest = edg;
                    }
                }
            }

            // validate smallestEdgeOnCut is part of provided mst list
            if (!inMstlist.get(e_shortest)) return false;

            bfs.enqueue(e_shortest);
            onQueue.put(e_shortest, true);
            int v1 = e_shortest.either();
            int v2 = e_shortest.other(v1);
            if (!mst_marked[v1]){
                mst_marked[v1] = true;
                marked_v.enqueue(v1);
            }
            else if (!mst_marked[v2]){
                mst_marked[v2] = true;
                marked_v.enqueue(v2);
            }
        }
        return true;
    }

    public static void main(String[] args){
//        In in = new In(args[0]);
//        EdgeWeightedGraph ewg = new EdgeWeightedGraph(in);
//        System.out.println(ewg.toString());


        String filename_G = "Chapter_4_3\\CreativeProblems\\graph_test2.txt";

        In in_G = new In(filename_G);

        EdgeWeightedGraph G = new EdgeWeightedGraph(in_G);

        PrimMST pmst = new PrimMST(G);
        KruskalMST kmst = new KruskalMST(G);
        LazyPrimMST lpmst = new LazyPrimMST(G);

        System.out.println(G.check(pmst.edges()));
        System.out.println(G.check(kmst.edges()));
        System.out.println(G.check(lpmst.edges()));

        System.out.println(pmst.weight());
        System.out.println(kmst.weight());
        System.out.println(lpmst.weight());



    }
}
