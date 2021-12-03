package Chapter_4_2.CreativeProblems;

import Chapter_1_3.Queue;
import Chapter_4_2.Digraph;

public class ShortestAncestralPath {

    int[] v1_dist;
    int[] v2_dist;
    boolean[] v1_marked;
    boolean[] v2_marked;

    int ancestor;
    int ancestor_dist;

    Queue<Integer> bfsCalls;

    public ShortestAncestralPath(Digraph G, int v1, int v2){

        v1_dist = new int[G.V()];
        v2_dist = new int[G.V()];
        v1_marked = new boolean[G.V()];
        v2_marked = new boolean[G.V()];

        // reverse digraph
        Digraph G_r = new Digraph(G.reverse());

        // run BFS on v1
        bfsCalls = new Queue<Integer>();
        bfsCalls.enqueue(v1);
        v1_dist[v1] = 0;
        while(!bfsCalls.isEmpty()){
            int v = bfsCalls.dequeue();
            v1_marked[v] = true;
            for (int w : G_r.adj(v)){
                if (!v1_marked[w]){
                    v1_dist[w] = v1_dist[v] + 1;
                    bfsCalls.enqueue(w);
                }
            }
        }

        // run bfs on v2
        bfsCalls = new Queue<Integer>();
        bfsCalls.enqueue(v2);
        v2_dist[v2] = 0;
        while(!bfsCalls.isEmpty()){
            int v = bfsCalls.dequeue();
            v2_marked[v] = true;
            for (int w : G_r.adj(v)){
                if (!v2_marked[w]){
                    v1_dist[w] = v1_dist[v] + 1;
                    bfsCalls.enqueue(w);
                }
            }
        }

        // determine which common ancestor (if they have any) yields the smallest ancestral distance
        ancestor = -1;
        ancestor_dist = G.V() * 2;
        for (int i = 0; i<G.V(); i++){
            if (v1_marked[i] && v2_marked[i]){
                if (v1_dist[i] + v2_dist[i] < ancestor_dist){
                    ancestor_dist = v1_dist[i] + v2_dist[i];
                    ancestor = i;
                }
            }
        }
    }

    public int getAncestor(){
        return ancestor;
    }
    public int getAncestor_dist(){
        return ancestor_dist;
    }



    public static void main(String[] args){


        int V = 9;
        Digraph G = new Digraph(V);


        int[] v1 = {0,1,2,3,3,4,5,0,8,8};
        int[] v2 = {1,2,3,4,5,6,7,8,6,7};

        for (int i = 0; i< v1.length; i++) G.addEdge(v1[i], v2[i]);

        ShortestAncestralPath sap = new ShortestAncestralPath(G, 6,7);

        System.out.println("Ancestor = " + sap.getAncestor());
        System.out.println("ShortestAncestralPath = " + sap.getAncestor_dist());

    }


}
