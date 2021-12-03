package Chapter_5_3;


import Chapter_1_3.Queue;

public class BoyerMoore
{
    private int[] right;
    private String pat;
    BoyerMoore(String pat)
    { // Compute skip table.
        q = new Queue<Integer>();
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1; // -1 for chars not in pattern
        for (int j = 0; j < M; j++) // rightmost position for
            right[pat.charAt(j)] = j; // chars in pattern
    }
    public int search(String txt)
    { // Search for pattern in txt.
        int N = txt.length();
        int M = pat.length();
        int skip;

        int firstOccurence = txt.length();

        for (int i = 0; i <= N-M; i += skip)
        { // Does the pattern match the text at position i ?
            skip = 0;
            for (int j = M-1; j >= 0; j--)
                if (pat.charAt(j) != txt.charAt(i+j))
                {
                    skip = j - right[txt.charAt(i+j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            if (skip == 0){
                if (q.isEmpty()) firstOccurence = i;
                q.enqueue(i);
                skip = 1;
                //return i; // found.
            }
        }
        return firstOccurence; // not found.
    }


    // added for Q5_3_9
    Queue<Integer> q;
    public int count(){
        return q.size();
    }
    public Iterable<Integer> searchAll(){
        return q;
    }



    public static void main(String[] args){
        String pattern = "needle"; // this example is a simple example.
        String text = "needless to say, this needle contains smaller needles but not like any other needle.";

        String pattern2 = "AABAAB"; // this example proves it works for overlapping patterns within the text.
        String text2 = "ABAABAABAAB";

        BoyerMoore bm = new BoyerMoore(pattern);
        bm.search(text);
        for (int x : bm.searchAll()) System.out.println(x);
    } // See page 769.
}
