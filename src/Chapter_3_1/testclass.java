//package Chapter_3_1;
//
//public class testclass<item1, item2> {
//
//    item1 a[];
//    item2 b[];
//
//    public testclass(Class<item1> c, int size){
//        a = new item1[size];
//        b = new item2[size];
//
//    }
//
//    public testclass(){}
//
//    public void display(){
//        System.out.print(a.toString() + b.toString());
//    }
//
//    public static void main(String[] args){
//
//        testclass<Integer, String> a= new testclass<Integer, String>(33, "hello");
//        a.display();
//
//
//
//    }
//}

package Chapter_3_1;

import java.lang.reflect.Array;

public class testclass<E> {

    private E[] a;

    public testclass(Class<E> c, int s) {
        // Use Array native method to create array
        // of a type only known at run time
        @SuppressWarnings("testclass")
        final E[] a = (E[]) Array.newInstance(c, s);
        this.a = a;
    }

    E get(int i) {
        return a[i];
    }

    public static void main(String[] args){}

}