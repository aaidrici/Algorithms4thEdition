package Chapter_4_4.CreativeProblem;

public class Q4_4_46 {


    //         - Assume there exist at least one negative cycle on a graph with k vertices.
    //         - Assume the selected cycle is only made of individual edges that are the smallest path between their vertex pair.
    //         - Given v0 is the first vertex from the negative cycle that is relaxed, all other k-1 vertex should be
    //           relaxed after (at most) k-1 passes, because there are shortest paths between each other.
    //         - Once all vertices were relaxed exactly once, v_{0} should be relaxed again because:
    //            dist[k-1] - distTo[0] + weight(v_{k_1}->v_{0}) < 0        ( <- this inequality must hold for all adjacent vertices in the negative loop)
    //            dist[k-1] + weight(v_{k_1}->v_{0}) < distTo[0]
    //         - the above inequality results in an infinite loop since there always exists a vertex in the negative loop that is
    //           ineligible for relaxation.
}
