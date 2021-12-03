package Chapter_6;

import Chapter_2_4.MinPQ;

public class minPQtest {



    public static void main(String[] args){


        int N = 100;
        MinPQ<Double> pq = new MinPQ<Double>(100);

        double[] randValues = new double[N];
        for (int i =0; i<N; i++) randValues[i] = Math.random();

        for (double x : randValues) pq.insert(x);
        while (!pq.isEmpty()) System.out.println(pq.delMin());


    }
}
