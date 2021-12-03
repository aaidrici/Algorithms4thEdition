package Chapter_2_1;

public class Chapter_2_1 {
    // 2.1.1 see Chapter_2_1\2_1_1
    // 2.1.2 In selection sort, every element is involved in an exchange exactly one time.
    // 2.1.3 An array with distinct elements put in decreasing order.
    // 2.1.4 see Chapter_2_1\2_1_4
    // 2.1.5
    // 2.1.6 Insertion sort runs faster than selection sort on arrays with identical keys because the array is already sorted
    // 2.1.7 Selection sort runs faster than Insertion sort on reversely sorted arrays, because event if
    //        they both apply the same number of comparison, selection sort transfers its key directly
    //        transfers the key across the array in a single exchange operation.
    // 2.1.8  quadratic in the worst case, and linear in the base case. Here it is somewhere in between
    // 2.1.9
    // 2.1.10 On average, insertion sort is twice as fast as selection sort.
    // 2.1.11 see ShellWithArray;
    // 2.1.12
    // 2.1.13 Assuming I want to go as fast as possible, I would use shell sort:
    //        - step 1: Compute the required sequence for N = 52. This gives us (3^k - 1)/2 < N/3 -> k = {1, 4}
    //        - step 2: Sort cards from group 1 { 4*k+g ; k =0,1,2,... and g = 1} using insertion sort
    //        - repeat step 2 with groups 2,3 and 4. i.e. use g = 2,3 and 4.
    //        - final step: use insertion sort on the whole deck.
    // 2.1.14  Apply insertion sort
    //              For pass no 1, exchange the top cards if the first one has a higher value then move N-1 cards to the bottom of the deck
    //              ...
    //          ... For pass no 'n', swap the two top cards if the first one has a higher value. Then toss the top card to the bottom.
    //               This whole process must be done 'n' time with at most one exchange allowed. Then move N-n cards to the bottom of the deck
    //              at pass no 'N', the deck should be sorted
    // 2.1.15  Selection sort is impossible here, it requires space for two creates. Insertion sort
    //         is feasible as it only requires room for one more crate, however its exchange cost is
    //         exchange cost is high ~n^2/4. The solution should be Shell sort since its an optimized
    //         version of Insertion sort.
    // 2.1.16  see Exercises\Certification
    // 2.1.17  see Exercises\Insertion and Exercise\Selection
    // 2.1.18  ...
    // 2.1.19  see Exercise\Insertion, obtained 942 as the larges number so far
    // 2.1.20  for Insertion sort, minimum number of comparison is N-1.
    //         Since the h-values used are { 1 4 13 40}, N should be different for each h-value:
    //         for h=1, N=100, thus 1 x (100-1) = 99 comparisons
    //         for h=4, N=25, thus 4 x (25-1) = 96 comparisons
    //         for h=13, N=7 or 8, thus  9x(8-1) + 4x(7-1) = 87 comparisons
    //         for h=40, N=2 or 3, thus   20x(3-1) + 20x(2-1) = 60 comparisons
    //          99 + 96 + 87 + 60 = 342 comparison should be the minimum
    // 2.1.21
    // 2.1.22
    // 2.1.23  see InsertionWithSentinel and see SortCompare
    //            InsertionWithSentinel seems to be 10-15% fast with N = 100;
    // 2.1.24
    // 2.1.25  see InsertionSortWithoutExchanges, there seem to be a 20-30% gain in speed
    // 2.1.26  see PrimitiveTypeComparison, using ints instead of Integers makes it roughly 10x faster
    // 2.1.27  see ShellSortWithSelection
    // 2.1.28  both should take the same amount of time since 1 exchange is done on avg. and exactly one compare. (no validation provided)
    // 2.1.29  see ShellWithArray (new sequence seems to be 5x time slower for arrays with 1000 elements)
    // 2.1.30  see GeometricIncrement
    // 2.1.31
    // 2.1.32
    // 2.1.34
    // 2.1.35
    // 2.1.36
    // 2.1.37
    // 2.1.38














    public static void main(String[] args){

    }
}



