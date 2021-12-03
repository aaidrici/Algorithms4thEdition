
package Chapter_2_2.CreativeProblems;
import Chapter_1_3.RandomQueue;
import edu.princeton.cs.algs4.StdRandom;

public class LinkedListShuffling {





    public static void shuffleLinkedList(LinkedList<Integer> a){

        int inputLLsize = a.N;
        // use of a top down approach to shuffle the LinkedList
        if (inputLLsize < 2) return;

        LinkedList<Integer> aux = new LinkedList<>();
        aux.last = a.last;

        Node ptr = a.first;
        Node temp;
        for (int i = 0; i<a.N/2-1 ; i++){
            ptr = ptr.next;
        }
        temp = ptr;
        ptr = ptr.next;
        temp.next = null;
        a.N = inputLLsize/2;
        a.last = temp;

        aux.first = ptr;
        aux.N = inputLLsize - inputLLsize/2;

        //a.display();
        //aux.display();




        shuffleLinkedList(a);
        shuffleLinkedList(aux);

        RandomMerged(a,aux);

        //a.display();
    }

    public static void RandomMerged(LinkedList<Integer> a, LinkedList<Integer> b){
        // takes two LL and randomly merged them

        LinkedList<Integer> c  = new LinkedList<Integer>();
        while (a.N != 0 && b.N != 0) {
            if (StdRandom.uniform(0,2) == 1) c.push(a.pop());
            else c.push(b.pop());
        }
        while (a.N != 0) c.push(a.pop());
        while(b.N != 0) c.push(b.pop());

        a.first = c.first;
        a.N = c.N;
        a.last = c.last;

    }



    public static void main(String[] args){

        LinkedList<Integer> a = new LinkedList();

        int N = 50;
        for (int i = 1; i<N ;i++)
            a.push(i);

        shuffleLinkedList(a);
        a.display();


    }



}
