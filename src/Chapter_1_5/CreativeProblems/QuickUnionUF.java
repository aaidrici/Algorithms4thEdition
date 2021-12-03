package Chapter_1_5.CreativeProblems;

import edu.princeton.cs.algs4.StdRandom;

public class QuickUnionUF {


    int count;
    int[] id;
    private int lastUnionNumberOfAccess;

    public QuickUnionUF(int N){
        id = new int[N];
        for (int i = 0; i<N; i++) id[i] = i;
        count = N;
    }

    public int getLastUnionNumberOfAccess(){
        int temp = lastUnionNumberOfAccess;
        lastUnionNumberOfAccess = 0;
        return temp;
    }

    public int count(){return count;}

    public boolean connected(int p, int q){return find(p) == find(q); }


    public int find(int key){

        while(id[key] != key) { key = id[key]; lastUnionNumberOfAccess+=2; }
        lastUnionNumberOfAccess++;

        return key;
    }



    public void union(int p, int q){
        int qID = find(q);
        int pID = find(p);

        if (pID == qID) return;

        id[pID] = qID;
        lastUnionNumberOfAccess++;
        count --;
    }


    public void printIdArray(){
        for (int x : id) System.out.print(x + " ");
        System.out.println();
    }

    public static void main(String[] args){


        int siteNo = 1000;
        int unionNo = 1500;
        int arrayAccessTotal = 0;
        int arrayAccessLastest = 0;
        double[] arrayAccess = new double[unionNo];
        double[] arrayAccessMean = new double[unionNo];
        double[] x = new double[unionNo];

        QuickUnionUF a = new QuickUnionUF(siteNo);

        for(int i = 0; i<unionNo; i++){
            x[i] = i;
            a.union(StdRandom.uniform(0,siteNo),StdRandom.uniform(0,siteNo));
            arrayAccessLastest = a.getLastUnionNumberOfAccess();
            arrayAccess[i] = arrayAccessLastest;
            arrayAccessTotal += arrayAccessLastest;
            arrayAccessMean[i] = arrayAccessTotal / (double)(i+1);
            System.out.println("ar = " + arrayAccess[i] + ", mean{ar} = " + arrayAccessMean[i]);
        }

        plot.Plot(x, arrayAccess, x, arrayAccessMean);




//        QuickUnionUF a = new QuickUnionUF(10);
//
//        a.printIdArray();
//        a.union(0,1);
//        a.union(1,2);
//        a.union(2,3);
//        a.union(3,4);
//
//        a.printIdArray();


    }


}
