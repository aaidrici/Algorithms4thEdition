package Chapter_3_4.Exercises;

public class Q3_4_10{




    public static void main(String[] args){

        char[] char_list = {'E','A','S','Y','Q','U','T','I','O','N'};


        linearProbingTable pt = new linearProbingTable(15);
        for (char x : char_list)
            pt.put(x,0);
        pt.display();

        System.out.println();
        linearProbingTable pt2 = new linearProbingTable(10);
        for (char x : char_list)
            pt2.put(x,0);
        pt2.display();

    }

}

class linearProbingTable{

    Character[] keys;
    Integer[] values;

    private int M;
    private int N;

    public linearProbingTable(int M){
        this.M = M;
        keys = new Character[M];
        values = new Integer[M];
    }

    public int hash(Character a){
        return ((int)a - 64) * 11 % M;
    }

    public void put(char key, int val){
        int k = hash(key);
        while (!(keys[k] == null || keys[k].compareTo(key) == 0)) k = (k+1)%M;
        keys[k] = key;
        values[k] = val;
    }

    public void display(){
        for (int i = 0; i<M; i++){
            if (keys[i] != null) System.out.print(keys[i] + " ");
            else System.out.print("- ");
        }
    }
}

