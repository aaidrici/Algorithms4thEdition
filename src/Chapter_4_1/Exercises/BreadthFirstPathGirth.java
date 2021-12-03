package Chapter_4_1.Exercises;

import Chapter_1_3.Queue;
import Chapter_4_1.Digraph;

public class BreadthFirstPathGirth
{
    private boolean[] marked; // Is a shortest path to this vertex known?
    private int[] edgeTo; // last vertex on known path to this vertex
    private final int s; // source
    private final int p;

    private int[] depth; // added 4.1.13

    public BreadthFirstPathGirth(Digraph G, int s, int p)
    {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        depth = new int[G.V()];
        this.s = s;
        this.p = p;
        bfs(G, s);
    }
    private void bfs(Digraph G, int s)
    {
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true; // Mark the source
        depth[s] = 0; // added for 4.1.13
        queue.enqueue(s); // and put it on the queue.
        while (!queue.isEmpty())
        {
            int v = queue.dequeue(); // Remove next vertex from the queue.
            for (int w : G.adj(v)){
                if (((w == p) && (v == s)) || ((w == s) && (v == p))){
                    // vertex between p and s shall be ignored
                    continue;
                }
                if (!marked[w]) // For every unmarked adjacent vertex,
                {
                    depth[w] = depth[v] + 1;
                    edgeTo[w] = v; // save last edge on a shortest path,
                    marked[w] = true; // mark it because path is known,
                    queue.enqueue(w); // and add it to the queue.
                }
            }
        }
    }
    public boolean hasPathTo(int v)
    { return marked[v]; }
    public Iterable<Integer> pathTo(int v){
        return null;
    }
    // Same as for DFS (see page 536).






    // added for 4.1.13
    public int dist(int s){return depth[s];}

    public static void main(String[] args){


    }


}





