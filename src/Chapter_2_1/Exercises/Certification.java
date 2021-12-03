package Chapter_2_1.Exercises;
import java.util.Arrays;

public class Certification {
    public static boolean check(Comparable[] a, Comparable[] b){
        if (a.length != b.length) return false;
        Arrays.sort(a);
        Arrays.sort(b);
        for (int i = 0; i< a.length; i++){
            if (a[i] != b[i]) return false;
        }
        return true;
    }
}
