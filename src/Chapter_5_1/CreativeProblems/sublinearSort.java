package Chapter_5_1.CreativeProblems;

public class sublinearSort {

    public static int[] sort(int[] a){

        // LSD sort on leading 16 bits
        // insertion sort on the remaining bits.

        final int R = 65536;

        int[] count = new int[R + 1];
        int[] aux = new int[a.length];

        for (int x : a){
            int b = (x >> 16) & 0x7FFF;
            if (x>>31 == 0){b+=32768;}
            count[b+1]++;
        }
        // frequency to indices
        for (int i = 0; i < R; i++){ count[i+1] += count[i]; }

        // arrange matrix
        for (int i = 0; i < a.length; i++){
            int b = (a[i] >> 16) & 0x7FFF;
            if (a[i]>>31 == 0){b+=32768;}
            aux[count[b]++] = a[i];
        }

        // perform insertion sort for the 16 LSB.
        RecursionSort(aux, 0,count[0] - 1);
        for (int r = 0; r < R; r++){
            RecursionSort(aux, count[r],count[r+1]-1);
        }

        return aux;
    }


    private static void RecursionSort(int[] a, int lo, int hi){
        for (int i = lo+1; i <= hi; i++){
            for (int j = i; j > 0; j--)
                if (a[j] < a[j-1])
                    swap(a, j, j-1);
                else break;
        }
    }

    private static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean check(int[] a){
        for (int i = 1; i< a.length -1; i++){ if (a[i-1] > a[i]) return false;}
        return true;
    }

    public static void main(String[] args){

        final int N = 1000;

        // generate array of random ints
        double sum = 0;
        int[] randomInts = new int[N];
        for (int i = 0; i<N;i++) {
            int randomVal = Integer.MIN_VALUE;
            randomVal += Math.random()*Integer.MAX_VALUE;
            randomVal += Math.random()*Integer.MAX_VALUE;
            randomInts[i] = randomVal;
            sum += randomVal;
        }




        System.out.println("=== Unsorted ===");
        for (int x : randomInts) System.out.println(x);
        System.out.print("\n\n");

        randomInts = sublinearSort.sort(randomInts);

        System.out.println("=== Partially Sorted ===");

        for (int i = 1; i < N; i++){
            System.out.println(randomInts[i] + ", diff = " + (randomInts[i-1] - randomInts[i]));
        }

        System.out.printf("Valid sorted output: " + check(randomInts));



    }

}
