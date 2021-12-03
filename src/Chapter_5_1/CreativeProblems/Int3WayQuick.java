package Chapter_5_1.CreativeProblems;

public class Int3WayQuick {

    int[] sortedArray;

    public static int digitAt(int a, int d){
        for (int i = 0; i < d; i++) a /= 10;
        return a % 10;
    }

    public Int3WayQuick(int[] a){
        int maxInt = Integer.MIN_VALUE;
        for (int x : a) if (x > maxInt || x < -maxInt) maxInt = x;
        int d = 0;
        while (maxInt != 0){
            maxInt /= 10;
            d++;
        }

        sort(a, 0, a.length -1, d-1);

        sortedArray = a;
    }

    public void sort(int[] a, int lo, int hi, int d){
        if (hi <= lo) return;

        int i = lo + 1;
        int v = digitAt(a[lo], d);
        int lt = lo;
        int gt = hi;

        while (i <= gt){
            int t = digitAt(a[i], d);
            if (t < v) exch(a, i++, lt++);
            else if (t > v) exch(a, i, gt--);
            else i++;
        }

        sort(a, lo, lt -1, d);
        if (d >= 0) sort(a, lt, gt, d-1);
        sort(a, gt+1, hi, d);

    }

    public int[] sortedArray(){
        return sortedArray;
    }

    private void exch(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean check(int[] a){
        for (int i = 1; i < a.length - 1; i++) if (a[i-1] > a[i]) return false;
        return true;
    }

    public static void main(String[] args){

        int N = 10;
        int[] randomInts;
        for (int trial = 0; trial < 1000; trial++){
            randomInts = new int[N];
            for (int i = 0 ; i < N ; i++) { randomInts[i] = (int)(Math.random()*N*100); }

            Int3WayQuick int3 = new Int3WayQuick(randomInts);
            int[] a2 = int3.sortedArray;
            System.out.println(int3.check(a2));
        }
    }
}
