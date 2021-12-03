package MiscTools;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Plots {


    public static void PlotOfDouble(double[] x, double[] y){
        // find bounds
        if (x.length != y.length) return;

        double SIDEMARGIN = 0.1;
        double VERTICALMARGIN = 0.05;


        double x_max = x[0], x_min= x[0], y_max= y[0], y_min= y[0];
        for (int i = 1; i<x.length; i++){
            if (x_max < x[i]) {x_max = x[i];}
            if (x_min > x[i]) {x_min = x[i];}
            if (y_max < y[i]) {y_max = y[i];}
            if (y_min > y[i]) {y_min = y[i];}
        }
        double x_del = x_max - x_min;
        double y_del = y_max - y_min;

        System.out.println("x_max = " + x_max);
        System.out.println("x_min = " + x_min);
        System.out.println("y_max = " + y_max);
        System.out.println("y_min = " + y_min);


        StdDraw.setCanvasSize(600,500);

        StdDraw.setYscale(y_min - VERTICALMARGIN*y_del, y_max + VERTICALMARGIN*y_del);
        StdDraw.setXscale(x_min - SIDEMARGIN*x_del, x_max + SIDEMARGIN*x_del);

        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.line(x_min,y_min, x_min,y_max);
        StdDraw.line(x_min,y_min, x_max,y_min);


        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        for (int i = 0; i<x.length ; i++)
            StdDraw.point(x[i], y[i]);

        double labelOffset = VERTICALMARGIN*y_del/2;
        Font font = new Font("Arial", Font.PLAIN, 10);
        StdDraw.setFont(font);

        for (double i = 0; i<6; i++)
            StdDraw.text(x_min + i/4*x_del,y_min-labelOffset, Double.toString(x_min + i/4*x_del),0);

        for (double i = 0; i<6; i++){
            String value = String.format("%.2f",y_min + i/4*y_del);
            System.out.println(value);
            StdDraw.textRight(x_min,y_min + i/4*y_del, value);
            //StdDraw.textRight(x_min ,10000, value);


        }

    }



    public static void main(String[] args){

        int N = 100;
        double[] x = new double[N];
        double[] y = new double[N];
        for (int i = 0; i< N; i++){
            x[i] = i;
            y[i] = Math.sin(x[i]*0.1);
            System.out.println("x,y = " + x[i] + "," + y[i]);
        }

        PlotOfDouble(x,y);


    }

}
