package Chapter_4_4;

        import Chapter_4_3.Edge;
        import Chapter_4_4.DirectedEdge;
        import Chapter_4_4.EdgeWeightedDigraph;
        import edu.princeton.cs.algs4.In;
        import edu.princeton.cs.algs4.IndexMinPQ;
        import edu.princeton.cs.algs4.Stack;

public class DijkstraSP_2ndTree
{
    private DirectedEdge[] edgeTo;
    private DirectedEdge[] alternateEdgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;
    private boolean hasSecondSPT;
    public DijkstraSP_2ndTree(EdgeWeightedDigraph G, int s)
    {
        hasSecondSPT = false;
        edgeTo = new DirectedEdge[G.V()];
        alternateEdgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty())
            relax(G, pq.delMin());
    }
    private void relax(EdgeWeightedDigraph G, int v)
    {
        for(DirectedEdge e : G.adj(v))
        {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight())
            {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
            // added part to populate alternateEdgeTo[]
            else if(distTo[w] == distTo[v] + e.weight()){
                alternateEdgeTo[w] = e;
                hasSecondSPT = true;
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

    // added part
    public Iterable<DirectedEdge> OtherPathTo(int v)
    {
        if (!hasPathTo(v)) return null;
        if (!hasSecondSPT) return null;

        Stack<DirectedEdge> path = new Stack<DirectedEdge>();

        DirectedEdge e = edgeTo[v];
        while (e != null){
            if (alternateEdgeTo[e.to()] != null){
                e = alternateEdgeTo[e.to()];
            }
            path.push(e);
            e = edgeTo[e.from()];
        }
        return path;
    }

    public static void main(String[] args){

        // current implementation assumes there is at most two unique SPT, however the method could easily be generalized
        // to an arbitrary large limit. chro

        String filename = "Chapter_4_4\\q4_4_4test.txt";
        In in = new In(filename);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        DijkstraSP_2ndTree sp = new DijkstraSP_2ndTree(G, 0);
        for (DirectedEdge e : sp.pathTo(3))
            System.out.println(e.toString());
        System.out.println("alternate path: ");
        for (DirectedEdge e : sp.OtherPathTo(3))
            System.out.println(e.toString());




    }


}