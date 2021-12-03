package Chapter_1_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
public class BinarySearch
{
    public static int rank(int key, int[] a)
    { // Array must be sorted.
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi)
        { // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key <= a[mid]) hi = mid;
            else if (key > a[mid]) lo = mid + 1;
        }

        if (a[lo] == key ) return lo;
        else return -1;
    }

    public static void main(String[] args)
    {
        String filename = "src/Chapter_1_4/inputFiles/Q10.txt";
        In file = new In(filename);
        int[] arr = file.readAllInts();


        for (int x : arr) System.out.print(x + " ");
        System.out.println();

        Arrays.sort(arr);

        for (int x : arr) System.out.print(x + " ");
        System.out.println();

        System.out.print(rank(-45, arr));
        file.close();
    }
}