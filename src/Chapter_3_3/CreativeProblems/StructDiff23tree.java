package Chapter_3_3.CreativeProblems;


import Chapter_1_3.Queue;

public class StructDiff23tree {


    public class row {
        row parent;
        row child;
        int numberOf2Node;
        int numberOf3Node;

        public row(int n, int m){
            numberOf2Node = n;
            numberOf3Node = m;
        }
        public int sum() {return numberOf2Node + numberOf3Node; }

    }


    int height;
    row root;


    // method to generate a simple23tree from another one by generating  another layer at the bottom level
    public StructDiff23tree(int height){
        root = new row(1,0);
        row x = root;
        for (int i = 0; i<height-1; i++){
            x.child = new row((int)Math.pow(2,i+1), 0);
            x.child.parent = x;
            x = x.child;
        }

    }

    public boolean increment(){

        // go at the very bottom row
        row x = root;
        while (x.child != null) x = x.child;

        // check whether it is possible to increment the number of 3-node. If not, go
        // one row above and try again.
        while (x.numberOf2Node == 0){
            x = x.parent;
            if (x == null) return false; // it is not possible to further increment
        }

        // update the combination of 3-nodes and 2-nodes and propagate the result downwards
        x.numberOf3Node++;
        x.numberOf2Node--;

        // fill the bottom rows with the right number of 2's and no 3's
        while (x.child != null){
            x.child.numberOf3Node = 0;
            x.child.numberOf2Node = x.numberOf2Node*2 + x.numberOf3Node*3;
            x = x.child;
        }
        return true;
    }



    public void display() {
        if (root != null) {
            row x = root;
            while (x != null) {
                for (int i = 0; i < x.numberOf2Node; i++) System.out.print("2 ");
                for (int i = 0; i < x.numberOf3Node; i++) System.out.print("3 ");
                System.out.println();
                x = x.child;
            }
            System.out.println("---------------");
        }
    }


    static int nCr(int n, int r) {
        return fact(n) / (fact(r) *
                fact(n - r));
    }
    static int fact(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }


    public static void main(String[] args){

        // generating all the structurally different tree is possible using
        // a made-up tree data structure where each child branch of a node
        // represent

        Queue<StructDiff23tree> q = new Queue<StructDiff23tree>();

        int height = 3;
        StructDiff23tree t = new StructDiff23tree(height);
        int N = 1000;
        t.display();
        for (int i = 0; i<N-1;i++){
            if (!t.increment()) {
                System.out.printf("There are %d structurally different 23-tree generate with a height %d", i+1, height);
                break;
            }
            t.display();

        }



    }


}
