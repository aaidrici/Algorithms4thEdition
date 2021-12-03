package Chapter_5_2;

public class TST_nr<Value> {

    private Node root;


    private class Node{
        Node left, right, down;
        Object value;
        char character;
    }

    public void put(String s, Value val){

        if (root == null && s.length() != 0){
            root = new Node();
            root.character = s.charAt(0);
        }
        Node node = root;

        for (int i = 0; i<s.length(); i++){
            char c = s.charAt(i);

            while (node.character != c){
                if (c < node.character){
                    if (node.left == null) {
                        node.left = new Node();
                        node.left.character = c;
                    }
                    node = node.left;
                }
                else if (c > node.character){
                    if (node.right == null){
                        node.right = new Node();
                        node.right.character = c;
                    }
                    node = node.right;
                }
            }

            if (i == s.length() - 1) continue; // last character
            if (node.down == null) {
                node.down = new Node();
                node.down.character = s.charAt(i+1);
            }
            node = node.down;
        }
        node.value = val;
    }


    public Value get(String s){
        if (s == null || s.length() == 0 || root == null) return null;
        Node node = root;
        for (int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            while (node.character != c){
                if (c < node.character) node = node.left;
                else if (c > node.character) node = node.right;
                if (node == null) return null;
            }
            if (i < s.length() - 1){
                node = node.down;
                if (node == null){
                    return null;
                }
            }
        }
        return (Value) node.value;
    }



    public static void main(String[] args){

        TST_nr<Integer> t = new TST_nr<Integer>();
        t.put("he", 3);
        t.put("travels", 2);
        System.out.println(t.get("kills"));


    }
}
