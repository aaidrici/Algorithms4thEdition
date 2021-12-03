package Chapter_2_5.Exercises;
import edu.princeton.cs.algs4.StdRandom;


// Question was misread. This file is useless.

public class SelectNumberOfCompares {


    public int NumberOfCompare;

    public SelectNumberOfCompares(){
        NumberOfCompare = 0;
    }

    public void ResetCompareCount(){
        NumberOfCompare = 0;
    }


    private void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }
    private boolean less(Comparable v, Comparable w) {
        NumberOfCompare++;
        return v.compareTo(w) < 0;
    }



    private int partition(Comparable[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi + 1; // left and right scan indices
        Comparable v = a[lo]; // partitioning item
        while (true) { // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], v)){
                NumberOfCompare++;
                if (i == hi) break;
            }
            while (less(v, a[--j])){
                NumberOfCompare++;
                if (j == lo) break;
            }
            NumberOfCompare++;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); // Put v = a[j] into position
        return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
    }


    public Comparable select(Comparable[] a, int k)
    {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo)
        {
            NumberOfCompare++;
            int j = partition(a, lo, hi);
            if (j == k) {
                NumberOfCompare++;
                return a[k];
            }
            else if (j > k) hi = j - 1;
            else if (j < k) lo = j + 1;
            NumberOfCompare++;
        }
        NumberOfCompare++;
        return a[k];
    }



    public static void main(String[] args){



        int[] arraySizes = {1000, 10000, 100000, 1000000};
        int numberOfTrial = 100;
        int numberOfCompares = 0;
        int k = 10;
        SelectNumberOfCompares selection = new SelectNumberOfCompares();


        System.out.print("Assuming cost model = C * N * log2N\n");
        System.out.printf("Sample size per array size value = %d\n", numberOfTrial);


        for (int N : arraySizes){

            System.out.printf(" Array size = %d, ", N);

            Integer[] a = new Integer[N];
            int totalnumberOfCompares = 0;

            for (int j = 0; j < numberOfTrial; j++){

                for (int i = 0; i<N; i++) a[i] = StdRandom.uniform(0,10*N);

                selection.select(a, k);
                numberOfCompares = selection.NumberOfCompare;
                selection.ResetCompareCount();
                totalnumberOfCompares += numberOfCompares;
            }
            System.out.printf("mean number of comparison %.4f, ", (double)totalnumberOfCompares / numberOfTrial);
            System.out.printf("C = %.4f\n", (double)totalnumberOfCompares / numberOfTrial / N / (Math.log10(N)/Math.log10(2)) );




        }



        // it is estimated the total number of compared will be on average proportional to log2(arraySize);




    }

}
