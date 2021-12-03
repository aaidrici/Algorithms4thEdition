package Chapter_5_5;

import Chapter_1_3.Queue;
import Chapter_3_5.Exercises.SET;
import edu.princeton.cs.algs4.*;

public class RLE {



    public static void compress(){

        SET<Character> set = new SET<>();
        BinaryStdOut stdOut;

        String a = StdIn.readAll();

        for (int i = 0; i<a.length(); i++)
            set.add(a.charAt(i));

        // write first 8 bits as the alphabet size (R)
        BinaryStdOut.write(set.size(), 8);

        // write the subsequent 7R bits as the character themselves, using the queue order
        String alpha = "";
        Queue<Character> q = new Queue<Character>();
        for (char x : set.keys()){
            q.enqueue(x);
            // alpha = alpha + x;     <- not used as suggested in the book.
            BinaryStdOut.write(x, 8);
        }

        // write the subsequent N * ceil(log2(R)) bits as the encoded message
        LinearProbingHashST<Character,Integer> hash = new LinearProbingHashST<>();
        int j = 0;
        for (char x : q)
            hash.put(x,j++);
        for (int i = 0; i < a.length(); i++)
            BinaryStdOut.write(hash.get(a.charAt(i)), 8);

        BinaryStdOut.close();
    }

    public static void expand(){

        // extract alphabet
        int R = BinaryStdIn.readInt(8);


        Character[] chars = new Character[R];
        for (int i = 0; i<R; i++){
            chars[i] = BinaryStdIn.readChar(8);
        }

        String output = "";
        while (!BinaryStdIn.isEmpty()){
            char nextChar = chars[BinaryStdIn.readInt(8)];
            BinaryStdOut.write(nextChar, 8);
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args){


        if (args[0].equals("-")) compress();
        if (args[0].equals("+")) expand();


    }
}
