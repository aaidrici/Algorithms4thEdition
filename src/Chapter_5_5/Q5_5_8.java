package Chapter_5_5;

public class Q5_5_8 {

    // For run-length compression:
    // bit code for `ab` is 11000011100010, meaning n ab's consists of 6n series of consecutive 1s and 0s.
    // Thus, the compression ratio should be 6n * 8 bits / n*7 bits which converges to 685.7%
    //
    // For Huffman compression:
    // The size of its encoded bitstream would be 17 + 2n bits:
    //    1. Since the trie has two nodes, expressing it take 17 bits: 3 bits (to represent the main node's left and
    //       right leafs 01 and 1) and 16 bits (one byte to encode `a` and one byte for `b`). The encoded tries
    //       results in 01 01100001 1 01100010 (or 01 01100010 1 01100001)
    //    2. n bit-pair of 10s (or 10s)  are needed to express the content based on the trie.
    //    3+9-. the total amounts to 17 + 2n
    // Consequently, the compression ratio equals (17+2n)/(7*n) = which converges to 2/7 (28.5%) as n -> inf
    //
    //
    // For LZW compression:
    // The size of the compressed bitstream will be
    //   1. Since the symbols `ab` are repeated, the compressed bitstream should take the form:
    //      0x61, 0x62, 0x81, 0x83, 0x82, 0x85, 0x84, 0x87, 0x86.... with a repeated pattern of +3,-1 starting 0x82.
    //      Also, 0x81 and 0x82 both represent 2 chars, 0x83 and 0x84 both represent 3 chars and the pattern repeats.
    //   2. N bytes can represents ((N/2)^2 + (N/2)) = X input characters
    //            0 = 0.25*N^2 + 0.5N - X
    //            0 = N^2 + 2*N - 4X
    //                N = (-2 + sqrt(4+16*X))/2 = (sqrt(4+16*X)-2)/2 where X = number of input char
    // Consequently, the compression ratio equals (sqrt(4+16*X)-1)*4/7 bits



}
