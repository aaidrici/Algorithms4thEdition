package Chapter_1_3;

public class RingBuffer<Item> {

    // convention used: 'last' is always pointing at an empty array cell provided the array is not full.
    private int n;
    private int first, last;
    boolean BufferIsFull;
    boolean BufferIsEmpty;

    private Item[] circularBuffer;

    private int mod(int a, int b){
        if (a >= 0) return a % b;
        else return mod( a + b, b);

    }

    public RingBuffer(int n){
        this.n = n;
        circularBuffer = (Item[]) new Object[n];
        first = 0;
        last = 0;
        BufferIsFull = false;
        BufferIsEmpty = true;
    }

    public void display(){
        if (BufferIsFull){
            System.out.print(circularBuffer[first] + " ");
            for (int i = first+1; i != last; i = (i+1)%n){
                System.out.print(circularBuffer[i] + " ");
            }
        }
        else if (BufferIsEmpty){
            for (int i = 0; i<n; i++){
                System.out.print("_ ");
            }
        }
        else{
            for (int i = first; i != last; i = (i+1)%n){
                System.out.print(circularBuffer[i] + " ");
            }
            for (int i = last; i != first; i = (i+1)%n) System.out.print("_ ");
        }
        System.out.println();

    }



    public void add(Item a){
        if (!BufferIsFull) {
            circularBuffer[last] = a;
            last = mod(last + 1, n);
            BufferIsEmpty = false;
            if (last == first) BufferIsFull = true;
        }
    }
    public Item pop(){
        if (!BufferIsEmpty) {
            last = mod(last - 1, n);
            if (last == first) BufferIsEmpty = true;
            BufferIsFull = false;
            return circularBuffer[last];
        }
        else return null;
    }

    public static void main(String[] args){

    }

}
