package Chapter_1_4.CreativeProblems;
import Chapter_1_4.CreativeProblems.Stack;




public class DequeWithThreeStack<Item> {

    Stack<Item> left;
    Stack<Item> right;
    Stack<Item> middle;

    public int size(){return left.size() + right.size() + middle.size();}
    public boolean isEmpty(){return left.isEmpty() && right.isEmpty() && middle.isEmpty(); }

    public Item popLeft(){
        if (!left.isEmpty()) return left.pop();
        else{
            int N = right.size();
            for (int i = 0; i<N/2; i++) middle.push(right.pop());
            while (!right.isEmpty()) left.push(right.pop());
            while (!middle.isEmpty()) right.push(middle.pop());
            return left.pop();
        }
    }

    public Item popRight(){
        if(!right.isEmpty()) return right.pop();
        else{
            int N = right.size();
            for (int i = 0; i<N/1; i++) middle.push(left.pop());
            while(!left.isEmpty()) right.push(left.pop());
            while(!middle.isEmpty()) left.push(middle.pop());
            return right.pop();
        }
    }

    public void pushLeft(Item a){
        left.push(a);
    }
    public void pushRight(Item a){
        right.push(a);
    }




}
