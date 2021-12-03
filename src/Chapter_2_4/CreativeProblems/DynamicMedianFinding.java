package Chapter_2_4.CreativeProblems;



import Chapter_2_4.MaxPQ;
import Chapter_2_4.MinPQ;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;


import static edu.princeton.cs.algs4.StdOut.print;

public class DynamicMedianFinding {


    private MaxPQ LeftSide;
    private MinPQ RightSide;
    private Comparable L;
    private Comparable R;

    public DynamicMedianFinding(int N){
        LeftSide = new MaxPQ(N/2+1);
        RightSide = new MinPQ(N/2+1);
        L = null;
        R = null;
    }

    public Comparable DelMedian(){
        // check if both PQs are empty
        if (LeftSide.size() == 0 && RightSide.size() == 0)
            return null;

        Comparable median2return;

        if (RightSide.size() > LeftSide.size()){
            if (RightSide.isEmpty()){
                R = null;
                median2return = null ;
            }
            else{
                median2return = RightSide.delMin();

                if (RightSide.isEmpty())
                    R = null;
                else{
                    R = RightSide.delMin();
                    RightSide.insert(R);
                }
            }
        }
        else {
            if (LeftSide.isEmpty()){
                L = null;
                median2return = null ;
            }
            else{
                median2return = LeftSide.delMax();

                if (LeftSide.isEmpty())
                    L = null;
                else{
                    L = LeftSide.delMax();
                    LeftSide.insert(L);
                }
            }
        }


        return median2return;
    }


    public void insert(Comparable v) {

        if (L == null) {
            LeftSide.insert(v);
            L = v;
        } else if (R == null) {
            RightSide.insert(v);
            R = v;
        } else {

            if (less(v, L)) // element belongs to left PQ
                LeftSide.insert(v);
            else
                RightSide.insert(v);

            while (LeftSide.size() < RightSide.size())
                LeftSide.insert(RightSide.delMin());
            while (RightSide.size() < LeftSide.size())
                RightSide.insert(LeftSide.delMax());


            // this part could be more efficient if we had a method to just check the value
            // of at the head of the heap

            R = RightSide.delMin();
            RightSide.insert(R);
            L = LeftSide.delMax();
            LeftSide.insert(L);

        }
    }


    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }


    public static void main(String[] args){

        int N = 20;

        DynamicMedianFinding dmf = new DynamicMedianFinding(2*N);

        MinPQ pq = new MinPQ(N);

        Comparable[] a = new Comparable[N];
        Comparable[] a_sorted = new Comparable[N];


        for (int i = 0; i<N; i++)
            a[i] = StdRandom.uniform(-10*N,N*10);

        a_sorted = Arrays.copyOf(a,a.length);
        Arrays.sort(a_sorted);
        for (int i = 0; i<N; i++)
            System.out.printf("%d ",a[i]);

        System.out.print("\n\n");

        for (int i = 0; i<N; i++)
            System.out.printf("%d ",a_sorted[i]);

        print("\n\n");

        for (int i = 0; i<N; i++){
            dmf.insert(a[i]);
//            print("L: ");
//            dmf.LeftSide.Display();
//            print("\n");
//            print("R: ");
//            dmf.RightSide.Display();
//            print("\n");
        }


        for (int i = 0; i<N; i++)
            System.out.print(dmf.DelMedian() + " ");






    }

}
