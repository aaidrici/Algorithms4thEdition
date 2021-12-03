package Chapter_2_2.CreativeProblems;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import Chapter_2_1.Exercises.Certification;

import java.util.Arrays;

public class MultiwayMergeSort {



    private static int k_const = 2; // order of the merges (has to be
    private static Comparable[] aux;

    public static int[] arraySplit(int k, int id1, int id2){ // id1 and id2 are inclusive in the array

        int[] bounds = new int[k+1];
        double incr = (id2-id1+1)/k;
        for (int i = 0; i<k; i++) bounds[i] = (int)Math.floor(id1 + i*incr);
        bounds[k] = id2+1;
        return bounds;
    }

    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    public static void merge(Comparable[] a, int[] bounds)
    { // Merge a[lo..mid] with a[mid+1..hi].

        int k = bounds.length-1;

        aux = new Comparable[a.length]; // delete this code later;

        // populate ptr array
        int[] ptr = new int[k];
        for (int i = 0; i<k; i++) ptr[i] = bounds[i];

        // Copy a[lo..hi] to aux[lo..hi].
        for (int i = bounds[0]; i <= bounds[k]-1; i++)
            aux[i] = a[i];

        int count = bounds[0];

        Comparable Smallest = -1;
        int target_id = -1;

        while(count <= bounds[k]-1){ // sweep through all id's

            // pick first available value as the Smallest value;
            int id = 0;
            while (id <= k){
                if (ptr[id] < bounds[id+1]){ // i.e. check if this sub-array isn't exhausted yet
                    target_id = id;
                    Smallest = aux[ptr[id]];
                    break;
                }
                id++;
            }
            if (id >= k) break; // all arrays are exhausted. Leave main loop.


            for (id = 0; id < k; id++){
                if (ptr[id] < bounds[id+1]) // i.e. check if this sub-array isn't exhausted yet
                    if (less(aux[ptr[id]], Smallest)){ // check if the next value it hold is the smallest so far
                        Smallest = aux[ptr[id]];
                        target_id = id;
                    }
            }
            ptr[target_id]++;
            a[count++] = Smallest;
        }
    }


    public static void sort(Comparable[] a, int k){
        k_const = k;
        int[] bounds = arraySplit(k, 0, a.length-1);
        sort(a, bounds);
    }

    private static void sort(Comparable[] a, int[] bounds){

        for (int i = 0; i < bounds.length-1; i++){ // go through every interval of the bounds' array

            int segment_size = bounds[i+1] - bounds[i];

            if (segment_size > k_const){ // if segment has more than k element, it must be further refined
                int[] sub_bounds = arraySplit(k_const,bounds[i], bounds[i+1]-1);
                sort(a,sub_bounds);
            }
            else if (segment_size <= k_const && segment_size > 1){ // if segment size within [2, k], it can merge sorted directly
                int[] sub_bounds = arraySplit(segment_size,bounds[i], bounds[i+1]-1);
                merge(a,sub_bounds);
            }
        }

        merge(a, bounds);
    }


    public static void DisplayRunTimeRatio (int k)
    {
        int N = 250;
        int trial_no = 100;
        int count = 0;

        double t1;
        double deltaTime = 0;
        double prevDeltaTime;
        double TotalTime = 0;
        double meanTimeFactorIncrease = 0;

        Comparable[] a = new Comparable[N];

        for (int j = 0; j < trial_no ; j++){
            for (int i = 0; i<N; i++) a[i] = StdRandom.uniform(0,N);
            t1 = System.currentTimeMillis();
            sort(a,k);
            deltaTime += System.currentTimeMillis() - t1;
        }

        prevDeltaTime = deltaTime;

        StdOut.printf("\nMultiway Merge sort with Factor K = %d\n", k);
        System.out.println("N = array size, T2/T1 = time increase factor");
        while(N < 10000){

            N = N *= 2;
            a = new Comparable[N];
            aux = new Comparable[N];

            for (int j = 0; j < trial_no ; j++){
                for (int i = 0; i<N; i++){ a[i] = StdRandom.uniform(0,N); aux[i] = a[i]; }

                t1 = System.currentTimeMillis();
                sort(a,k);
                deltaTime += System.currentTimeMillis() - t1;

                if(!Certification.check(a,aux)) System.out.println("sorting error detected");


            }

            StdOut.printf("Array size = %d, T2/T1 = %.3f \n",N, deltaTime / prevDeltaTime );
            meanTimeFactorIncrease += deltaTime / prevDeltaTime;
            TotalTime += deltaTime;
            prevDeltaTime = deltaTime;
            count++;
        }
        StdOut.printf("Total running time = %.2f sec \n", TotalTime / 1000 );
        StdOut.printf("Mean T2/T1 = %.2f \n", meanTimeFactorIncrease / count );
    }

    public static void DisplayMeanRunningTime (int N)
    {

        int trial_no = 100;
        int max_k_value = 50;
        int k_incr = 2;

        double t1;
        double deltaTime = 0;

        Comparable[] a;
        Comparable[] aux;

        StdOut.printf("\nMultiway Merge sort with array size N = %d\n", N);

           for (int k = 2; k <=max_k_value ; k+= k_incr){

               deltaTime = 0;
                a = new Comparable[N];
                aux = new Comparable[N];

                for (int j = 0; j < trial_no ; j++){
                    for (int i = 0; i<N; i++){ a[i] = StdRandom.uniform(0,N); aux[i] = a[i]; }

                    t1 = System.currentTimeMillis();
                    sort(a,k);
                    deltaTime += System.currentTimeMillis() - t1;

                    if(!Certification.check(a,aux)) System.out.println("sorting error detected");
                }
            StdOut.printf("k = %d, mean running time = %.3f sec\n",k, deltaTime/trial_no );
        }
    }



    public static void main(String[] args){


        // PART I - Let's try to analyze the order of Growth for each value of k
        //          and see if there is a difference
        //   Result discussion:
        //          The time ratio seems very close to 2.0 as the array size increase. This result confirms
        //          the merge sort algorithm remains linearithmic, regardless of the value of k.

//        for (int k = 2; k < 40; k+=2){
//            DisplayRunTimeRatio(k);
//        }

        // PART II - Let's try to find the optimum value of k for various array size ranging from 10^4 to 10^6
        //
        //    Result discussion:
        //          Quite hard to pin-point a k value for a given array size. Although there seem to be a general
        //          tendency for a shorter running time for increasing values of k.
        //          There seem to a minimum of k ~= 12 for array of size 1000
        //          There seem to a minimum of k ~= 18 for array of size 5000
        //          There seem to a minimum of k ~= 22 for array of size 5000

        int[] N = {10000};
        for (int n : N){
            DisplayMeanRunningTime(n);
        }
    }

}

