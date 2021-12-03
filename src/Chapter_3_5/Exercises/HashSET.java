package Chapter_3_5.Exercises;

import Chapter_3_4.LinearProbingHashST;

public class HashSET<Key>{


    private LinearProbingHashST<Key, Character> st;

    public HashSET(){st = new LinearProbingHashST<>(); }

    public void add(Key key){st.put(key, null); }

    public void delete(Key key){ st.delete(key); }

    public boolean contains(Key key){ return st.contains(key); }

    public boolean isEmpty(){ return st.size() == 0; }

    public int size(){return st.size(); }


    public static void main(String[] args){


        HashSET<String> set = new HashSET<String>();


        set.add("hay");
        set.add("word");
        set.add("Needless");
        set.add("to");
        set.add("say");
        set.delete("Needless");
        set.add("word");

        System.out.printf("size = %d\n", set.size());
        System.out.printf("contains the word `word` = %b\n", set.contains("word"));
        System.out.printf("contains the word `Needless` = %b\n", set.contains("Needless"));
        System.out.printf("is the set empty? = %b \n", set.isEmpty());

    }


}
