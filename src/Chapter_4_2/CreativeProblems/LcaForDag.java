package Chapter_4_2.CreativeProblems;
import Chapter_1_3.Queue;
import Chapter_4_2.Digraph;
import Chapter_4_2.Exercises.Degrees;

public class LcaForDag {

    int[] heights;
    boolean[] marked;
    boolean[] v1_anc;
    boolean[] v2_anc;
    Queue<Integer> bfsCalls;
    int global_lca;
    int global_lca_height;

    // Note: Current algorithm could have some improvement:
    // 1. It does not account for the presence of multiple sources (zero-indegree vertices) which each have
    //    their own lca for v1 and v2. The current version assumes only one of the



    public LcaForDag(Digraph G, int v1, int v2){
        heights = new int[G.V()];
        marked = new boolean[G.V()];
        global_lca = -1;
        global_lca_height = -1;
        Degrees deg = new Degrees(G);
        Queue<Integer> sources = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++) if (deg.indegree(v) == 0) sources.enqueue(v);
        if (sources.isEmpty()) {System.out.println("no source"); return;}
        for (int s : sources){

            bfsCalls = new Queue<Integer>();

            // populate height table & mark vertices accessible from the source
            int height = 0;
            bfsCalls.enqueue(s);
            while (!bfsCalls.isEmpty()){
                int v = bfsCalls.dequeue();
                marked[v] = true;
                for (int w : G.adj(v)){
                    if (heights[w] < heights[v] + 1) heights[w] = heights[v] + 1;
                    if (!marked[w]) bfsCalls.enqueue(w);
                }
            }

            // confirm both vertices are reachable from this source
            if (!marked[v1] || !marked [v2]) continue;

            // list all common ancestors of v1 and v2
            Digraph G_r = new Digraph(G.reverse());
            v1_anc = new boolean[G_r.V()];
            v2_anc = new boolean[G_r.V()];
            dfs1(G_r, v1);
            dfs2(G_r, v2);

            // determine which common ancestor has the largest height
            int lca = -1;
            int lca_height = -1;
            for (int i = 0; i < G_r.V(); i++){
                if (v1_anc[i] && v2_anc[i])
                    if (heights[i] > lca_height){
                        lca = i;
                        lca_height = heights[i];
                    }
            }
            if (global_lca < lca){
                global_lca = lca;
                global_lca_height = lca_height;
            }
        }
    }

    private void dfs1(Digraph G, int v){
        v1_anc[v] = true;
        for (int w : G.adj(v)){
            if (!v1_anc[w]) dfs1(G, w);
        }
    }
    private void dfs2(Digraph G, int v){
        v2_anc[v] = true;
        for (int w : G.adj(v)){
            if (!v2_anc[w]) dfs2(G, w);
        }
    }

    public int getLca(){return global_lca;}

    public int getLcaHeight(){return global_lca_height;}

    public void displayHeight(){
        System.out.println("id : height");
        for (int i = 0; i < heights.length; i++)
            System.out.println(i + " : " + heights[i]);
    }


    public static void main(String[] args) {

        int V = 9;
        Digraph G = new Digraph(V);

//        int[] v1 = {0,0,0,1,1,3,2,6,6,7,8};
//        int[] v2 = {2,1,5,3,4,5,5,3,4,6,7};

        int[] v1 = {0,1,2,3,3,4,5,0,8,8};
        int[] v2 = {1,2,3,4,5,6,7,8,6,7};



        for (int i = 0; i< v1.length; i++) G.addEdge(v1[i], v2[i]);

        LcaForDag lca = new LcaForDag(G,3,4);
        lca.displayHeight();

        System.out.println("lca=  " + lca.getLca());
        System.out.println("lca heigth =  " + lca.getLcaHeight());

    }

}
