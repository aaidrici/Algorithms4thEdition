package Chapter_4_1.Experiment;

import Chapter_4_1.Digraph;
public class RandomSimpleGraph {




    public static void main(String[] args){

        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);

        Digraph g = new Digraph(V);

        int e = 0;
        while (e < E){
            int v2 = (int)(Math.random() * V);
            int v1 = (int)(Math.random() * V);
            if (v1 == v2) {continue;}
            for (int x : g.adj(v1)){
                if (x == v2) continue;
            }
            e++;
            g.addEdge(v1, v2);
        }


        System.out.println(g.toString());
    }

}
