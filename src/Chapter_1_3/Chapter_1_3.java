package Chapter_1_3;

// java.util seems to be quite useful.

import edu.princeton.cs.algs4.StdRandom;
import java.lang.Math;
import java.lang.Integer;
import java.io.File;

public class Chapter_1_3 {

    // Q1.3.18
    //  It will remove the node subsequent to x, as its reference will no longer be accessible.

    // Q1.3.19
    //   for (Node x = First; x.next != null ; x = x.next) if (x.next.next == null) {x.next = full;}

    // Q1.3.20
    // public void nthElementRemoval(int n){

    // Q1.3.21
    //  see method find() in class linkedList

    // Q1.3.22
    //  it inserts Node t between x and x.next

    // Q1.3.23
    //  it the second code fragment, t ends up pointing at itself

    // Q1.3.24
    //  see method removeAfter() in method linkedList

    // Q1.3.25
    //  see method insertAfter()

    // Q1.3.26
    //  see method remove()

    // Q1.3.27
    //  see method max()

    // Q1.3.28
    //  see method recursiveMax()

    // Q1.3.29
    //  refer to class circular defined below

    // Q1.3.30
    // see reverse method() down there

    // Q1.3.31
    //  see method doubleLinkedList

    // Q1.3.32
    //  see method steque

    // Q1.3.33
    //  see method Deque (used single linked list instead of double)

    // Q1.3.34
    //  see method RandomBag

    // Q1.3.35 & Q1.3.36
    //  see method RandomQueue

    // Q1.3.37
    //  see Josephus client

    // Q1.3.38
    //  ... skipped

    // Q1.3.39
    // see RingBuffer class

    // Q1.3.40
    // see MoveToFront class - incomplete, come back to it later.

    // Q1.3.41
    // see second construction of class queue

    // Q1.3.42
    // see second constructor of class Stack

    // Q1.3.43
    //  see FileTreeStructure class

    // Q1.3.44
    //  see Buffer class (not tested...)

    // Q1.3.45
    //  pseudo code: sweep through linked list and increment a counter at push operations while decrementing it at pop operations. If the counter reaches negative values, the permutation is not possible.

    // Q1.3.46
    //  ----- ( I don't understand this)

    // Q1.3.47
    //  see methods catenate in Stack, Queue and Steque (although this one was not tested)

    // Q1.3.48
    //  see method TwoStackWithADeque (it is assumed both stacks have shared items, two counters could be used to ensure items are not shared between the two)

    // Q1.3.49
    //   ----- (skipped)

    // Q1.3.50
    //  see iterator method in Stack

    // Q1.3.51
    //   ---- (seems too laborious, give an attempt some other time)


    public static void main(String[] args) {


    }

}


class linkedList<Item>{

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

    public int recursiveMax(linkedList a){

        if (a.First.next == null){
            // Base case: single-item linked list
            return (int)a.First.content;
        }
        else{
            // recursive case: evaluate Max of shortened linkedList a
            linkedList shortenedLinkedList = a;
            shortenedLinkedList.First = shortenedLinkedList.First.next;
            return Integer.max(recursiveMax(shortenedLinkedList), (int)a.First.content);
        }
    }

    public void push(Item item)
    { // Add item to top of stack.
        Node oldfirst = First;
        First = new Node();
        First.content = item;
        First.next = oldfirst;
        N++;
    }
    public Item pop()
    { // Remove item from top of stack.
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
}

class Node<Item>{
    Item content;
    Node next;
    public Node(Node x){next = x;}
    public Node(Node x, Item item){next = x; content = item; }
    public Node(){}

}


class nullNodeException extends Exception{
    Node a;
    public nullNodeException (Node a){ this.a = a; }
}

class nullNodeExceptionThrower{

    static void NodeIsNull(Node a) throws nullNodeException{
        if (a == null){throw new nullNodeException(a);}
    }
}

class circular<Item>{
    int N = 0;
    Node first;
    Node last;
    public boolean isEmpty(){return N==0;}

    public void enqueue(Item item){
        Node oldLast = last;
        last = new Node();
        oldLast.next = last;
        last.content = item;
        N++;
        // additional command to turn a queue into a circular queue
        last.next = first;
    }

    public Item dequeue(){
        Item item = (Item)first.content;
        first = first.next;
        N--;
        if (isEmpty()) {last = null;}
        else {
            // additional command turn a queue into a circular queue
            last.next = first;
        }
        return item;
    }
}

// Q1.3.31
class doubleLinkedList<Item> {
    private doubleNode First;
    private doubleNode Last;
    private int N;

    public doubleLinkedList(){First = null; Last = null; N = 0;}

    void display(){
        for (doubleNode x = First; x != null; x = x.next) System.out.print(x.content + " ");
        System.out.print("\n");
    }

    void InsertAtTheBeginning(Item A){
        N++;
        doubleNode oldFirst = First;
        First = new doubleNode(A, null, oldFirst);
        if (oldFirst != null) oldFirst.prev = First;
        else Last = First;
    }
    void InsertAtTheEnd(Item A){
        N++;
        doubleNode oldLast = Last;
        Last = new doubleNode(A,oldLast,null);
        if (oldLast != null) oldLast.next = Last;
        else First = Last;

    }
    void RemoveFromTheBeginning(){
        if (First != null) {
            N--;
            First = First.next;
            if (First != null){ First.prev = null;}
            else {Last = null;}
        }
    }
    void RemoveFromTheEnd(){
        if (Last != null){
            N--;
            Last = Last.prev;
            if (Last != null){Last.next = null;}
            else {First = null;}
        }

    }
    void InsertBeforeAGivenNode(int n, Item A){
        if (n == 0){InsertAtTheBeginning(A);}
        else if (n > 0 && n <= N-1){
            doubleNode curr = First.next;
            for (int i = 1; i < n; i++) {curr = curr.next; }
            doubleNode inserted = new doubleNode(A, curr.prev, curr);
            curr.prev.next = inserted;
            curr.prev = inserted;
        }
    }
    void InsertAfterAGivenNode(int n, Item A){
        if (n < N-1 && n > -1){InsertBeforeAGivenNode(n+1,A);}
        if (n == N-1){InsertAtTheEnd(A); }
    }
    void RemovetAtGivenNode(int n){
        if (n == 0) RemoveFromTheBeginning();
        if (n == N-1) RemoveFromTheEnd();
        if (n > 0 && n < N-1){
            doubleNode curr = First.next;
            for (int i = 1; i < n; i++) {curr = curr.next; }
            curr.next.prev = curr.prev;
            curr.prev.next = curr.next;
        }
    }

    class doubleNode{
        Item content;
        doubleNode prev;
        doubleNode next;
        public doubleNode(Item content, doubleNode prev, doubleNode next){
            this.content = content;
            this.prev = prev;
            this.next = next;
        }
    }
}


































































