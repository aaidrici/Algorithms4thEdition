package Chapter_4_3.CreativeProblems;

import Chapter_1_3.Queue;
import Chapter_4_1.CreativeProblems.EdgeConnectivity;
import Chapter_4_3.Edge;
import Chapter_4_3.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class CriticalEdges {


    Edge[] sortedEdges;
    Queue<Edge> mst;
    Queue<Edge> criticalEdges;
    boolean[] marked;

    public CriticalEdges(EdgeWeightedGraph G){

        // Kruskal way:
        // import all edges from G and sort them
        sortedEdges = new Edge[G.E()];
        int i = 0;
        for (Edge e : G.edges()) sortedEdges[i++] = e;
        Arrays.sort(sortedEdges);

        mst = new Queue<Edge>();
        marked = new boolean[G.V()];

        Edge e;
        Edge e_prev = null;
        boolean prevEdgeInMst = false;

        for (i = 0; i < sortedEdges.length; i++){

            e = sortedEdges[i];
            if (i > 0) e_prev = sortedEdges[i-1];

            int v = e.either();
            int w = e.other(v);

            if(marked[v] && marked[w]){
                if ((i > 0) && (e.compareTo(e_prev) == 0) && prevEdgeInMst){
                    marked[v] = true;
                    marked[w] = true;
                    prevEdgeInMst = true;
                    mst.enqueue(e);
                }
                else prevEdgeInMst = false;
            }
            else{
                marked[v] = true;
                marked[w] = true;
                prevEdgeInMst = true;
                mst.enqueue(e);
            }
        }

        // determine which edges of mst() are critical or not.
        EdgeWeightedGraph mst_ewg = new EdgeWeightedGraph(G.V());
        for (Edge edg : mst) mst_ewg.addEdge(edg);
        EdgeConnectivity ec = new EdgeConnectivity(mst_ewg.toGraph());



        criticalEdges = new Queue<Edge>();
        int[] v1 = new int[ec.numberOfBridgeEdges()];
        int[] v2 = new int[ec.numberOfBridgeEdges()];
        int j = 0;
        for (int v : ec.criticalEdge_v1()) v1[j++] = v;
        j = 0;
        for (int v : ec.criticalEdge_v2()) v2[j++] = v;
        for (int v = 0; v < ec.numberOfBridgeEdges(); v++)
            for (Edge edg : mst_ewg.adj(v1[v])){
                if (edg.other(v1[v]) == v2[v]) {criticalEdges.enqueue(edg); break;}
            }
    }

    public Iterable<Edge> mst(){
        return mst;
    }

    public Iterable<Edge> criticalEdges(){
        return criticalEdges;
    }

    public static void main(String[] args){

        String filename = "src\\Chapter_4_3\\CreativeProblems\\CriticalEdgesInput.txt";
        In in = new In(filename);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        CriticalEdges ce = new CriticalEdges(G);
        for (Edge e : ce.mst()) System.out.println(e.toString());

    }
}
