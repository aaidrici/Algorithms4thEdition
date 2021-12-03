package Chapter_2_3.creativeProblems;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

public class HistogramGenerator {

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



    public static void PlotHistogram(double[] categories, double[] height){

        double bin_qty = height.length;

        double max_height = height[0];
        double total_height = 0;
        for (int i = 1; i<bin_qty; i++){
            total_height += height[i];
            if (height[i] > max_height)
                max_height = height[i];
        }

        double bottom_margin = 0.05; // expressed as percentage of histogram
        double top_margin = 0.05; //

        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0-bottom_margin, 1+0.05);

        double bin_relative_width = 0.5;

        int fontSize = 10;
        StdDraw.setFont(new Font("Arial", Font.BOLD, fontSize));
        double x,y;
        for (int i = 0; i<bin_qty; i++){
            y = height[i] / max_height / 2;
            x = 1.0/bin_qty * (i + 0.5);
            StdDraw.filledRectangle(x,y,1/bin_qty * bin_relative_width / 2,y);
            StdDraw.text(x,-bottom_margin/2,String.format("%.0g %n", categories[i]));
            StdDraw.text(x,2*y+top_margin/2,String.format("%.3g %% %n", height[i] / total_height * 100));
        }



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


        double[] Categories = {1,3,4,5,5,6,6};
        double[] heights =  {0.5,2,8,32,12,12,3};

        HistogramGenerator.PlotHistogram(Categories, heights);


    }


}