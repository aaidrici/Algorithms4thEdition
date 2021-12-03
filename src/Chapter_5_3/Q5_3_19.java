package Chapter_5_3;

public class Q5_3_19 {

// in this example, the Boyer-Moore takes ~N compares instead of ~N/M
//
// 1. M compares needed to determine c != k, skip = 1
// KAAAAAKKAAAAAKKAAAAAKKAAAAAKKAAAAAKKAAAAAKKAAAAAK
// CAAAAA
//
// 2. 1 compares needed to determine a != k, skip M
// KAAAAAKKKAAAAAKKAAAAAKKAAAAAKKAAAAAKKAAAAAKKAAAAAK
//  CAAAAA
//
// 3. previous two steps are repeated.
// KAAAAAKKAAAAAKKAAAAAKKAAAAAKKAAAAAKKAAAAAKKAAAAAK
//        CAAAAA
//...
//
// at every two step, M+1 comparison are done for every M+1 skip.





}
