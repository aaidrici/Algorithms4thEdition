package Chapter_1_5.CreativeProblems;

public class WeightedQuickUnionUFwithCompressedPath
{
    private int[] id; // parent link (site indexed)
    private int[] sz; // size of component for roots (site indexed)
    private int count; // number of components
    public WeightedQuickUnionUFwithCompressedPath(int N)
    {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
        sz = new int[N];
        for (int i = 0; i < N; i++) sz[i] = 1;
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
        if (sz[i] <= sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else { id[j] = i; sz[i] += sz[j]; }
        count--;
    }


    public void printIdArray(){
        for (int x : id) System.out.print(x + " ");
        System.out.println();
    }


    public static void main(String[] args){

        int N = 16;
        WeightedQuickUnionUFwithCompressedPath a = new WeightedQuickUnionUFwithCompressedPath(N);

        a.printIdArray();
        a.union(0,1);
        a.union(2,3);
        a.union(4,5);
        a.union(6,7);
        a.union(3,1);
        a.union(7,5);
        a.union(5,1);

        a.union(0+8,1+8);
        a.union(2+8,3+8);
        a.union(4+8,5+8);
        a.union(6+8,7+8);
        a.union(3+8,1+8);
        a.union(7+8,5+8);
        a.union(5+8,1+8);

        a.union(9,1);



        a.printIdArray();

    }



}