package Chapter_1_3;

import java.util.Iterator;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class RandomBag<Item> implements Iterable<Item>
{
    private Node first; // first node in list
    int N = 0;

    private class Node {
        Item item;
        Node next;
    }


    public void add(Item item) { // same as push() in Stack
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }


    private class ListIterator implements Iterator<Item> {
         // retrieve all elements and put them into an array
         Item[] shuffledContent = (Item[]) new Object[N];
         int index;
         public ListIterator(){
             index = 0;
             int[] randomOrder = new int[N];
             for (int i = 0; i<N; i++) randomOrder[i] = i;
             StdRandom.shuffle(randomOrder);
             int i = 0;
             for (Node x = first; x != null ; x = x.next){
                 shuffledContent[randomOrder[i++]] = x.item;
             }
         }

        private Node current = first;
        public boolean hasNext() { return index != N-1; }
        public void remove() { }
        public Item next()
        {
            return shuffledContent[index++];
        }
    }
}