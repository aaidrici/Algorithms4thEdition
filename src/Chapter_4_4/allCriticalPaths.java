package Chapter_4_4;


import Chapter_1_3.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class allCriticalPaths
{
    public static void main(String[] args)
    {
        int N = StdIn.readInt(); StdIn.readLine();
        EdgeWeightedDigraph G;
        G = new EdgeWeightedDigraph(2*N+2);
        int s = 2*N, t = 2*N+1;
        for (int i = 0; i < N; i++)
        {
            String[] a = StdIn.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            G.addEdge(new DirectedEdge(i, i+N, duration));
            G.addEdge(new DirectedEdge(s, i, 0.0));
            G.addEdge(new DirectedEdge(i+N, t, 0.0));
            for (int j = 1; j < a.length; j++)
            {
                int successor = Integer.parseInt(a[j]);
                G.addEdge(new DirectedEdge(i+N, successor, 0.0));
            }
        }


        // find the task at the end of result critical path:
        AcyclicLP lp = new AcyclicLP(G, s);

        // find all critical path end node.
        double maxDist = Double.NEGATIVE_INFINITY;
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < N; v++){
            if (lp.distTo(v) > maxDist){ // new min was found.
                maxDist = lp.distTo(v);
                while (!q.isEmpty()) q.dequeue();
                q.enqueue(v);
                continue;
            }
            else if (lp.distTo(v) == maxDist){
                q.enqueue(v);
            }
        }

        int i = 0;
        System.out.printf("There is a total of %d critical path(s): \b", q.size());
        for (Integer vf : q){
            // only the odd numbered must be printed
            int c = 0;
            System.out.printf("Path %d \n", ++i);
            for (DirectedEdge e : lp.pathTo(vf)){
                if (c%2 != 0) System.out.printf("task : %d, time = %.4f \n", e.from(), -1*e.weight());
                c++;
            }
        }

    }
}