package Chapter_1_5.CreativeProblems;

public class WeightedQuickUnionUFwithHeight
{
    private int[] id; // parent link (site indexed)
    private int[] ht; // size of component for roots (site indexed)
    private int count; // number of components
    public WeightedQuickUnionUFwithHeight(int N)
    {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
        ht = new int[N];
        for (int i = 0; i < N; i++) ht[i] = 1;
    }
    public int count()
    { return count; }
    public boolean connected(int p, int q)
    { return find(p) == find(q); }



    public int find(int key){

        int initKey = key;
        int tempKey;
        int root;

        while(id[key] != key) { key = id[key]; }
        root = key;

        key = initKey;
        while(id[key] != key) {
            tempKey = key;
            key = id[key];
            id[tempKey] = root;
        }
        return key;
    }



    public void union(int p, int q)
    {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
// Make smaller root point to larger one.
        if (ht[i] <= ht[j]) {
            id[i] = j;
            ht[j] = Math.max(ht[j], ht[i]+1);
        }
        else {
            id[j] = i;
            ht[i] = Math.max(ht[i], ht[j]+1);
        }
        count--;
    }


    public void printIdArray(){
        for (int x : id) System.out.print(x + " ");
        System.out.println();
    }


    public static void main(String[] args){

        int N = 16;
        WeightedQuickUnionUFwithHeight a = new WeightedQuickUnionUFwithHeight(N);

        a.printIdArray();
        a.union(0,1);

        a.printIdArray();

    }



}