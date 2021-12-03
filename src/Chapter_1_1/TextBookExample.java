package Chapter_1_1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;

public class TextBookExample {


    public static void main (String args[]) {

        double a[] = sortingStuff.randomArrayGenerator();
        sortingStuff.sorting1(a);

        ayy aa = new ayy();
    }
}

class ayy{
    int a = 1;
    static void ayy(){
       int a = 10;
    }

}


class DrawingSomeGraphs{ // this class contain different static methods where the stdDraw library is being tested out

    public static void func1(){
        int N = 100;
        StdDraw.setXscale(0,N);
        StdDraw.setYscale(0,N*N);
        StdDraw.setPenRadius(.01);
        for (int i = 1; i <= N; i++){
            StdDraw.point(i,i);
            StdDraw.point(i,i*i);
            StdDraw.point(i,i*Math.log(i));
        }
    }

    public static void func2(){
        int N = 100;
        double[] a = new double [N];
        for (int i = 0; i<N; i++){
            a[i] = StdRandom.random();
        }
        Arrays.sort(a);
        for (int i = 1; i<N; i++){
            double x = 1.0*i/N;
            double y = a[i]/2.0;
            double rw = 0.5/N;
            double rh = a[i]/2.0;
            StdDraw.filledRectangle(x,y,rw,rh);
        }
    }
}

class sortingStuff{

    public static void sorting1 (double[] a){
        // this function takes an array as an input and outputs its sorted version
        printArray(a);
        Arrays.sort(a);
        printArray(a);
    }
    static void printArray(double a[]){
        int length = a.length;
        for (int i = 0; i<length; i++)
            System.out.print(a[i] + " ");
        System.out.print("\n");
    }
    static double[] randomArrayGenerator(){ // returns an array of 10 random double
        double[] a = new double[10];
        for (int i = 0; i<10 ; i++)
            a[i] = Math.random();
        return a;
    }


}


class playingWithArrays{
    static void ayy() {
        // array declaration: long form
        double[] a;
        a = new double[10];
        for (int i = 0; i < 10; i++)
            a[i] = i;

        // array declaration: short form
        boolean[] b = new boolean[10];
        System.out.println("\n" + b[9]);

        // array declaration/creation/initialization short form
        int[] c = {12, 3, 4, 5};
        System.out.println(c[1] + c[3]);
    }
}

class InstanceVsStaticMethod{

    static void StaticMethod(){
        System.out.print("This is an example of a static method\n\n");
    }

    void InstanceMethod(){
        System.out.print("This is an example of an instance variable\n\n");
    }

}