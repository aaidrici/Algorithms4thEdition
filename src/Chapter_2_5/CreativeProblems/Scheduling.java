package Chapter_2_5.CreativeProblems;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;


public class Scheduling {


    public static void main(String[] args) {

        MinPQ<Task> pq = new MinPQ<Task>(10);

        while (!StdIn.isEmpty()) {
            String taskName = StdIn.readString();
            int taskTime = StdIn.readInt();
            Task t = new Task(taskName, taskTime);
            pq.insert(t);
        }


        while (!pq.isEmpty()){
            Task T = pq.delMin();
            System.out.printf("%s, time[ms] = %d\n", T.name, T.time);
        }

    }

}

class Task implements Comparable<Task>{

    String name;
    int time; // require time in ms

    public Task(String Name, int Time){
        time = Time;
        name = Name;
    }
    public int compareTo(Task that){
        return this.time - that.time;
    }
}