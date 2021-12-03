package Chapter_1_4;

import Chapter_1_3.Stack;

public class PairsInTwoArrays {

    public static void pairsInBothArrays(int[] a, int[] b){
        int i = 0;
        int j = 0;
        int[] c = new int[a.length];

        Stack<Integer> commonValues = new Stack<Integer>();

        while (i < a.length && j < b.length){
            if (a[i] > b[j]) j++;
            else if (a[i] < b[j]) i++;
            else {
                commonValues.push(a[i]);
                i++;
                j++;
            }
        }

        // need to implement code to suppress redundant values
        // this can be done in linear time

        for (int x : commonValues)
            System.out.print(a[i] + " ");
    }


    public static void main(String[] args){

    }


}
