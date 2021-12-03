package Chapter_3_2.Exercises;

import Chapter_3_2.BST;

public class BSTwithAvgCompares<Key extends Comparable<Key>, Value> extends BST<Key,Value> {



    // method one is recursive
     public double avgNumberOfCompares(){
         int sumOfAllNodeDepth = sumOfAllNodeDepth(root);
         return 1 + (double) sumOfAllNodeDepth / size();
     }

     public int sumOfAllNodeDepth(Node x){
         if (x == null) return 0;
         if (x.N == 1) return 0;


         int sum = 0;
         if (x.left != null) sum++;
         if (x.right != null) sum++;
         sum += sumOfAllNodeDepth(x.left) + sumOfAllNodeDepth(x.right);
         return sum;

     }





    public static void main(String[] args){

        BSTwithAvgCompares<Integer, Integer> bst = new BSTwithAvgCompares<Integer,Integer>();

        //Integer[] inputSequence = {2,6,3,10,-2,14,15};
        Integer[] inputSequence = {2,6,-2, 10};
        int i = 0;
        for (int x : inputSequence)
            bst.put(x, ++i);


        System.out.print(bst.sumOfAllNodeDepth(bst.root));

    }


}
