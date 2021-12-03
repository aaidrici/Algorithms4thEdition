package Chapter_2_2.CreativeProblems;


import Chapter_1_3.Queue;

import javax.swing.*;


public class QueueMerging {


    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }

//    public static Queue<Comparable> queueMerge(Queue<Comparable> a, Queue<Comparable> b){
//        // assuming the first element of each queue is the smaller elements
//
//
//        // NOTE: this approach could be ameliorated is we transfer iterative all elements to a single array
//
//
//        Queue sortedMergedQueue = new Queue<>();
//
//        if (!a.isEmpty() && !b.isEmpty()){
//
//            Comparable a_temp = a.dequeue();
//            Comparable b_temp = b.dequeue();
//
//            while(!a.isEmpty() && !b.isEmpty()){
//                if (less(a_temp, b_temp)){
//                    sortedMergedQueue.enqueue(a_temp);
//                    a_temp = a.dequeue();
//                }
//                else{
//                    sortedMergedQueue.enqueue(b_temp);
//                    b_temp = b.dequeue();
//                }
//            }
//            // at this point we know one of the queue is empty
//            if (less(a_temp, b_temp)){
//                sortedMergedQueue.enqueue(a_temp);
//                sortedMergedQueue.enqueue(b_temp);
//            }
//            else{
//                sortedMergedQueue.enqueue(b_temp);
//                sortedMergedQueue.enqueue(a_temp);
//            }
//        }
//
//        // at this state we know at least one array is empty
//
//        while(!a.isEmpty())
//            sortedMergedQueue.enqueue(a.dequeue());
//        while(!b.isEmpty())
//            sortedMergedQueue.enqueue(b.dequeue());
//
//        return sortedMergedQueue;
//    }
//

    public static Queue<Comparable> queueMerge(Queue<Comparable> a, Queue<Comparable> b){

        Queue<Comparable> mergedQueue = new Queue<Comparable>();

        if (a.size() > 0 && b.size() > 0){

            Comparable a_first = a.dequeue();
            Comparable b_first = b.dequeue();

            while (true){
                if (less(a_first, b_first)){
                    mergedQueue.enqueue(a_first);
                    if (!a.isEmpty())
                        a_first = a.dequeue();
                    else{
                        mergedQueue.enqueue(b_first);
                        break;
                    }
                }
                else{
                    mergedQueue.enqueue(b_first);
                    if(!b.isEmpty())
                        b_first = b.dequeue();
                    else{
                        mergedQueue.enqueue(a_first);
                        break;
                    }
                }
            }
        }
        while(!b.isEmpty())
            mergedQueue.enqueue(b.dequeue());
        while(!a.isEmpty())
            mergedQueue.enqueue(a.dequeue());
        return mergedQueue;
    }

    static void displayQueue(Queue<Comparable> a){
        int N = a.size();
        Comparable temp;
        for (int i = 0; i<N; i++){
            temp = a.dequeue();
            System.out.print(temp + " ");
            a.enqueue(temp);
        }
        System.out.println();
    }


    public static void main(String[] args){

        int[] contentOfQueue1 = {1};
        Queue a = new Queue<Integer>();
        for (Integer x : contentOfQueue1) a.enqueue(x);

        int[] contentOfQueue2 = {5,25,26};
        Queue b = new Queue<Integer>();
        for (Integer x : contentOfQueue2) b.enqueue(x);

        a = QueueMerging.queueMerge(a,b);

        a.display();

    }
}
