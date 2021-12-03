package Chapter_5_4;

public class Q5_4_11 {

    // note: The number of different bitstring of length 1000, is 2^1000, or 1.071509e+301
    //       which is an absurdly high number.


    // 1. 2^998
    // 2. 51       (equivalent to: pick an even number between 0 and 100 where every pair of bits on the left are 00's
    //              and every bit-pair on the right are 01's)
    //
    // 3. 2^1000 - sum_{i = 0 to 999}(f_i * 2^(1000-i-1))
    //    where f_i is the i'th number of the fibonacci sequence, i.e. f_1 = 1, f_2 = 1, f_3 = 2 and so on.
    //  ... this is the least inelegant solution I was able to find.
}
