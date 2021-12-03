package Chapter_1_3;

public class Josephus{

    // do not populate constructor
    public Josephus(){}


    public static void main(String[] args){

        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);


        Queue<Integer> circleOfPeople = new Queue<Integer>();

        for (int i = 1; i<=n ; i++)
            circleOfPeople.enqueue(i);

        for (int i = 0; i<n ; i++){
            int indexOfPersonKilled = circleOfPeople.dequeue();
            System.out.print(indexOfPersonKilled + "->");
            if (!circleOfPeople.isEmpty()){
                for (int k=0; k<m;k++){
                    int temp = circleOfPeople.dequeue();
                    circleOfPeople.enqueue(temp);
                }
            }
        }
    }
}
