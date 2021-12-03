package Chapter_3_2.CreativeProblems;

import Chapter_3_2.BST;
import edu.princeton.cs.algs4.StdRandom;

public class BST_cachedNode<Key extends Comparable<Key>, Value> extends BST<Key, Value> {



    private Node cachedNode = null;

    @ Override
    protected Value get(Node x, Key key)
    { // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.


        // added line for 3.2.8
        if (cachedNode != null && key == cachedNode.key) return cachedNode.val;

        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else {
            // added line for 3.2.8
            cachedNode = x;

            return x.val;
        }


    }

    @Override
    protected Node put(Node x, Key key, Value val)
    {
    // Change key’s value to val if key in subtree rooted at x.
    // Otherwise, add new node to subtree associating key with val.


        // added line for 3.2.8
        if (cachedNode != null && key == cachedNode.key) cachedNode.val = val;

        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else {
            x.val = val;

            // added line for 3.2.8
            cachedNode = x;
        }
        x.N = size(x.left) + size(x.right) + 1;

        return x;

    }



    public static void main(String[] args){

        // Experiment to prove populating a bst with the same integer input takes less time when using the caching method.


        BST_cachedNode<Integer, Integer> bst_01 = new BST_cachedNode<>();

        BST<Integer, Integer> bst_02 = new BST<Integer, Integer>();


        int N = 1000000; // number of elements in the binary search tree.
        Integer[] int_list = new Integer[N];
        for (int i = 0; i<N; i++) int_list[i] = StdRandom.uniform(0,10*N);


        long time01 = System.currentTimeMillis();
        for (Integer x : int_list) {
            bst_01.put(x,x);
        }
        long time02 = System.currentTimeMillis();
        System.out.printf("Time taken without caching : %d ms\n", time02-time01);

        time01 = System.currentTimeMillis();
        for (Integer x : int_list) {
            bst_02.put(x,x);
        }
        time02 = System.currentTimeMillis();
        System.out.printf("Time taken with caching : %d ms\n", time02-time01);






        bst_01.put(48,30);
        System.currentTimeMillis();



    }


}
