package Chapter_4_4.CreativeProblem;

public class Q4_4_47 {


//    Proposition I:
//    if a vertex is relaxed after K passes, its shortest path to the root found by the Bellman-Ford algorithm consists
//    of at least K edges.
//
//    Proof:
//    let v_{k}'s shortest path to s be s->v1->v2->v3... ->v_{k}. During the first pass, at least s must be relaxed since it is the only vertex with distTo[] != infinity. Its shortest path to itself is 0 edge. At every subsequent pass, at least the next one vertex - based on the above sequence - is relaxed, meaning their shortest path to the source is no smaller than n - 1, where n is the number of passes performed so far.
//    e.g. in pass#1, s,v1 and v2 are relaxed. In pass#2, v3 only is relaxed. In pass #3, v4 and v5 are relaxed.
//    A consequence of proposition I is at least one vertex has a shortest path consisting of V edges if the Bell-Ford algorithm does V passes.
//
//    Proposition II:
//    If a vertex's shortest path found by the Bellman-Forst algorithm has at least V edges, it has to include a loop.
//
//    Proof:
//    If the shortest path of a vertex has V edges, there must be at least V+1 vertices crossed along the path, meaning at
//    least one vertex is repeated. A
//
//    Proposition III:
//    If a vertex's shortest path found by the Bellman-Forst algorithm has a loop, the loop must be negative.
//
//    Proof by contraction:
//    The sum of the weight of the loop cannot be positive, otherwise the loop would not close at vertex `v` since
//    weight(sum of loop edge weight) > 0 implies ↓
//    distTo[v] + weight(sum of loop edge weight) > dist[v];




}



