package Chapter_5_5;

public class Q5_5_7 {

    // For run-length compression:
    // bit code for `a` is 1100001, meaning n a's consists of 2n+1 series of consecutive 1s and 0s.
    // Thus, the compression ratio should be (2n+1) * 8 bits / n*7,  bits which converges to 228.5%
    //
    //
    // For Huffman compression:
    // The size of its encoded bitstream would be 8 + n bits:
    //    1. Since the trie has only one node, expressing it take 1 bit (as a 1 to signal the only node is a leaf)
    //       with 7 more bits to express the character `a`
    //    2. n bits (of 1s) are needed to express the content based on the trie.
    //    3. the total amounts to 1 + 7 + n
    // Consequently, the compression ratio equals (8+n)/(7*n) = 8/(7n) + 1/7 which converges to 1/7 at n -> inf
    //
    //
    // For LZW compression:
    // The size of the compressed bitstream will be  (sqrt(1+8*X)-1)/2 * 8 bits
    //   1. Since the symbol `a` is repeated, the compressed bitstream should take the form 0x41, 0x81, 0x82...
    //      where 0x41 represents `a`, 0x81 represents `aa`, 0x82 represents `aaa` and so forth.
    //   2. N bytes can represents 1/2 * (N^2 + N) = X input characters
    //            0 = N^2 + N - 2X
    //                N = (-1 + sqrt(1+4*2X))/2 = (sqrt(1+8*X)-1)/2
    // Consequently, the compression ratio equals (sqrt(1+8*X)-1)*4/7 bits
}
