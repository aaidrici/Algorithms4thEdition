package Chapter_5_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class LsdWithVaryingSize {


    String[] list;

    public String[] in2StringArr(In in){
        Queue<String> q = new Queue<String>();
        while(!in.isEmpty()) q.enqueue(in.readString());
        String[] list = new String[q.size()];
        for (int i = 0; i < list.length; i++) list[i] = q.dequeue();
        return list;
    }


    public LsdWithVaryingSize(In in){
        list = in2StringArr(in);

        int maxStringSize = 0;
        for (String x : list) if (x.length() > maxStringSize) maxStringSize = x.length();

        for (int d = maxStringSize - 1; d >= 0; d--){

            int[] count = new int[127+1];

            // 1. compute frequency count
            for (int i = 0; i < list.length; i++){
                if (d < list[i].length()){
                    int c = list[i].charAt(d);
                    count[c+1]++;
                }
                else{count[1]++; } // assume smaller size string have trailing zeros.
            }

            // 2. transform count to indices
            for (int i = 0; i < count.length - 1; i++){count[i+1] += count[i]; }

            // 3. Distribute
            String[] newList = new String[list.length];
            for (int i = 0; i< list.length; i++){
                if (d < list[i].length())
                    newList[count[list[i].charAt(d)]++] = list[i];
                else
                    newList[count[0]++] = list[i];
            }

            // 4. copy back
            list = newList;

        }
    }

    public String[] sortedList(){
        return list;
    }



    public static void main(String[] args){

        // note: The implementation assumes input string are made of 7-bit ascii characters
        //       Strings with a length smaller than the max string length are assumed to have trailing `NUL` characters.

        String filename = "Chapter_5_1\\q5_1_9_input.txt";
        In in = new In(filename);
        LsdWithVaryingSize lsd = new LsdWithVaryingSize(in);

        String[] sortedList = lsd.sortedList();
        System.out.println("Sorted string list: \n");
        for (String x : sortedList) System.out.println(x);

    }

}
