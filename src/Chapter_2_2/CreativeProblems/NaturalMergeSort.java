package Chapter_2_2.CreativeProblems;

import Chapter_2_2.CreativeProblems.QueueMerging;
import Chapter_1_3.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class NaturalMergeSort {

    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    private static void displayQueueOfQueue (Queue<Queue<Comparable>> a){

        Queue<Comparable> currentQueue;
        Comparable currentItem;
        int length = a.size();
        for ( int i = 0; i<length; i++){
            currentQueue = a.dequeue();
            int subQueueLength = currentQueue.size();
            for (int j = 0 ; j<subQueueLength; j++){
                currentItem = currentQueue.dequeue();
                System.out.print(currentItem + " ");
                currentQueue.enqueue(currentItem);
            }
            System.out.print("| ");
            a.enqueue(currentQueue);
        }
        System.out.println();
    }

    public static void sort(Comparable[] unsortedArray){
        int N = unsortedArray.length;

        Queue<Queue<Comparable>> a = new Queue<Queue<Comparable>>();

        int count = 0;
        while (true ){

            Queue<Comparable> tempQueue = new Queue<Comparable>();

            while(true){
                tempQueue.enqueue(unsortedArray[count++]);
                if (count == N) break;
                if (less(unsortedArray[count], unsortedArray[count-1])) break;
            }
            a.enqueue(tempQueue);
            if (count == N)
                break;
        }

        Queue<Comparable> tempQueue = new Queue<Comparable>();
        tempQueue = iterativeSort(a);
        int i = 0;
        while(!tempQueue.isEmpty()) unsortedArray[i++] = tempQueue.dequeue();

    }

    public static Queue<Comparable> iterativeSort(Queue<Queue<Comparable>> a){
        // This methods takes a queue of queue, merges adjacent queue at each passes, then output the final queue

        int N = a.size();
        Queue<Comparable> a1;
        Queue<Comparable> a2;
        Queue<Comparable> tempQueue;

        while (N != 1){

            displayQueueOfQueue(a);
//
            for (int i = 0; i<N/2 ; i++){
                a1 = a.dequeue();
                a2 = a.dequeue();
                tempQueue = QueueMerging.queueMerge(a1,a2);
                a.enqueue(tempQueue);
            }
            if (N%2 != 0){
                a.enqueue(a.dequeue());
            }
            N = a.size();
        }
        displayQueueOfQueue(a);

        return  a.dequeue();
    }


    public static void main(String[] args){

        int N = 10;
        Integer[] a = new Integer[N];
        for ( int i = 0; i<N; i++)
            a[i] = StdRandom.uniform(0,N*10);

        sort(a);

        for (Integer x : a)
            System.out.print(x + " ");

    }



}
