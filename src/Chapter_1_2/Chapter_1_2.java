package Chapter_1_2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import java.lang.Math;



public class Chapter_1_2 {

    // Evaluate answers to problems in the book.
    public static void main(String args[]){



        // Questions 1.2.3
        //  pairsOfPoints name = new pairsOfPoints(10, 0.2, 0.8);

        // Questions 1.2.4
        //  A: Aliasing is occurring, both Strings share the same reference and thus it should print "world" twice.

        // Question 1.2.5
        //  A: Strings are immutable in Java, therefore it should print "Hello World".

        // Question 1.2.6
        //  System.out.println(Q_1_2_6("34512","12345"));

        // Questions 1.2.7
        //  A: It splits an input string in half, swap the two values, and repeats the procedure on the generated
        //  substrings until there is only one character left swapped, therefore it cannot be more efficient.

        // Question 1.2.9 (NOT DONE)
        //  ......... Not worth the fight, try again...


        // Question 1.2.10
        //  see Chapter_1_2.VisualCounter

        // Question 1.2.11, 1.2.12
        //  see Chapter_1_2.SmartDate

        // Question 1.2.13 & 1.2.14
        //  See Chapter_1_2.Transaction

        // Questions 1.2.15
        //  see methods readInt(), I cheated a little....

        // Questions 1.2.16
        // see method

        // Question 1.2.17
        // ........... Not worth the fight

        // Questions 1.2.18
        //   Not sure about the answer provided... but I guess it must be true.

        // Questions 1.2.19
        //  Look at Chapter_1_2.Rational



    }

    // Question 1.2.1
    public static double Q_1_2_1(int N){

        double[] x_coord = new double[N];
        double[] y_coord = new double[N];

        StdDraw.setPenRadius(0.005);
        for (int i = 0; i<N ; i++){
            x_coord[i] = StdRandom.uniform();
            y_coord[i] = StdRandom.uniform();
        }

        double norm = 1000;
        double smallestNorm = 10;
        for (int i = 0; i<N; i++){
            for (int j = i+1; j<N; j++){
                norm = Math.pow(x_coord[i] - x_coord[j], 2.0) + Math.pow(y_coord[j] - y_coord[i],2.0);
                if (norm < smallestNorm) smallestNorm = norm;
            }
        }
        return Math.sqrt(smallestNorm);
    }

    // Question 1.2.2
    public static void Q_1_2_2(int N, double[] a1, double[] a2){
        for (int i = 0; i<N ; i++){
            for (int j = i+1; j<N; j++){
                if(!(a2[i] < a1[j] | a1[i] > a2[j]))
                    System.out.println(a1[i] + ":" + a2[i] + " overlaps with " + a1[j] + ":" + a2[j]);

            }
        }

    }

    // supporting method for Question 1.2.2
    public static boolean Q_1_2_6(String s, String t){
        for (int i = 0; i< s.length(); i++) if (t.equals(s.substring(i,s.length()) + s.substring(0,i))) return true;
        return false;
    }

    int[] ReadInt(String filename){
        In stream = new In(filename);
        String input = stream.readAll();
        String[] inputArray = input.split("//s+");
        int [] ints = new int[inputArray.length];
        for (int i = 0 ; i < inputArray.length ; i++){
            ints[i] = Integer.parseInt(inputArray[i]);
        }
        return ints;
    }

        // Question 1.2.8
        //  It swaps the two arrays a and b. References are




    public static void readFile(String filename){

        In in = new In(filename);
        while(!in.isEmpty())
            System.out.println(in.readLine());

            assert true == true: "ayy bruh this is not true";

        in.close();
    }


    // these are all classes
    public class ay{
        PrivateAy name = new PrivateAy();
    }

    private class PrivateAy{
        
    }

    public static class misc_name{
        int a = 1;
        int b = 2;

        int[] A = new int[10];

        void instanceMethod(){
            a++;
        }
        public static void staticMethod(){
            System.out.print("test");
        }
    }

}





