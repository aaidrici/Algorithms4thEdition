package Chapter_5_2;

import Chapter_1_3.Queue;

public class TST<Value>
{
    private Node root; // root of trie
    private class Node
    {
        char c; // character
        Node left, mid, right; // left, middle, and right subtries
        Value val; // value associated with string
        int size; // needed for very eager implementation of size()
    }
    public Value get(String key){
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }
    private Node get(Node x, String key, int d)
    {
        if (key == "") return root;
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length() - 1)
            return get(x.mid, key, d+1);
        else return x;
    }

    public void put(String key, Value val)
    { root = put(root, key, val, 0); }

    private Node put(Node x, String key, Value val, int d)
    {
        char c = key.charAt(d);
        if (x == null) { x = new Node(); x.c = c; }
        if (c < x.c) x.left = put(x.left, key, val, d);
        else if (c > x.c) x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1)
            x.mid = put(x.mid, key, val, d+1);
        else{
            x.val = val;
        }

        // update size
        x.size = size(x.left) + size(x.right) + size(x.mid);
        if (x.val != null) x.size += 1;

        return x;
    }

    public boolean contains(String key){
        return get(key) != null;
    }



    // very eager implementation of size()
    public int size(){
        if (root == null) return 0;
        return root.size;
    }
    private int size(Node node){
        if (node == null) return 0;
        return node.size;
    }


    public Iterable<String> keys(){
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre){
        Queue<String> q = new Queue<String>();
        Node node = get(root, pre, 0);
        if (node != null){
            if (pre == "") collect(node, pre, q);
            else collect(node.mid,pre,q);
        }
        return q;
    }

    private void collect(Node node, String pre, Queue<String> q){

        if (node == null) return;

        collect(node.left, pre, q);

        if (node.val != null) q.enqueue(pre + node.c);
        collect(node.mid, pre+ node.c, q);

        collect(node.right, pre, q);
    }


    public Iterable<String> keysThatMatch(String pattern){
        Queue<String> q = new Queue<String>();
        collect(root, "", pattern, q);
        return q;
    }

    public void collect(Node x, String pre, String pattern, Queue<String> q){

        int d = pre.length();
        if (x == null) return;
        if ((d == pattern.length()) && (x.val != null)) q.enqueue(pre);



        collect(x.left, pre, pattern, q);

        char next = pattern.charAt(d);

        if (next == '.' || next == x.c){

            if (x.val != null && (pre.length() + 1 == pattern.length())) q.enqueue(pre + x.c);

            collect(x.mid, pre + x.c, pattern, q);
        }
        collect(x.right, pre, pattern, q);
    }


    public String longestPrefixOf(String s){
        int length = search(root, s, 0,0);
        return s.substring(0,length);
    }

    private int search(Node x, String s, int d, int length){
        if (x == null)
            return length;
        if (d == s.length())
            return length;
        if (x.val != null)
            length = d+1;


        char c = s.charAt(d);
        if (c < x.c) return search(x.left, s, d, length);
        else if (c > x.c) return search(x.right, s, d, length);
        else{
            return search(x.mid, s, d+1, length);
        }
    }





    public static void main(String[] args){

        TST<Integer> tst = new TST<Integer>();
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
                "dance",
                "dragon",
                "different",
                "diff",
                "differ",
                "differentiate",
                "ddr",
                "diameter",
                "peak",
                "integrated"};
        for (String x : wordList) tst.put(x, 1);

        String a = tst.longestPrefixOf("differentiatejrytfjhtf");
        System.out.println(a);

    }


}