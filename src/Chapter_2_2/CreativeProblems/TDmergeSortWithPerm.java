package Chapter_2_2.CreativeProblems;
import edu.princeton.cs.algs4.StdRandom;


public class TDmergeSortWithPerm {


    private static int[] aux; // auxiliary array for merges
    private static int[] perm; // auxiliary array for merges

//    private static boolean less(Comparable v, Comparable w)
//    { return v.compareTo(w) < 0; }

    private static boolean less(int id1, int id2, Comparable[] a)
    {
        Comparable v = a[id1];
        Comparable w = a[id2];
        return v.compareTo(w) < 0;
    }



    public static int[] sort(Comparable[] a)
    {
        aux = new int[a.length]; // Allocate space just once.
        perm = new int[a.length]; // Allocate space just once.


        for (int i = 0; i<a.length; i++)
            perm[i] = i;

        sort(a, 0, a.length - 1, perm);
        return perm;
    }

    private static void sort(Comparable[] a, int lo, int hi, int[] perm)
    { // Sort a[lo..hi].
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid, perm); // Sort left half.
        sort(a, mid+1, hi, perm); // Sort right half.
        merge(a, lo, mid, hi, perm); // Merge results (code on page 271).
    }


    public static void merge(Comparable[] a, int lo, int mid, int hi, int[] perm)
    { // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++)
            aux[k] = perm[k]; /// key ELEMENT TO CHANGE
        for (int k = lo; k <= hi; k++)
            if (i > mid) perm[k] = aux[j++];
            else if (j > hi ) perm[k] = aux[i++];
            else if (less(aux[j], aux[i], a)) perm[k] = aux[j++];
            else perm[k] = aux[i++];
    }

    public static void main(String[] args){
        int N = 10;
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0,N*2);

        int[] perm = new int[N];


        for (Integer x : a) System.out.print(x + " ");
        System.out.println();
        perm = sort(a);
        for (int x : perm) System.out.print(a[x] + " ");




    }





}
