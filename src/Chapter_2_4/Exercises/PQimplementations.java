package Chapter_2_4.Exercises;
import edu.princeton.cs.algs4.StdRandom;

import static Chapter_2_4.Exercises.PQimplementations.*;
import Chapter_2_4.Exercises.LinkedList_2;


public class PQimplementations {

    public static void sort(Comparable[] a)
    { // Sort a[] into increasing order.
        int N = a.length;
        for (int i = 1; i < N; i++)
        { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
    }
    public static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }
    public static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }




    public static void main(String[] args){

        int arraySize = 25;
        int N = 20;

        PQ_orderedLL pq = new PQ_orderedLL();

        for (int i = 0; i<arraySize ; i++){
            pq.Insert(StdRandom.uniform(0,N));
        }
        pq.Display();

        for (int i = 0; i<arraySize + 3 ; i++){
            System.out.print(pq.RemoveMax() + " ");
        }

    }

}

class PQ_unordered{
    private Comparable[] a;
    private int size;

    public PQ_unordered(){
        a = new Comparable[8];
        size = 0;
    }
    public void Insert(Comparable item){
        a[size++] = item;
        if(size == a.length){ // dynamic array is full and must be extended
            Comparable[] temp = new Comparable[a.length*2];
            for (int i = 0; i<size ; i++)
                temp[i] = a[i];
            a = temp;
        }
    }
    public Comparable RemoveMax(){
        if (size == 0) return null;
        int max_id = 0;
        Comparable maxItem;
        for (int i = 0; i<size; i++)
            if (less(a[max_id], a[i]))
                max_id = i;

        maxItem = a[max_id];
        exch(a,max_id,size-1);
        size--;

        if (size*4 < a.length){ // dynamic array must shrink
            Comparable[] temp = new Comparable[a.length/4];
            for (int i = 0; i<size ; i++)
                temp[i] = a[i];
            a = temp;
        }
        return maxItem;
    }
    public void Display(){
        for (int j = 0; j<size; j++)
            System.out.print(a[j] + " ");
        System.out.println();
    }


}

class PQ_ordered{

    private Comparable[] a;
    private int size;
;
    public PQ_ordered(){
        a = new Comparable[8];
        size = 0;
    }


    public static void InsertionSort(Comparable[] a, int size)
    {
        for (int i = 1; i < size ; i++)
        { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
    }

    public void Insert(Comparable item){
        a[size++] = item;

        // insertion sort is used as it works better on partially sorted arrays.
        // another approach would be to shift all items by one element until there a place where the new element should be inserted.
        InsertionSort(a, size);

        if(size == a.length){ // dynamic array is full and must be extended
            Comparable[] temp = new Comparable[a.length*2];
            for (int i = 0; i < size ; i++)
                temp[i] = a[i];
            a = temp;
        }
    }


    public Comparable RemoveMax(){
        if (size == 0) return null;
        Comparable maxItem = a[--size];
        if (size*4 < a.length){ // dynamic array must shrink
            Comparable[] temp = new Comparable[a.length/4];
            for (int i = 0; i<size ; i++)
                temp[i] = a[i];
            a = temp;
        }
        return maxItem;
    }


    public void Display(){
        for (int j = 0; j<size; j++)
            System.out.print(a[j] + " ");
        System.out.println();
    }

}

class PQ_unorderedLL{

    LinkedList_2<Comparable> a;
    public PQ_unorderedLL(){
        a = new LinkedList_2<Comparable>();
    }

    public void Insert(Comparable item){ a.push(item); }

    public Comparable RemoveMax(){ return a.RemoveMax();}

    public void Display(){
        a.Display();
    }

}

class PQ_orderedLL{
    LinkedList_2<Comparable> a;
    public PQ_orderedLL(){
        a = new LinkedList_2<Comparable>();
    }

    public void Insert(Comparable item){
        a.SortedPush(item);
    }
    public Comparable RemoveMax(){
        return a.pop();
    }

    public void Display(){
        a.Display();
    }

}