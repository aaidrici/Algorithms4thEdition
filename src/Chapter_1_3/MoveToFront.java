package Chapter_1_3;

import edu.princeton.cs.algs4.StdIn;

public class MoveToFront {



    public MoveToFront(){}




    public static void main(String[] args){

        QueueWithMoveToFront a = new QueueWithMoveToFront();

        while (!StdIn.isEmpty()){
            a.moveToFront(StdIn.readChar());
            System.out.print("new char entered/na");
        }
        a.display();
    }


}

class QueueWithMoveToFront extends Queue<Character>{

    void moveToFront(char a){
        // first step: suppress all instances of input character 'a' if present on the queue
        boolean charIsRepeated = false;
        Node prev = null;
        Node curr = first;
        while (curr != null){
            if (curr.item == a){
                charIsRepeated = true;
                curr = curr.next;
                if (prev == null) first = curr;
                else prev.next = curr;

            }
            else{
                prev = curr;
                curr = curr.next;
            }
        }
        // second step: if the value is repeated, put it at the front. Otherwise, enqueue it.
        if (!charIsRepeated) enqueue(a);
        else{
            Node oldFirst = first;
            first = new Node();
            first.item = a;
            first.next = oldFirst;
        }
    }


}
