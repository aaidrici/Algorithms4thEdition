package Chapter_2_2.CreativeProblems;

import Chapter_2_2.CreativeProblems.QueueMerging;
import Chapter_2_1.Exercises.Certification;
import Chapter_1_3.Queue;
import edu.princeton.cs.algs4.StdRandom;
import javax.management.QueryEval;
import java.util.Arrays;
import java.util.Random;

public class BottomUpQueueMergeSort {

    // private static Comparable[] a;
    private static Queue<Comparable>[] a;
    private static int arraySize;

    //
    public static void sort(Comparable[] array_address){

        arraySize = array_address.length;
        Queue<Queue<Comparable>> a = new Queue<Queue<Comparable>>();

        //a = new Queue[array_address.length];

        for(int i = 0; i<array_address.length; i++){
            Queue<Comparable> element = new Queue<Comparable>();
            element.enqueue(array_address[i]);
            a.enqueue(element);
        }
        iterativeSort(a, array_address);
    }



    private static void iterativeSort(Queue<Queue<Comparable>> a, Comparable[] array_address){
        // This methods takes a a queue of queue, merges the two last items,

        int N = a.size();
        Queue<Comparable> a1;
        Queue<Comparable> a2;
        Queue<Comparable> a3;

        while (N != 1){

            displayQueueOfQueue(a);
//
            for (int i = 0; i<N/2 ; i++){
                a1 = a.dequeue();
                a2 = a.dequeue();
                a3 = QueueMerging.queueMerge(a1,a2);
                a.enqueue(a3);
            }
            if (N%2 != 0){
                a.enqueue(a.dequeue());
            }
            N = a.size();
        }
        displayQueueOfQueue(a);

        a3 = a.dequeue();

        int i = 0;
        while(a3.isEmpty()) array_address[i++] = a3.dequeue();
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


    public static void main(String[] args){

        int N = 40;
        Integer[] a = new Integer[N];
        Integer[] a_orginal = new Integer[N];
        for ( int i = 0; i<N; i++){
            a[i] = StdRandom.uniform(0,100);
            a_orginal[i] = a[i];
        }

    }
}
