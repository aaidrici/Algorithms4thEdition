package Chapter_2_3.creativeProblems;


import Chapter_2_3.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class MedianOfThree
{

    static double variance;
    static int variance_count;

    public static void medianOfThree(Comparable[] a, int lo) {
        // order the first three item from id's element using selection sort (at most 3 compare operations)

        for (int i = lo; i < lo+3; i++)
            for (int j = i + 1; j < 3; j++)
                if (less(a[j], a[i]))
                    exch(a, j, i);
    }

    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }
    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }

    public static void sort(Comparable[] a)
    {
        StdRandom.shuffle(a); // Eliminate dependence on input.
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo) return;
        int j = partition(a, lo, hi); // Partition (see page 291).
        sort(a, lo, j-1); // Sort left part a[lo .. j-1].
        sort(a, j+1, hi); // Sort right part a[j+1 .. hi].
    }

    private static int partition(Comparable[] a, int lo, int hi)
    { // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi+1; // left and right scan indices

        Comparable v; // partitioning item
        if (hi - lo + 1 > 2){
            medianOfThree(a,lo);
            exch(a,lo,lo+1);
        }
        v = a[lo];

        while (true)
        { // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); // Put v = a[j] into position
        return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
    }

    public enum sortingMethod {Quicksort, MedianOfThree, MedianOfFive};
    public void sort(Comparable[] a,sortingMethod method){
        if (method == sortingMethod.MedianOfThree)
            MedianOfThree.sort(a);
        else if (method == sortingMethod.Quicksort)
            Quick.sort(a);
    }

    public static void main(String[] args){

        // Doubling test with regular quick sort or median of three..


        sortingMethod[] methodList = {sortingMethod.Quicksort, sortingMethod.MedianOfThree};
        for (sortingMethod x : methodList){


            if (x == sortingMethod.Quicksort)
                System.out.println("Sorting Method: Quick sort");
            else if( x== sortingMethod.MedianOfThree)
                System.out.println("Sorting Method: Quick sort with median of three");

            int trial = 1000;
            int N = 2000;
            Integer[] a = new Integer[N];
            Integer[] a_copy = new Integer[N];
            double totalTime = 0;
            double delta_t;
            double t1;
            double prev_time;

            for (int j = 0; j<trial ; j++){
                // randomize the two arrays
                for ( int i = 0; i<N ; i++){
                    a[i] = StdRandom.uniform(0,2*N);
                    a_copy[i] = a[i];
                }

                t1 = System.currentTimeMillis();

                if (x == sortingMethod.Quicksort)
                    Quick.sort(a);
                else if( x== sortingMethod.MedianOfThree)
                    MedianOfThree.sort(a);

                delta_t = System.currentTimeMillis() - t1;
                totalTime += delta_t;
            }

            prev_time = totalTime;

            int N_pts = 0;
            while(N_pts++ < 7){
                N *= 2;
                a = new Integer[N];
                a_copy = new Integer[N];

                for (int j = 0; j<trial ; j++){
                    // randomize the two arrays
                    for ( int i = 0; i<N ; i++){
                        a[i] = StdRandom.uniform(0,2*N);
                        a_copy[i] = a[i];
                    }

                    t1 = System.currentTimeMillis();
                    Quick.sort(a); // change tested function here...
                    delta_t = System.currentTimeMillis() - t1;
                    totalTime += delta_t;
                }


                StdOut.printf("N = %d, doublingRatio = %.4f\n",N,totalTime/prev_time);
                prev_time = totalTime;
            }

        }

        // Result discussion:
        //  It seems that the order of growth when using quick sort with the median of three is slightly smaller than
        //  using quick sort with the median of three improvement. However, the difference is quite small.
        //





    }


}
