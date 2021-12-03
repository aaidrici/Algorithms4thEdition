package Chapter_2_1.CreativeProblems;


import Chapter_2_1.Exercises.Certification;
import Chapter_2_1.Exercises.ShellWithArray;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;
import static Chapter_2_1.Exercises.Certification.check;
import Chapter_2_1.CreativeProblems.SortCompare;


public class InsertionSortWithoutExchanges {



    public static void sort(Comparable[] a) { // Sort a[] into increasing order.
        int N = a.length;
        for (int i = 1; i < N; i++)
        { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..


            int j = i;
            while (j > 0 && less(a[i], a[j-1])){
                j--;
            }
            // i = key id we need to move
            // j = desired final position
            Comparable temp = a[i];
            for (int k = i; k>=j+1; k--){
                a[k] = a[k-1];
            }
            a[j] = temp;
        }
    }
    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }


    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }


    public static void main(String[] args) {


        System.out.println("Speed ratio: ");
        double speedRatio = SortCompare.SpeedRatio(
                "InsertionSortWithoutExchanges", //
                "InsertionSort",
                1000,
                10000);
        StdOut.printf("%.5f",speedRatio);

    }




}
