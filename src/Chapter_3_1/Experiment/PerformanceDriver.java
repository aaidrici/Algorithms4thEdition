//package Chapter_3_1.Experiment;
//
//import Chapter_3_1.ArrayST;
//import Chapter_3_1.BinarySearchST;
//import edu.princeton.cs.algs4.StdRandom;
//
//import Chapter_3_1.CreativeProblems.ArraySTWithsoftwareCaching;
//
//import java.util.Arrays;
//
//public class PerformanceDriver {
//
//
//
//
//
//
//    private static Integer HalfedEveryStepDistribution(int n){
//        double x = StdRandom.uniform(0,1.0);
//        double theshold = 0.5;
//
//        for (int i = 0; i<n; i++) {
//            if (x < theshold) return i;
//            else theshold = 1 - (1 - theshold)/2;
//        }
//        return n-1;
//    }
//
//    private static Integer dist(int n){
//        return HalfedEveryStepDistribution(n);
//    }
//
//
//    public static void main(String[] args){
//
//        boolean orderedInputKeySequence = false;
//        int sampleSize = 10;
//
//        // symbolic tables declaration
//        ArraySTWithsoftwareCaching<Integer, String> st_usingCaching = new ArraySTWithsoftwareCaching<>(100);
//        BinarySearchST<Integer, String> st_ordered = new BinarySearchST<Integer, String>(100);
//
//
//
//
//        int[] N_values = {10};
//
//        double[] timeCaching = new double[N_values.length];
//        double[] timeOrdered = new double[N_values.length];
//        int ind = 0;
//
//        double t0;
//
//        for (int N : N_values){
//
//            for (int k = 0; k < sampleSize; k++) {
//
//
//
//                // generate key sequence
//                Integer[] keys = new Integer[N];
//                for (int i = 0; i<N; i++) keys[i] = i;
//                if (!orderedInputKeySequence)
//                    StdRandom.shuffle(keys);
//
//
//                t0 = System.currentTimeMillis();
//
//                // fill table & loop through hits
//                for (int j = 0; j < 10; j++)
//                    st_usingCaching.put(keys[j], "DummyValue");
//                for (int i = 0; i < 10 * N; i++)
//                    st_usingCaching.get(dist(N));
//
//                timeCaching[ind] += System.currentTimeMillis() - t0;
//
//
//                t0 = System.currentTimeMillis();
//
//                // fill table & loop through hits
//                for (int j = 0; j < N; j++)
//                    st_ordered.put(keys[j], "DummyValue");
//                for (int i = 0; i < 10 * N; i++)
// m                    st_ordered.get(dist(N));
//
//                timeOrdered[ind] += System.currentTimeMillis() - t0;
//
//                System.out.println("I'm there");
//
//            }
//
//            System.out.printf("N = %d\n Time for ST using key cache = %.4f ms, \n Time for ordered ST = %.4f ms \n\n", N, timeCaching[ind], timeOrdered[ind]);
//
//            ind++;
//        }
//
//
//
//
//
//    }
//
//}
