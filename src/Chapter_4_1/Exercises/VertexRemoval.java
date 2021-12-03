package Chapter_4_1.Exercises;

import Chapter_4_1.Digraph;

public class VertexRemoval{

    Digraph g;
    Boolean[] marked;

    public VertexRemoval(Digraph g){
        marked = new Boolean[g.V()];
        for (int i = 0; i<g.V(); i++)marked[i] = false;
        this.g = new Digraph(g);
    }

    public int VertexThatCanBeRemoved(int s){
        marked[s] = true;
        return dfs(s);
    }

    public Integer dfs(int s){
        int k = 0;
        int p = -1;
        for (Integer x : g.adj(s)){
            if(!marked[x]){
                marked[x] = true;
                k++;
                p = dfs(x);
            }
        }
        if (k != 0)
            return p;
        else
            return s;

    }

    @Override
    public String toString(){
        return g.toString();
    }

    public static void main(String[] args){


        // Proof:
        //
        // -> if a connected graph contains a vertex `v` connected to only one edge, this vertex can be removed
        //    and the graph should remain connected. Indeed, the removed edged disconnects only `v` from
        //    the other vertices.
        // -> if a connected graph does not contain a vertex `v` connected to only one edge, then the graph must
        //    be a perfect loop (V = E) and there exists exactly two distinct paths between any two given vertices.
        //    If a vertex is removed, there should still be exactly one path between any given pair of vertex,
        //    meaning the graph is still connected.



        // Test client:

        // connected graph example;
        int[] v1 = {0,1,3,3,5};
        int[] v2 = {1,2,1,4,3};

        int V = v1.length + 1; // number of node
        Digraph g = new Digraph(V);
        for (int i = 0; i < v1.length; i++)
            g.addEdge(v1[i], v2[i]);
        System.out.println(g.toString());

        VertexRemoval vr = new VertexRemoval(g);

        int p = vr.VertexThatCanBeRemoved(0);

        System.out.println(p);




    }


}
