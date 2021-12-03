package Chapter_1_4;

public class Chapter_1_4 {


    // QA 1: easier to debug code + easier to compare different algorithm based on the same input
    // QA 2: this is especially true for the first few iterations where the higher order's contribution to time is not dominant yet\
    // QA 3: highest order of the time complexity function, i.e. f(N) ~ g(N) -> lim{N->inf}f(N)/g(N) = 1
    // QA 4: big O notation is used to express the worst case, which is not the same as the order of growth
    // QA 5: ...
    // QA 6: ...
    // QA 7:


    // Q1.4.1
    //    Use simple combinatorics: N choose K with K = 3 gives us N*(N-1)*(N-2)/3!
    // Q1.4.2
    //    see class ThreeSum
    // Q1.4.3
    //    see class DoublingTest
    // Q1.4.4 to 1.4.6
    //    see handwrittenAnswers folder
    // Q1.4.7
    //    ...
    // Q1.4.8
    //    see numberPairs class
    // Q1.4.9
    //    see handwrittenAnswers folder
    // Q1.4.10
    //    see BinarySearch
    // Q1.4.11
    //    see StaticSETofInts
    // Q1.4.12
    //    see pairsInBothArrays (not tested...)
    // Q1.4.13
    //   ...
    // Q1.4.14
    //  ...
    // Q1.4.15
    //    see TwoSumFaster (not tested)
    //    see ThreeSumFaster (tested)
    // Q1.4.16
    //   ... sort the array then find the smallest delta between points
    // Q1.4.17
    //   ... finding both the max and min takes linear time. Return the difference, not worth implementing.
    // Q1.4.18
    //     see ArrayLocalMinimum
    // Q1.4.19
    //   ... Algorithm is figured out, however the implementation seems tricky
    // Q1.4.20
    //   ... ~3logN method: perform binary search to find peak, then perform binary search on both sides of the peak.
    // Q1.4.21
    //
    // Q1.4.22
    //   see BinarySearchWithAdditionAndSubtraction
    // Q1.4.23
    //   ....
    // Q1.4.24
    //   case  ~logN : start at floor N/2 and go to N/4 if the eggs break or 3/4N is it doesn't. Then repeat.
    //   case  ~2LogF : start at floor 0 and increase quadratically (0->1->2->4->8->16...) until the egg breaks.
    //                  repeat the same procedure starting from the previously attempted floor where it did not fail
    // Q1.4.25
    //   ...
    // Q1.4.26
    //      Run this on matlab
    //      syms a b c
    //      pt1 = [a,a^3];
    //      pt2 = [b,b^3];
    //      pt3 = [c,c^3];
    //      v1 = pt2 - pt1;
    //      v2 = pt3 - pt2;
    //      simplify(expand(v1(1)*v2(2) - v1(2)*v2(1)))
    //      >> this gives -(a - b)*(a - c)*(b - c)*(a + b + c) which can only equal zero when a + b + c = 0, assuming a,b and c are distinct
    //
    //    Q1.4.27
    //    see QueueWithTwoStacks
    // (CREATIVE PROBLEMS)
    // Q1.4.28
    //   see StackWithAQueue (not tested)
    // Q1.4.29
    //   see StequeWithTwoStacks
    //   (note: by constant amortized number of stack and steque operation, we mean the number of operation to be applied can be proportional to the size of the stacks
    // Q1.4.30
    //
    // Q1.4.31
    //   see DequeWithThreeStack (not tested) approach is different from solutions online
    // Q1.4.32
    //   in the worst case, M array access are needed to read the data, and 2*M-1 additional array access
    //   are needed to transfer data to larger arrays. This gives us M + 2*M - 1 = 3*M -1 ~ 3*M
    // Q1.4.33
    //   since it is a 32 bit machine, padding is done s.t. memory size is a multiple of 4 bytes:
    //      Integer = 8 + 4 = 12 bytes
    //      Date = 8 + 12 = 20 bytes]
    //      Counter = 8 + 4 + 4 = 16 bytes
    //      int[] = 12 + 4*N
    //      double[] = 12 + N*8
    //      double[][] = 12 + N*((4) + 12 + M*8)
    //      String = 8 + 12 + 4 = 24 bytes
    //      Node = 8 + (4) + (4) = 24 bytes
    //      Stack = 8 + (4) + 4 = 16 bytes
    // Q1.4.34
    //   This is identical to binary search which has a tilde approximation of ~lg(N)
    // Q1.4.35
    //    line 1: N nodes created and each one had two reference.
    //    line 2: N nodes created and N Integers created (2N). Each of the N nodes has 2 refs and each of the N Integer has 1 (3N)
    //    line 3:
    //
    //
    // Q1.4.36
    //
    // (EXPERIMENTS)
    // Q1.4.37
    //   Both classes have push() and pop() operation take linear time as expected
    //   FixedCapacityStackOfInts seems to run of of memory after 1billion elements whereas FixedCapacityStack<Integer>
    //   after 100 million. This is expected since each element requires more memory. The former also has its push and pop
    //   method take significantly more time. This is also expected since it requires more time to write/read the content
    //   each element.
    // Q1.4.38
    //    See ThreeSumVsNaiveThreeSum
    //      NaiveThreeSum is expected to take 6 times longer than ThreeSum, although it take on average 5 time longer.
    //      Perhaps the result will converges to 6 as the array size becomes larger.
    // Q1.4.39
    //      see DoublingRatio, it does make results more stable
    // Q1.4.40
    //   see FrequencyOfTriplets (work in progress)
    //   N * 1/(2*M-1) should be the expected number of triplets.
    // Q1.4.41
    //    TwoSum ~200 sec
    //    TwoSumFast ~ 0.1 sec
    //    threeSum ~40136235 sec (or 464 days)
    //    threeSumFast ~15000 sec (or 4 hours)
    // Q1.4.42
    //    ...
    // Q1.4.43
    //   ...
    // Q1.4.44
    //    see BirthdayProblem
    // Q1.4.45
    //    ...   not sure what that notation NH_N means....

    public static void main(String[] args){

    }


}



