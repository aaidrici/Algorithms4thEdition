package Chapter_3_2.Exercises;

import Chapter_3_2.BST;
import edu.princeton.cs.algs4.StdRandom;

public class BSTwithRandomKey<Key extends Comparable<Key>, Value> extends BST<Key, Value> {





    public Key RandomKey(){
        int k = StdRandom.uniform(0, root.N);
        return select(0);
    }


    public static void main(String[] args){

    }
}
