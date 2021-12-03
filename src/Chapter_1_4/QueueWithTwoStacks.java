package Chapter_1_4;
import Chapter_1_3.Queue;
import Chapter_1_4.CreativeProblems.Stack;



public class QueueWithTwoStacks<Item> {

    Stack<Item> A;
    Stack<Item> B;

    int N;
    public int size(){return A.size() + B.size(); }

    public QueueWithTwoStacks(){
        N = 0;
        A = new Stack<Item>();
        B = new Stack<Item>();
    }

    public void enqueue(Item a){
        if(A.isEmpty())
            while (!B.isEmpty())
                A.push(B.pop());
        A.push(a);
    }
    public Item dequeue(){
        if(B.isEmpty())
            while (!A.isEmpty())
                B.push(A.pop());
        return B.pop();
    }


}
