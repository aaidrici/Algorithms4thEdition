package Chapter_5_3;

public class Q5_3_4 {



    // worst case performance: ~ txt.length(), occurs when the txt is made up of a repeated pattern of m-1 consecutive blank
    // characters followed by a single non-blank character

    // typical text performance: ~ txt.length() / 5 + m
    //

    public static int searchForMConsecutiveBlanks(String txt, int m){

        for (int i = m-1; i < txt.length(); i += m){
            if (txt.charAt(i) != ' ') continue;

            int count = 1;


            int k = i - 1;
            while (k >= 0 && (txt.charAt(k) == ' ') && count <= m) {count++; k--; }
            int res = k+1;
            k = i + 1;
            while (k < txt.length() && (txt.charAt(k) == ' ') && count <= m) {count++; k++; }

            if (count == m) return res;

        }
        return txt.length();

    }

    public static void main(String args[]){
        String txt = "There are 5 consecutive blank characters here,     Let's try to find them. ";
        System.out.println(searchForMConsecutiveBlanks(txt, 5));

    }


}
