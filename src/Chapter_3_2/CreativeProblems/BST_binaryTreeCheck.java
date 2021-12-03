package Chapter_3_2.CreativeProblems;

import Chapter_3_2.BST;

import java.util.ArrayList;

public class BST_binaryTreeCheck <Key extends Comparable<Key>, Value> extends BST<Key, Value> {



    public boolean countCheck(){
        return countCheck(root);
    }

    private boolean countCheck(Node node){

        if (node == null) return true;

        int count = 1;
        if (node.left != null) count += node.left.N;
        if (node.right != null) count += node.right.N;

        if (node.N != count) return false;

        return countCheck(node.left) && countCheck(node.right);

    }



    public static void main(String[] args){


        BST_binaryTreeCheck<Integer,Integer> bst = new BST_binaryTreeCheck<>();

        ArrayList<Integer> content = new ArrayList<Integer>();
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


        System.out.print(bst.countCheck());

    }

}
