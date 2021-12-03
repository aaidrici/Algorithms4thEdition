package Chapter_5_2;

public class StringSET {


    private TrieST<Boolean> trie;
    public StringSET(){
        trie = new TrieST<Boolean>();
    }


    public void add(String key){
        trie.put(key, true);
    }

    public void delete(String key){
        trie.delete(key);
    }

    public boolean contains(String key){
        return trie.get(key) != null;
    }

    public boolean isEmpty(){
        return trie.size() == 0;
    }

    public int size(){
        return trie.size();
    }

    public String toString(){
        String toString = "";
        for (String x : trie.keys()) toString = toString + x + " ";
        return toString;
    }

    public boolean containsPrefix(String s){

        String ceil = trie.ceiling(s);
        if (ceil == null || ceil.length() < s.length()) return false;
        return ceil.substring(0,s.length()).equals(s);


    }


    public static void main(String[] args){
        StringSET set = new StringSET();
        String[] stringList = {"Hello", "this", "is", "a","test"};
        for (String x : stringList) set.add(x);
        System.out.println("toString() = " + set.toString());
        System.out.println("is the set empty?: " + set.isEmpty());
        System.out.println("size of set: " + set.size());
        System.out.println("Does the set contain `this`? " + set.contains("this"));
        System.out.println("Does the set contain `NoInThere`? " + set.contains("NoInThere"));


        System.out.println("\ndelete(`this`) is called\n");
        set.delete("this");

        System.out.println("toString() = " + set.toString());
        System.out.println("is the set empty?: " + set.isEmpty());
        System.out.println("size of set: " + set.size());
        System.out.println("Does the set contain `this`? " + set.contains("this"));
        System.out.println("Does the set contain `NoInThere`? " + set.contains("NoInThere"));

        System.out.println("\ndelete(`test`) is called\n");
        set.delete("test");

        System.out.println("toString() = " + set.toString());
        System.out.println("is the set empty?: " + set.isEmpty());
        System.out.println("size of set: " + set.size());
        System.out.println("Does the set contain `test`? " + set.contains("test"));
        System.out.println("Does the set contain `NoInThere`? " + set.contains("NoInThere"));

    }

}
