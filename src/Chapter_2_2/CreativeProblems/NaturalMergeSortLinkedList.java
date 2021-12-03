//package Chapter_2_2.CreativeProblems;
//
//
//import Chapter_1_3.LinkedList;
//
//
//public class NaturalMergeSortLinkedList {
//}
////    public static void SortLinkedList(array a){
////        //
////
////    }
////
////    private static boolean less(Comparable v, Comparable w)
////    {
////        return v.compareTo(w) < 0;
////    }
////
////
////    public static LinkedList<Comparable> linkedListMerger(LinkedList<Comparable> a, LinkedList<Comparable> b){
////
////        LinkedList<Comparable> returnedLinkedlist = new LinkedList<Comparable>();
////        Comparable a1, b1;
////        while (!a.isEmpty() && !b.isEmpty()){
////            a1 = a.pop();
////            b1 = b.pop();
////            if (less(a1,b1)){
////                returnedLinkedlist.push(a1);
////                b.push(b1);
////            }
////            else{
////                returnedLinkedlist.push(b1);
////                a.push(a1);
////            }
////        }
////        while(!a.isEmpty())
////            returnedLinkedlist.push(a.pop());
////        while(!b.isEmpty())
////            returnedLinkedlist.push(b.pop());
////        return returnedLinkedlist;
////
////    }
////
////
////    public static void main(String[] args){
////
////
////        LinkedList a = new LinkedList();
////        LinkedList b = new LinkedList();
////        LinkedList c = new LinkedList();
////        int[] a_content = {8,7,8,2,1};
////        int[] b_content = {23,10,3};
////        for (int x : a_content) a.push(x);
////        for (int x : b_content) b.push(x);
////
////
////
////        c = linkedListMerger(a,b);
////        while(!c.isEmpty()) System.out.print(c.pop() + " ");
////
//
////    }



