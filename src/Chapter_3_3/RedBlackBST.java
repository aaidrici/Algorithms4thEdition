package Chapter_3_3;

import Chapter_1_3.Queue;
import Chapter_3_2.BST;
import Chapter_3_3.CreativeProblems.OptimalStorage;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

    // this version has software caching implemented.

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public RedBlackBST() {
        cachedNode = new Node(null, null, 0, BLACK);
    }


    private Node root;

    private Node cachedNode;

    protected class Node {
        Key key; // key
        Value val; // associated data
        Node left, right; // subtrees
        int N; // # nodes in this subtree
        boolean color; // color of link from

        // parent to this node
        Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    void flipColors(Node h) {
//        h.color = RED;
//        h.left.color = BLACK;
//        h.right.color = BLACK;

        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
        //return h;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public void put(Key key, Value val) {
        // Search for key. Update value if found; grow table if new.
        // check whether the cached node is the right one to start with
        if ((root != null) && (cachedNode.key.compareTo(key) == 0)) {
            cachedNode.val = val;
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) { // Do standard insert, with red link to parent.
            Node x = new Node(key, val, 1, RED);
            cachedNode = x;
            return x;
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    public Value get(Key key) {
        if (cachedNode.val == null) return null;
        else if (cachedNode.key.compareTo(key) == 0) return cachedNode.val;
        return get(root, key);
    }

    protected Value get(Node x, Key key) { // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public Key min()
    {
        if (root == null) return null;
        return min(root).key;
    }
    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max()
    {
        if (root == null) return null;
        return max(root).key;
    }
    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left)
                + size(h.right);
        return x;
    }

    Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left)
                + size(h.right);
        return x;
    }

    public int depth() {
        return bst_depth(root, 0);
    }

    private int bst_depth(Node x, int d) {
        if ((x.left == null) && (x.right == null)) return d;

        int left_depth = 0, right_depth = 0;
        if (x.left != null) left_depth = bst_depth(x.left, d + 1);
        if (x.right != null) right_depth = bst_depth(x.right, d + 1);

        if (left_depth > right_depth) return left_depth;
        else return right_depth;
    }


    public boolean is23(){
        return is23(root);
    }
    private boolean is23(Node node){
        if (node == null) return true;

        if (isRed(node.right)) return false;
        if (isRed(node) && isRed(node.left)) return false;

        return is23(node.left) && is23(node.right);
    }

    public boolean isBalanced(){
        return isBalanced(root, 0) != -1;
    }
    public int isBalanced(Node node, int count){

        if (node == null) return count;

        int leftDepth = 0, rightDepth = 0;

        if (node.left != null){
            if (isRed(node.left)) leftDepth = isBalanced(node.left, count);
            else leftDepth = isBalanced(node.left, count + 1);
        }

        if (node.right != null) {
            if (isRed(node.right)) rightDepth = isBalanced(node.right, count);
            else rightDepth = isBalanced(node.right, count + 1);
        }

        if (node.right != null && node.left == null) leftDepth = rightDepth;
        else if (node.left != null && node.right == null) rightDepth = leftDepth;

        if (rightDepth == -1 || leftDepth == -1) return -1;
        if (rightDepth != leftDepth) return -1;
        return rightDepth;

    }

    // exercises 3.2.31
    public boolean isBST(){
        return isBST(root);
    }
    public boolean isBST(Node node){

        if (!this.isBinaryTree()) return false;
        if (!this.isOrdered(min(), max())) return false;
        if (!this.hasNoDuplicates()) return false;

        return true;
    }

    public boolean hasNoDuplicates(){
        return hasNoDuplicates(root);
    }
    private boolean hasNoDuplicates(Node node){
        ArrayList<Key> arrList = new ArrayList();
        hasNoDuplicates(arrList, node);
        Collections.sort(arrList);
        for (int i = 0; i<arrList.size() - 1; i++) {
            if (arrList.get(i).compareTo(arrList.get(i + 1)) == 0) return false;
        }
        return true;
    }
    private void hasNoDuplicates(ArrayList<Key> arraylist, Node node){
        if (node.left!=null) {
            arraylist.add(node.left.key);
            hasNoDuplicates(arraylist, node.left);
        }

        if (node.right!= null) {
            arraylist.add(node.right.key);
            hasNoDuplicates(arraylist, node.right);
        }
    }

    public boolean isBinaryTree(){
        return isBinaryTree(root);
    }
    private boolean isBinaryTree(Node node){

        if (node == null) return true;

        int count = 1;
        if (node.left != null) count += node.left.N;
        if (node.right != null) count += node.right.N;

        if (node.N != count) return false;

        return isBinaryTree(node.left) && isBinaryTree(node.right);

    }

    public boolean isOrdered(Key min, Key max){
        return isOrdered(root, min, max);
    }
    private boolean isOrdered(Node node, Key min, Key max){

        if ((node == null) || (node.left == null && node.right == null))
            return true;

        if (node.left != null && node.left.key.compareTo(node.key) > 0) return false;

        if (node.right != null && node.right.key.compareTo(node.key) < 0) return false;

        Node x = node;
        while (x.left != null) x = x.left;
        if (x.key.compareTo(min) < 0) return false;

        x = node;
        while (x.right != null) x = x.right;
        if (x.key.compareTo(max) > 0) return false;

        return isOrdered(node.left, min, node.key) && isOrdered(node.right, node.key, max);

    }



    // exercise 3.3.33
    public boolean isRedBlackBST(){
        return isBST() && is23() && isBalanced();
    }

    public void draw() {

        Double topAndBottomMarginRatio = 0.05;
        Double sideMarginRatio = 0.05;

        StdDraw.setCanvasSize(1500, 500);

        Font smallFont = new Font("Serif", Font.BOLD, 10);
        StdDraw.setFont(smallFont);


        StdDraw.setYscale(-topAndBottomMarginRatio, 1 + topAndBottomMarginRatio);
        StdDraw.setXscale(-sideMarginRatio, 1 + sideMarginRatio);

        Queue<Node> q = new Queue<Node>();
        q.enqueue(root);
        StdDraw.text(0.5 + 0.01, 1.0, String.valueOf(root.key));

        int d_max = depth();
        for (int d = 0; d < d_max; d++) {

            int n = (int) Math.pow(2, d);
            int childCount = 0;

            //System.out.print(n + "\n");

            //System.out.print("depth = " + d + "\n");
            for (int i = 0; i < n; i++) {
                Node t = q.dequeue();

                if (t == null) {
                    childCount += 2;
                    q.enqueue(null);
                    q.enqueue(null);
                    continue;
                }

                double parent_y = 1 - ((double) d) / d_max;
                double parent_x = (i + 1) * 1.0 / (Math.pow(2, d) + 1);

                double child_y = 1 - ((double) d + 1) / d_max;
                double child_x;
                //System.out.printf("%.3f : %.3f \n", parent_x, parent_y);

                childCount++;
                if (t.left != null) {
                    q.enqueue(t.left);
                    child_x = childCount / (Math.pow(2, d + 1) + 1);

                    if (isRed(t.left)) StdDraw.setPenColor(Color.RED);
                    else StdDraw.setPenColor(Color.BLACK);

                    StdDraw.line(parent_x, parent_y, child_x, child_y);
                    StdDraw.textLeft(child_x, child_y, String.valueOf(t.left.key));

                } else q.enqueue(null);

                childCount++;
                if (t.right != null) {
                    q.enqueue(t.right);
                    child_x = childCount / (Math.pow(2, d + 1) + 1);

                    if (isRed(t.right)) StdDraw.setPenColor(Color.RED);
                    else StdDraw.setPenColor(Color.BLACK);

                    StdDraw.line(parent_x, parent_y, child_x, child_y);
                    StdDraw.textLeft(child_x, child_y, String.valueOf(t.right.key));
                } else q.enqueue(null);


            }
            //System.out.print("\n\n");


        }
    }

    boolean is2node(Node x){
        if (isRed(x) || isRed(x.left)) return false;
        return true;
    }

    public void deleteMin(){
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;

// ORIGINAL IMPLEMENTATION
//        if (root == null) return;
//        if (root.N == 1) {
//            root = null;
//            return;
//        }
//        root = deleteMin(root);
//        root.N = size(root.left) + size(root.right) + 1;
    }
    public Node deleteMin(Node h){
        if (h.left == null)
            return null;
        if(!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);

// ORIGINAL IMPLEMENTATION
//        Node noToReturn;
//
//        if (b.left.left == null && isRed(b.left)){
//            b.left = null;
//        }
//        else if( !is2node(b.left)){
//            b.left = deleteMin(b.left); // nothing to do. Keep going down.
//        }
//        else if (!is2node(b.right)) {
//            // apply expected color change
//            b.color = BLACK;
//            b.left.color = RED;
//            // define B,C and D
//            Node d = b.right;
//            while(isRed(d.left.left)) d = d.left;
//            Node c;
//            c = d.left;
//            // perform swapping
//            d.left = c.right;
//            c.right = b.right;
//            b.right = c.left;
//            c.left = b;
//            b = c;
//            b.left = deleteMin(b.left);
//        }
//        else{
//            // both children of x.left are two nodes.
//            // -> flip B and C
//            b.left.color = RED;
//            b.right.color = RED;
//            b = rotateLeft(b);
//            b.color = BLACK;
//            b.left = deleteMin(b.left);
//        }
//
//        if (b.left != null && (isRed(b.left) && isRed(b.left.left))){
//            b = rotateRight(b);
//            flipColors(b);
//        }
//
//        if (b.right != null) b.right.N = size(b.right.left) + size(b.right.right) + 1;
//        b.N = size(b.left) + size(b.right) + 1;
//
//
//        return b;
    }

    public void deleteMax(){

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;

// ORIGINAL IMPLEMENTATION
//        if (root == null) return;
//        if (root.N == 1) {
//            root = null;
//            return;
//        }
//        root = deleteMax(root);
//        root.color = BLACK;
//
//        root.N = size(root.left) + size(root.right) + 1;
    }
    public Node deleteMax(Node h){

        if (isRed(h.left))
            h = rotateRight(h);
        if (h.right == null)
            return null;
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);


// ORIGINAL IMPLEMENTATION
//        if (b.right == null){b = b.left;}
//        else if (b.right.right == null){
//            b.right = b.right.left;
//            if (b.right != null) b.right.color = BLACK;
//        }
//        else if( !is2node(b.right )|| !is2node(b)){
//            b.right = deleteMax(b.right); // nothing to do. Keep going down.
//        }
//        else if (!is2node(b.left)) {
//
//            // apply expected color change
//            b.color = RED;
//            b.left.left.color = BLACK;
//
//            // define A,B and E
//            Node c = b.right;
//            Node a = b.left;
//            Node e = b.left.right;
//
//            a.right = c;
//            b.right = c.left;
//            c.left = b;
//            b.left = e;
//
//            b = a;
//
//
//            b = deleteMax(b);
//
//        }
//        else{
//            // both children of x.left are two nodes.
//            // -> flip B and C
//            b.left.color = RED;
//            b.right.color = RED;
//            b = rotateLeft(b);
//            b.color = BLACK;
//            b = deleteMax(b); // changed b.right for b in both spots.
//
//        }
//
//        if (b.left != null && (isRed(b.left) && isRed(b.left.left))){
//            b = rotateRight(b);
//            flipColors(b);
//            b.color = BLACK;
//        }
//
//        if (isRed(b.left) && isRed(b.right)){
//            b.left.color = BLACK;
//            b.right.color = BLACK;
//            b.color = RED;
//        }
//
//        if (b.left != null) b.left.N = size(b.left.left) + size(b.left.right) + 1;
//        b.N = size(b.left) + size(b.right) + 1;
//
//        return b;
    }


    public boolean isEmpty(){return size() == 0; }

    // Note: Took answer from book
    public void delete(Key key)
    {
        // took answer from book
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }
    // Note: Took answer from book
    private Node moveRedRight(Node h)
    { // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
    // Node: Took answer from book
    private Node moveRedLeft(Node h)
    { // Assuming that h is red and both h.left and h.left.left
        // are black, make h.left or one of its children red.
        flipColors(h);
        if (isRed(h.right.left))
        {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }
    private Node balance(Node h){
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
    // Note: Took answer from book;
    private Node delete(Node h, Key key)
    {
        if (key.compareTo(h.key) < 0)
        {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else
        {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0)
            {
                Node x = min(h.right);
                h.key = x.key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }

    public boolean contains(Key key){ return contains(key, root); }
    public boolean contains(Key key, Node h){
        if (h == null) return false;
        int cmp = h.key.compareTo(key);
        if (cmp == 0) return true;
        if (cmp > 0) return contains(key, h.left);
        return contains(key, h.right);
    }


    public Iterable<Key> keys()
    { return keys(min(), max()); }

    public Iterable<Key> keys(Key lo, Key hi)
    {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi)
    {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
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


    public static void main(String[] args) {


//    RedBlackBST<Integer,Integer> bst = new RedBlackBST<Integer, Integer>();
//    Integer[] int_list ={879, 18, 414, 313, 923, 213, 725, 218, 34, 662, 615, 253, 1, 541, 528, 652, 400, 393, 615, 980, 708, 463, 398, 68, 312, 115, 991, 130, 307, 311, 582, 50, 645, 253, 134, 867, 787, 538, 203, 392, 650, 777, 887, 112, 656, 528, 0, 866, 285, 971, 326, 32, 785, 268, 658, 207, 440, 980, 858, 105, 800, 457, 250, 623, 507, 219, 948, 934, 568, 316, 744, 804, 435, 36, 350, 879, 405, 862, 320, 971, 958, 939, 333, 254, 494, 849, 144, 658, 668, 95, 711, 46, 310, 48, 624, 809, 887, 801, 909, 260};
//
//    for (Integer x : int_list) bst.put(x, 0);
//
//    bst.deleteMax();
//    boolean is23 = bst.is23();
//    boolean hasNoDuplicate = bst.hasNoDuplicates();
//    boolean isBalanced = bst.isBalanced();
//    boolean isBinaryTree = bst.isBinaryTree();
//    boolean isOrdered = bst.isOrdered(bst.min(), bst.max());
//    System.out.println("is23 = " + is23);
//    System.out.println("hasNoDuplicate = " + hasNoDuplicate);
//    System.out.println("isBalanced = " + isBalanced);
//    System.out.println("isBinaryTree = " + isBinaryTree);
//    System.out.println("isOrdered = " + isOrdered);
//    bst.draw();


        int N = 100;
        Integer[] int_list = new Integer[N];

        for (int k = 0; k<1000; k++){
            for (int i = 0; i < N; i++) int_list[i] = StdRandom.uniform(0,10*N);

            RedBlackBST<Integer,Integer> bst = new RedBlackBST<Integer, Integer>();

            for (Integer x : int_list) bst.put(x, 0);

            System.out.print("Input sequence : \n");
            System.out.print("{");
            for (Integer x : int_list) System.out.print(x + ", ");
            System.out.print("};");
            System.out.println();

            bst.deleteMax();

            boolean is23 = bst.is23();
            boolean hasNoDuplicate = bst.hasNoDuplicates();
            boolean isBalanced = bst.isBalanced();
            boolean isBinaryTree = bst.isBinaryTree();
            boolean isOrdered = bst.isOrdered(bst.min(), bst.max());


            if (!(is23 && isBalanced &&  hasNoDuplicate && isBinaryTree && isOrdered)){

                System.out.println("Number of correct attempt before it failed: " + k);


                System.out.println("is23 = " + is23);
                System.out.println("hasNoDuplicate = " + hasNoDuplicate);
                System.out.println("isBalanced = " + isBalanced);
                System.out.println("isBinaryTree = " + isBinaryTree);
                System.out.println("isOrdered = " + isOrdered);

                System.out.print("Input sequence that made it fail : \n");
                System.out.print("{");
                for (Integer x : int_list) System.out.print(x + ", ");
                System.out.print("};");
                System.out.println();

                bst.draw();
                break;
            }
        }



    }
}






