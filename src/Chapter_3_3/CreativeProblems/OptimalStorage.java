package Chapter_3_3.CreativeProblems;

        import Chapter_1_3.Queue;
        import Chapter_3_2.BST;
        import edu.princeton.cs.algs4.BlackFilter;
        import edu.princeton.cs.algs4.StdDraw;
        import edu.princeton.cs.algs4.StdRandom;

        import java.awt.*;

public class OptimalStorage <Key extends Comparable<Key>, Value> {

    private Node root;
    private int N;

    private class Node{
        Key key;
        Value val;
        private Node left;
        private Node right;
        int N;

        public Node(Key key, Value value, int N){
            this.key = key;
            this.val = value;
            this.N = N;
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

                    if (isRed(t.left)) StdDraw.setPenColor(Color.RED);
                    else StdDraw.setPenColor(Color.BLACK);

                    StdDraw.line(parent_x, parent_y, child_x, child_y);
                    StdDraw.textLeft(child_x, child_y, String.valueOf(t.left.key));

                } else q.enqueue(null);

                childCount++;
                if (t.right != null) {
                    q.enqueue(t.right);
                    child_x = childCount / (Math.pow(2, d+1) + 1);

                    if (isRed(t.right)) StdDraw.setPenColor(Color.RED);
                    else StdDraw.setPenColor(Color.BLACK);

                    StdDraw.line(parent_x, parent_y, child_x, child_y);
                    StdDraw.textLeft(child_x, child_y, String.valueOf(t.right.key));
                } else q.enqueue(null);


            }
            //System.out.print("\n\n");



        }



    }

    // no need for change
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

    // no need for change
    public int depth_redblack() {
        return depth_redblack(root, 0);
    }
    private int depth_redblack(Node x, int d){
        if ((x.left == null) && (x.right == null)) return d;

        int left_depth = 0, right_depth = 0;
        if (x.left != null){
            if (!isRed(x.left)) left_depth = depth_redblack(x.left, d+1);
            else left_depth = depth_redblack(x.right, d);
        }
        if (x.right != null){
            if (!isRed(x.right)) right_depth = depth_redblack(x.right, d+1);
            else right_depth = depth_redblack(x.left, d);
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

    // function isRed is modified
    private boolean isRed(Node node){
        if (node == null) return false;
        if (node.left == null && node.right == null) return true;
        if ((node.left == null && node.right != null) || (node.left != null && node.right == null)) return true;
        if (node.left.key.compareTo(node.right.key) > 0) return true;
        return false;
    }

    // added function
    private void setToRed(Node node){
        if (node.left != null && node.right != null){
            if (node.left.key.compareTo(node.right.key) < 0){
                Node t = node.left;
                node.left = node.right;
                node.right = t;
            }
        }
    }

    // added function
    private void setToBlack(Node node){
        if (node.left != null && node.right != null){
            if (node.left.key.compareTo(node.right.key) > 0){
                Node t = node.left;
                node.left = node.right;
                node.right = t;
            }
        }
    }

    // corrected
    private Node rotateLeft(Node h){
        Node x;
        x = h.right;
        h.right = x.left;
        x.left = h;
        if (isRed(x)) setToRed(h);
        else setToBlack(h);
        setToRed(h);
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;

    }

    // corrected
    private Node rotateRight(Node h){
        Node x;
        x = h.left;
        h.left = x.right;
        x.right = h;
        if (isRed(x)) setToRed(h);
        else setToBlack(h);;
        setToRed(h);
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    // corrected
    private void flipColors(Node h){
        setToBlack(h.left);
        setToBlack(h.right);
        setToRed(h);
    }

    // corrected
    public void put(Key key, Value val)
    { // Search for key. Update value if found; grow table if new.
        root = put(root, key, val);
        // setToBlack(root);

    }

    // corrected
    protected Node put(Node x, Key key, Value val) {
// Change key’s value to val if key in subtree rooted at x.
// Otherwise, add new node to subtree associating key with val.


        if (x == null) {
            // added logic to account for the color of the parent node
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0 && !isRed(x) || cmp > 0 && isRed(x) )
            x.left = put(x.left, key, val);
        else if (cmp > 0 && !isRed(x) || cmp < 0 && isRed(x) )
            x.right = put(x.right, key, val);
        else x.val = val;




        if (!isRed(x)) {
            // original check
            if (isRed(x.right) && (!isRed(x.left))) x = rotateLeft(x);
        }
        else{
            // modified check to account for x being red
            if (isRed(x.left) && (!isRed(x.right))) x = rotateRight(x);
        }


        if (!isRed(x)) {
            // original check
            if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        }
        else{
            // modified check to account for x being red
            if (isRed(x.right) && isRed(x.right.right)) x = rotateLeft(x);
        }



        if (isRed(x.left) && isRed(x.right)) flipColors(x);



        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    private void swapKids(Node x){
        if (x != null){
            Node t = x.left;
            x.left = x.right;
            x.right = t;
        }


    }

//    public Value get(Key key) {
//        return get(root, key);
//    }
//    protected Value get(Node x, Key key)
//    { // Return value associated with key in the subtree rooted at x;
//        // return null if key not present in subtree rooted at x.
//        if (x == null) return null;
//        int cmp = key.compareTo(x.key);
//        if (cmp < 0) return get(x.left, key);
//        else if (cmp > 0) return get(x.right, key);
//        else return x.val;
//    }


    public static void main(String[] args){


        boolean DisplayTreeForValidation = true;

        OptimalStorage<Integer, Integer> bst = new OptimalStorage<>();



//            int N = 20;
//            Integer[] int_list = new Integer[N];
//            for (int i = 0; i < N; i++) {int_list[i] = StdRandom.uniform(0,10*N); }

        Integer[] int_list = {5, 10};

        for (Integer x : int_list){

            System.out.print(x + " ");
            bst.put(x, 1);

            if (DisplayTreeForValidation) {
                bst.draw();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }


            System.out.print("red-black BST depth: " +  bst.depth_redblack());
        }




    }


