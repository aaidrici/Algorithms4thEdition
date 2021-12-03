package Chapter_1_2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
public class VisualCounter {

    private int N;
    private int max;
    int[] numlist = {};

    public VisualCounter(int N, int max) {
        this.N = N;
        this.max = max;
        appendValue(N);
    }

    void increment() {
        if (N != max) {
            N++;
        }
        appendValue(N);
    }

    void decrement() {
        if (N != -max) {
            N--;
        }
        appendValue(N);
    }

    void appendValue(int a){
        numlist = Arrays.copyOf(numlist, numlist.length + 1);
        numlist[numlist.length - 1] = a;
    }
    void Display(){

        StdDraw.setXscale(0,1.01*(double)numlist.length);
        StdDraw.setYscale(-(double)max, (double)max);

        StdDraw.setPenRadius(0.005);
        for (int i = 0; i<numlist.length; i++){
            StdDraw.point(i,numlist[i]);
        }
    }

    public static void main(String args[]){

        int N = Integer.parseInt(args[0]);
        int max = Integer.parseInt(args[1]);

        VisualCounter testObj = new VisualCounter(N,max);
        for (int i = 0; i<20; i++){
            if (StdRandom.uniform() > 0.5)
                testObj.increment();
            else
                testObj.decrement();
        }
        testObj.Display();
    }


}