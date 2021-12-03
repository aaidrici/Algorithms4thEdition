package Chapter_3_1;
import Chapter_1_3.LinkedList;
import edu.princeton.cs.algs4.RandomSeq;

import java.util.Iterator;

public class OrderedSequentialSeachST<Key extends Comparable<Key>, Value> {

    // convention -> first item of the LL is the smaller item of the ST

    LinkedList<Entry> a;

    public OrderedSequentialSeachST(){
        a = new LinkedList<Entry>();
    }

    public void put(Key key, Value val){
        Entry entry = new Entry(val, key);
        int i = 0;
        for (Entry x : a){
            if (key.compareTo(x.key) == 0){
                a.replace(entry, i);
                return;
            }
            else if (key.compareTo(x.key) < 0){
                a.insertBeforeN(entry, i);
                return;
            }
            i++;
        }
        a.insertBeforeN(entry, i);
    }

    public Value get(Key key){
        for (Entry x : a) if (x.key.compareTo(key) == 0) return x.val;
        return null;
    }

    public void delete(Key key){
        int i = 0;
        for (Entry x : a)
            if (x.key.compareTo(key) == 0)
                a.suppress(i);
            else i++;
    }

    public void display(){
        for (Entry x : a) System.out.println(x.key + " " + x.val);
    }

    public boolean contains(Key key) {
        for (Entry x : a) if (x.key.compareTo(key) == 0) return true;
        return false;
    }

    public int size(){ return a.size(); }

    public boolean isEmpty () {return size() == 0; }

    class Entry{
        Value val;
        Key key;
        public Entry(Value val, Key key){
            this.val = val;
            this.key = key;
        }
    }

    public Iterator<Key> iterator(){
        return new keyIterator<Key>();
    }

    class keyIterator<Key> implements Iterator{
        public boolean hasNext(){return false;}
        public Key next(){return null; }
        public void remove(){}
    }

    public static void main(String[] args){

        OrderedSequentialSeachST<Integer, String> st = new OrderedSequentialSeachST<Integer,String>();
        st.put(22,"Jesus");
        st.put(33,"Alexandre");
        st.put(14, "Imane");
        st.put(23,"Jordan");
        st.put(23,"Michael");

        st.delete(23);
        st.display();



    }


}
