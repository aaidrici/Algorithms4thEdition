package Chapter_1_3;

public class TwoStackWithADeque<Item> {

    Deque<Item> deque;

    public TwoStackWithADeque(){
        deque = new Deque<Item>();
    }

    public Item popStack1(){
        return deque.popLeft();
    }
    public Item popStack2(){
        return deque.popRight();
    }
    public void pushStack1(Item a){
        deque.pushLeft(a);
    }
    public void pushStack2(Item a){
        deque.pushRight(a);
    }


}
