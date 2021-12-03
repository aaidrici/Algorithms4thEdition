package Chapter_3_5.Exercises;

import Chapter_3_3.RedBlackBST;

public class SET<Key extends Comparable<Key>> {

    RedBlackBST<Key, Character> st;

    public SET(){st = new RedBlackBST<Key, Character>(); }

    public void add(Key key) {st.put(key, null); }

    public void delete(Key key){st.delete(key); }

    public boolean contains(Key key){return st.contains(key);}

    boolean isEmpty(){return st.isEmpty();}

    public int size(){return st.size(); }

    public Iterable<Key> keys(){return st.keys(); }

    public static void main(String[] args){

        SET<String> set = new SET<String>();

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
