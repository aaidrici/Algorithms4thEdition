package Chapter_5_2;

import Chapter_1_3.Queue;
import com.sun.tracing.dtrace.Attributes;
import edu.princeton.cs.algs4.In;

public class SubstringMatches {


    // It is assumed the list is made up of small words.

    TrieST<String> trie;

    public SubstringMatches(){
        trie = new TrieST<String>();
    }

    public Iterable<String> containsSubstring(String s){
        Queue<String> q = new Queue<String>();
        Iterable<String> a = trie.keysWithPrefix(s);
        for (String x : a) q.enqueue(trie.get(x));
        return q;
    }

    public void put(String s){
        for (int i = 0; i< s.length(); i++) trie.put(s.substring(i, s.length()),s);
    }


    public void display(){
        for (String x : trie.keys()) System.out.println(x);
    }

    public static void main(String[] args){

        SubstringMatches sm = new SubstringMatches();

        String filename = "Chapter_5_2\\Q5_2_21_input.txt";
        In in = new In(filename);
        while (!in.isEmpty()) sm.put(in.readString());

        String substring = "zon";
        for (String x : sm.containsSubstring(substring)) System.out.println(x);

    }
}
