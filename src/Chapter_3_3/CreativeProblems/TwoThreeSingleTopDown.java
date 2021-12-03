

package Chapter_3_3.CreativeProblems;

        import Chapter_1_3.Queue;
        import Chapter_3_2.BST;
        import edu.princeton.cs.algs4.BlackFilter;
        import edu.princeton.cs.algs4.StdDraw;
        import edu.princeton.cs.algs4.StdRandom;

        import java.awt.*;
        import java.util.Stack;

public class TwoThreeSingleTopDown <Key extends Comparable<Key>, Value> {

    private static boolean RED = true;
    private static boolean BLACK = false;

    private Node root;
    private int N;

    private class Node{
        Key key;
        Value val;
        private Node left;
        private Node right;
        int N;
        boolean color = BLACK;

        public Node(Key key, Value value, int N, boolean color){
            this.key = key;
            this.val = value;
            this.N = N;
            this.color = color;
        }
    }





    // ************************************************************************************
    //  tool used to visualize the red-black BST
    //
    // ************************************************************************************

    public void draw() {


        Double topAndBottomMarginRatio = 0.05;
        Double sideMarginRatio = 0.05;

        StdDraw.setCanvasSize(500, 500);

        Font smallFont = new Font("Serif", Font.BOLD, 10);
        StdDraw.setFont(smallFont);



        StdDraw.setYscale(-topAndBottomMarginRatio, 1 + topAndBottomMarginRatio);
        StdDraw.setXscale(-sideMarginRatio, 1 + sideMarginRatio);

        Queue<Node> q = new Queue<Node>();
        q.enqueue(root);
        StdDraw.text(0.5 + 0.01, 1.0, String.valueOf(root.key));

        int d_max = depth();
        for (int d = 0; d < d_max; d++){

            int n = (int)Math.pow(2,d);
            int childCount = 0;

            //System.out.print(n + "\n");

            //System.out.print("depth = " + d + "\n");
            for (int i = 0; i < n; i++) {
                Node t = q.dequeue();

                if (t == null){
                    childCount += 2;
                    q.enqueue(null);
                    q.enqueue(null);
                    continue;
                }

                double parent_y = 1 - ((double)d)/d_max;
                double parent_x = (i+1) * 1.0 / (Math.pow(2, d) + 1);

                double child_y = 1 - ((double)d+1)/d_max;
                double child_x;
                //System.out.printf("%.3f : %.3f \n", parent_x, parent_y);

                childCount++;
                if (t.left != null) {
                    q.enqueue(t.left);
                    child_x = childCount / (Math.pow(2, d+1) + 1);

                    if (t.left.color == RED) StdDraw.setPenColor(Color.RED);
                    else StdDraw.setPenColor(Color.BLACK);

                    StdDraw.line(parent_x, parent_y, child_x, child_y);
                    StdDraw.textLeft(child_x, child_y, String.valueOf(t.left.key));

                } else q.enqueue(null);

                childCount++;
                if (t.right != null) {
                    q.enqueue(t.right);
                    child_x = childCount / (Math.pow(2, d+1) + 1);

                    if (t.right.color == RED) StdDraw.setPenColor(Color.RED);
                    else StdDraw.setPenColor(Color.BLACK);

                    StdDraw.line(parent_x, parent_y, child_x, child_y);
                    StdDraw.textLeft(child_x, child_y, String.valueOf(t.right.key));
                } else q.enqueue(null);


            }
            //System.out.print("\n\n");



        }



    }
    public int depth(){
        return bst_depth(root, 0);
    }
    private int bst_depth(Node x, int d){
        if ((x.left == null) && (x.right == null)) return d;

        int left_depth = 0, right_depth = 0;
        if (x.left != null) left_depth = bst_depth(x.left, d+1);
        if (x.right != null) right_depth = bst_depth(x.right, d+1);

        if (left_depth > right_depth) return left_depth;
        else return right_depth;
    }

    public int depth_redblack() {
        return depth_redblack(root, 0);
    }
    private int depth_redblack(Node x, int d){
        if ((x.left == null) && (x.right == null)) return d;

        int left_depth = 0, right_depth = 0;
        if (x.left != null){
            if (x.left.color == BLACK) left_depth = depth_redblack(x.left, d+1);
            else left_depth = depth_redblack(x.left, d);
        }
        if (x.right != null){
            if (x.right.color == BLACK) right_depth = depth_redblack(x.right, d+1);
            else right_depth = depth_redblack(x.right, d);
        }

        if (left_depth > right_depth) return left_depth;
        else return right_depth;
    }

    // ************************************************************************************
    //  Past this section, all of the methods are taken directly from the implementation of
    //  the generic BST and were left unmodified.
    // ************************************************************************************

    public int size() {
        return size(root);
    }
    protected int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    private boolean isRed(Node node){
        if (node == null) return false;
        return node.color == RED;
    }

    Node rotateLeft(Node h)
    {
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

    Node rotateRight(Node h)
    {
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

    private void flipColors(Node h){
        h.left.color = BLACK;
        h.right.color = BLACK;
        h.color = RED;
    }


    public void put(Key key, Value val){

        Stack<Node> nodePath = new Stack<Node>();

        if (root == null){
            root = new Node(key, val, 1, RED);
            return;
        }
        Node x = root;

        while (true){
            nodePath.add(x);
            int cmp = key.compareTo(x.key);
            if (cmp > 0){
                if (x.right != null) x = x.right;
                else {
                    x.right = new Node(key, val, 1, RED);
                    break;
                }
            }
            else if (cmp < 0){
                if (x.left != null) x = x.left;
                else{
                    x.left = new Node(key, val, 1, RED);
                    break;
                }
            }
            else{
                x.val = val;
                return;
            }
        }

        while (!nodePath.isEmpty()){
            x = nodePath.pop();

            if (isRed(x.right) && !isRed(x.left)){
                if (nodePath.isEmpty()){
                    root = rotateLeft(x);
                    x = root;
                }
                else{
                    Node x_parent = nodePath.pop();
                    if (x_parent.left == x) {
                        x_parent.left = rotateLeft(x);
                        x = x_parent.left;
                    }
                    else {
                        x_parent.right = rotateLeft(x);
                        x = x_parent.right;
                    }
                    nodePath.push(x_parent);
                }
            }
            if (isRed(x.left) && isRed(x.left.left)){
                if (nodePath.isEmpty()){
                    root = rotateRight(x);
                    x = root;
                }
                else {
                    Node x_parent = nodePath.pop();
                    if (x_parent.left == x){
                        x_parent.left = rotateRight(x);
                        x = x_parent.left;
                    }
                    else{
                        x_parent.right = rotateRight(x);
                        x = x_parent.right;
                    }
                    nodePath.push(x_parent);
                }
            }

            if (isRed(x.left) && isRed(x.right)) flipColors(x);

            x.N = size(x.left) + size(x.right) + 1;


        }
    }


    public Value get(Key key) {
        return get(root, key);
    }
    protected Value get(Node x, Key key)
    { // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }


    public static void main(String[] args){


        boolean DisplayTreeForValidation = true;

        if (DisplayTreeForValidation) {
            TwoThreeSingleTopDown<Integer, Integer> bst = new TwoThreeSingleTopDown<>();

            // generate array of integers to be inserted

            int N = 20;
            Integer[] int_list = new Integer[N];
            for (int i = 0; i < N; i++) {int_list[i] = StdRandom.uniform(0,10*N); }

//            Integer[] int_list = {5, -5, 0};

            for (Integer x : int_list){

                System.out.print(x + " ");
                bst.put(x, 1);

                bst.draw();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }

            }


            System.out.print("red-black BST depth: " +  bst.depth_redblack());
        }




    }

}
