package Chapter_4_2.CreativeProblems;

import Chapter_4_2.Digraph;
import Chapter_1_3.Queue;
import Chapter_4_2.Exercises.Degrees;

public class QueueBasedTopologicalSort {

    int[] indegrees;
    Queue<Integer> sq; // source queue
    Queue<Integer> ts; // ts = topologicalSort

    public QueueBasedTopologicalSort(Digraph G){
        indegrees = new int[G.V()];
        sq = new Queue<Integer>();
        ts = new Queue<Integer>();
        Degrees d = new Degrees(G);
        for (int i = 0; i< G.V(); i++){
            indegrees[i] = d.indegree(i);
            if (indegrees[i] == 0){
                sq.enqueue(i);
                ts.enqueue(i);
            }

        }

        while (!sq.isEmpty()){
            int v = sq.dequeue();
            for (int w : G.adj(v)){
                indegrees[w] --;
                if (indegrees[w] == 0) {
                    sq.enqueue(w);
                    ts.enqueue(w);
                }
            }
        }
    }


    public Iterable<Integer> getTopologicalSort(){
        return ts;
    }




    public static void main(String[] args){
        int V = 9;
        Digraph G = new Digraph(V);

//        int[] v1 = {0,0,0,1,1,3,2,6,6,7,8};
//        int[] v2 = {2,1,5,3,4,5,5,3,4,6,7};

        int[] v1 = {0,1,2,3,3,4,5,0,8,8};
        int[] v2 = {1,2,3,4,5,6,7,8,6,7};



        for (int i = 0; i< v1.length; i++) G.addEdge(v1[i], v2[i]);
        QueueBasedTopologicalSort qbts = new QueueBasedTopologicalSort(G);
        for (int x : qbts.getTopologicalSort()) System.out.print(x + " ");


    }
}
