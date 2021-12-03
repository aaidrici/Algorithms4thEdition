package Chapter_5_5;

public class Q5_5_9 {

    // Run-length compression:
    //
    // A random sequence of n 7-bit ascii character is equivalent to a random sequence of 1s and 0s of length 7n bits.
    // Because of the mean length of consecutive characters of random binary number is 2 (sum of i*2^-i for i = 1 to
    // inf equals 2), there should be on average n/2 consecutive sequences of 1s and 0s in a sequence of n binary numbers.
    // The resulting compression ratio should be on average (n/2 * 8 bits) / n bits  = 400%.
    //
    //
    // Huffman compression:
    //
    // All characters should have the same probability of occurrence, meaning the resulting trie should be close to
    // a perfectly balanced binary tree of depth 7, assuming all 7-bit ascii characters have a very large count.
    // To express the trie in bit code, (2^7)/2 0's and 127 1's are needed with 127 * 8 bits, resulting in 1088 bits
    // To express the location of each leaf for a perfectly balance binary tree of depth 7, it would take 7 bits. Thus,
    // the encoded message could be 7 bit long for each characters.
    // Finally, the compression ratio would be (1088 + 7n bits)/ (7n bits), which would converge to 100% as n -> inf.
    //
    //
    // LZW compression:
    //
    // 127^2 = 16364 gives the total number of possible combination of pairs of ascii-characters. This means there is
    // roughly a 0.0061% chance of using a code word > 0x80 after it is generated. This odd is 128 times smaller
    // for combination of 3 characters. Consequently, the majority of codewords used will have an ID < 0x80 and the
    // compression ratio should be only slightly smaller than the ratio Codeword length (L) over 7. For instance,
    // if L = 12, the compression ratio should be barely smaller than 12/7 = 170%
    //
    //
    //
    //




}
