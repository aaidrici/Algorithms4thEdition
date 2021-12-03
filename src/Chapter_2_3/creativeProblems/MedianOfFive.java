package Chapter_2_3.creativeProblems;

import Chapter_2_3.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class MedianOfFive {


    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }

    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }



    public static Comparable medianOfFive(Comparable[] a, int lo){
        int medianId;
        // Apply selection sort to first three item (takes at most 3 compares)
        for (int i = lo;i<lo+3;i++)
            for (int j = i+1; j<3; j++)
                if (less(a[j],a[i]))
                    exch(a,i,j);
        medianId = lo+1;

        // determine where the remaining two items are w.r.t the median of three items (2 compares)
        Boolean fourthItemGreaterThenMedia = less(a[medianId], a[lo+3]);
        Boolean fifthItemGreaterThanMedian = less(a[medianId], a[lo+4]);

        // either pick the previously found median, or select the highest element between a[3],a[4],a[0] or
        // smallest element between a[3],a[4],a[2] (at most 2 compares)
        if (fourthItemGreaterThenMedia ^ fifthItemGreaterThanMedian){
            return medianId;
        }
        else{
            if (fourthItemGreaterThenMedia == true){
                // pick smaller item between a[2], fourth item and fifth item
                if (less(a[lo+3],a[lo+4])){
                    if (less(a[lo+3],a[lo+2]))
                        return lo+3;
                    else
                        return lo+2;
                }
                else{
                    if (less(a[lo+4], a[lo+2]))
                        return lo+4;
                    else
                        return lo+2;
                }
            }
            else{
                // pick largest item between a[0], fourth item and fifth item

                if (less(a[lo+3],a[lo+4]))
                    if (less(a[lo+0],a[lo+4]))
                        return lo+4;
                    else
                        return lo+0;
                else{
                    if (less(a[lo+0],a[lo+3]))
                        return lo+3;
                    else
                        return lo+0;
                }
            }
        }
    }


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

        int id;
        Comparable v; // partitioning item
        if (hi - lo + 1 > 4){
            v = medianOfFive(a,lo);
            exch(a,lo,lo+1);
        }
        else{
            v = a[lo];
        }


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


    public static void main(String[] args){


        // Doubling test with quick sort with median of three improvement and quick sort with median of 5 improvement


        MedianOfThree.sortingMethod[] methodList = {MedianOfThree.sortingMethod.MedianOfThree, MedianOfThree.sortingMethod.MedianOfFive};
        for (MedianOfThree.sortingMethod x : methodList){


            if (x == MedianOfThree.sortingMethod.MedianOfFive)
                System.out.println("Sorting Method: Quick sort with median of five");
            else if( x== MedianOfThree.sortingMethod.MedianOfThree)
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

                if (x == MedianOfThree.sortingMethod.MedianOfThree)
                    MedianOfThree.sort(a);
                else if( x== MedianOfThree.sortingMethod.MedianOfFive)
                    MedianOfFive.sort(a);

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

