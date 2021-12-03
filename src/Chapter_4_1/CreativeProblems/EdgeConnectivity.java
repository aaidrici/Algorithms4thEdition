package Chapter_4_1.CreativeProblems;


import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import java.util.Arrays;

import java.util.Stack;

public class EdgeConnectivity {

    // implemented method is taken from cp-algorithms.com
    // link = [https://cp-algorithms.com/graph/bridge-searching.html]

    int[] low;
    int[] tin;
    boolean[] marked;
    Queue<Integer> bridges_v1;
    Queue<Integer> bridges_v2;
    int timer = 0;

    public EdgeConnectivity(Graph G){
        low = new int[G.V()];
        tin = new int [G.V()];
        marked = new boolean[G.V()];

        bridges_v1 = new Queue<Integer>();
        bridges_v2 = new Queue<Integer>();

        dfs(G, 0, -1);
    }

    private void dfs(Graph G, int v, int p){
        tin[v] = timer++;
        low[v] = tin[v];
        marked[v] = true;
        for (int w : G.adj(v)){
            if (w == p) continue;
            else if (!marked[w]){
                dfs(G,w,v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] > tin[v]){
                    // we have a bridge
                    bridges_v1.enqueue(v);
                    bridges_v2.enqueue(w);
                }
            }
            else{
                low[v] = Math.min(low[v], tin[w]);
            }
        }
    }

    public Iterable<Integer> criticalEdge_v1(){
        return bridges_v1;
    }
    public Iterable<Integer> criticalEdge_v2(){
        return bridges_v2;
    }
    public int numberOfBridgeEdges(){return bridges_v1.size(); }

    public static void main(String[] args){


        String filename = "Chapter_4_1\\CreativeProblems\\EdgeConnectivityInput.txt";
        In in = new In(filename);
        Graph G = new Graph(in);


        System.out.println(G.toString());

        EdgeConnectivity ec = new EdgeConnectivity(G);

        for (int v1 : ec.criticalEdge_v1()) System.out.print(v1 + " ");
        System.out.println();
        for (int v2 : ec.criticalEdge_v2()) System.out.print(v2 + " ");



//        EdgeConnectivity ec = new EdgeConnectivity(g);
//        ec.isEdgeConnected();
//        ec.display();
//

    }



}
