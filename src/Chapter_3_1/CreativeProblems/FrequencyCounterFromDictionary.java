package Chapter_3_1.CreativeProblems;

// try to finish this later.

import Chapter_3_1.ArrayST;
import Chapter_3_1.BinarySearchST;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.Scanner;

public class FrequencyCounterFromDictionary {



    public static void main(String[] args) throws Exception{


        String filename = "D:\\messages.txt";
        //String filename = args[0];

        System.out.print(filename);

        File thisFile = new File(filename);
        Scanner thisScanner = new Scanner(thisFile);

        String temp;

        ArrayST<String, Integer> st_unordered = new ArrayST<String, Integer>(20);
        BinarySearchST<String,Integer> st_ordered = new BinarySearchST<String, Integer>(20);

        class entry implements Comparable<entry>{
            Integer count;
            String word;
            public int compareTo(entry that){
                return this.count - that.count;
            }
        }

        MinPQ<entry> pq;

        while (thisScanner.hasNext()){
            temp = thisScanner.next();
            if (st_unordered.contains(temp)){
                int occurence = st_unordered.get(temp);
                st_ordered.put(temp, occurence+1);
                st_unordered.put(temp, occurence+1);
            }
            else{
                st_ordered.put(temp, 1);
                st_unordered.put(temp, 1);
            }
        }

        // print both tables:
        System.out.println("Displayed in order read");
        st_unordered.display();

        System.out.println("Displayed in order of occurence");
        st_ordered.display();
    }
}

