package Chapter_1_3;

public class Buffer {

    // data structure used: two stacks with the cursor intersecting both tops


    Stack<Character> leftStack;
    Stack<Character> rightStack;

    public Buffer(){
        leftStack = new Stack<Character>();
        rightStack = new Stack<Character>();
    }


    public void insert(char c){leftStack.push(c);}

    public char get(){
        Character item = null;
        if (!leftStack.isEmpty()){
            item = leftStack.pop();
            leftStack.push(item);
        }
        return item;
    }

    public char delete(){
        Character item = null;
        if (!leftStack.isEmpty()){
            item = leftStack.pop();
        }
        return item;
    }

    public void left(int k){
        for (int i = 0 ; i < k; i++ ){
            if (!leftStack.isEmpty()) rightStack.push(leftStack.pop());
            else break;
        }
    }
    public void right(int k){
        for (int i = 0 ; i < k; i++ ){
            if (!rightStack.isEmpty()) leftStack.push(rightStack.pop());
            else break;
        }
    }
    public int size(){return rightStack.size() + leftStack.size();}


    public static void main(String[] args){

    }
}


