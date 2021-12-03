package Chapter_4_3.CreativeProblems;

import Chapter_4_1.Digraph;
import Chapter_4_3.Edge;
import Chapter_4_3.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import Chapter_4_1.ConnectedComponent;
import edu.princeton.cs.algs4.Queue;

public class PrimMstForest {

    Edge[] toEdge;
    double[] distTo;
    boolean[] marked;
    IndexMinPQ<Double> q;
    int[] ids;

    public PrimMstForest(EdgeWeightedGraph G){
        toEdge = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        q = new IndexMinPQ<>(G.V());

        for (int v = 0; v < G.V(); v++) distTo[v] = Double.POSITIVE_INFINITY;


        // added line: populate `ids` array which holds a single vertex value part of each connected to each component.
        Digraph g_t = new Digraph(G.V());
        for (Edge e  : G.edges()){
            int w  = e.either();
            g_t.addEdge(w, e.other(w));
        }
        ConnectedComponent cc = new ConnectedComponent(g_t);
        ids = new int[cc.numberOfComponent()];

        for (int id = 0; id < ids.length; id++){
            int w = 0;
            while (cc.id(w) != id) w++;
            ids[id] = w;
        }

        for (int s : ids){
            distTo[s] = 0.0;
            q.insert(s, 0.00);
            while (!q.isEmpty()){
                visit(q.delMin(), G);
            }
        }
    }


    private void visit(int v, EdgeWeightedGraph G){

        marked[v] = true;

        for (Edge e : G.adj(v)){
            int w = e.other(v);
            if (marked[w]) continue;
            if (e.weight() < distTo[w] ){
                toEdge[w] = e;
                distTo[w] = e.weight();
                if (q.contains(w)) q.changeKey(w, e.weight());
                else q.insert(w, e.weight());
            }
        }
    }

    public Iterable<Edge> edges(){
        Queue<Edge> mst = new Queue<Edge>();
        for (int v = 0; v < toEdge.length; v++){

            boolean skip = false;
            for (int w = 0; w < ids.length; w++){ if (ids[w] == v) skip = true; }
            if (skip) continue;

            mst.enqueue(toEdge[v]);
        }
        return mst;
    }

    public static void main(String[] args){

        String filename = "Chapter_4_3\\CreativeProblems\\graph_test.txt";
        In in = new In(filename);
        EdgeWeightedGraph g = new EdgeWeightedGraph(in);

        PrimMstForest prim = new PrimMstForest(g);
        for (Edge e : prim.edges())
            System.out.println(e.either() + " - " + e.other(e.either()));
    }

}
