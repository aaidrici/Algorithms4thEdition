package Chapter_3_2.CreativeProblems;

import Chapter_1_3.Queue;
import Chapter_3_2.BST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.lang.Math;

public class BST_TreeDrawing<Key extends Comparable<Key>, Value> extends BST<Key, Value> {


    public void draw() {


        Double topAndBottomMarginRatio = 0.05;
        Double sideMarginRatio = 0.05;

        StdDraw.setCanvasSize(500, 500);
        //StdDraw.line(0,0,0.5,0.5);

        StdDraw.setYscale(-topAndBottomMarginRatio, 1 + topAndBottomMarginRatio);
        StdDraw.setXscale(-sideMarginRatio, 1 + sideMarginRatio);



        StdDraw.point(0, 0);
        StdDraw.point(1, 1);


        Queue<Node> q = new Queue<Node>();
        q.enqueue(root);

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
                    StdDraw.line(parent_x, parent_y, child_x, child_y);
                } else q.enqueue(null);

                childCount++;
                if (t.right != null) {
                    q.enqueue(t.right);
                    child_x = childCount / (Math.pow(2, d+1) + 1);
                    StdDraw.line(parent_x, parent_y, child_x, child_y);
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



    public static void main(String[] args){

        BST_TreeDrawing<Integer, Integer> bst = new BST_TreeDrawing<>();

        int N = 100; // number of elements in the binary search tree.
        Integer[] int_list = new Integer[N];
        for (int i = 0; i<N; i++) int_list[i] = StdRandom.uniform(0,10*N);

        //Integer[] int_list = {12,-5,7,0,56,-8,2,33};
        //Integer[] int_list = {0, -1, 1};

        for (Integer x : int_list) bst.put(x, 1);


        bst.draw();


    }

}
