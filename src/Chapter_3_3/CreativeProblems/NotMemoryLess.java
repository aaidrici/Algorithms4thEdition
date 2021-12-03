package Chapter_3_3.CreativeProblems;

import Chapter_3_2.CreativeProblems.PerfectBalance;
import Chapter_3_3.RedBlackBST;

public class NotMemoryLess {




    public static void main(String[] args){

        RedBlackBST<Integer,Integer> bst = new RedBlackBST<Integer,Integer>();


        Integer[] int_list = {10,4,24,75,1,-5,-40,-27,32};

        for (Integer x : int_list)bst.put(x, 0);


        bst.draw();

        boolean yes = true;

        if (yes)
            bst.draw();
        else {
            bst.put(-100,0);
            bst.deleteMin();
            bst.draw();
        }

        // {10,4,24,75,1,-5,-40,-27,32} is an example of sequence that will generate two different
        // redblack bst if a smaller value is added and removed afterwards. This is caused by the
        // mechanism where a node of the right child is transferred to the left child when the left
        // child is a 2-node.




    }

}
