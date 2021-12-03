package Chapter_3_5.CreativeProblems;

import Chapter_3_3.RedBlackBST;
import Chapter_3_4.LinearProbingHashST;
import java.util.ArrayList;
import java.util.Collections;

public class MathSET<Key extends Comparable<Key>> {


    Key[] universe;

    RedBlackBST<Key, Character> set;

    public MathSET(Key[] universe) {
        this.universe = universe;
        set = new RedBlackBST<Key, Character>();
    }

    public void add(Key key){set.put(key,null); }

    public void delete(Key key){set.delete(key);}

    public boolean isEmpty(){return set.isEmpty();}

    public int size(){return set.size();}

    public void union(MathSET<Key> a){ for (Key x : a.keys()) add(x); }

    public void intersection(MathSET<Key> a){
        for (Key x : keys()){if (!a.contains(x)) delete(x); }
    }

    public MathSET<Key> complement(){
        for (Key x : universe){
            if (!contains(x)) add(x);
            else delete(x);
        }
        return this;
    }


    // additional methods
    public Iterable<Key> keys(){
        return set.keys(); }

    public void display(){
        Iterable<Key> keys = keys();
        if (keys != null)
            for (Key x : keys()) System.out.print(x + " ");
    }

    public boolean contains(Key key) {return set.contains(key);}


    public static void main(String[] args){

        final int UPPERBOUND = 100;
        Integer[] universe = new Integer[UPPERBOUND];
        for (int i = 0; i < UPPERBOUND; i++) universe[i] = i;

        MathSET<Integer> set1 =  new MathSET<Integer>(universe);
        set1.add(23);
        set1.add(44);
        set1.add(11);

        MathSET<Integer> set2 =  new MathSET<Integer>(universe);
        set2.add(1);
        set2.add(7);
        set2.add(2);

        set1 = set1.complement();

        set1.display();

    }

}
