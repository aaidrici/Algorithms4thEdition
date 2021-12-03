package Chapter_2_4.Exercises;

public class LinearHeapCertificate {


    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }




    public static boolean ValidHeap(Comparable[] a){
        return ValidSubHeap(a, 1);
    }

    private static boolean ValidSubHeap(Comparable[] a, int k){

        // i.e. if k has a child on its left side
        if (k*2 > a.length-1) return true;
        if (less(a[k], a[2*k])) return false;
        if (!ValidSubHeap(a, k*2)) return false;

        // i.e. if k has a child on its right side
        if (2*k+1 > a.length-1) return true;
        if (less(a[k], a[2*k+1])) return false;
        if (!ValidSubHeap(a, k*2+1)) return false;

        return true;

    }


    public static void main(String[] args){

        Integer[] a = {0,8,8,8,8,8,5,5};
        if (ValidHeap(a))
            System.out.println("this heap is valid.");
        else
            System.out.println("This heap is not valid.");



    }



}

