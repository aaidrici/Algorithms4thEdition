package Chapter_2_5.Exercises;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import Chapter_2_4.MaxPQ;

import java.util.Arrays;

public class CompoundWords{


    private static boolean less(String v, String w)
    { return v.compareTo(w) < 0; }


    public static boolean find(String key, String[] a){
        int lo = 0;
        int hi = a.length-1;
        int mid = (hi - lo)/2;
        while (lo <= hi){
            mid = (hi + lo)/2;

            if (a[mid].compareTo(key) == 0) return true;

            if (less(key,a[mid]))
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        return false;
    }


    public static boolean HaveCommonSubstring(String a, String b){
        if (a.length() < b.length())
            return b.substring(0, a.length()).compareTo(a) == 0;
         else
            return a.substring(0, b.length()).compareTo(b) == 0;
    }

    public static String CommonSubstring(String a, String b){
        if (a.length() < b.length())
            return b.substring(a.length());
        else
            return a.substring(b.length());

    }



    public static void main(String[] args){

        int N = 1000;
        MaxPQ<String> pq = new MaxPQ<String>(N);

        while (!StdIn.isEmpty())
            pq.insert(StdIn.readString());


        String[] words = new String[pq.size()];
        int j = 0;
        while(!pq.isEmpty())
            words[j++] = pq.delMax();
        Arrays.sort(words);


        for (int i = 0; i < words.length - 1; i++){
            String a = words[i];
            String b = words[i+1];
            if (HaveCommonSubstring(a,b))
                if (find(CommonSubstring(a,b),words))
                    if (a.length() > b.length())
                        System.out.println(a + " is a compounded word");
                    else
                        System.out.println(b + " is a compounded word");


        }


    }
}

// java -cp .;algs4.jar;src Chapter_2_5.Exercises.CompoundWords < src\Chapter_2_5\wordList.txt
