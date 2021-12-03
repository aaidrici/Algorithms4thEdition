//package Chapter_5_3;
//
//import Chapter_1_3.Queue;
//
//public class palindrome {
//
//    // strategy:
//    //  - Use two hash function on two different segment of the string.
//    //  - In  order to be palindrome, the two hash must cancel out.
//    //
//    //
//    //
//
//
//    String pat;
//    public static int palindrome(String text){
//
//        Queue<Integer> q1 = new Queue<Integer>();
//        Queue<Integer> q2 = new Queue<Integer>();
//        int hash1 = 0;
//        int hash2 = 0;
//        int M = 4;
//
//        int Q = 997;
//        int R = 10;
//        int RM = 1;
//        for (int i = 1; i <= M-1; i++) // Compute R^(M-1) % Q for use
//            RM = (R * RM) % Q; // in removing leading digit.
//
//        for (int i = 0; i<text.length(); i++){
//            char c = text.charAt(i);
//
//            hash1 = (hash1*10 + c)%Q;
//
//
//        }
//
//    }
//
//
//    public static void main(String[] args){
//
//    }
//
//}
