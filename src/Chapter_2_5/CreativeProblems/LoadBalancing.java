package Chapter_2_5.CreativeProblems;
import Chapter_2_4.MaxPQ;
import Chapter_2_4.MinPQ;
import Chapter_2_5.CreativeProblems.Task;
import edu.princeton.cs.algs4.StdIn;

public class LoadBalancing {



    public static void main(String[] args){

        MaxPQ<Task> taskQueue = new MaxPQ<Task>(100);

        int N = 4; // number of processors;

        // declare priority queue for processors
        MinPQ<Processor> processorQueue = new MinPQ<Processor>(N);
        for (int i = 0; i<N; i++){
            Processor p = new Processor(i);
            processorQueue.insert(p);
        }

        // read tasks and their associated time
        while (!StdIn.isEmpty()) {
            String taskName = StdIn.readString();
            int taskTime = StdIn.readInt();
            Task t = new Task(taskName, taskTime);
            taskQueue.insert(t);
        }

//        while (!taskQueue.isEmpty()){
//            Task T = taskQueue.delMax();
//            System.out.printf("%s, time[ms] = %d\n", T.name, T.time);
//        }


        // schedule the work
        while (!taskQueue.isEmpty()){
            Processor p = processorQueue.delMin();
            Task T = taskQueue.delMax();
            p.insertTask(T);
            processorQueue.insert(p);
        }

        // display resulting schedule for each processors;
        while(!processorQueue.isEmpty()){
            Processor p = processorQueue.delMin();
            System.out.printf("=== Processor %d === \n   Total job time: %d ms\n   Total no. of jobs: %d\n", p.processorId, p.TotalJobTime, p.jobs.size());
            while(!p.jobs.isEmpty()){
                Task T = p.jobs.delMax();
                System.out.printf("%-20s time[ms] = %d\n", T.name, T.time);
            }
            System.out.println();
        }

    }

}

class Processor implements Comparable<Processor>{

    int processorId;
    int TotalJobTime;
    MaxPQ<Task> jobs;

    public Processor(int id){
        processorId = id;
        jobs = new MaxPQ<Task>(1000);
    }

    public void insertTask(Task t){
        jobs.insert(t);
        TotalJobTime += t.time;
    }

    public int compareTo(Processor that){
        return this.TotalJobTime - that.TotalJobTime;
    }
}