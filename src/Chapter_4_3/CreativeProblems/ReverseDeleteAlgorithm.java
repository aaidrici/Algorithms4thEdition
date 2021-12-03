package Chapter_4_3.CreativeProblems;

import Chapter_1_3.Queue;
import Chapter_1_3.Stack;
import Chapter_4_3.EdgeWeightedGraph;
import Chapter_4_3.Edge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SequentialSearchST;

import java.util.Arrays;

public class ReverseDeleteAlgorithm {

    // Strategy:
    //  I. include all edges
    //  II. BFS() is used starting from one vertex of the removed edge to ensure

    Edge[] edges;
    Queue<Integer> bfsCalls;
    Queue<Edge> mst;
    SequentialSearchST<Edge, Boolean> removedEdges;
    boolean[] marked;


    public ReverseDeleteAlgorithm(EdgeWeightedGraph G){
        edges = new Edge[G.E()];
        removedEdges = new SequentialSearchST<Edge, Boolean>();
        int i = 0;
        for (Edge e : G.edges()){
            edges[i++] = e; //
            removedEdges.put(e, false); // set all edges not suppressed
        }

        Arrays.sort(edges);



        for (int j = G.E() - 1; j >= 0; j--){

            System.out.println("visited edge = " + edges[j].toString());

            // add edges[i] to stack of deleted edges
            removedEdges.put(edges[j], true);
            int v1 = edges[j].either();
            int v2 = edges[j].other(v1);

            // check whether v1 & v2 from edges[i] are still connected
            bfsCalls = new Queue<Integer>();
            bfsCalls.enqueue(v1);
            marked = new boolean[G.V()];

            boolean v2_canBeReached = false;

            while (!bfsCalls.isEmpty()){
                int v = bfsCalls.dequeue();
                marked[v] = true;
                for (Edge e : G.adj(v)){
                    if (removedEdges.get(e)) continue; // ignore removed edges
                    int w = e.other(v);
                    if (w == v2) {
                        v2_canBeReached = true;
                        break;
                    }
                    if (!marked[w]) bfsCalls.enqueue(w);
                }
                if (v2_canBeReached)
                    break;
            }
            if (!v2_canBeReached)
                removedEdges.put(edges[j], false); // edges[i] cannot be deleted
        }

        mst = new Queue<Edge>();
        for (Edge e : G.edges()) if (!removedEdges.get(e)) mst.enqueue(e);

    }

    public Iterable<Edge> mst(){
        return mst;
    }



    public static void main(String[] args){

        String filename = "Chapter_4_3\\CreativeProblems\\graph_test2.txt";
        In in = new In(filename);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        ReverseDeleteAlgorithm rda = new ReverseDeleteAlgorithm(G);
        for (Edge e : rda.mst()) System.out.println(e.toString());

    }
}
