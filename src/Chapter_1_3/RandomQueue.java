package Chapter_1_3;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;
import java.util.Iterator;

public class RandomQueue<Item> implements Iterable<Item>{

    int N = 0;
    Node first;
    Node last;

    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty() {return N==0;}

    public void enqueue(Item item)
    { // Add item to the end of the list.
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    // Q1.3.35 part
    public Item dequeue()
    {

        Item item;
        int indexOfItemToRemove = StdRandom.uniform(N);
        if (indexOfItemToRemove == 0) {
            item = first.item;
            first = first.next;
        }
        else {
            Node prevIndex = first;
            for (int i = 0; i < indexOfItemToRemove-1; i++) { prevIndex = prevIndex.next;};
            item = prevIndex.next.item;
            prevIndex.next = prevIndex.next.next;
        }

        if (isEmpty()) last = null;
        N--;
        return item;
    }

    // Q1.3.36 part
    public Iterator<Item> iterator(){
        return new RandomQueueIterator();
    }
    private class RandomQueueIterator implements Iterator<Item>{
        int index = 0;
        Item[] shuffledItemList;

        public RandomQueueIterator(){
            index = 0;
            shuffledItemList = (Item[]) new Object[N];
            int i = 0;
            for (Node x= first; x != null ; x = x.next) {shuffledItemList[i++] = x.item;}
            StdRandom.shuffle(shuffledItemList);
        }

        public boolean hasNext(){
            if (index == N) return false;
            else return true;
        }
        public Item next(){
            return shuffledItemList[index++];
        }
    }



}