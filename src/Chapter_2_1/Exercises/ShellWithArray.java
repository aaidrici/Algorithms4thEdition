package Chapter_2_1.Exercises;


import Chapter_2_1.CreativeProblems.SortCompare;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ShellWithArray
    {

        public enum arrayMethod{ Exercise2_1_11_array, Exercise2_1_29_array, Exercise2_1_30_array}

        private static arrayMethod selectedArrayMethod = arrayMethod.Exercise2_1_11_array;

        private static int N;
        private static int[] h_values;
        private static int t;

        public static void sort(Comparable[] a)
        { // Sort a[] into increasing order.
            N = a.length;

            int[] h_values;

            if (selectedArrayMethod == arrayMethod.Exercise2_1_11_array)
                h_values = useExercise2_1_11_array();
            else if (selectedArrayMethod == arrayMethod.Exercise2_1_29_array)
                h_values = useExercise2_1_29_array();
            else if (selectedArrayMethod == arrayMethod.Exercise2_1_30_array)
                h_values = useExercise2_1_30_array();
            else
                return;


             for (int h : h_values){
             // h-sort the array.
                for (int i = h; i < N; i++)
                { // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                    for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                        exch(a, j, j-h);
                }
            }
        }

        public static void sort(Comparable[] a , arrayMethod b){
            selectedArrayMethod = b;
            sort(a);
        }
        public static void sort(Comparable[] a, int input_t){
            t = input_t;
            selectedArrayMethod = arrayMethod.Exercise2_1_30_array;
            sort(a);
        }


        public static int power(int base, int pow){
            int result = 1;
            while (pow > 0){
                result *= base;
                pow--;
            }
            return result;
        }

        private static int[] useExercise2_1_11_array(){
            int count = 1;
            int H = 1;
            while (H < N/3){ H = 3*H + 1; count ++;  } // 1, 4, 13, 40, 121, 364, 1093, ...
            int[] h_values = new int[count];
            for (int k = 0; k < count; k++){ h_values[count-k-1] = (power(3,k+1)-1)/2; }
            return h_values;
        }

        private static int[] useExercise2_1_29_array(){
            int[] a = {1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905, 8929, 16001, 36289, 64769, 146305, 260609};
            return a;
        }

        private static int[] useExercise2_1_30_array(){
            if (t == 1){
                int[] h_values = new int[1];
                h_values[0] = 1;
                return h_values;
            }

            int i = 1;
            while (power(t,i) < N) i++;
            int[] h_values = new int[i];

            h_values[i-1] = 1;
            for (int j = 1; j < i ; j++) h_values[i-j-1] = power(t,j);

//            // delete later:
//            for (int h : h_values) System.out.print(h + " ");
//            System.out.println();

            return h_values;
        }

                private static void exch(Comparable[] a, int i, int j)
        { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }

        private static boolean less(Comparable v, Comparable w)
        { return v.compareTo(w) < 0; }

        public static void show(Comparable[] a)
        { // Print the array, on a single line.
            for (int i = 0; i < a.length; i++)
                StdOut.println(a[i] + " ");
            StdOut.println();
        }

        public static void main(String[] args){


//            // exercise 2.1.11
//            int N = 1000;
//            Double[] a = new Double[N];
//            for (int i = 0; i<N ; i++){a[i] =StdRandom.uniform(0,10.0); }
//
//            ShellWithArray.sort(a);
//            ShellWithArray.show(a);



            // exercise 2.1.29
            double timeRatio;
            timeRatio = SortCompare.SpeedRatio("ShellSort_Q2_1_29_Array", "ShellSortDefaultArray", 1000, 100);
            StdOut.printf("%.4f", timeRatio);

        }
    }

