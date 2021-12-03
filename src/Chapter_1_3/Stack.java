package Chapter_1_3;

import java.util.Iterator;
import java.util.ConcurrentModificationException;

public class Stack<Item> implements Iterable<Item>
{
    private Node first; // top of stack (most recently added node)
    private int N; // number of items
    private class Node
    { // nested class to define nodes
        Item item;
        Node next;
    }

    public Stack(){}
    public Stack(Stack<Item> a){
        Stack<Item> temp = new Stack<Item>();
        while (!a.isEmpty()) {temp.push(a.pop());}
        while (!temp.isEmpty()){
            Item copiedItem = temp.pop();
            a.push(copiedItem);
            push(copiedItem);
        }
    }
    public void display(){
        for (Item x : this) System.out.print(x + " ");
    }

    public boolean isEmpty() { return first == null; } // Or: N == 0.
    public int size() { return N; }
    public void push(Item item)
    { // Add item to top of stack.
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    public Item pop()
    { // Remove item from top of stack.
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    public Iterator<Item> iterator()
    { return new ListIterator(); }
    private class ListIterator implements Iterator<Item>
    {
        int elementCount;
        public ListIterator(){ // mechanism used to prevent the client code from altering the numbers of element in the queue
            elementCount = N;
        }
        private Node current = first;
        public boolean hasNext()
        {
            if (elementCount != N) {throw new ConcurrentModificationException();}
            return current != null;
        }
        public void remove() {
            if (elementCount != N) {throw new ConcurrentModificationException();}
        }
        public Item next()
        {
            if (elementCount != N) {throw new ConcurrentModificationException();}
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public Item peek(){
        if (first == null) return null;
        return first.item;
    }

    // note: Orientation of catenation is unspecified, hence it is assumed (first Node is assumed to be the end of the data structure)
    public void catenate(Stack<Item> toAppend){
        Stack<Item> temp = new Stack<Item>();
        Item toTransfer;
        while (!toAppend.isEmpty()) temp.push(toAppend.pop());
        while (!temp.isEmpty()){
            toTransfer = temp.pop();
            push(toTransfer);
            toAppend.push(toTransfer);
        }
    }

// See page 147 for test client main().
}
