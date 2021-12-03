package Chapter_5_2;

import edu.princeton.cs.algs4.In;

public class UniqueSubstring {

    // Since it is expected the input string will have a very large size,
    // Ternary search strings (TST) are used as a symbol table;

    public static void main(String[] args){

        TST tst = new TST();
        String filename = args[0];
        int L = Integer.parseInt(args[1]);

        In in = new In(filename);
        String content = in.readAll();
        for (int i = 0; i< content.length() - L; i++){
            String substring = content.substring(i, i+L);
//            System.out.println(i + ": " + substring + ", " + tst.contains(substring));
            tst.put(substring, 0);

        }

        System.out.println("Total number of unique substrings: " + tst.size());

    }

}
