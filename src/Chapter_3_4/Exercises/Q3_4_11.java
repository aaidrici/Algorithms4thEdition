package Chapter_3_4.Exercises;

public class Q3_4_11 {




    public static void main(String[] args){

        char[] char_list = {'E','A','S','Y','Q','U','T','I','O','N'};
        linearProbingTableThatResizes pt = new linearProbingTableThatResizes(4);
        for (char x : char_list){
            pt.put(x,0);
            pt.display();
            System.out.println();
        }
    }
}

class linearProbingTableThatResizes{

    Character[] keys;
    Integer[] values;

    private int M;
    private int N;

    public linearProbingTableThatResizes(int M){
        this.M = M;
        N = 0;
        keys = new Character[M];
        values = new Integer[M];
    }

    public int hash(Character a){
        return ((int)a - 64) * 11 % M;
    }

    public void put(char key, int val){

        if (N*2 >= M) resize(M*2);

        int k = hash(key);
        while (!(keys[k] == null || keys[k].compareTo(key) == 0)) k = (k+1)%M;
        if (keys[k] == null) N++;
        keys[k] = key;
        values[k] = val;
    }

    public void resize(int M_new) {
        int oldM = M;
        M = M_new;
        N = 0;

        Character[] keysTemp = keys;
        Integer[] valuesTemp = values;
        keys = new Character[M_new];
        values = new Integer[M_new];
        for (int i = 0; i < oldM; i++) {
            if (keysTemp[i] != null) {
                put(keysTemp[i], valuesTemp[i]);
            }
        }
    }


    public void display(){
        for (int i = 0; i<M; i++){
            if (keys[i] != null) System.out.print(keys[i] + " ");
            else System.out.print("- ");
        }
    }
}