package Chapter_4_4.CreativeProblem;

import Chapter_4_3.Edge;
import Chapter_4_4.DijkstraSP;
import Chapter_4_4.DirectedEdge;
import Chapter_4_4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Queue;

public class gridSP {



    boolean movingLeftAndUpAllowed = true;
    EdgeWeightedDigraph G;
    DijkstraSP dsp;
    int[][] grid;

    public gridSP(int[][] grid){
        this.grid = grid;
        G = new EdgeWeightedDigraph(grid.length*grid.length);
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j< grid.length; j++){
                int v = i * grid.length + j;

                if (i != grid.length - 1) {
                    DirectedEdge e = new DirectedEdge(v, v+grid.length,grid[i+1][j]);
                    G.addEdge(e);
                }
                if (j != grid.length - 1) {
                    DirectedEdge e = new DirectedEdge(v, v+1,grid[i][j+1]);
                    G.addEdge(e);
                }
                if (j != 0 && movingLeftAndUpAllowed) {
                    DirectedEdge e = new DirectedEdge(v, v-1,grid[i][j-1]);
                    G.addEdge(e);
                }
                if (i != 0 && movingLeftAndUpAllowed)  {
                    DirectedEdge e = new DirectedEdge(v, v-grid.length,grid[i-1][j]);
                    G.addEdge(e);
                }
            }
        }

        dsp = new DijkstraSP(G, 0);

    }

    Iterable<Integer> pathTo(int v){

        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(grid[0][0]);
        for (DirectedEdge e : dsp.pathTo(v)) q.enqueue((int)e.weight());
        return q;
    }

    int sumOfPath(int v){

        int sum = grid[0][0];
        for (DirectedEdge e : dsp.pathTo(v)) sum += ((int)e.weight());
        return sum;
    }


    public static void  main(String[] args){

        int[][] d = {{7, 5, 2, 4, 5, 4},
                    {8, 4, 36, 15, 2, 8},
                    {31, 24, 18, 77, 2, 5},
                    {74, 0, 49, 45, 9, 6},
                    {8, 8, 4, 2, 8, 4},
                    {5, 3, 1, 1, 3, 1}};

        gridSP gsp = new gridSP(d);

        for (int v = 0; v < d.length*d.length; v++){
            System.out.printf("SP to <%d,%d>: ", v/d.length, v%d.length);
            for (int x : gsp.pathTo(v)) System.out.print(x + " ");
            System.out.printf(" (sum = %d)\n", gsp.sumOfPath(v));
        }

    }
}
