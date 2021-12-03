

package Chapter_1_4.CreativeProblems;

public class StackWithAQueue<Item> {

    private Node last;
    private Node first;
    int N;

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){return N; }

    public void enqueue(Item content){
        Node oldLast = last;
        last = new Node();
        last.content = content;
        last.next = null;
        if(!isEmpty()) oldLast.next = last;
        else first = last;
        N++;
    }

    public Item dequeue(){
        Item item = first.content;
        first = first.next;
        if (!isEmpty()) last = null;
            N--;
        return item;
    }

    public Item pop(){
        return dequeue();
    }
    public void push(Item a){
        enqueue(a);
        for (int i = 0; i<N-1; i++)
            enqueue(dequeue());
    }


    private class Node{
        Node next;
        Item content;
        public Node(){next = null;}
    }

    public static void main(String[] args){



    }

}
