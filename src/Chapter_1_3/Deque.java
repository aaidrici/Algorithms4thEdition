package Chapter_1_3;

public class Deque<Item> {
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


    public void pushRight(Item item) // ADD FROM RIGHT, pushRight()
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
    public Item popLeft() // REMOVE FROM LEFT, popLeft()
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


    // Methods part of the stack method
    public Item popRight(){
        if (last != null){
            if (N == 1){
                Item itemToReturn = last.item;
                last = null;
                first = null;
                N--;
                return itemToReturn;
            }
            else{
                Item returnedItem = last.item;
                Node secondLast = first;
                while (secondLast.next.next != null) secondLast = secondLast.next;
                secondLast.next = null;
                last = secondLast;
                N--;
                return returnedItem;
            }
        }
        return null;
    }

    public void pushLeft(Item inputItem) { // ADD FROM LEFT
        Node oldFirst = first;
        first = new Node();
        first.next = oldFirst;
        first.item = inputItem;
        N++;
    }

}
