package Chapter_1_4;

import java.util.Arrays;
public class StaticSETofInts
{
    private int[] a;
    public StaticSETofInts(int[] keys)
    {
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++)
            a[i] = keys[i]; // defensive copy
        Arrays.sort(a);
    }
    public boolean contains(int key)
    { return rank(key) != -1; }


    private int rank_FirstInstance(int key){
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi)
        { // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key <= a[mid]) hi = mid;
            else if (key > a[mid]) lo = mid + 1;
        }

        if (a[lo] == key ) return lo;
        else return -1;
    }

    private int rank_LastInstance(int key){
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi)
        { // Key is in a[lo..hi] or not present.
            int mid = hi - (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key >= a[mid]) lo = mid;
        }
        if (a[hi] == key ) return lo;
        else return -1;
    }

    public int howMany(int key){
        int a = rank_FirstInstance(key);
        if (a == -1) return -1;
        else {
            int b = rank_LastInstance(key);
            return b - a + 1;
        }
    }

    private int rank(int key)
    { // Binary search.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi)
        { // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }





}