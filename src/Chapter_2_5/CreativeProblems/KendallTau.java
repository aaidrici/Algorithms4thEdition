package Chapter_2_5.CreativeProblems;

import java.awt.event.KeyListener;
import java.util.Arrays;

public class KendallTau {


    private static int inversionNumber;
    private static int[] finalOrder;

    public static void reset(){
        inversionNumber = 0;
        finalOrder = null;
    }
    public static int[] returnFinalOrder(){
        return finalOrder;
    }

    // this can be done using an alternate version of merge sort which counts the number of inversion
    private static Comparable[] aux; // auxiliary array for merges
    private static int[] aux_id; // auxiliary array for merges


    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi)
    { // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++){ // Copy a[lo..hi] to aux[lo..hi].
            aux[k] = a[k];
            aux_id[k] = finalOrder[k];
        }
        for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
            if (i > mid){
                inversionNumber += (j - k);
                a[k] = aux[j];
                finalOrder[k] = aux_id[j++];
            }
            else if (j > hi ){
                a[k] = aux[i];
                finalOrder[k] = aux_id[i++];

            }
            else if (less(aux[j], aux[i])) {
                inversionNumber += (j - k);
                a[k] = aux[j];
                finalOrder[k] = aux_id[j++];

            }
            else{
                a[k] = aux[i];
                finalOrder[k] = aux_id[i++];
            }
    }
    public static void sort(Comparable[] a) {
        inversionNumber = 0;
        finalOrder = new int[a.length];
        aux_id = new int[a.length];
        for (int i = 0; i<a.length; i++){
            finalOrder[i] = i;
            aux_id[i] = 0;
        }
        aux = new Comparable[a.length]; // Allocate space just once.
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi)
    { // Sort a[lo..hi].
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid); // Sort left half.
        sort(a, mid+1, hi); // Sort right half.
        merge(a, lo, mid, hi); // Merge results (code on page 271).
    }


    public static int KendallTau(Comparable[] a, Comparable[] b){


        // sort 'a' and apply same manipulation to 'b'
        sort(a);
        int[] re_order = returnFinalOrder();
        reset();
        for (int i = 0; i<a.length; i++) a[i] = b[re_order[i]];

        // determine number of inversion
        sort(a);
        return inversionNumber;
    }


    public static void main(String[] args){
        Integer[] a = {2,1,3,5,4};
        Integer[] b = {5,3,1,4,2};

        System.out.print(KendallTau(a,b));




    }






}
