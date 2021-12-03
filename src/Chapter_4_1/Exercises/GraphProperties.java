package Chapter_4_1.Exercises;

import Chapter_1_3.Queue;
import Chapter_4_1.BreadthFirstPaths;
import Chapter_4_1.Digraph;

public class GraphProperties {

    Digraph g;
    int[] depth;
    boolean[] marked;

    int diam;
    int rad;
    int cent;
    int s;

    public GraphProperties(Digraph g){
        this.g = g;
        depth = new int[g.V()];
        marked = new boolean[g.V()];
        udpateProperties(0); // if no `s` is specified, just take zero
    }
    public GraphProperties(Digraph g, int s){
        this.g = g;
        depth = new int[g.V()];
        marked = new boolean[g.V()];
        udpateProperties(s);
    }

    // note: it is assumed the graph is connected. Otherwise, the results of radius(), center() and diameter() are wrong.

    private int eccentricity(int s){
        return bfs(s);
    }

    private int bfs(int s){

        resetArrays();
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(s);
        marked[s] = true;
        depth[s] = 0;
        int maxDepth = 0;
        while (!q.isEmpty()){
            int x = q.dequeue();
            for (int w : g.adj(x))
            if (!marked[w]){
                marked[w] = true;
                depth[w] = depth[x] + 1;
                q.enqueue(w);
                // check for max here:
                if (depth[w] > maxDepth) maxDepth = depth[w];
            }
        }
        return maxDepth;
    }

    private void resetArrays(){
        for (int i = 0; i<g.V(); i++){
            depth[i] = 0;
            marked[i] = false;
        }
    }

    public void udpateProperties(int s){

        // current vertex with min/max eccentricity
        int vMinEcc = s;
        int vMaxEcc = s;
        int minEcc = eccentricity(s);
        int maxEcc = minEcc;
        System.out.println("eccentricity of s = " + minEcc);

        boolean searchOngoing = true;
        while (searchOngoing){
            searchOngoing = false;
            for (int w : g.adj(vMinEcc)){
                int adj_ecc = eccentricity(w);
                if (adj_ecc < minEcc){
                    minEcc = adj_ecc;
                    vMinEcc = w;
                    searchOngoing = true;
                    break;
                }
            }
        }

        searchOngoing = true;
        while (searchOngoing){
            searchOngoing = false;
            for (int w : g.adj(vMaxEcc)){
                int adj_ecc = eccentricity(w);
                if (adj_ecc > maxEcc){
                    maxEcc = adj_ecc;
                    vMaxEcc = w;
                    searchOngoing = true;
                    break;
                }
            }
        }
        rad = minEcc;
        diam = maxEcc;
        cent = vMinEcc;
    }

    public int center(){return cent; }
    public int radius(){return rad; }
    public int diameter(){return diam; }




    public int girth(){
        int smallestCycle = g.V() + 1;
        int cycle;
        for (int s = 0; s < g.V(); s++){
            for (int v : g.adj(s)){
                BreadthFirstPathGirth bfp = new BreadthFirstPathGirth(g, s, v);
                if (bfp.hasPathTo(v)){
                   cycle = 1 + bfp.dist(v);
                   if (cycle < smallestCycle) smallestCycle = cycle;
                }
            }
        }
        if (smallestCycle == g.V() + 1) return -1; // graph is acyclic
        else return smallestCycle;
    }


    public int wiener(){
        // the methods assumes the graph is connected
        int wienerSum = 0;
        for (int v = 0; v < g.V(); v++){
            BreadthFirstPaths bfp = new BreadthFirstPaths(g, v);
            for (int w = 0; w < v; w++) wienerSum += bfp.dist(w);
        }
        return wienerSum;
    }



    public static void main(String[] args){

        Digraph g = new Digraph(9);
//        int[] v1 = {0, 1,  1, 2, 4, 5, 0};
//        int[] v2 = {1, 2,  4, 5, 6, 6, 3};

        int[] v1 = {0, 1, 1, 2, 4, 5, 0,7,8,8};
        int[] v2 = {1, 2,4, 5, 6, 6, 3,2,5,7};

        for (int i = 0; i<v1.length; i++)
            g.addEdge(v1[i], v2[i]);


        GraphProperties gp = new GraphProperties(g);

        for (int s = 0; s < 9; s++)
        System.out.println(s + ": " + gp.eccentricity(s));
        System.out.println("radius = " + gp.radius());
        System.out.println("diameter = " + gp.diameter());
        System.out.println("center = " + gp.center());
        System.out.println("wiener = " + gp.wiener());
        System.out.println("girth  = " + gp.girth());


//        result:
//        Radius = 9
//        Diameter = 18
//        Center = 2057
    }
}



//package Chapter_4_1.Exercises;
//
//        import Chapter_1_3.Queue;
//        import Chapter_4_1.BreadthFirstPaths;
//        import Chapter_4_1.ConnectedComponent;
//        import Chapter_4_1.Graph;
//
//public class GraphProperties {
//
//    Graph g;
//    int[] depth;
//    boolean[] marked;
//
//    int diam;
//    int rad;
//    int cent;
//    int s;
//
//    public GraphProperties(Graph g){
//        this.g = g;
//        depth = new int[g.V()];
//        marked = new boolean[g.V()];
//        udpateProperties(0); // if no `s` is specified, just take zero
//    }
//    public GraphProperties(Graph g, int s){
//        this.g = g;
//        depth = new int[g.V()];
//        marked = new boolean[g.V()];
//        udpateProperties(s);
//    }
//
//    // note: it is assumed the graph is connected. Otherwise, the results of radius(), center() and diameter() are wrong.
//
//    private int eccentricity(int s){
//        return bfs(s);
//    }
//
//    private int bfs(int s){
//
//        resetArrays();
//        Queue<Integer> q = new Queue<Integer>();
//        q.enqueue(s);
//        marked[s] = true;
//        depth[s] = 0;
//        int maxDepth = 0;
//        while (!q.isEmpty()){
//            int x = q.dequeue();
//            for (int w : g.adj(x))
//                if (!marked[w]){
//                    marked[w] = true;
//                    depth[w] = depth[x] + 1;
//                    q.enqueue(w);
//                    // check for max here:
//                    if (depth[w] > maxDepth) maxDepth = depth[w];
//                }
//        }
//        return maxDepth;
//    }
//
//    private void resetArrays(){
//        for (int i = 0; i<g.V(); i++){
//            depth[i] = 0;
//            marked[i] = false;
//        }
//    }
//
//    public void udpateProperties(int s){
//
//        System.out.println("Component Update has stated");
//
//        // determine vertices connected to s;
//        ConnectedComponent cc = new ConnectedComponent(g);
//        Queue<Integer> verticesConnectedToS = new Queue<Integer>();
//        for (int i = 0; i < g.V(); i++){
//            if (cc.id(i) == cc.id(s)) verticesConnectedToS.enqueue(i);
//        }
//        System.out.println();
//
//        int minEccentricity = g.V();
//        int maxEccentricity = 0;
//        int center = 0;
//        // target only the component connected to `s`
//        for (int v : verticesConnectedToS){
//            System.out.println("Vertex being analyzed: " + v);
//
//            int ecc = eccentricity(v);
//            if (ecc < minEccentricity){
//                minEccentricity = ecc;
//                center = v;
//            }
//            if (ecc > maxEccentricity) maxEccentricity = ecc;
//        }
//
//        rad = minEccentricity;
//        diam = maxEccentricity;
//        cent = center;
//    }
//
//    public int center(){return cent; }
//    public int radius(){return rad; }
//    public int diameter(){return diam; }
//
//
//
//
//    public int girth(){
//        int smallestCycle = g.V() + 1;
//        int cycle;
//        for (int s = 0; s < g.V(); s++){
//            for (int v : g.adj(s)){
//                BreadthFirstPathGirth bfp = new BreadthFirstPathGirth(g, s, v);
//                if (bfp.hasPathTo(v)){
//                    cycle = 1 + bfp.dist(v);
//                    if (cycle < smallestCycle) smallestCycle = cycle;
//                }
//            }
//        }
//        if (smallestCycle == g.V() + 1) return -1; // graph is acyclic
//        else return smallestCycle;
//    }
//
//
//    public int wiener(){
//        // the methods assumes the graph is connected
//        int wienerSum = 0;
//        for (int v = 0; v < g.V(); v++){
//            BreadthFirstPaths bfp = new BreadthFirstPaths(g, v);
//            for (int w = 0; w < v; w++) wienerSum += bfp.dist(w);
//        }
//        return wienerSum;
//    }
//
//
//
//    public static void main(String[] args){
//
//        Graph g = new Graph(9);
////        int[] v1 = {0, 1,  1, 2, 4, 5, 0};
////        int[] v2 = {1, 2,  4, 5, 6, 6, 3};
//
//        int[] v1 = {0, 1, 1, 2, 4, 5, 0,7,8,8};
//        int[] v2 = {1, 2,4, 5, 6, 6, 3,2,5,7};
//
//        for (int i = 0; i<v1.length; i++)
//            g.addEdge(v1[i], v2[i]);
//
//
//        GraphProperties gp = new GraphProperties(g);
//
//        for (int s = 0; s < 9; s++)
//            System.out.println(s + ": " + gp.eccentricity(s));
//        System.out.println("radius = " + gp.radius());
//        System.out.println("diameter = " + gp.diameter());
//        System.out.println("center = " + gp.center());
//        System.out.println("wiener = " + gp.wiener());
//        System.out.println("girth  = " + gp.girth());
//
//
////        result:
////        Radius = 9
////        Diameter = 18
////        Center = 2057
//    }
//}
