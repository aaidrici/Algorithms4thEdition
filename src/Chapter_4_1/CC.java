package Chapter_4_1;

public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;

    Digraph g;
    int callNumber = 0;

    public CC(Digraph G)
    {
        g = new Digraph(G);
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s = 0; s < G.V(); s++)
            if (!marked[s])
            {
                dfs(s);
                //dfs(G, s);
                count++;
            }
    }


    private void dfs(int v)
    {
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v))
            if (!marked[w])
                dfs(w);
    }


    public boolean connected(int v, int w)
    { return id[v] == id[w]; }
    public int id(int v)
    { return id[v]; }
    public int count()
    { return count; }
}
