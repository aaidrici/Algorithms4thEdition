package Chapter_5_2;

public class TriesST_nr<Value> {

    static final int R = 256;

    private Node root;

    private static class Node{
        private Object val;
        private  Node[] next = new Node[R];
    }


    public Value get(String s){
        Node node = root;
        for (int i = 0; i<s.length(); i++) {
            if (node == null) return null;
            node = node.next[s.charAt(i)];
        }
        if (node == null) return null;
        return (Value) node.val;
    }

    public void put(String s, Value v){

        if (root == null && s.length() > 0) root = new Node();
        Node node = root;

        for (int i = 0; i < s.length(); i++){
            int c = s.charAt(i);
            if (node.next[c] == null) node.next[c] = new Node();
            node = node.next[c];
        }
        node.val = v;
    }

    public static void main(String[] args){


        TriesST_nr<Integer> t = new TriesST_nr<Integer>();

        t.put("hello",1);
        Integer val = t.get("hello");
        System.out.println(val);


    }

}