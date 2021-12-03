package Chapter_3_2.Experiements;

import Chapter_3_2.BST;
import edu.princeton.cs.algs4.StdRandom;

public class BST_height<Key extends Comparable<Key>, Value> extends BST<Key, Value> {


    public int depth(){
        return bst_depth(root, 0);
    }
    private int bst_depth(BST.Node x, int d){
        if ((x.left == null) && (x.right == null)) return d;

        int left_depth = 0, right_depth = 0;
        if (x.left != null) left_depth = bst_depth(x.left, d+1);
        if (x.right != null) right_depth = bst_depth(x.right, d+1);

        if (left_depth > right_depth) return left_depth;
        else return right_depth;
    }

    public static void main(String[] args){


        int trial_num = 1000;
        Integer[] N = {10000, 100000};

        //Integer[] N = {10000};

        for (Integer n : N){

            BST_height<Integer,Integer> bst = new BST_height<>();

            double height_count = 0;

            for (int trial_i = 0; trial_i < trial_num; trial_i++){

                // populate bst with 'n' random values.
                for (int j = 0; j<n; j++) bst.put(StdRandom.uniform(0,n), 1);

                height_count += bst.depth();

            }

            System.out.printf("for n = %d \n",n);
            System.out.printf("mean depth = %.3f\n", height_count/trial_num);
            System.out.printf("2.99Lg(N) = %f\n\n", Math.log(n)*2.99);


        }
    }
}
