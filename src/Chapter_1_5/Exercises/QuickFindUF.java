package Chapter_1_5.Exercises;

public class QuickFindUF {

    int count;
    int[] id;

    public QuickFindUF(int N){
        id = new int[N];
        for (int i = 0; i<N; i++) id[i] = i;
        count = N;
    }

    public int count(){return count;}

    public boolean connected(int p, int q){return find(p) == find(q); }

    public int find(int key){return id[key]; }

    public void union(int p, int q){

        int qID = find(q);
        int pID = find(p);
        if (pID == qID) return;

        for (int i = 0; i < id.length ; i++)
            if (id[i] == pID)
                id[i] = qID;
        count --;
    }



    public static void main(String[] args){




    }



}
