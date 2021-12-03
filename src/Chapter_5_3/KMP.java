package Chapter_5_3;

import Chapter_1_3.Queue;
import edu.princeton.cs.algs4.In;

public class KMP
{
    private String pat;
    private int[][] dfa;
    public KMP(String pat)
    { // Build DFA from pattern.
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++)
        { // Compute dfa[][j].
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][X]; // Copy mismatch cases.

            // bottom line modified to ensure the multiple overlapping patterns can be obtained.
            // dfa[pat.charAt(j)][j] = j+1; // Set match case.
            if (j+1 != M) dfa[pat.charAt(j)][j] = j+1;

            X = dfa[pat.charAt(j)][X]; // Update restart state.
        }
    }
    public int search(String txt)
    { // Simulate operation of DFA on txt.

        char lastPatChar = pat.charAt(pat.length()-1);
        int firstOccurence = txt.length();

        q = new Queue<Integer>();
        int i, j, N = txt.length(), M = pat.length();
        for (i = 0, j = 0; i < N; i++) {

            char nextChar = txt.charAt(i);

            if (j == M-1 && nextChar == lastPatChar){
                // search hit
                q.enqueue(i - j);
                if (q.isEmpty()) firstOccurence = i - j;
            }

            j = dfa[nextChar][j];
        }
        return firstOccurence; // not found (hit end of text)
    }

    // added for Q5_3_8
    private Queue<Integer> q;
    public Iterable<Integer> searchAll(){
        return q;
    }
    public int count(){
        return q.size();
    }

    // added for Q5_3_25
    public int search(In in){
        int x = 0;
        int count = 0;
        while (!in.isEmpty()){
            int c = in.readChar();
            count++;
            if (x == pat.length() - 1 && c == pat.charAt(pat.length()-1)) return count - pat.length();
            x = dfa[c][x];
        }
        return -1;

    }



    public static void main(String[] args){

//        String pattern = "needle"; // this example is a simple example.
//        String text = "needless to say, this needle contains smaller needles but not like any other needle.";
//
//        String pattern2 = "AABAAB"; // this example proves it works for overlapping patterns within the text.
//        String text2 = "ABAABAABAAB";
//
//        KMP kmp = new KMP(pattern2);
//        kmp.search(text2);
//        for (int x : kmp.searchAll()) System.out.println(x);
        String filename = "Chapter_5_3\\inputText.txt";
        In in = new In(filename);
        KMP kmp = new KMP("needle");
        System.out.println(kmp.search(in));

    }

 }