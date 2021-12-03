package Chapter_1_3;

import java.util.Iterator;

public class LinkedList<Item> implements Iterable<Item>{


    public Iterator<Item> iterator(){
        return new IteratorList();
    }

    private class IteratorList implements Iterator<Item> {
        Node<Item> x = new Node<Item>(First);
        public boolean hasNext(){return x.next != null; };
        public Item next(){ x = x.next; return x.content; };
        public void Remove(){};
    }

    public int size(){return N;}

    public void insertBeforeN(Item item, int n){
        // insert item at node n and increment position of subsequent nodes

        if (n == 0) {push(item); return;}

        Node x = First;
        for (int i = 0; i < n-1; i++)
            x = x.next;

        Node insertedNode = new Node(x.next, item);
        x.next = insertedNode;
        N++;
    }

    public void replace(Item item, int n){
        Node x = First;
        for (int i = 0; i<n; i++)x = x.next;
        x.content = item;
    }

    public void suppress(int n){
        if (n == 0)
            pop();
        else {
            Node x = First;
            for (int i = 0; i < n-1; i++) x = x.next;
            x.next = x.next.next;
            N--;
        }

    }


    // do some research on the difference between "Class" and "class"
    private Class<Item> type;

    private int N = 0;

    Node First;

    public boolean isEmpty() {return First == null;}

    public void delete(int n){
        if (n == 1)
            First = First.next;
        else if (n <= N && n > 1){
            int i = 1;
            for (Node x = First; x.next != null; x = x.next)
                if (i++ == n){x.next = x.next.next;}
        }
    }

    public boolean find(String key){
        for (Node x = First; x.next != null; x = x.next){if (x.content == key) return true; }
        return false;
    }

    public void removeAfter(Node a){
        if (a.next != null && a != null){
            for (Node x = First; x.next != null; x = x.next)
                if (x == a) {x.next = x.next.next; };
        }
    }

    public void insertAfter(Node a, Node b){
        if (a != null && b != null){b = a.next; a.next = b; }
    }

    public void remove(String key) {
        Node prev = new Node();
        Node curr = First;
        int n = 0;
        while (curr != null){
            if (curr.content == key){
                if (curr == First) First = First.next;
                prev.next = curr.next;
                curr = prev.next;
                System.out.print("(-)");
            }
            else{
                prev = curr;
                curr = curr.next;
                System.out.print("->");
            }
        }
    } // verified

    public int max(){

        // Need to ensure "Item" is Integer, otherwise the method max() should be disabled
        // -> figure out a way to implement this check

        if (First == null)
            return 0;
        else {
            int MaxRef = Integer.MIN_VALUE;
            for (Node x = First; x.next != null; x = x.next) {
                if ((int) x.content > MaxRef) {
                    MaxRef = (int) x.content;
                }
            }
            return MaxRef;
        }
    }

    public int recursiveMax(LinkedList a){

        if (a.First.next == null){
            // Base case: single-item linked list
            return (int)a.First.content;
        }
        else{
            // recursive case: evaluate Max of shortened linkedList a
            LinkedList shortenedLinkedList = a;
            shortenedLinkedList.First = shortenedLinkedList.First.next;
            return Integer.max(recursiveMax(shortenedLinkedList), (int)a.First.content);
        }
    }

    public void push(Item item) { // Add item to top of stack.
        Node oldfirst = First;
        First = new Node();
        First.content = item;
        First.next = oldfirst;
        N++;
    }

    public Item pop() { // Remove item from top of stack.
        Item item = (Item)First.content;
        First = First.next;
        N--;
        return item;
    }

    public void printContent(){
        for (Node x = First; x != null; x = x.next)
            System.out.print(x.content);
    }

    public void reverse(){
        First=linkedListReversal(First);
    }

    private Node linkedListReversal(Node x){
        Node first = x;
        Node reverse = null;
        while (first != null){
            Node second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        return reverse;
    }

    public static void main(String args[]){}

}