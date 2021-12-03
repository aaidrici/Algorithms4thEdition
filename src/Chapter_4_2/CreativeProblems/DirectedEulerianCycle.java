package Chapter_4_2.CreativeProblems;

import Chapter_1_3.Stack;
import Chapter_4_2.Digraph;
import Chapter_4_2.Exercises.Degrees;

public class DirectedEulerianCycle {

    // Note: the DirectedCycle class only allows to tell whether their are cycles or not within a digraph.
    // Directed DirectedEulerianCycle aims at finding whether there is a Eulerian cycle or not.

    // Proof:
    //  -> is was proved earlier strongly connected digraph have a directed cycle that visits every vertex
    //     at least once.
    //  -> For that cycle to be Eulerian, it must visit all the edge making up the cycle exactly once, by definition.
    //     Thus, when the cycle crosses a vertex, it enters it through one of its indegree unvisited edge and leaves it
    //     through one of its outdegree unvisited edge. In order to have all the edges visited exactly once, there must
    //     one outdegree edge for each indegree edge for a given vertex. Hence, their sum must be identical.



    private boolean marked[];
    private boolean onStack[];
    private Stack<Integer> eulerianCycle;
    private int[] edgeTo;
    Degrees deg;

    public DirectedEulerianCycle(Digraph G){
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        deg = new Degrees(G);
        for (int v = 0; v < G.V(); v++){
            if(!marked[v]) dfs(G,v);
        }

    }
    private void dfs(Digraph G, int v){
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)){
            if (hasEulerianCycle()) return;
            else if (!marked[w]){
                edgeTo[w] = v;
                dfs(G,w);
            }
            else{
                boolean inOutDegreeSumToZero = true;
                eulerianCycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]){
                    if (deg.indegree(x) != deg.outdegree(x)) inOutDegreeSumToZero = false;
                    eulerianCycle.push(x);
                }
                eulerianCycle.push(w);
                eulerianCycle.push(v);
                if ((deg.indegree(w) != deg.outdegree(w)) || (deg.indegree(v) != deg.outdegree(v))) inOutDegreeSumToZero = false;
                if (!inOutDegreeSumToZero) eulerianCycle = null;
            }
        }
        onStack[v] = false;
    }

    public Boolean hasEulerianCycle(){
        return eulerianCycle != null;
    }

    public static void main(String[] args){

        int V = 5;
        Digraph G = new Digraph(V);
        int[] v1 = {0,1,2,3,4};
        int[] v2 = {1,2,3,4,1};
        for (int i = 0; i<v1.length; i++) G.addEdge(v1[i], v2[i]);

        DirectedEulerianCycle ec = new DirectedEulerianCycle(G);
        System.out.println(ec.hasEulerianCycle());





    }

}
