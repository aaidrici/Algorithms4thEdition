package Chapter_2_4.CreativeProblems;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;






// in order to make this work:

// Build the class using the IDE
// >> cd C:\Users\Ali Idrici\Documents\04-project\04-Algorithms>
// >> java -cp .\out;algs4.jar Chapter_2_4.CreativeProblems.ClosestEuclideanPoints < src\Chapter_2_4\CreativeProblems\Coordinates.txt


public class ClosestEuclideanPoints {


    private final static  double COORD_UPPER_LIMIT = 10.0;
    private final static double COORD_LOWER_LIMIT = -10.0;
    public static void pointsGenerator(String fileName, int N)throws IOException {

        double x;
        double y;
        double z;

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (int i = 0; i < N; i++) {
            x = StdRandom.uniform(COORD_LOWER_LIMIT, COORD_UPPER_LIMIT);
            y = StdRandom.uniform(COORD_LOWER_LIMIT, COORD_UPPER_LIMIT);
            z = StdRandom.uniform(COORD_LOWER_LIMIT, COORD_UPPER_LIMIT);

            String line = String.format("%.3f", x) + " " + String.format("%.3f", y) + " " + String.format("%.3f", z) + "\n";
            writer.write(line);

        }
        writer.close();
    }




    public static void main(String[] args) throws IOException {

        // use this file for simplicity
        String filename = "src/Chapter_2_4/CreativeProblems/Coordinates.txt";
        int numberOfGeneratedPoint = 50;
        boolean generateNewPoints = true;
        if (generateNewPoints)
            pointsGenerator(filename, numberOfGeneratedPoint);


        // create PQ
        int N = numberOfGeneratedPoint;
        DistancePQ<EuclidianPoint> pq = new DistancePQ<>(N);

        double x, y, z;

        int i = 0;
        while (!StdIn.isEmpty() && i++ < N) {


            x = StdIn.readDouble();
            y = StdIn.readDouble();
            z = StdIn.readDouble();

            EuclidianPoint pt = new EuclidianPoint(x,y,z);

            pq.insert(pt);
            pt.Display();

        }

        System.out.println();
        for (int j = 0; j<N; j++){
            EuclidianPoint pt = pq.delMax();
            pt.Display();
        }



    }
}

// set working directory as : C:\Users\Ali Idrici\Documents\04-project\04-Algorithms
// use following command:

// javac

//C:\Users\Ali Idrici\Documents\04-project\04-Algorithms>java -cp algs4.jar;src Chapter_2_4.CreativeProblems.ClosestEuclidianPoints2 < src/Chapter_2_4/CreativeProblems/Coordinates.txt



class EuclidianPoint implements Comparable<EuclidianPoint>{
    public double x, y, z;
    public double dist2originInverse;
    public EuclidianPoint(double x, double y, double z){
        this.x = x; this.y = y; this.z = z;
        dist2originInverse = 1/Math.sqrt(x*x + y*y + z*z);
    }
    public int compareTo(EuclidianPoint that){
        return (int)(1000*(this.dist2originInverse - that.dist2originInverse));
    }
    public void Display() {
        System.out.printf("(%.4f, %.4f, %.4f) dist = %.4f\n", x, y, z, 1/dist2originInverse);
    }
}


class DistancePQ<EuclidianPoint extends Comparable<EuclidianPoint>> {
    private EuclidianPoint[] pq; // heap-ordered complete binary tree
    private int N = 0; // in pq[1..N] with pq[0] unused

    public DistancePQ(int maxN) {
        pq = (EuclidianPoint[]) new Comparable[maxN + 1];
    }

    // added for Q2.4.19
    public DistancePQ(EuclidianPoint[] a) {

        N = a.length;
        pq = (EuclidianPoint[]) new Comparable[N + 1];
        for (int i = 1; i < N + 1; i++)
            pq[i] = a[i - 1];
        for (int i = N / 2; i > 0; i--)
            sink(i);

    }

    public void display() {
        for (int i = 1; i < N + 1; i++)
            System.out.print(pq[i] + " ");
    }

    // added for Q2.4.22
    private void CheckForArrayResize() {
        EuclidianPoint[] temp;
        if (N > pq.length * 4) {
            temp = (EuclidianPoint[]) new Comparable[(N + 1) / 2];
            for (int i = 1; i < N; i++)
                temp[i] = pq[i];
            pq = temp;
        } else if (pq.length == N + 1) {
            temp = (EuclidianPoint[]) new Comparable[(N + 1) * 2];
            for (int i = 1; i < N; i++)
                temp[i] = pq[i];
            pq = temp;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(EuclidianPoint v) {
        pq[++N] = v;
        swim(N);
    }

    public EuclidianPoint delMax() {
        EuclidianPoint max = pq[1]; // Retrieve max key from top.
        exch(1, N--); // Exchange with last item.
        pq[N + 1] = null; // Avoid loitering.
        sink(1); // Restore heap property.
        return max;
    }

    // See pages 145-147 for implementations of these helper methods.
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private void exch(int i, int j) {
        EuclidianPoint t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    // implemented for Q2.4.26
    private void SwimFast(int k) {
        EuclidianPoint val2swim = pq[k];
        while (k > 1 && less(pq[k/2], val2swim)) {
            pq[k] = pq[k / 2];
            k = k / 2;
        }
        pq[k] = val2swim;
    }

    private void SwimRegular(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void swim(int k) {
        SwimFast(k);
        //SwimRegular(k);
    }


    private void sink(int k) {
        //SinkRegular(k);
        SinkFast(k);
    }

    private void SinkRegular(int k) {

        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }


    // implemented for Q2.4.26
    private void SinkFast(int k) {

        EuclidianPoint val2Sink = pq[k];

        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(val2Sink, pq[j])) {
                // k is the final destination of Val2sink
                break;
            }
            pq[k] = pq[j];
            k = j;
        }
        pq[k] = val2Sink;

    }
}
