package Chapter_4_4.CreativeProblem;




import Chapter_1_3.Stack;
import Chapter_4_4.DirectedEdge;
import Chapter_4_4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;

public class SourceSinkProblem {



    DirectedEdge edgeTo[];
    IndexMinPQ<Double> pq;
    double distTo[];
    int t;


    public SourceSinkProblem(EdgeWeightedDigraph G, int s, int t){
        this.t = t;
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());


        for (int v = 0; v < G.V(); v++) distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0;

        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()){
            int v = pq.delMin();
            relax(G, v);
            if (v == t) break;
        }
    }


    public void relax(EdgeWeightedDigraph G, int v){

        for (DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (distTo[v] + e.weight() < distTo[w]){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.changeKey(v, distTo[w]);
                else {pq.insert(w,distTo[w]); }
            }
        }
    }


    public Iterable<DirectedEdge> pathTo(){
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

        SourceSinkProblem ssp = new SourceSinkProblem(G,4,3);
        for (DirectedEdge e : ssp.pathTo())
            System.out.println(e.toString());


    }

}

