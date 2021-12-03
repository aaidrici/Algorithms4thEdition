package Chapter_2_5.CreativeProblems;

import Chapter_2_4.MinPQ;
import edu.princeton.cs.algs4.StdIn;

public class California {



    public static void main(String[] args){


        MinPQ<UnbiasedNameOrder> pq = new MinPQ<UnbiasedNameOrder>(10);

        System.out.print("Enter a list of name (only use capital letters): \n");
        while(!StdIn.isEmpty())
            pq.insert(new UnbiasedNameOrder(StdIn.readString()));

        while(!pq.isEmpty())
            System.out.println(pq.delMin().returnName());


   }


}


class UnbiasedNameOrder implements Comparable<UnbiasedNameOrder>{

    static char[] mapChar = {'H', 'J', 'Q', 'W', 'S', 'Y', 'L', 'I', 'R', 'E', 'T', 'Z', 'F', 'O', 'D', 'V', 'C', 'A', 'K', 'P', 'U', 'G', 'B', 'N', 'X', 'M' };

    private String name;
    private String sortingName;

    public UnbiasedNameOrder(String Name){
        name = Name;
        sortingName = convertName(Name);
    }

    public String returnName(){
        return name + " -> " + sortingName;
    }

    private static String convertName(String initName){
        String newName = "";

        for (int i = 0; i<initName.length(); i++)
            newName += mapChar[(int)initName.charAt(i) - 65];

        return newName;
    }

    public int compareTo(UnbiasedNameOrder that){
        return this.sortingName.compareTo(that.sortingName);
    }

}

