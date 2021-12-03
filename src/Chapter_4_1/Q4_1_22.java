package Chapter_4_1;

import edu.princeton.cs.algs4.In;
public class Q4_1_22 {



    public static void main(String[] args){

        String filename = "Chapter_4_1/movies.txt";
        String actorName = "";
        SymbolGraph sg = new SymbolGraph(filename, "/");
        In stream = new In(filename);
        Digraph g = sg.G();

        int s = sg.indexOf("Bacon, Kevin");
        BreadthFirstPaths bfp = new BreadthFirstPaths(g, s);

        // list of some of the Oscar nominees for Actor in a leading role in 2020
        String[] listOfName = {"DiCaprio, Leonardo","Pryce, Jonathan", "Banderas, Antonio (I)", "Driver, Adam"};
        for (String x : listOfName){
            int dist = bfp.dist(sg.indexOf(x));
            System.out.println("Kevin Bacon number of " + x + " = " + dist / 2);
            System.out.println("Kevin Bacon number of " + x + " = " + dist / 2);
        }



    }
}
