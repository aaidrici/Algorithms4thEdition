//package Chapter_4_4.CreativeProblem;
//
//import Chapter_1_3.Stack;
//import Chapter_4_2.DirectedCycle;
//import Chapter_4_4.DirectedEdge;
//import Chapter_4_4.EdgeWeightedDigraph;
//import edu.princeton.cs.algs4.Edge;
//import edu.princeton.cs.algs4.In;
//import edu.princeton.cs.algs4.Queue;
//
//public class bitonicSP {
//
//
//    DirectedEdge[] edgeTo;
//    DirectedEdge[] edgeTo_bitonic;
//    double[] distTo;
//    Queue<Integer> q;
//    boolean[] onQueue;
//    int cost;
//    Iterable<Integer> cycle;
//
//    public bitonicSP(EdgeWeightedDigraph G, int s){
//
//        distTo = new double[G.V()];
//        q = new Queue<Integer>();
//        edgeTo = new DirectedEdge[G.V()];
//        onQueue = new boolean[G.V()];
//        cost = 0;
//
//        for (int v = 0; v < G.V(); v++)
//            distTo[v] = Double.POSITIVE_INFINITY;
//
//        distTo[s] = 0.0;
//        q.enqueue(s);
//        onQueue[s] = true;
//
//        while (!q.isEmpty()){
//            int v = q.dequeue();
//            onQueue[v] = false;
//            relax(G, v);
//        }
//    }
//
//    private void relax(EdgeWeightedDigraph G, int v){
//
//
//
//        for (DirectedEdge e : G.adj(v)){
//            int w = e.to();
//
//            boolean eligible = true;
//            if (e.weight() > )
//
//            if ((distTo[w] > distTo[v] + e.weight()) && eligible){
//                distTo[w] = distTo[v] + e.weight();
//                edgeTo[w] = e;
//                if (!onQueue[w]){
//                    q.enqueue(w);
//                    onQueue[w] = true;
//                }
//            }
//            if (cost++ % G.V() == 0){
//                findNegativeCycle();
//            }
//        }
//    }
//
//    //
//
//    public boolean hasPathTo(int w){
//        return (edgeTo[w] != null);
//    }
//
//    public double distTo(int v){
//        return distTo[v];
//    }
//
//    public Iterable<DirectedEdge> pathTo(int w){
//        if (edgeTo[w] == null) return null;
//        Stack<DirectedEdge> stack = new Stack<DirectedEdge>();
//        for (DirectedEdge e  = edgeTo[w]; e != null; e = edgeTo[e.from()]){
//            stack.push(e);
//        }
//        return stack;
//    }
//
//    private void findNegativeCycle()
//    {
//        int V = edgeTo.length;
//        EdgeWeightedDigraph spt;
//        spt = new EdgeWeightedDigraph(V);
//        for (int v = 0; v < V; v++)
//            if (edgeTo[v] != null)
//                spt.addEdge(edgeTo[v]);
//        DirectedCycle cf;
//        cf = new DirectedCycle(spt);
//        cycle = cf.cycle();
//    }
//
//    public static void main(String[] args){
//
//        String filename = "Chapter_4_4\\Q4_4_35_input.txt";
//        In in = new In(filename);
//        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
//        bitonicSP bsp = new bitonicSP(G, 0);
//        for (int v = 1; v < G.V(); v++){
//            System.out.print("\n" + v + ": ");
//            for (DirectedEdge e : bsp.pathTo(v)) {
//                System.out.print(e.from() + "->" + e.to() + ", ");
//            }
//        }
//
//
//
//
//    }
//
//
//}
