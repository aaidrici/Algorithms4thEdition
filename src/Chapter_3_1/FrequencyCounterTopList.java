package Chapter_3_1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;



public class FrequencyCounterTopList {






    public static void main (String[]args) {


        Queue<String> qVals = new Queue<String>();

        int minlen = Integer.parseInt(args[0]); // key-length cutoff
        ST<String, Integer> st = new ST<String, Integer>();
        while (!StdIn.isEmpty()) { // Build symbol table and count frequencies.
            String word = StdIn.readString();
            if (word.length() < minlen) continue; // Ignore short keys.
            if (!st.contains(word)) st.put(word, 1);
            else {
                st.put(word, st.get(word) + 1);
            }
        }

        // Find a key with the highest frequency count.
        String max = "";
        st.put(max, 0);
        qVals.enqueue(max);

        for (String word : st.keys())
            if (st.get(word) > st.get(max)){ // new max, queue must be emptied
                max = word;
                while(!qVals.isEmpty()) qVals.dequeue();
                qVals.enqueue(word);
            }
            else if (st.get(word).equals(st.get(max)))
                qVals.enqueue(word);


            while(!qVals.isEmpty()){
                String word = qVals.dequeue();
                StdOut.println(word + " " + st.get(word));
            }

    }


}


