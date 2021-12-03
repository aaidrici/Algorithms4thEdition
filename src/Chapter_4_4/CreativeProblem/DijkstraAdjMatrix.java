package Chapter_4_4.CreativeProblem;


import Chapter_1_3.Stack;
import Chapter_4_4.EdgeWeightedDigraph;
import Chapter_4_4.EdgeWeightedDigraphDense;

public class DijkstraAdjMatrix {


    // in dijkstra's algorithm, as soon as a vertex leaves the priority queue, it can be considered
    // to have a labeled distance equivalent to its shortest distance with the source.


    // assuming the s'th row represent the out-degree edges from s to another vertex.

    double[][] adjMatrix;
    double[] distTo;
    int[] edgeTo;
    boolean[] leftTheQueue;

    public DijkstraAdjMatrix(Double[][] G, int s){
        int V = G.length;
        distTo = new double[G.length];
        edgeTo = new int[G.length];
        leftTheQueue = new boolean[G.length];


        for (int i = 0 ; i< G.length; i++){
            distTo[i] = Double.POSITIVE_INFINITY;
            edgeTo[i] = -1;
        }
        distTo[s] = 0;

        while (true){
            double smallestDist = Double.POSITIVE_INFINITY;
            int l = 0; // vertex id with smallest distTo[] value
            for (int i = 0; i < V; i++){
                if (!leftTheQueue[i] && distTo[i] < smallestDist){
                    l = i;
                    smallestDist = distTo[i];
                }
            }
            leftTheQueue[l] = true;

            // relax all eligible vertices adjacent to l;
            int ineligibleVertices = 0;
            for (int v = 0; v < V; v++){
                if (!leftTheQueue[v] && G[l][v] != null && distTo[l] + G[l][v] < distTo[v]){
                    distTo[v] = distTo[l] + G[l][v];
                    edgeTo[v] = l;
                    ineligibleVertices++;
                }
            }
            if (ineligibleVertices == 0) break;
        }
    }


    public Iterable<Integer> pathTo(int v){
        if (distTo[v] == Double.POSITIVE_INFINITY) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int w = v; distTo[w] != 0; w = edgeTo[w])
            path.push(w);
        return path;
    }

    public double dist(int v){return distTo[v]; };


    public static void main(String[] args){


        //Double[][] G = {{null,1.7,5.1},{null,null,1.2},{null,null,null}};
        Double[][] G = {{null,1.7,5.1,7.1,null},{0.4,null,1.2,0.4},{3.1,0.11,null,7.1},{1.1, 1.1, 1.1, null}};

        DijkstraAdjMatrix m = new DijkstraAdjMatrix(G, 0);

        for (int i = 0; i<4; i++)
            System.out.println(m.distTo[i]);


    }

}
