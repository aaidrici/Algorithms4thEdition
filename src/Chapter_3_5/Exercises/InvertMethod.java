package Chapter_3_5.Exercises;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.ST;

public class InvertMethod {


    public static ST<String, Bag<String>> invert (ST<String, Bag<String>> st){

        ST<String, Bag<String>> st_return = new ST<String, Bag<String>>();

        for (String x : st.keys()){
            Bag<String> t = st.get(x);
            for (String y : t){
                if (!st_return.contains(y)){
                    Bag<String> k = new Bag<String>();
                    k.add(x);
                    st_return.put(y, k);
                }
                else{
                    Bag<String> k = st_return.get(y);
                    k.add(x);
                    st_return.put(y,k);
                }
            }
        }

        return st_return;
    }

    public static void display(ST<String, Bag<String>> st){
        for (String x : st.keys()){
            System.out.print(x + " ");
            Bag<String> bag = st.get(x);
            for (String y : bag) System.out.print(y + " ");
            System.out.println();
        }
    }


    public static void main(String[] args){
        ST<String,Bag<String>> bag = new ST<>();

        Bag<String> t;

        t = new Bag<>();
        t.add("3");
        t.add("5");
        t.add("7");
        bag.put("A", t);
        t = null;

        t = new Bag<>();
        t.add("5");
        t.add("7");
        t.add("9");
        bag.put("B", t);


        display(bag);

        System.out.println();
        bag = invert(bag);

        display(bag);



    }


}
