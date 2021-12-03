package Chapter_1_3;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>
{
    protected Node first; // link to least recently added node
    protected Node last; // link to most recently added node
    protected int N; // number of items on the queue
    protected class Node

    { // nested class to define nodes
        Item item;
        Node next;
    }

    public Queue (){}

    public Queue (Queue<Item> queue){
        Queue<Item> temp = new Queue<Item>();
        Item sharedItem;
        int n = 0;
        while (!queue.isEmpty()){
            temp.enqueue(queue.dequeue());
            n++;
        }
        while (n > 0){
            n--;
            sharedItem = temp.dequeue();
            enqueue(sharedItem);
            queue.enqueue(sharedItem);
        }
    }


    public boolean isEmpty() { return first == null; } // Or: N == 0.
    public int size() { return N; }
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
    public Item dequeue()
    { // Remove item from the beginning of the list.
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }

    public void display(){
        Node x = first;
        while (x != null) {System.out.print(x.item + " "); x = x.next;}
        System.out.println();
    }

    public Iterator<Item> iterator()
    { return new ListIterator(); }
    protected class ListIterator implements Iterator<Item>
    {
        protected Node current = first;
        public boolean hasNext()
        { return current != null; }
        public void remove() { }
        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // appends at queue 'a' to the current queue
    public void catenate(Queue<Item> toAppend){
        Queue<Item> temp = new Queue<Item>();
        Item itemToAdd;
        while (!toAppend.isEmpty()) temp.enqueue(toAppend.dequeue());
        while (!temp.isEmpty()){
            itemToAdd = temp.dequeue();
            toAppend.enqueue(itemToAdd);
            enqueue(itemToAdd);
        }
    }
// See page 150 for test client main().
}