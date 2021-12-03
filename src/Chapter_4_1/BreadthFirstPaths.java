package Chapter_4_1;

import Chapter_1_3.Queue;

public class BreadthFirstPaths
{
    private boolean[] marked; // Is a shortest path to this vertex known?
    private int[] edgeTo; // last vertex on known path to this vertex
    private final int s; // source

    private int[] depth; // added 4.1.13

    public BreadthFirstPaths(Digraph G, int s)
    {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        depth = new int[G.V()];
        this.s = s;
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
            for (int w : G.adj(v))
                if (!marked[w]) // For every unmarked adjacent vertex,
                {
                    depth[w] = depth[v] + 1;
                    edgeTo[w] = v; // save last edge on a shortest path,
                    marked[w] = true; // mark it because path is known,
                    queue.enqueue(w); // and add it to the queue.
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


        int v1[] = {0,1,2,5,4};
        int v2[] = {1,2,5,4,3};
        Digraph g = new Digraph(v1.length + 1);

        for (int i = 0; i<v2.length; i++) g.addEdge(v1[i], v2[i]);

        System.out.println(g.toString());

        BreadthFirstPaths bfs = new BreadthFirstPaths(g, 0);
        System.out.println(bfs.dist(3));



    }


}





