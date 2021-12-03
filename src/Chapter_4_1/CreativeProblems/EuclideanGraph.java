package Chapter_4_1.CreativeProblems;

import Chapter_4_1.Digraph;
import edu.princeton.cs.algs4.Draw;

import java.awt.*;

public class EuclideanGraph {

    Digraph g;

    public Double[] y;
    public Double[] x;
    double bound[] = {0,0,0,0};

    public EuclideanGraph(Digraph g){
        this.g = new Digraph(g);
        x = new Double[g.V()];
        y = new Double[g.V()];
    }

    // specify as well the bounds of the input point in space
    public EuclideanGraph(Digraph g, double x1, double x2, double y1, double y2){
        this.g = new Digraph(g);
        x = new Double[g.V()];
        y = new Double[g.V()];
        bound[0] = x1;
        bound[1] = x2;
        bound[2] = y1;
        bound[3] = y2;

    }

    public void set_x_y(int vertex_id, Double x, Double y){

        this.x[vertex_id] = x;
        this.y[vertex_id] = y;

        if (x < bound[0]) bound[0] = x;
        else if (x > bound[1]) bound[1] = x;
        if (y < bound[2]) bound[2] = y;
        else if (y > bound[3]) bound[3] = y;
    }



    public void Display(){
        Draw d = new Draw();
        d.setCanvasSize(500, 500);
        d.setXscale(bound[0], bound[1]);
        d.setYscale(bound[2], bound[3]);

        Font f = new Font("TimesRoman", Font.BOLD, 10);
        d.setFont(f);


        // Drawing grids
        d.setPenColor(Draw.LIGHT_GRAY);
        double scale = 1;
        double xv = 0;
        if (bound[0] > 0){ while (xv <= bound[0]) xv+= scale; }
        else {while (xv >= bound[0]) xv -= scale;}
        while (xv < bound[1]) {d.line(xv,bound[2] ,xv, bound[3]); xv+= scale; }

        double yv = 0;
        if (bound[2] > 0){ while (yv <= bound[2]) yv+= scale; }
        else {while (yv >= bound[2]) yv -= scale;}
        while (yv < bound[3]) {
            d.line(bound[0],yv, bound[1], yv);
            yv += scale;
        }



        // drawing edges
        d.setPenColor(Draw.RED);
        for (int v1 = 0; v1 < g.V(); v1++){
            for (int v2 : g.adj(v1)) {
                d.line(x[v1], y[v1], x[v2], y[v2]);
            }
        }

        // drawing vertices
        d.setPenColor(Draw.BLACK);
        for (int v = 0; v < g.V(); v++)
            d.point(x[v], y[v]);

        // writing vertex number
        d.setPenColor(Draw.BLACK);
        for (int v = 0; v < g.V(); v++)
            d.textRight(x[v], y[v],Integer.toString(v));
        d.show();

        d.textLeft(bound[0], bound[2]+0.1,"scale = " + Double.toString(scale));
    }

    public static void main(String[] args){

        int N = 20;
        int V = 20;

        Digraph g = new Digraph(N);
        for (int i = 0; i<V; i++){
            int v1 = (int)Math.floor(Math.random()*N);
            int v2 = (int)Math.floor(Math.random()*N);
            g.addEdge(v1, v2);
        }


        EuclideanGraph eg = new EuclideanGraph(g);

        for (int v = 0; v < g.V(); v++)
            eg.set_x_y(v,Math.random(), Math.random());



        eg.Display();

        System.out.println(g.toString());
    }
}
