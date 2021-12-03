package Chapter_4_1;

import Chapter_1_3.Queue;
import edu.princeton.cs.algs4.In;

public class BaconHistogram {

    public static void main(String[] args){

        String filename = "Chapter_4_1/movies.txt";
        String delim = "/";
        SymbolGraph sg = new SymbolGraph(filename, delim);
        Digraph g = sg.G();

        int s = sg.indexOf("Bacon, Kevin");
        BreadthFirstPaths bfp = new BreadthFirstPaths(g, s);

        int[] KevinBaconNumbers = {0,0,0,0,0,0,0,0,0};

        for (int v = 0; v < g.V(); v++){

            if (bfp.hasPathTo(v)){
                int dist = bfp.dist(v);
                if (dist % 2 == 0){
                    System.out.println(sg.nameOf(v) + ", KevinBaconNumber = " + dist/2);
                    KevinBaconNumbers[dist/2]++;
                }

                // String node = sg.nameOf(v);
            }
            else{

            }
        }

        int numberOfActorWithAKevinBaconNumber = 0;
        for (int x : KevinBaconNumbers)  numberOfActorWithAKevinBaconNumber += x;

        // Determine the total number of movies
        In stream = new In("Chapter_4_1/movies.txt");
        Queue<String> q = new Queue<String>();
        while(stream.hasNextLine()){
            String[] a = stream.readLine().split("/");
            q.enqueue(a[0]);
        }
        int NoKevinBaconNumbers = g.V() - q.size() - numberOfActorWithAKevinBaconNumber;


        System.out.println("\n\n\nfinal result:");
        for (int i = 0; i<KevinBaconNumbers.length; i++){
            System.out.println(KevinBaconNumbers[i]  + " actor(s) with a Kevin Bacon number of " + i);
        }
        System.out.println(NoKevinBaconNumbers + " actor(s) with no Kevin Bacon number");




    }


}
