package Chapter_5_5;

public class Q5_5_14 {


    // If all the frequencies of a set of characters were unique, the resulting the trie would not be unique
    // because any node can be swapped with its sibling, which would result in a different trie with the same
    // frequency hierarchy.

    // However, if we consider children node swap not a manipulation that can generate a different trie, then yes
    // the resulting trie from a set of unique frequency should result in a unique trie.
    // Proof: The huffman trie generation process can result in different trie, if at some point in the process,
    //        at least three node have the lowest frequency.
    //        It possible to have two node with the smallest frequency sum, say we have at some point in the trie
    //        generation process:
    //        x1, x2, x3, x4 ... xk, x_{k+1},...
    //        where
    //        x1 < x2 < x3 < x4 ... xk < x_{k+1},...
    //        It is possible pick a combination of x1 and x2 such that x1+x2 = x_{k} which would result in the next step:
    //        x3 < x4 < ... xk_{k} <= x_{k} < x_{k+1},...
    //        However, it would becomes impossible to have a third instance of x_{k} since every remaining node
    //        has a frequency sum higher than both x1 and x2, meaning any subsequent node summation will result in
    //        frequency sum strictly greater than x1 + x2 + 2.

}
