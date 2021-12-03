package Chapter_5_2;

import Chapter_1_3.Queue;

import java.awt.geom.Point2D;
import java.util.Arrays;

public class TrieST<Value>
{
    private static int R = 256; // radix
    private Node root; // root of trie
    private static class Node
    {
        private Object val;
        private Node[] next = new Node[R];
        private int size;  // needed for eager implementation of size()
    }
    public Value get(String key)
    {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }
    private Node get(Node x, String key, int d)
    { // Return value associated with key in the subtrie rooted at x.
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        return get(x.next[c], key, d+1);
    }
    public void put(String key, Value val)
    { root = put(root, key, val, 0); }

    private Node put(Node x, String key, Value val, int d)
    { // Change value associated with key if in subtrie rooted at x.
        if (x == null) {
            x = new Node();
            x.size = 0;
        }
        if (d == key.length()) {
            if (x.val == null) x.size += 1;
            x.val = val;
            return x; }
        char c = key.charAt(d); // Use dth key char to identify subtrie.


        // To avoid looping though the size of all element on the array, the size of the next subtree to
        // examine is saved, subtracted from x.size after the recursive call, then x.size is increased
        // by the size of the updated subtree.

        int preSize = 0;
        if (x.next[c] != null) preSize = x.next[c].size;

        x.next[c] = put(x.next[c], key, val, d+1);

        x.size  = x.size - preSize + x.next[c].size;

        return x;
    }

    public boolean contains(String key){
        return get(key) != null;
    }

    public void delete(String key)
    { root = delete(root, key, 0); }
    private Node delete(Node x, String key, int d)
    {
        if (x == null) return null;
        if (d == key.length()){
            if (x.val != null){ x.size --; }
            x.val = null;
        }
        else
        {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);

            // update size
            x.size = 0;
            for (char ch = 0; ch < R; ch++) if (x.next[ch] != null) x.size += x.next[ch].size;
            if (x.val != null) x.size++;

        }
        if (x.val != null) return x;
        for (char c = 0; c < R; c++)
            if (x.next[c] != null) return x;
        return null;
    }

    public Iterable<String> keys()
    { return keysWithPrefix(""); }
    public Iterable<String> keysWithPrefix(String pre)
    {
        Queue<String> q = new Queue<String>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }
    private void collect(Node x, String pre,
                         Queue<String> q)
    {
        if (x == null) return;
        if (x.val != null) q.enqueue(pre);
        for (char c = 0; c < R; c++)
            collect(x.next[c], pre + c, q);
    }













    public Iterable<String> keysThatMatch(String pat)
    {
        Queue<String> q = new Queue<String>();
        collect(root, "", pat, q);
        return q;
    }
    public void collect(Node x, String pre, String pat, Queue<String> q)
    {
        int d = pre.length();
        if (x == null) return;
        if (d == pat.length() && x.val != null) q.enqueue(pre);
        if (d == pat.length()) return;
        char next = pat.charAt(d);
        for (char c = 0; c < R; c++)
            if (next == '.' || next == c)
                collect(x.next[c], pre + c, pat, q);
    }










    public int size(){
        if (root == null) return 0;
        return root.size;
    }


    // methods for Q5_2_8
    public String floor(String s){
        return floor(root, s, 0, "");
    }

    private String floor(Node node, String word, int d, String res){

        if (node.val != null && node.size == 1) return res;
        if ((word != null) && d == word.length()) return null;

        char j_init = (char)(R - 1);
        if (word!= null) j_init = word.charAt(d);

        for (char j =j_init; j > 0; j--){
            if (node.next[j] != null){

                String nextWord = null;
                if (word != null && j == word.charAt(d)) nextWord = word;

                String s = floor(node.next[j], nextWord, d+1,  res + j);

                if (s != null) return s;
            }
        }
        return null;
    }

    public String ceiling(String s){
        return ceiling(root, s, 0, "");
    }
    private String ceiling(Node node, String word, int d, String res){

        if (word == null){
            if (node.val != null && node.size == 1) return res;
        }
        else{
            if(res.length() == word.length()){
                word = null;
                if (node.val != null && node.size == 1) return res;
            }
        }

        char j_init = 0;
        if (word!= null) j_init = word.charAt(d);
        for (char j =j_init; j < (char)(R-1); j++){
            if (node.next[j] != null){
                String nextWord = null;
                if (word != null && j == word.charAt(d)) nextWord = word;
                String s = ceiling(node.next[j], nextWord, d+1,  res + j);
                if (s != null) return s;
            }
        }
        return null;
    }

    public int rank(String s){
        String floor = floor(s);
        if (floor == null) return 0;
        Node node = root;
        int sum = 0;
        for (int j = 0; j < floor.length(); j++){
            char c = floor.charAt(j);
            for (char i = 0; i< c; i++){
                if (node.next[i] != null)
                    sum += node.next[i].size;
            }
            node = node.next[c];
        }
        if (s.equals(floor)) sum--;
        return sum +1;
    }

    public String select(int k){
        String str = "";
        Node node = root;
        char x = 0;
        int sum = 0;

        for (char c = 0; c < R; c++){
            if (node.next[c] != null){
                if (sum + node.next[c].size> k){
                    str += c;
                    node = node.next[c];
                    c = (char)( -1);
                }
                else {sum += node.next[c].size; }
            }
        }

        return str;
    }



    public static void main(String[] args){

        TrieST<Integer> t = new TrieST<Integer>();
        String[] wordList = {"relate",
                "money",
                "sharp",
                "variety",
                "texture",
                "prestige",
                "wrong",
                "tube",
                "onion",
                "dinner",
                "peak",
                "integrated",
                "emotion"};

        Arrays.sort(wordList);
        int i = 0;
        for (String x : wordList) System.out.println(i++ + ": " + x);
        System.out.println();

        for (String x : wordList) t.put(x, 0);

        String c = t.ceiling("dinner");
        int rank = t.rank("presti");
        System.out.println(rank);
        System.out.println(t.select(4));

    }

}