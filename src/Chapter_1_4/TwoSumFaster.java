package Chapter_1_4;


import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class TwoSumFaster {


    int[] a;

    public TwoSumFaster(int[] intputArray){
        a = Arrays.copyOf(intputArray,intputArray.length);
        Arrays.sort(a);
    }

    public static int count(int[] a){

        if (a.length < 3) return 0;

        int count = 0;
        int id1 = 0, id2 = 1;

        // find the adjacent pair with a sum closest to zero;
        int SmallestSum = Math.abs(a[id1]+a[id2]);
        while(id2 < a.length){
            if (Math.abs(a[id1] + a[id2]) <= SmallestSum){
                SmallestSum = Math.abs(a[id1] + a[id2]);
                id1++;
                id2++;
            }
            else break;
        }
        //System.out.print("adjacent pair closes to summing to zero: " + id1 + " " + id2);
        System.out.println();

        while (id1 > -1 && id2 < a.length){
            //.out.println("id1/id2 = " + id1 + " " + id2);
            if (a[id1] + a[id2] == 0){
                count ++;
                id1 --;
                id2 ++;
            }
            else if (a[id1] + a[id2] > 0) id1--;
            else id2 ++;
        }
        return count;
    }




    public static void main(String[] args){

        int size =  100;
        int[] a = new int[size];
        for (int i = 0; i<a.length; i++) a[i] = StdRandom.uniform(-100, 100);


        Arrays.sort(a);
        for (int x : a) System.out.print(x + " ");
        System.out.println();
        System.out.print(TwoSumFaster.count(a));


    }
}

//
//    public static int count(int[] a){
//        // assume we look for distinct pairs - i.e. identical pairs are not counted twice
//
//        if (a[0] > 0 || a[a.length-1] < 0 || a.length < 2) return 0;
//
//        // 1st: find the id with cell value closest to zero
//        int idClosest2zero = 0;
//        for (int i = 1; i<a.length; i++)
//            if (Math.abs(a[i]) < Math.abs(a[idClosest2zero]))
//                idClosest2zero = i;
//
//        // if the value closest to zero has multiple instances, its leftmost instance is picked.
//        int negVal_id = idClosest2zero;
//        int posVal_id = idClosest2zero + 1;
//        int val;
//        int incr = 0;
//        while (negVal_id >= 0 ||  posVal_id < a.length){
//            if (negVal_id == -a[posVal_id]){
//                incr ++;
//                val = a[negVal_id];
//                while (a[negVal_id] == val && negVal_id >= 0) negVal_id--;
//                while (a[posVal_id] == val && posVal_id < a.length) posVal_id++;
//            }
//            else{
//                if (-a[negVal_id] < a[posVal_id]) negVal_id--;
//                else posVal_id++;
//            }
//        }
//        return incr;
//    }
