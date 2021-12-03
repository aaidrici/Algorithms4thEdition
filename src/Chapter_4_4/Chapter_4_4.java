package Chapter_4_4;

public class Chapter_4_4 {

    // 4.4.1 false. e.g. given there are two paths from s to w: path I with three edges of weight 0.1 and path II with
    //       two edges of weight 0.2. Path I is shorter, but if all edges are added a weight of 0.5, path II becomes shorter.
    // 4.4.2 see EdgeWeightedDigraph
    // 4.4.3 see EdgeWeightedDigraphDense
    // 4.4.4 see Q_4_4.JPG
    // 4.4.5 see Q_4_5-6.JPG
    // 4.4.6 see Q_4_5-6.JPG
    // 4.4.7 see DijkstraSP_2ndTree
    // 4.4.8 see SptMaxLength
    // 4.4.9 Westerly to Norwich should be 18 + 12 (westerly->NewLondon->Norwich) instead of 101
    // 4.4.10
    // 4.4.11
    // 4.4.12 see Chapter_4_2.DirectedWeightedCycle and Chapter_4_2.topological
    // 4.4.13 see Q_4_13.JPG
    // 4.4.14
    // 4.4.15 the method returns null, as distTo[v] still equals Double.INFINITY.
    // 4.4.16
    // 4.4.17
    // 4.4.18 see allCriticalPaths
    // 4.4.19 see rates
    // 4.4.20
    // 4.4.21
    // 4.4.22 The number of edges can be doubled such that vertex x points to vertex x' with the weight of x->x' being
    //        the weight of x. x' can then point to all vertices x was originally pointing to with a weight of zero.
    //        e.g. path on the vertex-weighted graph: 0 -> 4 -> 5 -> 1
    //             path on the edge-weighted graph: 0->0'->4->4'->5->5'->1->1'
    // 4.4.23 see SourceSinkProblem
    // 4.4.24 see MultipleSourceSP
    // 4.4.25 see TwoSubsetSP
    // 4.4.26 see DijkstraAdjMatrix
    // 4.4.27
    // 4.4.28 see AcyclicLP
    // 4.4.29
    // 4.4.30
    // 4.4.31 There is really just one path between any pair of vertex in this case.
    //        linear pre-processing: from one end to the other, compute the sum of the edge weight encountered
    //        and assign the latest sum to the current vertex
    //        constant time distance: return the sum difference between the two vertex.
    // 4.4.32
    // 4.4.33 see gridSP
    // 4.4.34 see MonotonicSP
    // 4.4.35
    // 4.4.36 use bfs()...
    // 4.4.37 see criticalEdge
    // 4.4.38 see sensitivityAnalysis
    // 4.4.39
    // 4.4.40
    // 4.4.41
    // 4.4.42 see Q4_4_42
    // 4.4.43
    // 4.4.44
    // 4.4.45
    // 4.4.46 see Q4_4_46
    // 4.4.47 see Q4_4_47
    // 4.4.48

    // 4.4.49
    // 4.4.50
    // 4.4.51
    // 4.4.52
    // 4.4.53
    // 4.4.54
    // 4.4.55
    // 4.4.56
    // 4.4.57
    // 4.4.58
    // 4.4.59

}
