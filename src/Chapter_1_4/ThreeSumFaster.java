package Chapter_1_4;


import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class ThreeSumFaster {


    int[] a;

    public ThreeSumFaster(int[] intputArray){
        a = Arrays.copyOf(intputArray,intputArray.length);
        Arrays.sort(a);
    }

    public int applyThreeSumFaster(){
        // assume values are distinct

        if (a[0] > 0 || a[a.length-1] < 0 || a.length < 3) return 0;

        int incr = 0;

        for (int id_1 = 0; id_1 < a.length-2; id_1++){

            if (a[id_1] > 0) break;

            // position id_2 and id_3 to their initial position
            int id_2 = id_1+1;
            int id_3 = id_1+2;

            int tempsum = Math.abs(a[id_1] + a[id_2] + a[id_3]);

            while (id_3 < a.length-1){
                if (Math.abs(a[id_1] + a[id_2+1] + a[id_3+1]) <= tempsum ){
                    id_2++;
                    id_3++;
                    tempsum = Math.abs(a[id_1] + a[id_2] + a[id_3]);
                }
                else
                    break;
            }

            // sweep through array to see if there a sum that equates zero
            int sum;
            int repeatedValuesOnLeft = 0;
            int repeatedValuesOnRight = 0;

            while (id_1 < id_2  &&  id_3 < a.length){
                repeatedValuesOnLeft = 0;
                repeatedValuesOnRight = 0;

                //System.out.print("id1 = " + id_1 + ", id2 = " + id_2 + ", id3 = " + id_3 );
                sum = a[id_1] + a[id_2] + a[id_3];
                if (0 == sum){
                    int id2val = a[id_2];
                    int id3val = a[id_3];
                    do {
                        id_2 --;
                        repeatedValuesOnLeft ++;
                        if (id_2 == id_1) break;
                    } while (a[id_1] + a[id_2] + a[id_3] == 0);
                    do {
                        id_3 ++;
                        repeatedValuesOnRight ++;
                        if (id_3 == a.length) break;
                    } while (a[id_1] + a[id_2] + a[id_3] == 0);

                    if(id2val == id3val) {
                        int N = repeatedValuesOnRight + repeatedValuesOnLeft;
                        incr += N*(N+1) / 2;
                    }
                    else incr += (repeatedValuesOnRight * repeatedValuesOnLeft);
                }
                else if (sum > 0)
                    id_2--;
                else
                    id_3++;

                //System.out.println(" incr=" + incr);
            }
        }
        return incr;
    }


    public static void main(String[] args){

        String inputFileName = "src/Chapter_1_4/inputFiles/Q15input.txt";
        In file = new In(inputFileName);
        int[] a = file.readAllInts();
        Arrays.sort(a);
        for ( int x : a)
            System.out.print(x + " ");
        System.out.println();

        ThreeSumFaster threeSumFaster = new ThreeSumFaster(a);
        int numberOfThreesum = threeSumFaster.applyThreeSumFaster();
        System.out.print(numberOfThreesum);
    }
}
