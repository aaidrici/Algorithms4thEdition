//package Chapter_2_5.CreativeProblems;
//
//import java.util.Arrays;
//
//public class ForceStability {
//
//
//
//
//
//    public static void UnstableSort(Comparable[] a, int lb, int up, int attrib){
//        // use any (non)stable sorting method:
//        Comparable[] temp = Arrays.copyOfRange(a, lb, up);
//        Arrays.sort(temp);
//        int j = 0;
//        for (int i = lb; i<up; i++) a[i] = temp[j++];
//    }
//
//    public static void stableSort(Comparable[] a, int lb, int ub){
//
//        UnstableSort(a, lb, ub);
//        int ptr = lb;
//        int repeatedKeysCount = 1;
//
//        while (ptr < ub-1){
//
//            while ((ptr < ub-1) && (a[ptr] == a[ptr+1])){
//                repeatedKeysCount++;
//                ptr++;
//            }
//
//            if (repeatedKeysCount > 1){
//                // create copy of sorted segment with continuous identical values
//                Comparable[]
//
//
//                stableSort(a,ptr-1-repeatedKeysCount,ptr+1, attrib+1);
//                repeatedKeysCount = 1;
//            }
//
//
//            ptr++;
//        }
//
//
//    }
//    public static void stableSort(Comparable[] a){
//
//        // assume each comparable item of a has a 'key' member object
//        stableSort(a, 0, a.length, 0);
//
//    }
//
//
//
//    public static void main(String[] args){
//
//        //
//
//
//    }
//
//}
//
//
//class miscType{
//    Integer a;
//    Integer b;
//    Integer b;
//}
//
//
//class multiDimVal<type>{
//
//
//
//    Comparable[] a;
//
//    public static Comparable[]
//
//    public multiDimVal(int n)
//
//}
//
