package Chapter_3_2;

import Chapter_1_3.Queue;
import Chapter_3_2.CreativeProblems.BST_binaryTreeCheck;

import java.util.ArrayList;
import java.util.Collections;

public class BST <Key extends Comparable<Key>, Value>{

    protected Node root; // root of BST

    protected class Node
    {
        public Key key; // key
        public Value val; // associated value
        public Node left, right; // links to subtrees
        public int N; // # nodes in subtree rooted here
        public Node(Key key, Value val, int N)
        { this.key = key; this.val = val; this.N = N; }
    }

    public int size()
    { return size(root); }
    protected int size(Node x)
    {
        if (x == null) return 0;
        else return x.N;
    }

    public Value get(Key key)
    { return get(root, key); }

    protected Value get(Node x, Key key)
    { // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val)
    { // Search for key. Update value if found; grow table if new.
        root = put(root, key, val);
    }

    protected Node put(Node x, Key key, Value val)
    {
// Change key’s value to val if key in subtree rooted at x.
// Otherwise, add new node to subtree associating key with val.
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min()
    {
        return min(root).key;
    }

    private Node min(Node x)
    {
        if (x.left == null) return x;
        return min(x.left);
    }


    public Key max()
    {
        return max(root).key;
    }

    private Node max(Node x)
    {
        if (x.right == null) return x;
        return max(x.right);
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

    public void deleteMin()
    {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.
                right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() { root = deleteMax(root); }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.right) + size(x.left) + 1;
        return x;
    }



    public void delete(Key key)
    { root = delete(root, key); }

    private Node delete(Node x, Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else
        {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right); // See page 407.
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
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

    // exercise 3.2.30
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

    // exercise 3.2.29
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

    // exercises 3.2.31
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

//     exercises 3.2.32
    public boolean isBST(){
        return isBST(root);
    }
    public boolean isBST(Node node){

        if (!this.isBinaryTree()) return false;
        if (!this.isOrdered(min(), max())) return false;
        if (!this.hasNoDuplicates()) return false;

        return true;
    }


    public static void main(String[] args){
        BST<Integer,Integer> bst = new BST<Integer, Integer>();

        ArrayList<Integer> content = new ArrayList<>();
        content.add(10);
        content.add(-4);
        content.add(5);
        content.add(3);
        content.add(1);
        content.add(2);
        content.add(12);
        content.add(26);
        content.add(109);
        content.add(66);

        for (Integer x : content) bst.put(x, 0);

        System.out.print(bst.isBST());

    }



}



