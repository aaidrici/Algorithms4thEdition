package Chapter_3_5.CreativeProblems;

import Chapter_3_3.RedBlackBST;
import Chapter_3_5.Exercises.SparseVector;

public class SparseMatrix {

    // Note: By convention, Sparse Vector are assumed to be vertical

    public int a, b; // a: matrix height, b: matrix width

    RedBlackBST<Integer, SparseVector> matrix;

    public SparseMatrix(int a, int b){
        this.a = a;
        this.b = b;
        matrix = new RedBlackBST<Integer,SparseVector>();
    }

    public void put(double x, int i, int j){ // i: row position, j: column position
        if (i >= a || j >= b) return;
        SparseVector column_vector_j = matrix.get(j);
        if (column_vector_j == null){column_vector_j = new SparseVector();}
        column_vector_j.put(i,x);
        matrix.put(j, column_vector_j);
    }

    public Double get(int i, int j){
        if (i >= a || j >= b) return null;
        SparseVector t = matrix.get(j);
        if (t == null) return 0.0;
        else return t.get(i);
    }

    public void display(){
        for (int i = 0; i<b; i++){
            for (int j = 0; j<b; j++){
                System.out.printf("%8.2f", get(i,j));
            }
            System.out.println();
        }
    }

    public SparseVector getVector(int i){
        return matrix.get(i);
    }
    public void putVector(int i, SparseVector a){
        matrix.put(i , a);
    }

    public SparseMatrix sum(SparseMatrix that){
        if (that.a != this.a || that.b != this.b){
            System.out.println("Matrix size not compatible!");
            return null;
        }
        else{
            SparseMatrix result = new SparseMatrix(this.a, this.b);
            for (int i = 0; i<b; i++){
                SparseVector vec1 = this.getVector(i);
                SparseVector vec2 = that.getVector(i);
                if (vec1 != null) result.putVector(i, vec1.sum(vec2));
                else if (vec2 != null) result.putVector(i, vec2.sum(vec1));
                else result.putVector(i, new SparseVector());
            }
            return result;
        }
    }

    public static SparseMatrix RandomSparseMatrix(int a, int b){
        SparseMatrix m = new SparseMatrix(a,b);
        for (int i = 0; i<a; i++) {
            for (int j = 0; j < b; j++) {
                m.put(Math.random(), i, j);
            }
        }
        return m;
    }

    public static SparseMatrix MatrixProduct(SparseMatrix m1, SparseMatrix m2){
        // return the matrix product m1 * m2
        if (m1.b != m2.a || m1.a != m2.b) {
            System.out.print("Matrix sizes do not match");
            return null;
        }
        SparseMatrix result = new SparseMatrix(m1.a, m2.b);
        for (int i = 0; i < m1.a; i++){

            // build horizontal vector
            SparseVector v_hrztl = new SparseVector();
            for (int j = 0; j < m2.b; j++){
                SparseVector v1 = m1.getVector(j);
                if (v1 != null && v1.contains(i)) v_hrztl.put(j, v1.get(i));
            }
            //System.out.print("v_hrztl = ");
            //v_hrztl.display();
            //System.out.println();

            for (int j = 0; j< m2.b; j++){
                result.put(m2.getVector(j).dot(v_hrztl),i,j);
            }
        }
        return result;

    }



    public static void main(String[] args){


        SparseMatrix m = new SparseMatrix(4,4);
        m.put(2, 0,0);
        m.put(1, 1,1);
        m.put(1, 2, 2);
        m.put(1, 3, 3);

        SparseMatrix m2 = RandomSparseMatrix(4,4);

        SparseMatrix m3 = MatrixProduct(m2, m);

        m.display();
        System.out.println();

        m2.display();
        System.out.println();

        m3.display();



    }


}
