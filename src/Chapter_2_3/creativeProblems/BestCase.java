package Chapter_2_3.creativeProblems;

import Chapter_1_4.BinarySearch;
import Chapter_2_3.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;

public class BestCase {




    public static int medianOfArray(Comparable[] a){
        // There are algorithms that can find the median in ~n time.
        // For simplicity sake, let's just use quicksort ~nlogn

        Comparable[] a_copy = new Comparable[a.length];
        for (int i = 0; i<a.length; i++) a_copy[i] = a[i];
        Quick.sort(a_copy);
        Comparable median = a_copy[(a_copy.length - 1) / 2];

        return 10;
    }




    public static void main(String[] args){

        // generate random array then sort it
        int N = 12;
        Integer[] a = new Integer[N];
        for (int i = 0; i<N; i++)
            a[i] = StdRandom.uniform(0,5*N);
        Quick.sort(a);

        // re-order it such that quick sort will always partition its (sub-)arrays exactly in the middle.
        ArbreBinaire tree = new ArbreBinaire(a);
        Integer[] optimalArray = tree.GenerateOptimalQuickSortArray();
        for (Integer x : optimalArray) System.out.print(x + " ");
    }

}




class ArbreBinaire{

    private Node summitNode;
    private int N = -1;
    private int numberAssignment = 0;
    private Integer[] array;

    public ArbreBinaire(Integer[] a){
        summitNode = new Node();
        summitNode.size = a.length;
        this.pushdown(summitNode);
        array = a;
        this.populateIds();

    }

//    public Node LowestCommonNode(Node a, Node b){
//        // make sure node a and b are on the same level
//    }


    public Integer[] GenerateOptimalQuickSortArray(){
        fillArray(summitNode);
        return summitNode.content;
    }

    public void fillArray(Node a){

        if (a == null) return;

        fillArray(a.downLeft);
        fillArray(a.downRight);

        a.content = new Integer[a.size];

        int i;
        int count = 0;
        if (a.downLeft != null){
            for (i = 0; i < a.downLeft.size; i++)
                a.content[count++] = a.downLeft.content[i];
            a.downLeft.content = null;
        }

        a.content[count++] = a.key;

        if (a.downRight != null){
            for (i = 0; i<a.downRight.size; i++)
                a.content[count++] = a.downRight.content[i];
            a.downRight.content = null;
        }

        // swap median item with first item on the array, unless it is the case already
        if (a.downLeft != null){
            Integer temp = a.content[0];
            a.content[0] = a.key;
            a.content[a.downLeft.size] = temp;

            // Further entropy can be added by swapping elements across the median ...
            //
            //  for simplicity sake & because it was not asked for, this won't be implemented.
            //
        }


    }


    public void populateIds(){
        populateIds(summitNode);
    }
    public void populateIds(Node a){

        if (a == null) return;
        if (a.size == 1){
            a.id = numberAssignment++;
            a.key = array[a.id];
            return;
        }

        populateIds(a.downLeft);
        a.id = numberAssignment++;
        a.key = array[a.id];
        populateIds(a.downRight);
    }


    public void pushdown(){
        pushdown(summitNode);
    }

    public void pushdown(Node a){

        if (a.size <= 1) {
            return;
        }

        int valueOnLeft = (a.size - 1)/2;
        if (valueOnLeft == 0)
            a.downLeft = null;
        else{
            a.downLeft = new Node();
            a.downLeft.size = valueOnLeft;
            a.downLeft.up = a;
            pushdown(a.downLeft);
        }

        int valueOnRight = a.size - (a.size - 1)/2;
        if (valueOnRight == 0)
            a.downRight = null;
        else{
            a.downRight = new Node();
            a.downRight.size = (a.size-1) - (a.size - 1)/2;
            a.downRight.up = a;
            pushdown(a.downRight);
        }
    }


    public void printTree(){

        boolean exitFlag;
        int level = 0;
        while(true){
            exitFlag = printTree(summitNode, level++);
            System.out.println();
            if (exitFlag == true) break;
        }

    }
    public boolean printTree(Node a, int c){

        boolean bottomMostLevel = false;

        if(c == 0) {
            if (a == null){
                System.out.print(" _ ");
            }
            else{
                System.out.print(a.key + " ");
                if (a.downLeft == null && a.downRight == null) bottomMostLevel = true;
            }
            return bottomMostLevel;
        }

        boolean bool1, bool2;
        bool1 = printTree(a.downLeft, c-1);
        bool2 = printTree(a.downRight, c-1);
        return bool1 || bool2;

    }


    private class Node{
        Node up;
        Node downLeft = null;
        Node downRight = null ;
        int size; // size of node
        int id; // position of its key in the ordered array
        int key; // element of  array a.k.a median
        Integer[] content;
    }


}