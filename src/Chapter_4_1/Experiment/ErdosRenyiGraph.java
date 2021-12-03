package Chapter_4_1.Experiment;
import Chapter_4_1.Digraph;


public class ErdosRenyiGraph {






    public static void main(String[] args){
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);

        Digraph g = new Digraph(V);
        for (int e = 0; e < E; e++){
            int v1 = (int)(Math.random() * V);
            int v2 = (int)(Math.random() * V);
            g.addEdge(v1, v2);
        }

        System.out.println(g.toString());


    }

}
