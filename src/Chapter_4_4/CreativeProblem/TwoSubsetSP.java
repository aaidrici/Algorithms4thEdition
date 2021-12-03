package Chapter_4_4.CreativeProblem;

        import Chapter_1_3.Stack;
        import Chapter_4_4.DirectedEdge;
        import Chapter_4_4.EdgeWeightedDigraph;
        import edu.princeton.cs.algs4.Bag;
        import edu.princeton.cs.algs4.In;
        import edu.princeton.cs.algs4.IndexMinPQ;
        import edu.princeton.cs.algs4.Queue;

        import java.util.HashSet;
        import java.util.Set;

public class TwoSubsetSP {


    double[] distTo;
    DirectedEdge[] edgeTo;
    IndexMinPQ<Double> pq;

    public TwoSubsetSP(EdgeWeightedDigraph G, Iterable<Integer> sources, Iterable<Integer> sinks){
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        pq = new IndexMinPQ<>(G.V());
        Set<Integer> sinksRemaining = new HashSet<>();

        for (int v = 0; v < G.V(); v++) distTo[v] = Double.POSITIVE_INFINITY;
        for (int s : sources) {
            distTo[s] = 0;
            pq.insert(s, 0.00);
        }
        for (int s : sinks)
            sinksRemaining.add(s);

        // stop the process as soon as all the sinks vertex ids exit the priority queue
        while (!pq.isEmpty()){
            int v = pq.delMin();
            relax(G, v);
            sinksRemaining.remove(v);
            if (sinksRemaining.isEmpty()) break;
        }
    }


    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.changeKey(w, distTo[w]);
                else {pq.insert(w, distTo[w]); }
            }
        }
    }

    public Iterable<DirectedEdge> pathTo(int t){
        if (distTo[t] == Double.POSITIVE_INFINITY) return null;
        else{
            Stack<DirectedEdge> path = new Stack<DirectedEdge>();
            for (int w = t; edgeTo[w] != null; w = edgeTo[w].from())
                path.push(edgeTo[w]);
            return path;
        }
    }


    public static void main(String[] args){

        String filename = "Chapter_4_4\\tinyEWD.txt";
        In in = new In(filename);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        int[] source = {0,1,2,3,4,5};
        int[] sinks = {7};
        Queue<Integer> sources_queue = new Queue<>();
        Queue<Integer> sinks_queue = new Queue<>();
        for (int x : source) sources_queue.enqueue(x);
        for (int x : sinks) sinks_queue.enqueue(x);

        TwoSubsetSP ssp = new TwoSubsetSP(G,sources_queue, sinks_queue);

        for (DirectedEdge e : ssp.pathTo(6))
            System.out.println(e.toString());

    }

}
