package Chapter_3_2.CreativeProblems;


import Chapter_3_2.BST;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

public class BST_isOrdered<Key extends Comparable<Key>, Value> extends BST<Key, Value>{



    public boolean orderCheck(Key min, Key max){
        return orderCheck(root, min, max);
    }


    private boolean orderCheck(Node node, Key min, Key max){

        if ((node == null) || (node.left == null && node.right == null))
            return true;

        if(node.left != null && node.left.key.compareTo(min) < 0) return false;

        if(node.right != null && node.right.key.compareTo(max) > 0) return false;

        return orderCheck(node.left, min, max) && orderCheck(node.right, min, max);

    }



    public static void main(String[] args){

        BST_isOrdered<Integer, Integer> bst = new BST_isOrdered<Integer,Integer>();

        // populate Binary search tree
        int N = 10;

        ArrayList<Integer> content = new ArrayList<>();


        for (Integer x : content) bst.put(x, 0);

        System.out.print(bst.orderCheck(-3, 110));

    }

}
