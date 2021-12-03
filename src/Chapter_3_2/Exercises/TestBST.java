package Chapter_3_2.Exercises;

import Chapter_3_2.BST;

import java.util.Arrays;

public class TestBST {


    public static void main(String[] args){


        BST<Integer, Integer> bst = new BST<Integer, Integer>();

        Integer[] inputSequence = {10,32,22,20,5,-2,40,21};

        Integer[] sortedInputSequence = Arrays.copyOf(inputSequence,inputSequence.length);
        Arrays.sort(sortedInputSequence);


        int i = 0;
        for (int x : inputSequence) bst.put(x, i++);

        boolean OneOfTheTestHasFailed = false;

        // testing min()
        if (bst.min() != -2){
            OneOfTheTestHasFailed = true;
            System.out.println("Min() test failed");
        }

        // testing max()
        if (bst.max() != 40){
            OneOfTheTestHasFailed = true;
            System.out.println("Min() test failed");
        }

        // testing floor()
        if(bst.floor(11) != 10) {
            OneOfTheTestHasFailed = true;
            System.out.println("Floor() test failed");
        }

        // testing ceiling()
        if (bst.ceiling(37) != 40) {
            System.out.println("Ceiling() test failed");
            OneOfTheTestHasFailed = true;
        }

        // testing select()
        if (bst.select(2).compareTo(10) != 0) {
            System.out.println("select() test failed");
            OneOfTheTestHasFailed = true;
        }

        // testing rank()
        if (bst.rank(21) != 4) {
            System.out.println("rank() test failed");
            OneOfTheTestHasFailed = true;

        }

        // testing delete()
        //bst.delete(10);


        // testing deleteMin()
        Integer prevMinVal = bst.min();
        bst.deleteMin();
        if (bst.min().compareTo(prevMinVal) == 0){
            System.out.println("deleteMin() test failed");
            OneOfTheTestHasFailed = true;
        }
        bst.put(prevMinVal,1);


        // testing deleteMax()
        Integer prevMaxVal = bst.max();
        bst.deleteMax();
        if (bst.max().compareTo(prevMaxVal) == 0){
            System.out.println("deleteMax() test failed");
            OneOfTheTestHasFailed = true;
        }
        bst.put(prevMaxVal,2);


        // testing keys()
        int j = 0;
        for (Integer x : bst.keys()) {
            if (x.compareTo(sortedInputSequence[j++]) != 0){
                OneOfTheTestHasFailed = true;
                System.out.print("keys() test has failed");
                break;
            }
        }



        if (!OneOfTheTestHasFailed)
            System.out.print("All tests were successful");


    }
}
