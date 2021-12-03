package Chapter_2_4.CreativeProblems;
import edu.princeton.cs.algs4.StdRandom;


public class PQWithExplicitLinks {

    private enum action {remove, add};

    public int N;
    private Node head;
    private Node tail;
    public PQWithExplicitLinks(){
        head = null;
        tail = head;
        N = 0;
    }

    private void exch(Node a, Node b){
        Comparable temp = a.item;
        a.item = b.item;
        b.item = temp;
    }

    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }

    private void sink(){
        Node ptr = head;

        while(true){

            if (ptr.right_child != null){
                if(less(ptr.left_child.item, ptr.right_child.item)){
                    if(less(ptr.item, ptr.right_child.item)){
                        exch(ptr, ptr.right_child);
                        ptr = ptr.right_child;
                    }
                    else
                        break;
                }
                else{
                    if(less(ptr.item, ptr.left_child.item)){
                        exch(ptr, ptr.left_child);
                        ptr = ptr.left_child;
                    }
                    else
                        break;
                }

            }
            else if (ptr.left_child != null){
                if(less(ptr.item, ptr.left_child.item)){
                    exch(ptr, ptr.left_child);
                    ptr = ptr.left_child;
                }
                else
                    break;
            }
            else
                break;

        }



    }

    private void swim(Node ptr){

        while (ptr != head && less(ptr.parent.item, ptr.item)){
            exch(ptr, ptr.parent);
            ptr = ptr.parent;
        }

    }


    public void Insert(int item){

        int id = N + 1; // target item to add

        if (id == 1){
            head = new Node(item, null);
            N++;
            //tail = head;
            return;
        }

        // id should never be zero, 1 = head;
        // depth zero = head level
        int n = id;
        int id_depth = -1;
        while (n != 0){
            id_depth++;
            n /= 2;
        }

        Node ptr = head;
        int level_width = Power(2,id_depth);  // number of element at that depth
        int row_pos = id - level_width;

        int middle = (level_width-1)/2 ;
        int lb = 0;
        int rb = level_width-1;

        for (int k = 0; k < id_depth; k++){
            if (middle < row_pos) {
                // go right
                // System.out.println("Right -> ");
                if (k == id_depth - 1) {
                    ptr.right_child = new Node(item, ptr);
                    tail = ptr.right_child;
                    N++;
                    break;
                } else {
                    ptr = ptr.right_child;
                    lb = middle + 1;
                    middle = (rb + lb) / 2;
                }
            }
            else{
                // go left
                // System.out.println("Left <- ");
                if (k == id_depth - 1) {
                    ptr.left_child = new Node(item, ptr);
                    tail = ptr.left_child;
                    N++;
                    break;
                }
                ptr = ptr.left_child;
                rb = middle;
                middle = (rb+lb)/2;
            }
        }

        swim(tail);
    }

    public Comparable RemoveMax(){

        int id = N; // target item to remove

        Comparable maxItem = null ;

        if (N == 0){
            System.out.print("it's fucking empty dawg.");
            return maxItem;
        }

        maxItem = head.item;
        if (id == 0){
            head = null;
            tail = null;
            N--;
            return maxItem;
        }

        // id should never be zero, 1 = head;
        // depth zero = head level
        int n = id;
        int id_depth = -1;
        while (n != 0){
            id_depth++;
            n /= 2;
        }


        Node ptr = head;
        int level_width = Power(2,id_depth);  // number of element at that depth
        int row_pos = id - level_width;

        int middle = (level_width-1)/2 ;
        int lb = 0;
        int rb = level_width-1;


        for (int k = 0; k < id_depth; k++){
            if (middle < row_pos) {
                // go right
                // System.out.println("Right -> ");
                if (k == id_depth - 1) {
                    exch(ptr.right_child, head);
                    ptr.right_child = null;
                    N--;
                    break;
                } else {
                    ptr = ptr.right_child;
                    lb = middle + 1;
                    middle = (rb + lb) / 2;
                }
            }
            else{
                // go left
                // System.out.println("Left <- ");
                if (k == id_depth - 1) {
                    exch(ptr.left_child, head);
                    ptr.left_child = null;
                    N--;
                    break;
                }
                ptr = ptr.left_child;
                rb = middle;
                middle = (rb+lb)/2;
            }
        }

        sink();

        return maxItem;

    }

    public static int Power(int a, int b){
        //returns a^b;
        int product = 1;
        while (b-- > 0)
            product *= a;
        return product;
    }

    private class Node{
        public Node parent;
        public Node left_child;
        public Node right_child;
        public Comparable item;
        public Node(Comparable a, Node parentNode){
            parent = parentNode;
            item = a;
            left_child = null;
            right_child = null;
        }
    }

    public static void main(String[] args){

        int N = 23;
        Integer[] a = new Integer[N];
            for (int i = 0; i<N; i++){
                a[i] = StdRandom.uniform(0,2*N);
                System.out.print(a[i] + " ");
            }
            System.out.println();


        PQWithExplicitLinks pq = new PQWithExplicitLinks();

        for (int i = 0; i<N; i++)
            pq.Insert(a[i]);

        for (int i = 0; i<N; i++)
            System.out.print(pq.RemoveMax() + " ");



    }


}
