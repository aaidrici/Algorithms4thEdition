package Chapter_1_4;

import java.util.Arrays;

public class BinarySearchWithAdditionAndSubtraction {


    private int[] a;

    public BinarySearchWithAdditionAndSubtraction(int[] keys) {
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++)
            a[i] = keys[i]; // defensive copy
        Arrays.sort(a);
    }

    public boolean contains(int key)
    { return rank(key) != -1; }

    private int rank(int key) {

        int leftBound = 0;
        int rightBound = a.length-1;

        int B = 0;
        int A = 1;
        int ind = 0;

        while (leftBound <= rightBound){
            ind = leftBound + A - 1;


            if (ind > rightBound){
                leftBound = leftBound + B; // we already know a[leftBound + B - 1] != key does not equal key
                B = 0;
                A = 1;
                continue;
            }
            else if (a[ind] > key){
                leftBound = leftBound + B; // we already know a[leftBound + B - 1] != key does not equal key
                rightBound = ind-1;
                B = 0;
                A = 1;
                continue;
            }
            else if (a[ind] == key) return ind;

            B = A;
            A = A + A;
            //System.out.println(leftBound + ":" + rightBound + "... " + "A=" + A + " B=" + B);
        }
        return -1;
    }



    public static void main(String[] args){

        int[] a = {1,2,4,4,5,6,7,10,11,15,16};
        BinarySearchWithAdditionAndSubtraction A = new BinarySearchWithAdditionAndSubtraction(a);
        System.out.print(A.rank(10));


    }
}


