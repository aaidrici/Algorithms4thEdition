package Chapter_5_1.Experiment;

public class Q5_1_18 {


    public static String[] randomDecimalKeys(int n, int w){
        String[] keys = new String[n];
        for (int i = 0; i<n; i++){
            char[] charList = new char[w];
            for (int j = 0; j<w; j++){
                int num = (int)(Math.random() * 10) + 48;
                charList[j] = (char)(num);
            }
            keys[i] = String.valueOf(charList);
        }
        return keys;
    }

    public static void main(String[] args){
        String[] a = randomDecimalKeys(100,32);
        for (String x : a) System.out.println(x);

    }
}
