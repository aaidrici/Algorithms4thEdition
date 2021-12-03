package Chapter_5_1.CreativeProblems;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class InPlaceKeyIndexCounting {


    public InPlaceKeyIndexCounting(In in){


        final int R = 256;

        Queue<String> q = new Queue();
        while(!in.isEmpty()) q.enqueue(in.readString());
        String[] a = new String[q.size()];
        for (int i = 0; i<a.length; i++)a[i] = q.dequeue();



        int N = a.length;
        int d = 0;

        int[] count = new int[R+1];
        // Compute frequency counts.
        for (int i = 0; i < N; i++)
            count[a[i].charAt(d) + 1]++;
        // Transform counts to indices.
        for (int r = 0; r < R; r++)
            count[r+1] += count[r];

        int i = 0;
        while (i < N){
            char c = a[i].charAt(d);
            if (i < count[c] || i >= count[c+1]){
                int j = count[c];
                while (a[j].charAt(d) == c) j++;
                exchange(a, i, j);
            }
            else{ i++; }
        }

        for (String x : a) System.out.println(x);
    }


    static private void exchange(String[] a, int i, int j){
        String t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args){
        String filename = "Chapter_5_1\\CreativeProblems\\Q5_1_11_input.txt";
        In in = new In(filename);
        InPlaceKeyIndexCounting kic = new InPlaceKeyIndexCounting(in);
    }






}
