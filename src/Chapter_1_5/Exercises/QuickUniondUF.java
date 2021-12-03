package Chapter_1_5.Exercises;

public class QuickUniondUF {

    int count;
    int[] id;

    public QuickUniondUF(int N){
        id = new int[N];
        for (int i = 0; i<N; i++) id[i] = i;
        count = N;
    }

    public int count(){return count;}

    public boolean connected(int p, int q){return find(p) == find(q); }

    public int find(int key){
        while(id[key] != key)
            key = id[key];
        return key;
    }

    public void union(int p, int q){

        int qID = find(q);
        int pID = find(p);
        if (pID == qID) return;
        else id[p] = q;
        count --;
    }


}
