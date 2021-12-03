package Chapter_4_1.Experiment;
import Chapter_4_1.CreativeProblems.EuclideanGraph;
import Chapter_4_1.Digraph;

public class RandomEuclideanGraph {





    public static void main(String[] args){

        int V = 50;

//        V = Integer.parseInt(args[0]);
//        E = Integer.parseInt(args[1]);

        final double X_MIN = -5;
        final double Y_MIN = -5;
        final double X_MAX = 5;
        final double Y_MAX = 5;

        Digraph g = new Digraph(V);
        EuclideanGraph eg = new EuclideanGraph(g, X_MIN, X_MAX, Y_MIN, Y_MAX);

        for (int i = 0; i<V ;i++){
            double x1 = Math.random()*(X_MAX - X_MIN) + X_MIN;
            double y1 = Math.random()*(Y_MAX - Y_MIN) + Y_MIN;
            eg.set_x_y(i,x1,y1);
            System.out.println(x1 + ": " + y1);
        }


        double d = 1.9;
        // brute force ~n^2
        for (int v = 0; v < g.V(); v++){
            for (int vp = 0; vp < v; vp++){
                double dist = Math.pow((eg.x[v] - eg.x[vp]),2) + Math.pow(eg.y[v] - eg.y[vp], 2);
                if (dist < d * d) g.addEdge(v, vp);
            }
        }


        eg.Display();

    }
}
