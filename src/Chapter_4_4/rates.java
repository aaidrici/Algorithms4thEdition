package Chapter_4_4;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class rates {

    double[][] rates;
    int N;
    IndexMinPQ<Double> q;
    Queue<Integer> bestPath;
    String[] currencyName;

    double bestRate;

    public rates(In in){

        N = in.readInt();
        in.readLine();
        bestRate = 0.00;
        bestPath = null;
        currencyName = new String[N];
        rates = new double[N][N];
        Queue<String> currencyNames = new Queue<String>();

        for (int i = 0; i<N; i++){
            String[] line = in.readLine().split("\\s+");
            currencyName[i] = line[0];
            for (int j = 0; j < N; j++)
                rates[i][j] = Double.parseDouble(line[j+1]);
        }



        iter(new Queue<Integer>());
    }

    public void iter(Queue<Integer> input){

        boolean[] checked = new boolean[N];
        for (Integer x : input) checked[x] = true;
        for (int n = 0; n<N; n++){

            if (!checked[n]) {

                Queue<Integer> input_copy1 = new Queue<Integer>(); // used for recursive calls
                Queue<Integer> input_copy2 = new Queue<Integer>(); // used for computing rate of new combination
                for (int x: input) {
                    input_copy1.enqueue(x);
                    input_copy2.enqueue(x);
                }
                input_copy1.enqueue(n);

                input_copy2.enqueue(n);
                input_copy2.enqueue(input_copy2.peek());

//                // print the queue
//                for (int x : input_copy2) System.out.print(x + " ");
//                // print the rates
//                System.out.printf("rate: %.4f\n", rate(input_copy2));

                Double r = rate(input_copy2);
                if (r > bestRate){
                    bestRate = r;
                    bestPath = input_copy2;
                }

                iter(input_copy1);
            }
        }
    }


    public Double rate(Iterable<Integer> seq){
        Double effectiveRate = 1.0;
        Integer x_prev = null;
        for (int x : seq){
            if (x_prev == null) {
                x_prev = x;
                continue;
            }
            effectiveRate *= rates[x_prev][x];
            x_prev = x;
        }
        return effectiveRate;
    }

    public Iterable<String> bestPath(){
        Queue<String> path = new Queue<String>();
        for (int x : bestPath) path.enqueue(currencyName[x]);
        return path;
    }

    public static void main(String[] args){

        String filename = "Chapter_4_4\\rates.txt";
        In in = new In(filename);
        rates r = new rates(in);

        System.out.printf("Best rate: %.4f\n", r.bestRate);
        for (String x : r.bestPath()) System.out.print(" ->" + x );




    }
}



