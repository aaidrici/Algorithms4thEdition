package Chapter_1_3;

public class Steque<Item> {
    private Node first; // link to least recently added node
    private Node last; // link to most recently added node
    private int N; // number of items on the queue
    private class Node
    { // nested class to define nodes
        Item item;
        Node next;
    }
    public boolean isEmpty() { return first == null; } // Or: N == 0.
    public int size() { return N; }


    public void enqueue(Item item) // ADD FROM RIGHT, pushRight()
    {
        if (last != null) {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else oldlast.next = last;
            N++;
        }
    }
    public Item pop() // REMOVE FROM LEFT, popLeft()
    { // Remove item from the beginning of the list.
        if (first != null){
            Item item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }
        else return null;
    }


    public void push(Item inputItem) { // ADD FROM LEFT
        Node oldFirst = first;
        first = new Node();
        first.next = oldFirst;
        first.item = inputItem;
        N++;
    }

    public void catenate(Steque<Item> toAppend){
        Stack<Item> temp = new Stack<Item>();
        Item toTransfer;
        while (!toAppend.isEmpty()) temp.push(toAppend.pop());
        while (!temp.isEmpty()){
            toTransfer = temp.pop();
            push(toTransfer);
            toAppend.push(toTransfer);
        }
    }


}
