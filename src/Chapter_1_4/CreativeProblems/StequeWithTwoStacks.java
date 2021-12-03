package Chapter_1_4.CreativeProblems;
import Chapter_1_4.CreativeProblems.Stack;

public class StequeWithTwoStacks<Item>{


    Stack<Item> back ;
    Stack<Item> front;

    public void push(Item a){
        while(!back.isEmpty()) front.push(back.pop());
        front.push(a);
    }
    public Item pop(){
        while(!back.isEmpty()) front.push(back.pop());
        return front.pop();
    }

    public void enqueue(Item a){
        while(!front.isEmpty()) back.push(front.pop());
        front.push(a);
    }


}


