package Chapter_4_3.CreativeProblems;


import Chapter_3_4.LinearProbingHashST;
import Chapter_4_1.Cycle;
import Chapter_4_1.Digraph;
import Chapter_4_3.Edge;
import Chapter_4_3.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;


public class VyssotskyMst {

    // Strategy:
    // I. Use BFS to visit all edges and include them all in the mst()
    // II. After each edge inclusion, check whether a cycle was created or not.
    // III. keep track of delete edges

    // Refinement note:
    // - hash tables used to keep track of whether an edge was visited or not
    // - cycle searches done through DFS() calls are initiated where at a vertex where the last edge was added


    Digraph mst;
    Bag<Integer>[] adj_del;
    Queue<Edge> q;
    Queue<Edge> mst_edges;

    LinearProbingHashST<Edge, Boolean> visitedEdges;

    public VyssotskyMst(EdgeWeightedGraph G){
        mst = new Digraph(G.V());
        mst_edges = new Queue<Edge>();

        adj_del = (Bag<Integer>[]) new Bag[G.V()];
        q = new Queue<Edge>();

        // set all visitedEdges to false;
        visitedEdges = new LinearProbingHashST<Edge, Boolean>();
        for (Edge e : G.edges()) visitedEdges.put(e, false);

        // start at any edge
        Edge initEdge = G.edges().iterator().next();
        q.enqueue(initEdge);
        visitedEdges.put(initEdge, true);


        while(!q.isEmpty()){

            // try adding that new edge to mst
            Edge e = q.dequeue();
            System.out.println("visiting edge: " + e.toString());

            int v = e.either();
            int w = e.other(v);
            mst.addEdge(v,w);


            // determine whether a new cycle was created
            mst_edges.enqueue(e);
            Edge longestEdgeOnCycle = BreakCycle(mst, G, v);

            if (longestEdgeOnCycle != null) {

                // delete largest edge to break new cycle
                mst_edges = removeFromQueue(mst_edges, longestEdgeOnCycle);

                // rebuild mst based on deleted edge
                mst = new Digraph(G.V());
                for (Edge edg : mst_edges){
                    mst.addEdge(edg.either(), edg.other(edg.either()));
                }
            }

            for (Edge k : G.adj(v)){
                if (!visitedEdges.get(k)){
                    q.enqueue(k);
                    visitedEdges.put(k,true);
                }
            }
            for (Edge k : G.adj(w)){
                if (!visitedEdges.get(k)){
                    q.enqueue(k);
                    visitedEdges.put(k, true);
                }
            }
        }
    }

    public static boolean contains(Queue<Edge> q, Edge v){
        for (Edge e : q) if (e == v) return true;
        return false;
    }

    private Edge BreakCycle(Digraph mst, EdgeWeightedGraph G, int startingVertex) {
        // returns null if there are no cycle
        // otherwise, it returns the edge with the largest weight in that cycle
        Cycle cycle = new Cycle(mst, startingVertex);
        if (cycle.hasCycle()) {
            Queue<Integer> qq = new Queue<Integer>();
            Queue<Edge> edges = new Queue<Edge>();

            for (int v : cycle.cycle()) { qq.enqueue(v);}

            for (int i = 0; i < qq.size(); i++){
                int w = qq.dequeue();
                qq.enqueue(w);
                int v = qq.peek();

                for (Edge e : G.adj(v)) {
                    if (e.other(v) == w){
                        edges.enqueue(e);
                        break;
                    }
                }
            }

            // return edges with largest weight
            Edge largestWeightEdge = edges.dequeue();
            while(!edges.isEmpty()){
                Edge e = edges.dequeue();
                if (e.weight() > largestWeightEdge.weight()) largestWeightEdge = e;
            }
            return largestWeightEdge;
        }
        return null;
    }


    public static <Value>Queue<Value> removeFromQueue(Queue<Value> q, Value v){
        if (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i<size; i++){
                if (q.peek() == v) q.dequeue();
                else q.enqueue(q.dequeue());
            }
        }
        return q;
    }

    public Iterable<Edge> mstEdges(){
        return mst_edges;
    }



    public static void main(String[] args){

        String filename = "Chapter_4_3\\CreativeProblems\\graph_test2.txt";
        In in = new In(filename);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        VyssotskyMst v_mst = new VyssotskyMst(G);
        System.out.println("\n\n mst: ");
        for (Edge e : v_mst.mstEdges()) System.out.println(e.toString());
    }
}