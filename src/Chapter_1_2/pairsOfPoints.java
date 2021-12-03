package Chapter_1_2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;


public class pairsOfPoints{

    double Min;
    double Max;
    double[] a1;
    double[] a2;
    int N ;

    public pairsOfPoints(int n,double max, double min){
        Min = min;
        Max = max;
        N = n;
        generatePoint(N);
        display();
        CountPairsOfOverLappingSegments();
        CountOverlappingSegment();
    }

    public void generatePoint(int N){
        a1 = new double[N];
        a2 = new double[N];
        for (int i = 0; i<N ; i++){
            a1[i] = StdRandom.uniform();
            a2[i] = StdRandom.uniform();
            if (a1[i] > a2[i]){
                double temp = a1[i];
                a1[i] = a2[i];
                a2[i] = temp;
            }
        }
    }
    public void display(){
        double height;
        for (int i =0; i<N; i++){
            height = StdRandom.uniform()*(Max-Min)+Min;
            StdDraw.line(a1[i],height, a2[i],height);
        }
    }



    public void CountPairsOfOverLappingSegments(){
        int overlapingSegmentCount = 0;
        for (int i = 0; i<N; i++){
            for (int j = i+1; j<N; j++){
                if(!(a2[i] < a1[j] | a1[i] > a2[j])) // condition for overlapping segments
                    overlapingSegmentCount++;
            }
        }
        System.out.println("Number of pairs of overlapping segments: " + overlapingSegmentCount);

    }
    public void CountOverlappingSegment(){
        // Questions 1.2.3 sounds a bit broad so let's just find the highest number of overlapping segments
        double[] a1_sortedCopy = a1.clone();
        double[] a2_sortedCopy = a2.clone();

        Arrays.sort(a1_sortedCopy);
        Arrays.sort(a2_sortedCopy);

        System.out.println("Sequence of overlapping segments (from left of right):");
        System.out.println("The highest number represents the largest number of overlapping segments");
        int overlap_count = 0;
        int a1_c = 0, a2_c = 0;
        for (int i = 0 ; i < 2*N ; i++){


            if (a1_c == N){
                overlap_count--;
                a2_c ++;
            }
            else if (a1_sortedCopy[a1_c] < a2_sortedCopy[a2_c]) {
                overlap_count++;
                a1_c++;
            }
            else{
                overlap_count--;
                a2_c ++;
            }
            System.out.print(overlap_count + "; ");
        }



    }



    public void reset(){
        a1 = null;
        a2 = null;
    }

}