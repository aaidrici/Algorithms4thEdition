package Chapter_5_2;

import edu.princeton.cs.algs4.In;

public class containsPrefix {


    public static void main(String[] args){

        StringSET set = new StringSET();
        String filename = "Chapter_5_2\\Q5_2_21_input.txt";
        In in = new In(filename);
        while (!in.isEmpty()) set.add(in.readString());

        String prefix = "zona";
        System.out.println(set.containsPrefix(prefix));

    }
}
