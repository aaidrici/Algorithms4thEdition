package Chapter_3_5.Exercises;

import Chapter_3_4.LinearProbingHashST;
import Chapter_3_5.CreativeProblems.SparseMatrix;

public class SparseVector
{

    // notice "HashST" is replaced for "LinearProbingHashST" from the original implementation

    private LinearProbingHashST<Integer, Double> st;
    public SparseVector()
    { st = new LinearProbingHashST<Integer, Double>(); }
    public int size()
    { return st.size(); }
    public void put(int i, double x)
    { st.put(i, x); }
    public double get(int i)
    {
        if (!st.contains(i)) return 0.0;
        else return st.get(i);
    }
    public double dot(double[] that)
    {
        double sum = 0.0;
        for (int i : st.keys())
            sum += that[i]*this.get(i);
        return sum;
    }
    public double dot(SparseVector that)
    {
        double sum = 0.0;
        for (int i : st.keys()){
            if (that.contains(i)) sum += this.get(i) * that.get(i);
        }
        return sum;
    }

    // added methods

    private Iterable<Integer> keys() {return st.keys();}
    public boolean contains(int i){ return st.contains(i); }

    public SparseVector sum(SparseVector that){

        if (that == null) return this;

        SparseVector x = new SparseVector();

        for (Integer i : this.keys()) x.put(i, this.get(i));

        for (Integer i : that.keys()){
            if (!this.contains(i)) x.put(i, that.get(i));
            else {
                double sum = this.get(i) + that.get(i);
                x.put(i, sum);
            }
        }
        return x;
    }

    public void display(){
        int N = size();
        int k = 0;
        int j = 0;
        while (j < N){
            if (contains(k)) {System.out.printf("%5.2f ", get(k)); j++;}
            else System.out.printf("%5.2f ", 0.000);
            k++;
        }
    }

    public static void main(String[] argsd){

        SparseVector a = new SparseVector();
        a.put(3,1243.2);
        a.put(0,33);
        a.put(1,-10);
        a.display();
        System.out.println();

        SparseVector b = new SparseVector();
        b.put(1,1243.2);
        b.put(2,428);
        b.display();
        System.out.println();

        a = a.sum(b);
        a.display();

    }


}
