package Chapter_3_4;

public class q3_4_1 {



    public static int hash(char x){
        int k = x - 64;
        int M = 5;
        return (11 * k) % M;
    }

    public static void main(String[] args){

        Character[] keys = {'E','A','S','Y','Q','U','T','I','O','N'};

        for (char x : keys){
            System.out.print(x + " ");
        }
        System.out.println();
        for (char x : keys){
            System.out.print(hash(x) + " ");
        }

        // final result should be:
        //
        // chain 00: E, Y, T, O
        // chain 01: A, U,
        // chain 02: Q,
        // chain 03: ,
        // chain 04: S, I, N

    }

}
