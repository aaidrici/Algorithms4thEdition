package Chapter_1_5.CreativeProblems;

import edu.princeton.cs.algs4.StdRandom;

public class QuickFindUF {

    int count;
    int[] id;
    int lastUnionNumberOfAccess = 0;

    public QuickFindUF(int N){
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

    public int find(int key){lastUnionNumberOfAccess ++; return id[key]; }

    public void union(int p, int q){

        int qID = find(q);
        int pID = find(p);
        if (pID == qID) return;

        for (int i = 0; i < id.length ; i++){
            if (id[i] == pID){
                id[i] = qID;
                lastUnionNumberOfAccess ++;
            }
            lastUnionNumberOfAccess ++;
        }

        count --;
    }



    public static void main(String[] args){


        int siteNo = 1000;
        int unionNo = 1500;
        int arrayAccessTotal = 0;
        int arrayAccessLastest;
        double[] arrayAccess = new double[unionNo];
        double[] arrayAccessMean = new double[unionNo];
        double[] x = new double[unionNo];

        QuickFindUF a = new QuickFindUF(siteNo);

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


    }


}


