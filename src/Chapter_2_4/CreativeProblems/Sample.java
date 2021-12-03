package Chapter_2_4.CreativeProblems;
import Chapter_2_4.MaxPQ;

public class Sample {

    MaxPQ pq;
    double T;
    double[] p;

    public Sample(double[] P){


        pq = new MaxPQ(1000);
        for (int i = 0; i<P.length; i++)


        this.p = P;
        T = 0;
        for (int i = 0; i<p.length; i++) T += p[i];
    }


    public double random(int i){
        return p[i] / T;
    }

}

