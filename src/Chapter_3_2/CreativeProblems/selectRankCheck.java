package Chapter_3_2.CreativeProblems;

import Chapter_3_2.BST;

import java.util.ArrayList;

public class selectRankCheck<Key extends Comparable<Key>,Value> extends BST<Key, Value> {


    public boolean RankSelectCheck(){
        for (int i = 0; i < size(); i++){
            if ( i != rank(select(i))) return false;
        }
        for (Key x : keys()){
            if (x != select(rank(x))) return false;
        }
            return true;
    }

    public static void main(String[] args){


        selectRankCheck<Integer,Integer> bst = new selectRankCheck<Integer, Integer>();

        ArrayList<Integer> content = new ArrayList<>();
        content.add(10);
        content.add(-4);
        content.add(5);
        content.add(3);
        content.add(1);
        content.add(2);
        content.add(12);
        content.add(26);
        content.add(109);
        content.add(66);

        for (Integer x : content) bst.put(x, 0);

        System.out.print(bst.RankSelectCheck());
    }

}
