package Chapter_2_2.CreativeProblems;

import edu.princeton.cs.algs4.StdRandom;
import Chapter_2_1.Exercises.Insertion;

public class TDmergeImproved {

        public static void sort(Comparable[] a)
        {

            // improvement one : cutoff
            if (a.length < 15){
                Insertion.sort(a);
            }


            Comparable[] aux = new Comparable[a.length]; // Allocate space just once.
            sort(a,aux, 0, a.length - 1);
        }

        private static boolean less(Comparable v, Comparable w)
        { return v.compareTo(w) < 0; }

        public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
        { // Merge a[lo..mid] with a[mid+1..hi].



//            // we already know aux contained the arrays with separated sorte
//            for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
//                aux[k] = a[k];
//
            int i = lo, j = mid+1;
            for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
                if (i > mid) a[k] = aux[j++];
                else if (j > hi ) a[k] = aux[i++];
                else if (less(aux[j], aux[i])) a[k] = aux[j++];
                else a[k] = aux[i++];
        }

        private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
        { // Sort a[lo..hi].

            // improvement one : cutoff
            if ((hi-lo+1) < 15){
                PartialInsertionSort(a, aux, lo, hi);
                return;
            }

            // improvement two: check whether the array is already sorted:
            Boolean arrayIsSorted = true;
            for (int i = lo; i< hi; i++){
                if (less(a[i+1], a[i])) {arrayIsSorted = false; break; }
            }
            if (arrayIsSorted == true) return;


            if (hi <= lo) return;
            int mid = lo + (hi - lo)/2;
            sort(a, aux, lo, mid); // Sort left half.
            sort(a, aux, mid+1, hi); // Sort right half.
            merge(a, aux, lo, mid, hi); // Merge results (code on page 271).
        }


    // this version of insertion sort only partially sorts the input array a[] and writes
    // its sorted segment into array b
    public static void PartialInsertionSort(Comparable[] a, Comparable[] b, int l_id, int r_id){
        for (int i = l_id+1; i <= r_id; i++)
        { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--){
                b[j] = a[j-1];
                b[j-1] = a[j];
                //exch(b, j, j-1);
            }
        }
    }
    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }


    public static void main(String[] args){

            int N = 100;
            Integer[] a = new Integer[N];
            for (int i = 0; i<N; i++){
                a[i] = StdRandom.uniform(0,N);
            }
            sort(a);

            for (Integer x: a) System.out.print(x + " ");


        }




    }


