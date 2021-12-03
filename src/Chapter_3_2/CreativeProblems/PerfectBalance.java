package Chapter_3_2.CreativeProblems;

import Chapter_3_2.BST;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class PerfectBalance<Key extends Comparable<Key>, Value> extends BST<Key, Value>{



    public static int power(int a, int b){
        int result = 1;
        for (int i = 0; i < b; i++) result *= a;
        return result;
    }






    public BST<Key, Integer> ArrayToBalancesBST(BST<Key, Integer> bst, ArrayList<Key> vals, int cp, int height){

        bst.put(vals.get(cp), null);

        if (height <= 1) return bst;

        int jump = power(2,(height-2))-1;

        if (cp + jump + 1 < vals.size() && vals.get(cp + jump + 1) != null)
            bst = ArrayToBalancesBST(bst, vals, cp + jump + 1, height - 1);

        if (cp - jump - 1 >= 0 && vals.get(cp - jump - 1) != null)
            bst = ArrayToBalancesBST(bst, vals, cp - jump - 1, height - 1);



        return bst;
    }



    public BST<Key, Integer> ArrayToBalancedBST(ArrayList<Key> vals){

        // initialize the binary search tree that will be populated.
        BST<Key, Integer> bst = new BST<Key, Integer>();

        // determine the characteristics of the bst ahead.
        int height = 0; //
        while (power(2, height) - 1 < vals.size()){ height++; }

        int centerPos = vals.size() / 2 ;

        int maxSize = power(2,height) - 1;

        int missingNodesAtTheBottom = maxSize - vals.size();

        // pre-condition the input array - put null items in the right cells.
        ArrayList<Key> vals_preconditioned =  new ArrayList<Key>();

        int j = 0;
        int k = 0;
        while (j < maxSize){
            if (missingNodesAtTheBottom > 0){
                vals_preconditioned.add(j++, null);
                missingNodesAtTheBottom--;
            }
            vals_preconditioned.add(j++, vals.get(k++));
        }

        for (int i = 0; i<maxSize; i++)
            System.out.print(vals_preconditioned.get(i) + " ");

        // call the recursive method such that converts the preconditioned arrayList into a balance BST.

        return ArrayToBalancesBST(bst, vals_preconditioned, centerPos, height);

    }


    public static void main(String[] args) {

        PerfectBalance<Integer, String> bst = new PerfectBalance<>();

        // start with an initial array of Keys that's random
        int N = 14;
        ArrayList<Integer> keys = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) keys.add(StdRandom.uniform(0,N*100));

        Collections.sort(keys);

        bst.ArrayToBalancedBST(keys);

        System.out.println();

    }

}
