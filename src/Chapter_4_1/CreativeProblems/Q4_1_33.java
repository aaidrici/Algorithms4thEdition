package Chapter_4_1.CreativeProblems;

public class Q4_1_33 {


    //  -> Proof all graphs with no cycle are bipartite:
    //         Let D be the path length between `s` and `v`
    //         Given an arbitrary starting vertex `s`, there exist only one path between any vertex `v` and `s` if
    //         the graph has no loop. Consequently,  all vertices adjacent to `v` must have a path to `s` equal to
    //         either D + 1 or D - 1. i.e. if the depth of `v` is even, the depth of its adjacent vertices must be even.
    //         If we split all vertices based on if their path length w.r.t `s` is odd or even, we obtain a bipartite graph.
    //        -> Proof all graphs that contains loop of even-length only are bipartite:
    //         Given an arbitrary starting vertex `s`, all the different paths that can be generated
    //         between `s` and any vertex `v` are either all odd or even. The reason being any given path crossing
    //         a loop counterclockwise has an auxiliary path where the loop is crossed clockwise. Since the loop
    //         has an even length, the distance increment resulting from crossing the loop is either odd in both cases
    //         or even in both cases (the sum of 2 odd number is even, and the sum of 2 even number is also even).
    //         If we split all vertices based on if their path length w.r.t `s` is odd or even, we obtain a bipartite graph.
    //         (no matter which path is taken when crossing loops)
    //        -> Proof all graphs that contains at least one loop that is not even-length is not bipartite
    //         if any loop has an odd length, the distance between any two vertices in that loop has both an odd
    //         length path and an even length path based on whether a clockwise or counterclockwise path is taken.
    //         If we pick any of the two vertices are a source vertex, the other one has both an odd an even depth.
    //         Thus, the graph cannot be bipartite.
}
