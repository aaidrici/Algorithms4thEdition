package Chapter_4_1;

import java.util.Stack;

public class ConnectedComponent {
    private boolean[] marked;
    private int[] id;
    private int count;
    private int numOfComp;

    Digraph g;
    Stack<Integer> dfs_path;

    public ConnectedComponent(Digraph G)
    {
        dfs_path = new Stack<Integer>();
        g = new Digraph(G);
        marked = new boolean[G.V()];
        id = new int[G.V()];
        count = 0;

        for (int s = 0; s < G.V(); s++){
            if (!marked[s]){
                dfs_path.push(s);
                while (!dfs_path.isEmpty()){
                    int p = dfs_path.pop();
                    for (int w : g.adj(p))
                        if (!marked[w]){
                            dfs_path.push(w);
                            marked[w] = true;
                            id[w] = count;
                        }
                }
                count++;
            }

        }
        numOfComp = count;
    }

    public boolean connected(int v, int w)
    { return id[v] == id[w]; }
    public int id(int v)
    { return id[v]; }
    public int count()
    { return count; }
    public int numberOfComponent(){
        return numOfComp;
    }



    public static void main(String[] args){


        Digraph g = new Digraph(13);
        int[] v1 = {0,1,2,3,4,6,7,8,10, 11 };
        int[] v2 = {1,2,3,4,0,5,6,9,9,12 };

        for (int i = 0; i<v1.length; i++) g.addEdge(v1[i], v2[i]);

        ConnectedComponent cc = new ConnectedComponent(g);

        for (int v = 0; v < g.V(); v++) System.out.println("v  = " + v + ", id = " + cc.id[v]);
    }
}
