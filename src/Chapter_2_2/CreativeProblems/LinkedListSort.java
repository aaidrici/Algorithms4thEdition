package Chapter_2_2.CreativeProblems;
//import Chapter_1_3.LinkedList;
import edu.princeton.cs.algs4.StdRandom;

// The textbook specifies the terms "linked-list" and not "stack" or "queues". This means we should not be limited
// to the API associated to either stacks or queues. Otherwise "linked-list" would not be mentioned.


import javax.swing.*;

public class LinkedListSort {

    public static void sort(LinkedList a){
        // takes an un-sorted linked-list and sorts it using merge sort

    }


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    private static void SinglePassSort(LinkedList<Comparable> a, Node init){

        // cannot be unsorted if there are less than two elements
        if (init == null){
            if (a == null ) return;
            if (a.first == null) return;
            if (a.first.next == null) return;
        }
        else
            if (init.next == null) return;



        Node<Comparable> ptr;
        Node<Comparable> temp;

        if (init == null)
            ptr = a.first;
        else
            ptr = init.next;

        LinkedList<Comparable> list1 = new LinkedList<>();
        LinkedList<Comparable> list2 = new LinkedList<>();
        LinkedList<Comparable> list3;

        // merge the first two sorted segments
        list1.first = ptr;
        while(ptr != null){
            if (ptr.next != null ){
                if(!less((Comparable)ptr.next.item, ptr.item)){
                    ptr = ptr.next;
                }
                else
                    break;
            }
            else{
                return; // there is no element following ptr. The function must end here.
            }
        }
        temp = ptr;
        ptr = ptr.next;
        temp.next = null;
        list1.last = temp;

        list2.first = ptr;
        while(ptr != null){
            if (ptr.next != null){
                if(!less((Comparable)ptr.next.item, ptr.item)){
                    ptr = ptr.next;
                }
                else
                    break;
            }
            else
                break; //last element must leave
        }
        temp = ptr;
        list2.last = ptr;
        ptr = ptr.next;
        temp.next = null;


        list3 = merge(list1, list2);


        // glue LHS of newly sorted list
        if (init == null)
            a.first = list3.first; // a.first had its value altered. It must be corrected.
        else
            init.next = list3.first; // init represents

        // glue RHS of newly sorted list
        list3.last.next = ptr; // merge list3 to the remaining linked-list

        SinglePassSort(a,list3.last);



    }

    private static LinkedList<Comparable> merge(LinkedList<Comparable> a, LinkedList<Comparable> b){

        // assuming head/first points at the smallest element in the LL.
        Node<Comparable> alpha = a.first;
        Node<Comparable> beta = b.first;
        Node<Comparable> head = new Node<Comparable>();
        Node<Comparable> head_origin = head;

        int count = 0; // element count in the linkedlist

        while (alpha != null && beta != null){
            if (less(alpha.item,beta.item)){
                head.next = alpha;
                head = alpha;
                alpha = alpha.next;
            }
            else{
                head.next = beta;
                head = beta;
                beta = beta.next;
            }
            count++;
        }

        while (alpha != null){
            head.next = alpha;
            head = alpha;
            alpha = alpha.next;
            count++;
        }
        while (beta != null){
            head.next = beta;
            head = beta;
            beta = beta.next;
            count++;
        }

        LinkedList resultingLinkedList = new LinkedList();
        resultingLinkedList.first = head_origin.next;
        resultingLinkedList.last = head;
        resultingLinkedList.N = count;
        return resultingLinkedList;
    }




    public static void main(String[] args){
        LinkedList<Comparable> a = new LinkedList<>();

        int N = 10;
        for (int i = 0; i < N; i++) a.push(StdRandom.uniform(0,40));

//        a.push(30);
//        a.push(28);
//        a.push(26);
//        a.push(24);
//        a.push(29);
//        a.push(27);
//        a.push(25);
//        a.push(23);
//        a.push(130);
//        a.push(128);
//        a.push(126);
//        a.push(124);
//        a.push(129);
//        a.push(127);
//        a.push(125);
//        a.push(123);

        a.display();

        SinglePassSort(a, null);
        a.display();
        SinglePassSort(a, null);
        a.display();
        SinglePassSort(a, null);
        a.display();

    }
}






class LinkedList<Item>{

    public Node<Item> first;
    public Node last;
    public int N = 0;

    public void display(){
        for (Node x = first; x != null; x = x.next)
            System.out.print(x.item + " -> ");
        System.out.print("null\n");
    }

    public void push(Item inputItem){
        Node newFirst = new Node();
        newFirst.item = inputItem;
        newFirst.next = first;
        first = newFirst;
        N++;
    }

    public Item pop(){
        if (N != 0){
            Item item = first.item;
            first = first.next;
            N--;
            if (first == null) last = null;
            return item;
        }
        else return null;
    }

}


class Node<Item>{
    Item item;
    Node next;
}

