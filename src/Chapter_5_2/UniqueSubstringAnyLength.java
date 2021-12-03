package Chapter_5_2;
import edu.princeton.cs.algs4.In;

public class UniqueSubstringAnyLength {

    // Since it is expected the input string will have a very large size,
    // Ternary search strings (TST) are used as a symbol table;

    public static void main(String[] args){

        TST tst = new TST();
        String filename = args[0];

        In in = new In(filename);
        String content = in.readAll();
        for (int i = 0; i< content.length() ; i++){
            for (int j = i+1; j< content.length(); j++){
                String substring = content.substring(i, j);
//                System.out.println(i + ": " + substring + ", " + tst.contains(substring));
                System.out.println(i + ": " + substring);

                tst.put(substring, 0);
            }

        }

        System.out.println("Total number of unique substrings: " + tst.size());

    }

}
