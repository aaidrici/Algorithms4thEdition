package Chapter_3_2.Exercises;


import Chapter_3_2.BST;

public class BSTwithHeight<Key extends Comparable<Key>, Value> extends BST<Key, Value> {


    protected BSTwithHeight.Node root;

    private int cachedLevel = 0;


    // Q 3.2.6 part1: This first method implements the recursive method
    public int heightRecursive(){
        if (root == null) return 0;
        return heightRecursive(root);
    }

    public int heightRecursive(BSTwithHeight.Node node){
        if (node == null) return 0;
        if (node.N == 1) return 0;

        int leftHeight = heightRecursive(node.left);
        int rightHeight = heightRecursive(node.right);

        if (leftHeight > rightHeight) return leftHeight + 1;
        else return rightHeight + 1;

    }


    // Q 3.2.6 part2: This second method requires extensive modifications of the provided implementation of BST
    // where nodes also have a member defining their depth
    public int heightNonRecursive(){return depth(root);}

    protected class Node{
        private Key key; // key
        private Value val; // associated value
        protected BSTwithHeight.Node left, right; // links to subtrees
        protected int depth; // (height of the node's subtree) added line for exercise 3.2.6
        protected int level; // (path length w.r.t. root) added line for exercises 3.2.7
        protected int totalLevel; // (sum of all node's level under a node) added line for exercises 3.2.7
        protected int N; // # nodes in subtree rooted here
        public Node(Key key, Value val, int N)
        { this.key = key; this.val = val; this.N = N; depth = 0; level = 0; totalLevel = 0; }
    }

    public void put(Key key, Value val) { // Search for key. Update value if found; grow table if new.
        root = put(root, key, val);
        cachedLevel = 0; // reset cached level at the end of a put
    }

    private int depth(Node x){
        if (x == null) return 0;
        return x.depth;
    }

    private BSTwithHeight.Node put(BSTwithHeight.Node x, Key key, Value val) {
        // Change key’s value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.

        if (x == null){
            Node temp = new BSTwithHeight.Node(key, val, 1);
            temp.level = cachedLevel; // added line for level update
            temp.totalLevel = temp.level; // added line for total level update
            return temp;
        }
        int cmp = key.compareTo((Key)x.key);
        if (cmp < 0){
            cachedLevel++; // added line for level update
            x.left = put(x.left, key, val);
        }
        else if (cmp > 0) {
            cachedLevel++; // added line for level update
            x.right = put(x.right, key, val);
        }
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;

        // added line (depth update)
        if (depth(x.left) > depth(x.right)) x.depth = depth(x.left) + 1;
        else x.depth = depth(x.right) + 1;

        // added line (totalLevel path update)
        x.totalLevel = x.level + totalLevel(x.left) + totalLevel(x.right);

        return x;
    }

    public int size() { return size(root); }

    private int size(BSTwithHeight.Node x) {
        if (x == null) return 0;
        else return x.N;
    }




    // Q 3.2.7 part 1: average number of compare with linear time and space proportional to depth.
    public int totalTreePath(){
        // return the sum of the depths for all nodes
        return totalTreePath(root, 0);
    }
    private int totalTreePath(Node node, int level){
        if (node == null) return 0;
        return level + totalTreePath(node.left,level+1) + totalTreePath(node.right,level+1);
    }
    public double avgComparedRecursive(){
        return 1.0 + (double)totalTreePath() / size();
    }




    // Q 3.2.7 part 1: average number of compare with constant time and space proportional to depth.
    public double avgComparedNonRecursive(){
        return 1.0 + (double)totalLevel(root) / size();
    }

    public int totalTreePathNonRecursive(){
        return totalLevel(root);
    }
    private int totalLevel(Node node){
        if (node == null) return 0;
        return node.totalLevel;
    }


    public static int power(int a, int b){
        int product = 1;
        for (int i = 0; i<b; i++) product *= a;
        return product;
    }


    public static double optCompares(int N){

        if (N <= 1) return 0;

        int sumOfDepths = 0;
        int level = 0;
        int rowSize;
        int n = N;


        while (n >= power(2,level)) {
            rowSize = power(2,level);
            sumOfDepths += rowSize * level++;
            n -= rowSize;
        }
        sumOfDepths += n * level;


        System.out.println(sumOfDepths);
        return 1.0 + (double) sumOfDepths / N;

    }


    public static void main(String[] args){

        BSTwithHeight<Integer, Integer> bst = new BSTwithHeight<Integer, Integer>();

        Integer[] inputSequence = {10, 20, 33, 34, 32, 5, 12, 3, 1, 0, 45, 12, -4, 24};
        int i = 0;
        for (int x: inputSequence)
            bst.put(x, ++i);


//        // example for Q 3.2.6
//        System.out.println(bst.heightRecursive());
//        System.out.println(bst.heightNonRecursive());
//
//        // Example for Q 3.2.7
//        System.out.println(bst.avgComparedRecursive());
//        System.out.println(bst.avgComparedNonRecursive());
//

        System.out.println(optCompares(8));

    }
}
