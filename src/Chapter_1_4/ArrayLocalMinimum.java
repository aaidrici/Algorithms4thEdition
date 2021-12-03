package Chapter_1_4;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class ArrayLocalMinimum {


    static int ArrayLocalMin(int[] a){


        if (a.length > 2){
            int mid = (a.length-1)/2;
                if (a[mid] < a[mid+1] && a[mid] < a[mid-1])
                    return a[mid];
                else{
                    int[] a_halved;

                    if (a[mid-1] < a[mid])
                        a_halved = Arrays.copyOfRange(a,0,mid);
                    else
                        a_halved = Arrays.copyOfRange(a,mid+1,a.length);
                    return ArrayLocalMin(a_halved);
                }

        }
        else if(a.length == 2){
            if (a[0] < a[1]) return a[0];
            else return a[1];
        }
        else
            return a[0];

    };


    public static void main(String[] args){

        String filename = "src/Chapter_1_4/inputFiles/Q18.txt";
        In file = new In(filename);
        int[] intValues = file.readAllInts();
        for (int x : intValues) System.out.print(x + " ");
        System.out.println();
        System.out.print(ArrayLocalMin(intValues));
    }


}
