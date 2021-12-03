package Chapter_4_2;

import Chapter_4_1.SymbolGraph;
import Chapter_4_1.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;



public class SymbolDigraph
{
    private ST<String, Integer> st; // String -> index
    private String[] keys; // index -> String
    private Digraph G; // the graph
    public SymbolDigraph(String stream, String sp)
    {
        st = new ST<String, Integer>();
        In in = new In(stream); // First pass
        while (in.hasNextLine()) // builds the index
        {
            String[] a = in.readLine().split(sp); // by reading strings
            for (int i = 0; i < a.length; i++) // to associate each
                if (!st.contains(a[i])) // distinct string
                    st.put(a[i], st.size()); // with an index.
        }
        keys = new String[st.size()]; // Inverted index
        for (String name : st.keys()) // to get string keys
            keys[st.get(name)] = name; // is an array.
        G = new Digraph(st.size());


        in = new In(stream); // Second pass
        while (in.hasNextLine()) // builds the graph
        {
            String[] a = in.readLine().split(sp); // by connecting the
            int v = st.get(a[0]); // first vertex
            for (int i = 1; i < a.length; i++) // on each line
                G.addEdge(v, st.get(a[i])); // to all the others.
        }
    }
    public boolean contains(String s) { return st.contains(s); }
    public int indexOf(String s) { return st.get(s); }
    public String nameOf(int v) { return keys[v]; }
    public Digraph G() { return G; }


    public static void main(String[] args){
        String filename = args[0];
        String delim = args[1];
        SymbolGraph sg = new Chapter_4_1.SymbolGraph(filename, delim);

        Digraph G = sg.G();

        while (StdIn.hasNextLine()){
            String source = StdIn.readLine();
            for (int v : G.adj(sg.indexOf(source)))
                StdOut.println("   " + sg.nameOf(v));
        }
    }

}
