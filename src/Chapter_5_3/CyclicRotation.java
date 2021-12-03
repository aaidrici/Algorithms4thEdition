package Chapter_5_3;

public class CyclicRotation {



    public static boolean check(String a, String b){
        if (a.length() != b.length()) return false;
        int Q = 997;
        int R = 256;
        int RM = 1;
        for (int i = 0; i < a.length()-1; i++)
            RM = (RM * R) % Q;

        // compute hash of reference String a
        long hash_a = 0;
        for (int i = 0; i<a.length(); i++) hash_a = (hash_a*R + a.charAt(i))%Q;

        // compute hash of reference String a
        long hash_b = 0;
        for (int i = 0; i<b.length(); i++) hash_b = (hash_b*R + b.charAt(i))%Q;

        if (hash_a == hash_b) return true;

        for (int i = 0; i<a.length()-1; i++){
            char c = b.charAt(i); // both the leading char that is removed and the trailing char that is added.
            // remove leading character for string b
            hash_b = (hash_b + Q - (c*RM)%Q)%Q;
            // add trailing character for string b
            hash_b = (hash_b * R + c)%Q;

            if (hash_a == hash_b) return true;
        }
        return false;

    }

    public static void main(String[] args){

        System.out.println(CyclicRotation.check("example", "pleexam"));


    }
}
