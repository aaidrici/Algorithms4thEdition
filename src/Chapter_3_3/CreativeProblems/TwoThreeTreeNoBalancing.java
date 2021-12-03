package Chapter_3_3.CreativeProblems;

import Chapter_1_3.Queue;
import Chapter_3_2.BST;
import edu.princeton.cs.algs4.BlackFilter;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

public class TwoThreeTreeNoBalancing <Key extends Comparable<Key>, Value> {

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

    public void put(Key key, Value val)
    { // Search for key. Update value if found; grow table if new.
        root = put(root, key, val, BLACK);

    }
    protected Node put(Node x, Key key, Value val, Boolean Color) {
// Change key’s value to val if key in subtree rooted at x.
// Otherwise, add new node to subtree associating key with val.
        if (x == null) {
            // added logic to account for the color of the parent node
            return new Node(key, val, 1, Color);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            // added logic to account for the color of the parent node
            boolean childColor = RED;
            if (x.color == RED || (x.right != null && x.right.color == RED)) childColor = BLACK;
            x.left = put(x.left, key, val, childColor);
        }
        else if (cmp > 0) {
            // added logic to account for the color of the parent node
            boolean childColor = RED;
            if (x.color == RED || (x.left != null && x.left.color == RED)) childColor = BLACK;
            x.right = put(x.right, key, val, childColor);
        }
        else x.val = val;


        // Since we do not care about stability for now, these additional instructions can be
        // commented out.
//        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
//        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
//        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        x.N = size(x.left) + size(x.right) + 1;
        return x;
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

        // Hypothesis: The generated three is made up of 3-link nodes in majority with a few 2-link
        //             nodes lying on the leaves of the tree. This suggests the order of growth of the
        //             height `H(N)` should be ~log3(N) since N(H)~power(H,3)
        // Result:     Assuming the order of growth is ~log3(N), the resulting scaling factor seem to
        //             converge to 2.0 for very large values. Although, the obtained result is not convincing.
        //
        //



        boolean DisplayTreeForValidation = false;
        if (DisplayTreeForValidation) {
            TwoThreeTreeNoBalancing<Integer, Integer> bst = new TwoThreeTreeNoBalancing<>();
            int N = 50;
            for (int i = 0; i < N; i++) {
                int generatedKey = StdRandom.uniform(0, 10 * N);
                System.out.print(generatedKey + " ");
                bst.put(generatedKey, 1);
            }
            bst.draw();
            System.out.print("red-black BST depth: " +  bst.depth_redblack());
        }


        System.out.print("");


        Integer[] N_vals = {10,100,1000,10000,100000,1000000};

        int trial_no = 100;
        System.out.printf("%5s %10s %30s\n", "N", "Depth", "C (where H(N) ~ C*log3(N))");
        for (Integer N : N_vals){
            int CumulativeDepth = 0;
            for (int k = 0; k<trial_no; k++){
                TwoThreeTreeNoBalancing<Integer, Integer> bst = new TwoThreeTreeNoBalancing<>();
                for (int i = 0; i<N; i++) bst.put(StdRandom.uniform(0,N*10),0);
                CumulativeDepth += bst.depth_redblack() + 1;
            }
            Double meanDepth = (double)(CumulativeDepth)/trial_no;

            System.out.printf("%5d %10.4f %30.4f\n",N, meanDepth, meanDepth / Math.log(N)/Math.log(3));
        }







    }

}
