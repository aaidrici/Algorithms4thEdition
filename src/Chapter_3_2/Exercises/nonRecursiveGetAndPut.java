package Chapter_3_2.Exercises;

import Chapter_3_2.BST;
import edu.princeton.cs.algs4.Queue;

public class nonRecursiveGetAndPut <Key extends Comparable<Key>, Value> extends BST<Key, Value> {

    Queue<Node> tempQueue;


    public int size(Node x){
        if (x == null) return 0; 
        else return x.N;
    }

    @Override
    public void put(Key key, Value value){
          Node node = root;
          Node prevNode = null;
          int cmp = 0;

          tempQueue = new Queue<Node>();


          while (node != null){
              cmp = key.compareTo(node.key);
              if (cmp == 0){
                  node.val = value; // there is no need to update the tree size
                  return;
              }
              prevNode = node;
              if (cmp > 0){
                  tempQueue.enqueue(node);
                  node = node.right;
              }
              else{
                  tempQueue.enqueue(node);
                  node = node.left;
              }

          }

          if (prevNode == null) root = new Node(key, value, 1);
          else if (cmp > 0)  prevNode.right = new Node(key, value, 1);
          else prevNode.left = new Node(key, value, 1);

          for (Node x : tempQueue) x.N ++;

    }

    @Override
    public Value get(Key key){
        Node node = root;

        while (node != null){
            int cmp = key.compareTo(node.key);
            if (cmp == 0)
                return node.val;
            else if (cmp > 0)
                node = node.left;
            else
                node = node.right;
        }
        return null;
    }


    // Q3.2.14
    @Override
    public Key max(){
        Node x = root;
        if (x == null) return null;
        while (x.right != null) x = x.right;
        return x.key;
    }
    @Override
    public Key min(){
        Node x = root;
        if (x == null) return null;
        while (x.left != null) x = x.left;
        return x.key;
    }
    @Override
    public Key ceiling(Key k){

        Node x = root;
        Node tempCeil = null;
        int cmp;

        while (true){

            if (x == null){
                if (tempCeil != null) return tempCeil.key;
                return null;
            }

            cmp = x.key.compareTo(k);

            if (cmp == 0) return x.key;

            else if (cmp > 0){  // didn't bust, keep going down
                tempCeil = x;
                x = x.left;
            }
            else { // busted, go right
                x = x.right;
            }
        }

    }
    @Override
    public Key floor(Key k){

        Node x = root;
        Node tempFloor = null;
        int cmp;

        while (true){

            if (x == null){
                if (tempFloor != null) return tempFloor.key;
                return null;
            }

            cmp = x.key.compareTo(k);

            if (cmp == 0) return x.key;

            else if (cmp < 0){  // didn't bust, keep going down
                tempFloor = x;
                x = x.right;
            }
            else { // busted, go right
                x = x.left;
            }
        }

    }
    @Override
    public int rank(Key key){
        Key k = ceiling(key);

        if (k == null) return size(root);
        // form this point, we know there is at least one node in the queue

        Queue<Node> tempPath = getPath(k);

  //        if (tempPath != null){
//            for (Node x : tempPath) System.out.print(x.key + " ");
//            System.out.println();
//        }

        // count the number of elements of the right
        Node parent = tempPath.dequeue();
        Node child = null;
        int count = 0;

        while (!tempPath.isEmpty()){
            child = tempPath.dequeue();
            if (child == parent.left)
                count += 1 + size(parent.right);
            parent = child;
        }
        count += size(parent.right);

        return size(root) - count - 1;
    }
    @Override
    public Key select(int n){
        Node child = root;
        Node parent = null;
        int count = 0;
        int n_nodes_left;

        while(child != null){
            n_nodes_left = count + size(child.left);

            if (n == n_nodes_left) return child.key;
            else if (n > n_nodes_left){
                parent = child;
                child = child.right;
                count += 1 + size(parent.left);
            }
            else{
                parent = child;
                child = child.left;
            }
        }
        return null;

    }



    public int getSize(Key key){
        Node node = root;

        while (node != null){
            int cmp = node.key.compareTo(key);
            if (cmp == 0)
                return node.N;
            else if (cmp > 0)
                node = node.left;
            else
                node = node.right;
        }
        return -1;
    }

    public Queue<Node> getPath(Key k){
        Queue<Node> tempPath = new Queue<Node>();

        Node node = root;

        while (node != null){
            tempPath.enqueue(node);

            int cmp = k.compareTo(node.key);
            if (cmp == 0){
                return tempPath;
            }
            else if (cmp > 0)
                node = node.right;
            else
                node = node.left;
        }
        return null;
    }



    public static void main(String[] args){
        nonRecursiveGetAndPut<Integer, Integer> bst = new nonRecursiveGetAndPut<>();

//        Integer[] sequence = {2, -2, 10, 15, -8, 7, 14};
        Integer[] sequence = {0, 6, 2, 10, 4, 8, 12, -12, -18, -10, -22, -16, -14, -4, -20, -8, -2, -6};

        int i = 0;
        for (Integer x : sequence) bst.put(x, i++);

        for (Integer x : bst.keys()) System.out.print(x + " ");
        System.out.println();

        System.out.println(bst.rank(-11));

    }



}
