package Chapter_3_4.CreativeProblems;

import Chapter_3_4.LinearProbingHashST;


public class LinearProblingHashSTLazyDelete<Key, Value> extends LinearProbingHashST<Key, Value> {

    private int n_nullval = 0;

    public LinearProblingHashSTLazyDelete(){}

    // implement lazy delete for 3.4.26
    // key is preserved, but value is set to null
    public void delete(Key key){
        for (int i = 0; i<M; i++){
            if (keys[i] == key) {
                vals[i] = null;
                N--;
                return;
            }
        }
    }


    @Override
    // resize is changed s.t. cells with empty values have their keys set to null
    protected void resize(int cap) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(cap);
        for (int i = 0; i < M; i++)
            if (vals[i] != null) {        // <- this line only has changed
                t.put(keys[i], vals[i]);
            }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    // calls delete() on a randomly selected non-empty cell of the linearProbing hash table
    private void randomDelete(){
        if (N == 0) return;
        int i = (int)(M * Math.random());
        while (vals[i] == null) i = (i+1)%M;
        delete(keys[i]);
    }



    public static void main(String[] args){

        final int RANDVAL = 1000;
        LinearProblingHashSTLazyDelete<Integer, Integer> st = new LinearProblingHashSTLazyDelete<>();

        for (int i = 0; i<100; i++){
            int x = (int)(Math.random()*RANDVAL);
            st.put(x,x);

            // delete one random keys every two put() calls
            if (i%2 == 1) st.randomDelete();

            st.display();
        }



    }
}
