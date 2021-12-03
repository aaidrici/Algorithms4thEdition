package Chapter_2_2.CreativeProblems;

import Chapter_2_2.CodeProvidedInBook.TopDownMergeSort;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Triplicate {
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    public static void GenerateThreeListOfRandomNames(String[] list1, String[] list2, String[] list3) throws Exception
    {
        File file = new File("src\\Chapter_2_2\\DataForProblems\\1000NamesList.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int i = 0;
        int N = 1000;
        String[] completeList = new String[N];
        while ((st = br.readLine()) != null && i < N)
            completeList[i++] = st;

        // populate the three list of strings
        int n = list1.length;
        for (int j = 0; j<n; j++){
            list1[j] = completeList[StdRandom.uniform(0,N-1)];
            list2[j] = completeList[StdRandom.uniform(0,N-1)];
            list3[j] = completeList[StdRandom.uniform(0,N-1)];
        }
    }

    public static void displayList(String[] a){
        for(String x : a) System.out.println(x);
    }

   public static void sortNameList(String[] a){
        // User merge sort to guarantee linearithmic running time
       TopDownMergeSort.sort(a);
   }

   public static String firstCommonName(){
        int N = 100;
        String[] a = new String[N];
        String[] b = new String[N];
        String[] c = new String[N];

        try{
            GenerateThreeListOfRandomNames(a,b,c);
        }
        catch(Exception e){

       }
       sortNameList(a);
       sortNameList(b);
       sortNameList(c);

       int j = 0;
       int k = 0;

       for (int i = 0; i<N ; i++){
           while( j < N-1 && less(b[j], a[i])) j++;
           while( k < N-1 && less(c[k], a[i])) k++;

           if ((a[i] == b[j]) && (a[i] == c[k])) return a[i];
       }
       return "No common name found";
   }



    public static void main(String[] args){
        System.out.println(firstCommonName());
    }

}
