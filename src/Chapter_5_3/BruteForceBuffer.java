package Chapter_5_3;

import Chapter_1_3.Queue;
import edu.princeton.cs.algs4.In;

public class BruteForceBuffer {


    public static int search(In in, String pat){
        Queue<Character> q = new Queue<Character>();
        int j = 0;
        int count = 0;
        while(!in.isEmpty()){
            count++;
            char c = in.readChar();
            if (c == pat.charAt(j)){
                j++;
                q.enqueue(c);
                if (j == pat.length()) return count - pat.length();
            }
            else{
                j = 0;
                while(!q.isEmpty()) q.dequeue();
            }
        }

        return -1;

    }

    public static void main (String[] args){
        String filename = "Chapter_5_3//inputText.txt";
        In in = new In(filename);
        int ans = BruteForceBuffer.search(in, "test");
        System.out.println(ans);
    }

}
