package Chapter_5_5;

public class Q5_5_19 {

    // let K be the number of arrows pointing to non-leaf nodes (i.e. node with at least one child)
    // let n be the total number of leafs
    // For any given trie, it can be proved K + 2 = n
    // Additionally, k + 1 represents the number of pivot point in the three where left and right children can be
    // swapped, meaning there are 2^(K+1) possible combination of tries where the hierarchy of frequency is conserved.
    // since n-2 = K, we have 2^(n-2+1) = 2^(n-1) possible pivot point or unique trie definition.
    //
    //
    // Note: the real number of different trie that can be generated can be higher than 2^(n-1) if nodes are not just
    // swapped within the same parent note, but also within higher order nodes.


}
