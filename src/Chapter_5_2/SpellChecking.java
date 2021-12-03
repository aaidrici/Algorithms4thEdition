package Chapter_5_2;

import edu.princeton.cs.algs4.In;

public class SpellChecking {


    // potential improvement
    // 1. include punctuation as a way to delimit the words from the text,
    //    instead of only using whitespaces.


    public static void main(String[] args){

        String filename_dictionary = args[0];
        String filename_targetText = args[1];

        In in = new In(filename_dictionary);
        TrieST<Integer> trie = new TrieST<Integer>();
        while (!in.isEmpty()) trie.put(in.readString(), 0);
//
//        for (String x : trie.keys()) System.out.println(x);

        System.out.println("The following words do not appear in the dictionary: ");
        in = new In(filename_targetText);
        int targetText_wordCount = 0;
        int targetText_wordNotInDict = 0;
        while(!in.isEmpty()){
            String word = in.readString();
            targetText_wordCount++;
            if (!trie.contains(word)){
                System.out.println(word);
                targetText_wordNotInDict++;
            }
        }
        System.out.printf("Out of %d words, %d couldn't be found in the dictionary. ",targetText_wordCount, targetText_wordNotInDict);


    }

}
