package Chapter_3_4.Exercises;

public class Q3_4_1 {





    public static int hash(char x){
        int k = x - 64;
        return (11 * k) % 5;
    }

    public static void main(String[] args){

        Character[] char_list = {'E','A','S','Y','Q','U','T','I','O','N'};
        for (char x : char_list)
            System.out.print(x + " ");

        System.out.println();

        for (char x : char_list)
            System.out.print(hash(x) + " ");


        // Resulting table should look like:
        // Chain 0: E, Y, T, O
        // Chain 1: A, U
        // Chain 2: Q
        // Chain 3:
        // Chain 4: S, I, N



    }

}
