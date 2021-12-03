package Chapter_3_2.CreativeProblems;


import Chapter_3_2.BST;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class BST_threading <Key extends Comparable<Key>,Value> {

    public Node root;

    protected class Node
    {
        public Key key; // key
        public Value val; // associated value
        public BST_threading.Node left, right; // links to subtrees
        public int N; // # nodes in subtree rooted here
        public Node(Key key, Value val, int N)
        { this.key = key; this.val = val; this.N = N; }

        // added lines
        public Node pred, succ;
        public Node(Key key, Value val, int N, Node pred, Node succ)
        { this.key = key; this.val = val; this.N = N; this.pred = pred; this.succ = succ; }
    }


    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){

        if (node == null){
            // modified constructor
            Node pred = floor(root, key);
            Node succ = ceiling(root, key);
            Node newNode = new Node(key, value, 1, pred, succ);
            if (pred != null) pred.succ = newNode;
            if (succ != null) succ.pred = newNode;
            return newNode;
        }
        int comp = node.key.compareTo(key);
        if (comp < 0) node.right = put(node.right, key, value);
        else if (comp > 0) node.left = put(node.left, key, value);
        else node.val = value;
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void deleteMin(){
        root = deleteMin(root);

        // added line to update pred value
        Node min = min(root);
        min.pred = null;

    }

    // Changed from BST
    public void deleteMax() {
        root = deleteMax(root);

        // added line to update succ value
        Node x = max(root);
        x.succ = null;
    }

    public Node delete(Node x, Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else
        {
            // at this stage we know node x is about to get deleted.

            if (x.pred != null) x.pred.succ = x.succ;
            if (x.succ != null) x.succ.pred = x.pred;

            if (x.right == null){
                return x.left;
            }
            if (x.left == null){
                return x.right;
            }
            Node t = x;
            x = min(t.right); // See page 407.
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    //
    // Past this point, all methods remain unchanged from the original implementation of BST.
    //

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.right) + size(x.left) + 1;
        return x;
    }
    public Node deleteMin(Node x){
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key){
        root = delete(root, key);
    }



    public Key min()
    {
        return min(root).key;
    }

    public Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x)
    {
        if (x.right == null) return x;
        return max(x.right);
    }

    public int size()
    { return size(root); }
    protected int size(Node x)
    {
        if (x == null) return 0;
        else return x.N;
    }


    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }


    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }


    public Key select(int k)
    {
        return select(root, k).key;
    }

    private Node select(Node x, int k)
    { // Return Node containing key of rank k.
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);
        else return x;
    }

    public int rank(Key key)
    { return rank(key, root); }

    private int rank(Key key, Node x)
    { // Return number of keys less than x.key in the subtree rooted at x.
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }


    public static void main(String[] args){

        BST_threading<Integer, Integer> bst = new BST_threading<>();

        int N  = 10;
        Integer[] int_list = new Integer[N];
        for (int i = 0; i<N; i++)
            int_list[i] = StdRandom.uniform(0,10*N);


        // test the put() function
        for (Integer x : int_list) bst.put(x,x);

        // test the delete() function
        bst.delete(int_list[0]);
        bst.delete(int_list[7]);
        bst.delete(int_list[3]);

        // test the deleteMin() function
        bst.deleteMin();

        // test the deleteMax() function
        bst.deleteMax();


        System.out.printf("Sorted Array: \n");
        Arrays.sort(int_list);
        for (Integer x : int_list) System.out.printf("%d\n", x);

        System.out.printf("Sorted Array following successor links: \n");
        BST_threading.Node x = bst.min(bst.root);
        while (x != null){
            System.out.printf("%d\n", x.key);
            x = x.succ;
        }
        System.out.printf("flipped-Sorted Array following predecessor links: \n");
        x = bst.max(bst.root);
        while (x != null){
            System.out.printf("%d\n", x.key);
            x = x.pred;
        }




    }

}























































