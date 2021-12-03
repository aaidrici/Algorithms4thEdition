package Chapter_2_1.Exercises;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Insertion
{

    private static enum displayMode{
        blank, Animated
    }

    private static int X_CANVAS_SIZE = 500;
    private static int Y_CANVAS_SIZE = 100;
    private static double BAR_HEIGHT_CEIL = 80;
    private static double BAR_WIDTH = 2.0;
    private static double BAR_GAP = 1.0;
    private static double BOTTOM_MARGIN = 10;
    private static double LEFT_MARGIN = 10;
    private static double x1, x2, y1, y2;
    private static double scaling_factor;
    private static Boolean firstPass = true;
    static displayMode currentDisplayMode = displayMode.blank;




    public static void sort(Comparable a[], displayMode requestedDisplayMode ){
        currentDisplayMode = requestedDisplayMode;
        sort(a);
    }

    public static void sort(Comparable[] a) { // Sort a[] into increasing order.

        if (currentDisplayMode == displayMode.Animated){
            Comparable a_max = a[0];
            for (int i = 1; i < a.length; i++) {
                if (a[i].compareTo(a_max) > 0) a_max = a[i];
            }
            scaling_factor = BAR_HEIGHT_CEIL / (double)a_max;
            prepareCanvas();
        }

        int N = a.length;
        for (int i = 1; i < N; i++)
        { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--){
                exch(a, j, j-1);


                if (currentDisplayMode == displayMode.Animated){
                    DynamicCanvasPrint(a, scaling_factor, j, j-1);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        }
    }
    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }


    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }

// See page 245 for less(), exch(), isSorted(), and main().

    private static void prepareCanvas(){
        StdDraw.setCanvasSize(X_CANVAS_SIZE, Y_CANVAS_SIZE);
        StdDraw.setXscale(0.0, X_CANVAS_SIZE);
        StdDraw.setYscale(0.0, Y_CANVAS_SIZE);
    }

    private static void DynamicCanvasPrint(Comparable[] a, double scaling_factor, int key1, int key2){
        StdDraw.setPenColor(StdDraw.BLACK);

        if (firstPass == true){ // all bars need to be drawn
            firstPass = false;
            StdDraw.setPenColor(StdDraw.BLACK);
            for (int i = 0; i < a.length; i++) {
                x1 = LEFT_MARGIN + i * (BAR_WIDTH + BAR_GAP) + BAR_WIDTH / 2;
                x2 = BAR_WIDTH / 2;
                y1 = (double)a[i] / 2 * scaling_factor + BOTTOM_MARGIN;
                y2 = (double)a[i] / 2 * scaling_factor;
                StdDraw.filledRectangle(x1, y1, x2, y2);

            }
        }
        else { // only redraw bar with key 1 and key 2
            int[] keys = {key1, key2};
            for (int i : keys){

                x1 = LEFT_MARGIN + i * (BAR_WIDTH + BAR_GAP) + BAR_WIDTH / 2;
                x2 = BAR_WIDTH / 2;
                y1 = (double)a[i] / 2 * scaling_factor + BOTTOM_MARGIN;
                y2 = (double)a[i] / 2 * scaling_factor;

                // reset canvas locally
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.filledRectangle(x1, BAR_HEIGHT_CEIL/2+ BOTTOM_MARGIN, x2, BAR_HEIGHT_CEIL/2);

                // re-draw the updated line
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledRectangle(x1, y1, x2, y2);
            }
        }
    }

    public static void main(String[] args) {

        int N = 100;
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = (Double)StdRandom.uniform(0, 1.0);
        }

        Insertion.sort(a,displayMode.Animated);


    }
}
