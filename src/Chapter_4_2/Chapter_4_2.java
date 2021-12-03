package Chapter_4_2;

public class Chapter_4_2 {


    // 4.2.1  V_{max} = V*(V-1)/2, V_{min} = V-1
    // 4.2.2 see Digraph
    // 4.2.3 see Digraph
    // 4.2.4 see Digraph
    // 4.2.5 see Digraph
    // 4.2.6 see Digraph
    // 4.2.7 see Degrees
    // 4.2.8 see q4_2_8-10.jpg
    // 4.2.9 see q_2_19
    // 4.2.10 see q4_2_8-10.jpg
    // 4.2.11 Let this family include all directional graph with V vertices such that where any
    //        combination of vertices (of size `s` where 0 <= s <= V) defines a cycle. This implies
    //        the total number of distinct cycles in the graph equals the total number of possible
    //        combination, which equals:
    //              sum_{k = 0...V} : nchoosek(k,V) = 2^V
    //        The result is exponential w.r.t. the total number of vertices.
    // 4.2.12 Given a graph G, all its edges can be split in two groups:
    //           I. those making up all the paths between pairs of vertices within a strong group.
    //           II. those linking two strong groups (edges of this group are the only one shown in the kernel representation)
    //        -> When all edges belonging to group I are flipped, all strong component remain intact because any pair of
    //        vertices <v,w> within a strong components are still reachable from one another.
    //        -> When all edges belonging to group II are flipped, all strong group remain intact as well.
    //        The sink/source relationship between strong groups (defined by the orientation of group II edges) is simply
    //        flipped, which has no impact on the strong components.
    //        Consequently, when both group I and II edges are flipped, the strong component remain the same.
    // 4.2.13 I. given two vertices v and w are in the same strong component, v is reachable from w and w is reachable from v.
    //           Consequently, appending the directed path from v to w with the directed path from w to v results in a cycle (that may not be simple)
    //        II. Given there exists a cycle containing two vertices w and v, there must exists a directed path from v to w
    //            and from w to v, meaning the pair <v,w> belong to the same strong component.
    //        part I and II result in an IFF condition.
    // 4.2.14 Since there exists an edge from `v` to a vertex of a strong component C - from which v is not part of - this
    //        implies all directed path from v to any vertex of the strong component C passes by this edges. Consequently,
    //        if all edges are flipped, there exist a direct path from any vertex of the strong component C to `v` but not
    //        the other way around, since `v` is not part of the strong component C. Hence `v` occurs before all vertices of C.
    // 4.2.15  apply G^{R} to prev exercise.
    // 4.2.16
    // 4.2.17 see q4_2_17.jpg
    // 4.2.18 The strong components of a DAG are its vertices. DAG have not cycles by definition, hence strong group cannot
    //        include more than one vertex.
    // 4.2.19 Every vertex would belong to its own strong group. i.e. there would be V strong groups.

    // 4.2.20 False, unless the adjacency table is specifically arranged a certain way.
    // 4.2.21 False, a counter example is provided in q4_2_21
    // 4.2.22 True, we proved earlier the strong components of a digraph and its reverse are identical. Therefore,
    //        running the Kosaraju-Sharir algorithms on G^{R} should yield the same strong group separation.
    // 4.2.23 True, the first dfs() pass used to compute the reverse post-order of G^R remains untouched.
    //        Recall that both dfs() and bfs() have the same exploratory capability, i.e. if they are given the same graph
    //        and source vertex, the same set of vertex will be marked by the end of their recursive call.
    //        Therefore, if a recursive call - using either dfs or bfs - is initiated from an element of the reverse
    //        post-order sequence, by the end of the recursive call, the vertices marked during the recursive call will
    //        be consecutive on that reverse post-order sequence. Hence, the Kosaraju-Sharir algorithm would still work.
    // 4.2.24
    // 4.2.25 V*(V-1)/2
    // 4.2.26 same 11 edges, with those added: 1->9,0->2,0->4,5->4,2->4,0->9,5->9,6->9
    // 4.2.27 This would work only if a single BFS call to a source vertex was needed to explore the whole digraph.
    //        If more than one bfs call is needed, one source vertex of the bfs call may have a non-zero indegree
    //        and therefore cannot be in position 0 of the a topological order sequence.
    // 4.2.28 see DirectedEulerianCycle
    // 4.2.29 see LcaForDag
    // 4.2.30 see ShortestAncestralPath
    // 4.2.31
    // 4.2.32 I. Find a vertex with indegree 0 (linear time), II. run dfs (linear time). If any vertex has more than one adjacent vertex,
    //           return false. III. At the end of the recursive call, return false if not all the vertices were marked. Return true otherwise.
    // 4.2.33 Determine whether there exist a hamiltonian path using the algorithm described in question 4.2.32
    // 4.2.34
    // 4.2.35 The combination of presence/absence of directed edges between vertex can be represented by a vector of
    //        boolean of size V^2. This means there are 2^(V^2) possible outcome.
    //        ((10^80)*(10^20)*(3600*24*365)*(10^9)) / 2^(20^2) = 1/8188.2 = 0.01221%
    // 4.2.36
    // 4.2.37
    // 4.2.38
    // 4.2.39 see QueueBasedTopologicalSort
    // 4.2.40 see SmallestDigraphCycle
    // 4.2.41 This algorithm consist of a small modification to the Kosaraju-Sharir Algorithm:
    //        I. compute the reverse post-order of the G^{R}, just like in the original algorithm.
    //        II. Apply DFS() to each unmarked vertex using the order found in I, just like in the original algorithm
    //        III. while DFS() is running, label each vertex a binary color opposite to the current vertex's color.
    //             if an adjacent vertex is marked and it belongs to the same strong group and it has the same color,
    //             an odd-length cycle was found, return true. return false if no odd-length cycle is found at all.
    // 4.2.42 Determine the topological order of the DAG using the reserved post-order method. Label the very last vertex
    //        from that sequence as `v`. Reverse the Graph to obtain G^{R}. Apply DFS() or BFS() from `v` and return
    //        true if the whole is marked - otherwise return false;
    // 4.2.43 Apply the Kosaraju-Sharir Algorithm, pick any vertex 'v' part of the last strong component, reverse the input
    //        graph, apply DFS() or BFS() starting from `v` and return true if the whole graph is marked - otherwise
    //        return false.
    // 4.2.44

    // 4.2.45
    // 4.2.46
    // 4.2.47
    // 4.2.48
    // 4.2.49
    // 4.2.50
    // 4.2.51
    // 4.2.52
    // 4.2.53
    // 4.2.54
    // 4.2.55

}
