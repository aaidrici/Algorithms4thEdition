package Chapter_3_4.Exercises;

import java.util.Arrays;

public class PerfectHashFunction {


    public static int MThatGenerateNoCollision(char[] char_list, int a){


        Integer[] indices = new Integer[char_list.length];


        for (int M = char_list.length; M < 1000; M++){
            for (int i = 0; i<indices.length; i++){
                int k = (int)char_list[i] - 64;
                indices[i] = (a * k) % M;
            }
            Arrays.sort(indices);

            boolean MValueHasBeenFound = true;

            for (int i = 0; i < indices.length - 1; i++) {
                if (indices[i] == indices[i + 1]) {
                    MValueHasBeenFound = false;
                    break;
                }
            }

            if (MValueHasBeenFound) return M;
        }
        return -1;
    }

    public static void main(String[] args){


        char[] chars = {'S', 'E', 'A', 'R', 'C', 'H', 'X', 'M', 'P', 'L'};

        int M = MThatGenerateNoCollision(chars, 1);

        System.out.print("Value of M that generates no collision: " + M);

    }
}
