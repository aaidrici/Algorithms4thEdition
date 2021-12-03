package Chapter_3_5.CreativeProblems;

import Chapter_3_2.BST;
import Chapter_3_3.RedBlackBST;

public class NonOverlappingIntervals {



    RedBlackBST<Integer, Boolean> interval_bst;

    public NonOverlappingIntervals(){interval_bst = new RedBlackBST<Integer, Boolean>();}

    public void addInterval(int LeftBound, int rightBound){
        interval_bst.put(LeftBound, true);
        interval_bst.put(rightBound, false);

        // Note, extra protection could be added to ensure `LeftBound` and `rightBound` are not
        // allowed entries when they lie on an existing interval.

    }

    public boolean LiesOnAnInterval(int x){
        // if it lies within an interval, floor(x) should return a node holding a value of true OR return no value at all
        Integer b = interval_bst.floor(x);
        if (b == null) return false;
        else return interval_bst.get(b);
    }

    public static void main(String[] args){

        NonOverlappingIntervals bst = new NonOverlappingIntervals();

        Integer[] leftBounds = {10,30,50}; // left bounds are inclusive in the interval
        Integer[] rightBounds = {20,40,60}; // right bounds are excluded in the interval

        for (int i = 0; i<3; i++) bst.addInterval(leftBounds[i], rightBounds[i]);

        for (int i = 0; i<80; i++){
            System.out.println(i + ": " + bst.LiesOnAnInterval(i));
        }




    }

}

