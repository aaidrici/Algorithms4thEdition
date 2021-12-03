package Chapter_2_5.Exercises;

import Chapter_1_3.Queue;

import java.util.Arrays;

public class Dedup {


    public static String[] dedup(String[] a){
        Arrays.sort(a);

        Queue<Integer> queue = new Queue<Integer>();

        for (int i = 0; i<a.length - 1; i++)
            if (a[i].compareTo(a[i+1]) == 0)
                queue.enqueue(i);


        int numberOfUniqueStrings = a.length - queue.size();
        String[] returnedArray = new String[numberOfUniqueStrings];

        int id2skip = -1; // -1 means there is no more ids to skip;
        if (!queue.isEmpty()) id2skip = queue.dequeue();

        int k = 0;
        for (int i = 0; i<a.length; i++){
            if (id2skip != i)
                returnedArray[k++] = a[i];
            else{
                if (!queue.isEmpty())
                    id2skip = queue.dequeue();
                else
                    id2skip = -1;
            }
        }
    return returnedArray;
    }


    public static void main(String[] args){

        String[] a  = {"this","a","simple","test","test","to","to","see","whether", "whether"};
        String[] a_cleaned = dedup(a);
        for (String x : a_cleaned) System.out.println(x);

    }

}
