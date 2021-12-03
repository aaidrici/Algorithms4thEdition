package Chapter_2_4.Exercises;

public class LinkedList_2<Item>{

    private int size;
    private Node first;


    public LinkedList_2(){
        first = null;
    }


    public void push(Item a){
        Node oldFirst = first;
        first = new Node(a);
        first.next = oldFirst;
        size++;
    }


    public Item pop(){
        if (size == 0) return null;
        Item returnItem = first.item;
        first = first.next;
        size--;
        return returnItem;
    }


    public static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }
    public static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }


    public Item RemoveMax(){
        if (size == 0) return null;
        int maxNodeId = 0;
        Item maxNodeItem = first.item;
        int id = 0;
        for (Node x = first.next; x != null; x = x.next){
            id++;
            if (less((Comparable) maxNodeItem, (Comparable)x.item)){
                maxNodeId = id;
                maxNodeItem = x.item;
            }

        }
        pop_nth_item(maxNodeId);
        return maxNodeItem;
    }


    public Item pop_nth_item(int n){ // indexing starts at zero

        if (n >= size) return null;
        Item maxItem;
        if (n == 0){
            maxItem = first.item;
            first = first.next;
        }
        else{
            Node ptr = first;
            for (int i = 0; i < n-1; i++) ptr = ptr.next;
            maxItem = ptr.next.item;
            ptr.next = ptr.next.next;
        }
        size--;
        return maxItem;
    }


    public void SortedPush(Item a){
        if ((size == 0) || less((Comparable)first.item, (Comparable) a)){
            push(a);
            return;
        }
        else{
            Node ptr = first;
            while(ptr.next != null){
                if(less((Comparable)ptr.next.item, (Comparable)a)){
                    Node temp = ptr.next;
                    ptr.next = new Node(a);
                    ptr.next.next = temp;
                    size++;
                    return;
                }
                ptr = ptr.next;
            }
            ptr.next = new Node(a);
            size++;
            return;
        }
    }


    private class Node {
        Node next;
        Item item;

        public Node(Item a) {
            next = null;
            item = a;
        }
    }


    public void Display(){
        Node ptr = first;
        while (ptr != null){
            System.out.print(ptr.item + " -> ");
            ptr = ptr.next;
        }
        System.out.println();
    }


    public static void main(String[] args){
        LinkedList_2 a = new LinkedList_2<Integer>();
        int[] content = {1,5,8,4345,345,2,7};
        for (int x : content) a.push(x);
        a.Display();
    }

}
