package Chapter_3_1;

public class Entry<Key extends Comparable<Key>, Value> implements Comparable<Entry<Key, Value>>{
    public Key key;
    public Value value;
    public Entry(){}
    public Entry(Key k, Value v){key = k; value = v; }
    public int compareTo(Entry<Key, Value> entry){
        return this.key.compareTo((entry.key));
    }
}
