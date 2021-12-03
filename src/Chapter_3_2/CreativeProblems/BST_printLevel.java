package Chapter_3_2.CreativeProblems;

import Chapter_1_3.Queue;
import Chapter_3_2.BST;

public class BST_printLevel <Key extends Comparable<Key>, Value> extends BST<Key, Value> {



    public void printLevel(){
        printLevel(root);
    }
    public void printLevel(Node x){

        boolean separateLevelsWithVerticalSlash = true;

        Queue<Node> q = new Queue<Node>();
        q.enqueue(x);

        while (!q.isEmpty()){
            int n = q.size();
            for (int i = 0; i < n; i++){
                Node t = q.dequeue();
                System.out.print(t.key + " ");
                if (t.left != null) q.enqueue(t.left);
                if (t.right != null) q.enqueue(t.right);
            }
            if (separateLevelsWithVerticalSlash) System.out.print(" | ");
        }


    }


    public static void main(String[] args){

        BST_printLevel<Integer, Integer> bst = new BST_printLevel();

        Integer[] vals = {12,-5,7,0,56,-8,2,33};
        for (Integer x : vals) bst.put(x, 1);

        bst.printLevel();
    }

}
