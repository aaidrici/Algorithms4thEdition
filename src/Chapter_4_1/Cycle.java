package Chapter_4_1;

import Chapter_1_3.Queue;

public class Cycle {

    private boolean[] marked;
    private boolean hasCycle;
    private int toEdge[];
    private boolean[] onStack;
    Queue<Integer> cycle;



    public Cycle(Digraph G, int sourceVertex){
        toEdge = new int[G.V()];
        onStack = new boolean[G.V()];
        marked = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++){
            int s = (i + sourceVertex) % G.V();
            if (!marked[s])
                dfs(G, -1, s);
        }
    }

    public Cycle(Digraph G) {
        this(G, 0);
    }

    private void dfs(Digraph G, int u, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : G.adj(v)){
            if (!marked[w]) {
                toEdge[w] = v;
                dfs(G, v, w);
            } else if (w != u && !hasCycle) {
                hasCycle = true;
                cycle = new Queue<Integer>();
                for (int k = v; k != w; k = toEdge[k]){ cycle.enqueue(k); }
                cycle.enqueue(w);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }


    // added method
    public Iterable<Integer> cycle(){
        return cycle;
    }


    public static void main(String[] args){
        int V = 5;
        Digraph g = new Digraph(V);
        int[] v1 = {0,1,2,3,3};
        int[] v2 = {1,2,3,0,4};
        for (int i = 0; i< v1.length; i++) g.addEdge(v1[i], v2[i]);
        Cycle c = new Cycle(g);

        for (int k : c.cycle()) System.out.println(k + " ");

    }


}



