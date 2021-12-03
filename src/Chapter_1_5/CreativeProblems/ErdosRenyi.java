package Chapter_1_5.CreativeProblems;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class ErdosRenyi {






    public static int count(int N){

        QuickUnionUF a = new QuickUnionUF(N);

        int connectionCount = 0;

        while (true){
            int no1 = StdRandom.uniform(0,N);
            int no2 = StdRandom.uniform(0,N);
            if (!a.connected(no1, no2)){
                a.union(no1,no2);
                connectionCount++;
            }
            if (a.count() == 1) break;

        }
        return connectionCount;
    }

    public static void main(String[] args){

        int userInput = StdIn.readInt();
        System.out.print(count(userInput));

    }
}
