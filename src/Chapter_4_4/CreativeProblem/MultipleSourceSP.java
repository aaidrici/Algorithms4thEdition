package Chapter_4_4.CreativeProblem;

import Chapter_1_3.Stack;
import Chapter_4_4.DirectedEdge;
import Chapter_4_4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;

public class MultipleSourceSP {


    double[] distTo;
    DirectedEdge[] edgeTo;
    IndexMinPQ<Double> pq;

    public MultipleSourceSP(EdgeWeightedDigraph G, Iterable<Integer> sources){
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        pq = new IndexMinPQ<>(G.V());

        for (int v = 0; v < G.V(); v++) distTo[v] = Double.POSITIVE_INFINITY;
        for (int s : sources) {
            distTo[s] = 0;
            pq.insert(s, 0.00);
        }

        while (!pq.isEmpty()){
            relax(G, pq.delMin());
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

        int[] sources = {1,2,3};
        Queue<Integer> q = new Queue<Integer>();
        for (int x : sources) q.enqueue(x);

        MultipleSourceSP ssp = new MultipleSourceSP(G,q);
        for (DirectedEdge e : ssp.pathTo(4))
            System.out.println(e.toString());

    }

}
