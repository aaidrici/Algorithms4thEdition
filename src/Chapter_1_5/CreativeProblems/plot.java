package Chapter_1_5.CreativeProblems;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;


public class plot {


    private static double minOfDoubleArray(double[] a ){
        double temp = a[0];
            for (int i = 1; i<a.length ; i++)
                if (temp > a[i]) temp = a[i];
            return temp;
    }
    private static double maxOfDoubleArray(double[] a ){
        double temp = a[0];
        for (int i = 1; i<a.length ; i++)
            if (temp < a[i]) temp = a[i];
        return temp;
    }



    public static void Plot(double[] x, double[] y, double[] x2, double[] y2){

        double x_min = Math.min(minOfDoubleArray(x), minOfDoubleArray(x2));
        double x_max = Math.max(maxOfDoubleArray(x), maxOfDoubleArray(x2));
        double y_min = Math.min(minOfDoubleArray(y), minOfDoubleArray(y2));
        double y_max = Math.max(maxOfDoubleArray(y), maxOfDoubleArray(y2));

        System.out.println(x_min);
        System.out.println(x_max);
        System.out.println(y_min);
        System.out.println(y_max);



        double delta_y = y_max - y_min;
        double delta_x = x_max - x_min;

        double BOTTOMMARGIN = 0.06 * delta_y;
        double TOPMARGIN = 0.01 * delta_y;
        double LEFTMARGIN = 0.06 * delta_x;
        double RIGHTMARGIN = 0.01 * delta_x;

        StdDraw.setXscale(x_min - LEFTMARGIN, x_max + RIGHTMARGIN);
        StdDraw.setYscale(y_min - BOTTOMMARGIN, y_max + TOPMARGIN);

        StdDraw.setPenRadius(0.002);
        StdDraw.line(x_min, y_min, x_min ,y_max);
        StdDraw.line(x_min, y_min, x_max, y_min);


        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        for (int i = 0; i < x.length; i++){
            StdDraw.point(x[i], y[i]);
        }
        StdDraw.setPenColor(StdDraw.RED);
        for (int i = 0; i < x.length; i++){
            StdDraw.point(x2[i], y2[i]);
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        int fontSize = 10;
        StdDraw.setFont(new Font("Arial", Font.BOLD, fontSize));
        StdDraw.textRight(x_min-LEFTMARGIN/5, y_min, Double.toString(y_min));
        StdDraw.textRight(x_min-LEFTMARGIN/5, y_max, Double.toString(y_max));
        StdDraw.textRight(x_min, y_min-BOTTOMMARGIN/3, Double.toString(x_min));
        StdDraw.textRight(x_max, y_min-BOTTOMMARGIN/3, Double.toString(x_max));

    }

    public static void main(String[] args){


//        int[] y = new int[20];
//        int[] x = new int[20];
//        for (int i = 0; i<20; i++){
//            x[i] = i;
//            y[i] = StdRandom.uniform(-200,200);
//        }
//
//        int[] y2 = new int[20];
//        int[] x2 = new int[20];
//        for (int i = 0; i<20; i++){
//            x2[i] = i;
//            y2[i] = StdRandom.uniform(-10,10);
//        }
//
//
//        plot.Plot(x,y,x2,y2);


    }


}
