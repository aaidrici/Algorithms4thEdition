package Chapter_4_2.Exercises;
import Chapter_1_3.Stack;
import Chapter_4_2.Digraph;
import edu.princeton.cs.algs4.In;

public class Degrees {


    Digraph g;
    Digraph g_r;
    int[] indegree;
    int[] outdegree;

    public Degrees(Digraph g){
        this.g = new Digraph(g);
        outdegree = new int[g.V()];
        for (int v = 0; v < g.V(); v++){
            int d = 0;
            for (int t : g.adj(v)) d++;
            outdegree[v] = d;
        }


        indegree = new int[g.V()];
        g_r = g.reverse();
        for (int v = 0; v < g_r.V(); v++){
            int d = 0;
            for (int t : g_r.adj(v)) d++;
            indegree[v] = d;
        }
    }


    public int indegree(int v){ return indegree[v]; }

    public int outdegree(int v){ return outdegree[v]; }

    public Iterable<Integer> sinks(){
        Stack<Integer> st = new Stack();
        for (int v = 0; v < g.V(); v++) {
            if (outdegree[v] == 0) st.push(v);
        }
        return st;

    }
    public Iterable<Integer> sources(){
        Stack<Integer> st = new Stack();
        for (int v = 0; v < g.V(); v++){
            if (indegree[v] == 0) st.push(v);
        }
        return st;
    }
    public boolean isMap(){
        for (int d : outdegree) if (d != 1) return false;
        return true;
    }


    public static void main(String[] args){

        String filename = "Chapter_4_2/input_graph.txt";
        In in = new In(filename);
        Digraph g = new Digraph(in);


        Degrees d = new Degrees(g);

        System.out.println("Indegree & Outdegree of each vertex");
        for (int i = 0; i < g.V(); i++) System.out.printf("v: %d, indegree: %d, outdegree: %d \n", i, d.indegree(i), d.outdegree(i));

        System.out.print("\nSources: ");
        for (int x : d.sources()) System.out.print(x + " ");
        System.out.print("\nSinks: ");
        for (int x : d.sinks()) System.out.print(x + " ");

        System.out.println("Is this digraph a map: " + d.isMap());


    }


}
