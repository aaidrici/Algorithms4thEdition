package Chapter_5_1.CreativeProblems;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class MsdWithQueue {

    String[] list;
    Queue<String> ql;

    private static final int R = 256;



    public MsdWithQueue(In in){

        ql = new Queue();
        while(!in.isEmpty()) ql.enqueue(in.readString());
        ql = sort(ql, 0);
        for (String x : ql) System.out.println(x);
    }

    private Queue<String> sort(Queue<String> a, int d){
        Queue<String>[] bins = new Queue[R];


        for (String x : a){
            int c = charAt(x, d);
            if (bins[c] == null) bins[c] = new Queue<String>();
            bins[c].enqueue(x);
        }

        Queue<String> stitched = new Queue<String>();
        for (Queue<String> b : bins){
            if (b == null) continue;
            if (b.size() > 1){
                b = sort(b, d + 1);
            }
            while (!b.isEmpty()) stitched.enqueue(b.dequeue());
        }

        return stitched;
    }


    private int charAt(String x, int d){
        if (d >= x.length()) return 0;
        return x.charAt(d) + 1;
    }


    public static void main(String[] args){
        String filename = "Chapter_5_1\\CreativeProblems\\Q5_1_11_input.txt";
        In in = new In(filename);
        MsdWithQueue msd = new MsdWithQueue(in);
    }

}
