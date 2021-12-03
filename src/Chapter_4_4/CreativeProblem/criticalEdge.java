package Chapter_4_4.CreativeProblem;

public class criticalEdge {

    // Strategy:
    // 0. Apply Bellman-Ford on the initial graph to find the shortest path between v and w.
    // 1. Generate a tree `G_r` using the shortest paths dictated by the edgeTo[] array
    // 2. Pick one edge along the initial shortest path and label it 'e_{0}'
    // 3. list all vertices which become isolated when `e_{0}` is removed from G_r and include e_{0}'s 2 vertices.
    // 4. Apply Bellman-Ford using the same edgeTo[] and distTo[] values generate in step 0, but override the values of
    //    edgeTo[x] = null and distTo[x] = double.infinity for all vertices x found in step 3.
    // 5. evaluate the increase in distance between w and v.
    // 6. repeat step 2 to 5 using all edges e_{i} making up the initial shortest path from v to w.
    // 7. output which edge e_{i} generates the highest increase in distance between v and w.
    //

}
