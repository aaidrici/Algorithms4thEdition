package Chapter_4_3.CreativeProblems;

import Chapter_1_3.Queue;
import Chapter_4_3.Edge;
import Chapter_4_3.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Boruvka {

    Queue<Edge> mst;


    Queue<Integer>[] components; // data structure used to loop through all vertex part of a component
    int[] id;  //  data structure use to identify to which component a vertex belongs to

    public Boruvka(EdgeWeightedGraph G){
        mst = new Queue<Edge>();

        id = new int[G.V()];
        components = new Queue[G.V()];
        for (int j = 0 ; j < G.V(); j++){
            components[j] = new Queue<Integer>();
            components[j].enqueue(j);
            id[j] = j; // initially, all edges are their own component;
        }

        int componentCount = G.V();
        int comp_id = -1;

        while (componentCount > 1){

            comp_id = (comp_id + 1) % G.V();
            Queue<Integer> c = components[comp_id];

            if (c == null) continue;

            Edge minEdge = new Edge(-1,-1,Double.POSITIVE_INFINITY);
            int w2add = -1;
            for (int v : c){
                for (Edge e : G.adj(v)){

                    if (id[v] == id[e.other(v)]) continue; // ignores edges within the same component

                    if (e.weight() < minEdge.weight()) {
                        minEdge = e;
                        w2add = e.other(v);
                    }
                }
            }

            mst.enqueue(minEdge);
            int compIdToMergeWith = id[w2add];
            for (Integer x : components[compIdToMergeWith]){
                c.enqueue(x);
                id[x] = comp_id;
            }
            components[compIdToMergeWith] = null;
            componentCount--;
        }
    }




    Iterable<Edge> mst(){
        return mst;
    }

    public static void main(String[] args){
        String filename = "Chapter_4_3\\CreativeProblems\\graph_test2.txt";
        In in = new In(filename);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        Boruvka b_mst = new Boruvka(G);
        System.out.println("\n\n mst: ");
        for (Edge e : b_mst.mst()) System.out.println(e.toString());
    }

}


//
//    int v = -1;
//        while (uf.count() > 1){
//                v = ((v+1) % G.V());
//
//                Edge minEdge = new Edge(-1,-1,Double.POSITIVE_INFINITY);
//                for (Edge e : G.adj(v)){
//                int w = e.other(v);
//                if (!uf.connected(w,v) && e.weight() < minEdge.weight())
//        minEdge = e;
//        }
//        if (minEdge.either() == -1) continue;
//
//        mst.enqueue(minEdge);
//        uf.union(v, minEdge.other(v));
//
//        }
