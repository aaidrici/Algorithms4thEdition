package Chapter_4_4;

import Chapter_1_3.Queue;
import edu.princeton.cs.algs4.Bag;

public class EdgeWeightedDigraphDense {


    Double[][] matrix;
    int E;
    int V;

    public EdgeWeightedDigraphDense(int V){
        this.V = V;
        matrix = new Double[V][V];
        E = 0;
    }

    public void addEdge(int v, int w, double weight){
        E++;
        matrix[v][w] = weight;
    }


    public int V() { return V; }
    public int E() { return E; }

    public Iterable<DirectedEdge> adj(int v)
    {
        Queue<DirectedEdge> adjRow = new Queue<DirectedEdge>();
        for (int w = 0; w < V(); w++){
            if (matrix[v][w] != null)
                adjRow.enqueue(new DirectedEdge(v, w, matrix[v][w]));
        }
        return adjRow;
    }


    public Iterable<DirectedEdge> edges()
    {
        Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++)
            for (DirectedEdge e : adj(v))
                bag.add(e);
        return bag;
    }

    public static void main(String[] args){
        EdgeWeightedDigraphDense G = new EdgeWeightedDigraphDense(10);
        G.addEdge(1,2, 33.0);
        G.addEdge(2,3, 33.0);
        for (DirectedEdge e : G.edges())
            System.out.println(e.toString());
    }
}
