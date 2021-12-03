package Chapter_5_1;

import Chapter_1_3.Queue;
import edu.princeton.cs.algs4.In;

public class Q5_1_7 {


    Queue<String>[] q;


    public Q5_1_7(In in){
        sort(in);
    }

    public void sort(In in){
        Queue<Integer> keys = new Queue<Integer>();
        Queue<String> strings = new Queue<String>();


        int maxKey = 0;

        while (!in.isEmpty()){
            strings.enqueue(in.readString());
            int i = in.readInt();
            if (i > maxKey) maxKey = i;
            keys.enqueue(i);
        }

        q = new Queue[maxKey + 1];
        for (int i = 0; i < maxKey +1; i++) q[i] = new Queue<String>();

        while (!keys.isEmpty()){
            q[keys.dequeue()].enqueue(strings.dequeue());
        }
    }


    public void print(){

        int i = 0;
        for (Queue<String> x : q){
            while (!x.isEmpty()) System.out.println(Integer.toString(i) + " " + x.dequeue());
            i++;
        }
    }

    public static void main(String[] args){


        String filename = "Chapter_5_1\\Q5_1_1_input.txt";
        In in = new In(filename);
        Q5_1_7 sort = new Q5_1_7(in);
        sort.print();


    }

}
