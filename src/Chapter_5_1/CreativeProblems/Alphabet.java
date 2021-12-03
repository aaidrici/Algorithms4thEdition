package Chapter_5_1.CreativeProblems;


import Chapter_1_3.Queue;
import Chapter_3_2.CreativeProblems.PerfectBalance;
import Chapter_3_4.LinearProbingHashST;
import Chapter_3_4.SeparateChainingHashST;
import Chapter_3_5.Exercises.HashSET;
import edu.princeton.cs.algs4.MinPQ;

public class Alphabet {


    LinearProbingHashST<Character, Integer> set;
    char[] int2char;


    public Alphabet(String s){
        set = new LinearProbingHashST();
        Queue<Character> q = new Queue<Character>();
        int r = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set.contains(c)){
                set.put(c, r++);
                q.enqueue(c);
            }
        }
        int2char = new char[r];
        for (int i = 0; i < r; i++) int2char[i] = q.dequeue();
    }



    public char toChar(int index){ return int2char[index]; }

    public int toIndex(char c){
        if (!contains(c)) return -1;
        return set.get(c);
    }

    public boolean contains(char c) {return set.contains(c);}

    public int R(){ return int2char.length; }

    public int lgR(){
        int i = 1;
        int lg = 0;
        while (i < int2char.length) { i*=2; lg++; }
        return lg;
    }

    int[] toIndices(String s){
        int[] indexForm = new int[s.length()];
        for (int i = 0; i< s.length(); i++) indexForm[i] = toIndex(s.charAt(i));
        return indexForm;
    }

    String toChars(int[] indices){
        char[] charForm = new char[indices.length];
        for (int i = 0; i< charForm.length; i++) charForm[i] = int2char[indices[i]];
        return String.valueOf(charForm);
    }


    public static void main(String[] args){

        String s = "abcdefghi";
        Alphabet alphabet = new Alphabet(s);

        System.out.println(alphabet.lgR());

    }


}
