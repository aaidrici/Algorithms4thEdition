package Chapter_3_4;

public class chapter_3_4 {

    // 3.4.1 see Q3_4_1
    // 3.4.2 see SeparateChainingHashST
    // 3.4.3 see SeparateChainingHashST
    // 3.4.4 see MThatGenerateNoCollision
    // 3.4.5 Syntactically, yes. Although it is a terrible hash function: It is easy to compute, but it does not uniformly distribute the output.
    //       If we were to use this hash function for a `separate chaining`, it would equivalent to using a single sequential search on an unordered linkedList.
    //       Alternatively, if we were to use this function for `linear probing`, it would be would equivalent to using a single sequential on a unordered array.
    //
    // 3.4.26
    // 3.4.27
    // 3.4.6 see Q_3_4_6-7.jpg
    // 3.4.7 see Q_3_4_6-7.jpg                                 
    // 3.4.8
    // 3.4.9
    // 3.4.10 see Q3_4_10
    // 3.4.11 see Q3_4_11
    // 3.4.12 None of the presented sequence can be the final result of the hash table. B and C must always populate position 0 and 1, A & G must
    //        always populate position 2 & 3 and D,E & F must always populate position 4,5 and 6, regardless of their order.
    //        The minimum and maximum value are both equal to 1 + 2 + 1 + 2 + 1 + 2 + 3  = 12.
    // 3.4.13 a and c (the later being a special case of the former)
    // 3.4.14 a and c as well.
    // 3.4.15 Assuming the hash function outputs a constant index, the largest continuous array would be of size N/2. Thus, N/2 is the worst case.
    // 3.4.16 Estimation: Given the final hash table and assuming the hashing is random, there is a 0.5 change any given cell is populated by a key.
    //        There are 10'000 cells with an index multiple of 100 in 1e6. Hence there is a 1/(2^(1e4)) = 1/(1.0715e+301), which is virtually zero.
    //        Exact solution: Use a hypergeometric solution where N = 1e6, K = 1e4, n = 5e5, k = 1e4. The previous estimate can
    // 3.4.17 Resulting table:
    //          P | M | - | - | A | H | S | L | - | - | E | - | - | - | R | X |
    //          10| 9 | - | - | 8 | 4 | 0 | 11| - | - | 12| - | - | - | 3 | 7 |
    // 3.4.18 see SeparateChainingHashST
    // 3.4.19 see LinearProbingHash and SeparateChainingHashST_upgraded
    // 3.4.20 see LinearProbingHashST
    // 3.4.21 see LinearProbingHashST
    // 3.4.22
    // 3.4.23   ((hash * 256) + X) % 255
    //          ((hash * 256) % 255 + X % 255) % 255       (property of modulus operator)
    //          ((hash % 255 + X % 255) % 255              ("*256" has no influence on the result)
    //          (hash + X) % 255                           (property of modulus operator)
    //       The final hash function ends up being a modular arithmetic sum ranging from 0 to 254 inclusively.
    //       Hence any permutation of a list of character will result in the same hash output
    //
    // 3.4.24
    // 3.4.25
    // 3.4.26 see LinearProblingHashSTLazyDelete
    // 3.4.27 see SeparateChainingHashSTDoubleProbing
    // 3.4.28
    // 3.4.29
    // 3.4.30 see chi2Stats in SeparateChainingHashST
    // 3.4.31
    // 3.4.32 The resulting hash function is the SUM X_{i}*31^(n-i) [i=0 to n] where X_{i} is the i'th character of the string converted to an int
    //        For example, given n = 3, all combinations of X_{i} satisfying:
    //                X_0*31^3 + X_1*31^2 + X_2*31 + X_3 = some const. C.
    //        Should hash to the same value provided `C` is the same.
    //        To keep `C` identical, decrement X_{i} while incrementing 31 times X_{i+1}
    //        For instance, starting with X_i's = {4,3,3}, we have {3,34,3},{3,65,3},{1,96,3}
    //        hashing to same value.

    // 3.4.33
    // 3.4.34
    // 3.4.35 see Chi2Test
    // 3.4.36 see ListLengthRange
    // 3.4.37
    // 3.4.38 see SeparateChainingDistribution
    // 3.4.39
    // 3.4.40
    // 3.4.41
    // 3.4.42
    // 3.4.43
}



