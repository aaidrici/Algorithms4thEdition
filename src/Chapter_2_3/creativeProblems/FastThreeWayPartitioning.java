package Chapter_2_3.creativeProblems;
import Chapter_2_1.Exercises.Certification;
import Chapter_2_3.Quick;
import edu.princeton.cs.algs4.StdRandom;

public class FastThreeWayPartitioning {

    static Integer j_low;
    static Integer j_high;

    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }
    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }

    public static void sort(Comparable[] a)
    {
//        StdRandom.shuffle(a); // Eliminate dependence on input.
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi)
    {

        if (hi <= lo) return;
        partition(a, lo, hi); // Partition (see page 291).

        sort(a, lo, j_low); // Sort left part a[lo .. j-1].
        sort(a, j_high, hi); // Sort right part a[j+1 .. hi].
    }

    private static void partition(Comparable[] a, int lo, int hi)
    { // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi+1; // left and right scan indices
        int p = lo+1;
        int q = hi;

        Comparable v = a[lo]; // partitioning item
        while (true)
        { // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;

            if (i >= j) break;

            // ADDED FROM ORIGINAL QUICK SORT
            // prior to making a swap, we must ensure values equal to a[lo] are put to the tails.
            if (a[lo] == a[i]) {exch(a, i, p++);j++;  continue; };
            if (a[lo] == a[j]) {exch(a, j, q--);i--;  continue; }

            exch(a, i, j);

        }

        // there are two different scenarios to consider:
        //   j could either be strictly greater than a[lo], in this case, proceed to swap a[lo] and a[j]
        //   j could already be equal to a[lo], in this case we already want to consider it into the
        exch(a, lo, j); // Put v = a[j] into position


        // ADDED FROM ORIGINAL QUICK SORT
        int c = 1;
        while (q < hi) exch(a,++q,j + c++);
        j_high = j + c;
        c = 1;
        while (p > lo+1) exch(a,--p, j - c++);
        j_low = j - c;


        if (a[lo] == a[j]){
            if(j_high < hi+1){
                exch(a, j_high, lo);
                j_high++;
            }
            else{
                exch(a, j_low, lo);
                j_low--;
            }
        }
    }


    public static void main(String[] args){
//
//        int N = 20;
//        Integer[] a = new Integer[N];
//        for (int i = 0; i<N; i++)
//            a[i] = StdRandom.uniform(0,7);
//        a[0] = 3;

        Integer[] a = {3, 3, 1, 2, 1, 4, 0, 0, 4, 3, 1, 4, 3, 3, 5, 4, 0, 1, 0, 4};
        Integer[] a_original = java.util.Arrays.copyOf(a,a.length);


        for (Integer x : a) System.out.print(x + " ");
        System.out.println();

        sort(a);

        for (Integer x : a) System.out.print(x + " ");

        if (Certification.check(a, a_original))
            System.out.println("The test Has passed.");


    }

}
