package Chapter_5_3;

import Chapter_1_3.Queue;
import Chapter_3_2.CreativeProblems.PerfectBalance;
import edu.princeton.cs.algs4.In;

public class RabinKarp
{
    private boolean runCheck = true;
    private String pat; // pattern (only needed for Las Vegas)
    private long patHash; // pattern hash value
    private int M; // pattern length
    private long Q; // a large prime
    private int R = 256; // alphabet size
    private long RM; // R^(M-1) % Q
    public RabinKarp(String pat)
    {
        q = new Queue<Integer>();
        this.pat = pat; // save pattern (only needed for Las Vegas)
        this.M = pat.length();
        // Q = longRandomPrime(); // See Exercise 5.3.33.
        this.Q = 997;
        RM = 1;
        for (int i = 1; i <= M-1; i++) // Compute R^(M-1) % Q for use
            RM = (R * RM) % Q; // in removing leading digit.
        patHash = hash(pat, M);
    }
    public boolean check(int i, String text) // Monte Carlo (See text.)
    {
        for (int j = 0; j<M; j++)
            if (text.charAt(i+j) != pat.charAt(j)) return false;
        return true;
    } // For Las Vegas, check pat vs txt(i..i-M+1).

    private long hash(String key, int M)
    { // Compute hash for key[0..M-1].
        long h = 0;
        for (int j = 0; j < M; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }
    // See text (page 775).
    private int search(String txt)
    { // Search for hash match in text.
        int N = txt.length();
        long txtHash = hash(txt, M);

        int firstOccurence = N;

        if (patHash == txtHash){
            if (q.isEmpty()) firstOccurence = 0;
            q.enqueue(0); // Match at beginning.
        }

        for (int i = M; i < N; i++)
        { // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q;
            txtHash = (txtHash*R + txt.charAt(i)) % Q;
            if (patHash == txtHash)
                if (check(i - M + 1, txt)){
                    // return i - M + 1; // match
                    if (q.isEmpty()) firstOccurence = i - M + 1;
                    q.enqueue(i - M + 1);
                }
        }

        return firstOccurence; // no match found
    }


    // Added methods for Q5_3_10
    private Queue<Integer> q;
    public int count(){
        return q.size();
    }
    public Iterable<Integer> searchAll(){
        return q;
    }


    // Added method for Q5_3_25
    public int search(In in){
        int count = 0;
        Queue<Character> q = new Queue<Character>();
        long hash = 0;
        while (!in.isEmpty()){
            count++;
            char c = in.readChar();
            if (q.size() == pat.length()){
                char b = q.dequeue();
                q.enqueue(c);
                // remove leading edge
                hash = (hash+Q-(RM*b)%Q)%Q;
                // add trailing edge
                hash = (hash*R + c)%Q;
            }
            else{
                q.enqueue(c);
                // add trailing edge
                hash = (hash*R + c)%Q;
            }

            if (patHash == hash)
                return count - pat.length();
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
//        RabinKarp rk = new RabinKarp(pattern);
//        rk.search(text);
//        for (int x : rk.searchAll()) System.out.println(x);

        String filename = "Chapter_5_3\\inputText.txt";
        In in = new In(filename);
        RabinKarp rk = new RabinKarp("needle");
        System.out.println(rk.search(in));
    }

}