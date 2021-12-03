
package Chapter_2_4;

public class Chapter_2_4 {


    // 2.4.1 returned sequence: "R R P O T Y I I U QE"
    // 2.4.2 returning the maximum value takes constant time, but inserting an element takes linear time.
    // 2.4.3 see PQimplementations
    //              OPERATION:     | RemoveMax()         |   Insert()
    //              -------------------------------------------------------
    //              unsorted PQ    |     ~n (amortized)  |     ~1
    //              sorted PQ      |     ~1              |     ~n (amortized)
    //              unsorted LL PQ |     ~n              |     ~1
    //              sorted LL PQ   |     ~1              |     ~n
    //
    // 2.4.4 No. A maximum oriented heap has its largest element at index 1 and is not necessary monotonically decreasing
    // 2.4.5 Y T U S Q N E A S I O E
    // 2.4.6 see Exercises/2.4.6.JPG
    // 2.4.7 i) anywhere between [ 2^floor(log2(k)) , min(heap_size, 2^k-1)]
    //       ii) using the formula above
    //        for k = 2, anywhere outside [2,3] but within [1,31]
    //        for k = 3, anywhere outside [2,7] but within [1,31]
    //        for k = 4, anywhere outside [4,15] but within [1,31]
    // 2.4.8 The kth smallest item is the (31-k+1)th largest item. The formula defined in 2.4.7 can be used using this fact
    // 2.4.9 There are four distinct heaps: ECDAB, ECDBA, EDCAB and EDCBA
    //       There are two distinct heaps: BBAAA and BABAA
    // 2.4.10 (k+1)*2 and (k+1)*2+1
    // 2.4.11 Unordered arrays should be more efficient, but Heap might also be efficient based on the exact number of Insert() and removeMax()
    // 2.4.12 Ordered arrays should be more efficient, but again, heap might also the better option.
    // 2.4.13
    // 2.4.14 heap of size N should have a depth 'b' equal to floor(log2(N))+1 meaning the very last element which gets
    //        to position one during sink() cannot be more than the b'th largest element.
    //        The highest of the heap-tree the b'th element can be is floor(log2(b))+1.
    //        Thus the minimal number of exch() is floor(log2(b)) = floor(log2(floor(log2(N))+1))
    //
    // 2.4.15 see Exercises/LinearHeapCertificate
    // 2.4.16 any array that is already heap sorted will minimize the number of sink(), and thus compare calls.
    //        e.g. [32, 31, 30, 29, ... 3, 2, 1]
    //        any minimum oriented heap should maximize the number of sink() calls during heap construction
    //        e.g. [1,2,3,... 29,30,31,32]
    // 2.4.17
    // 2.4.18
    // 2.4.19 see Chapter_2_4.MaxPQ
    // 2.4.20 sink-based construction uses N/2 sink() calls, and each calls makes at most 4 comparisons, hence 4*N/2 = 2N
    //        additionally, each sink() call makes 2 exch() calls, hence 2*N/2 = N

    // 2.4.21 Stack: Associate each item pushed to a key that is incremented after each push. A push() call
    //               consists of inserting the key value to the PQ. A RemoveMax call on the PQ then returns
    //               the key associated to the very last value pushed.
    //        Queue: Same idea as with stack, but the key must be decremented instead. Each removeMax call will return
    //               the key associated to the first
    //        Random Queue:    RandomDequeue()
    //                      Generate a random number n between 0 and N-1 (N = heap size). Then transfer n elements from
    //                      the main heap to a secondary heap using removeMax() and insert() back to back. RemoveMax()
    //                      from the main heap the random dequeued item. Then transfer back n elements from
    //                      the 2nd heap to the main heap using removeMax() and insert() back to back.
    //                         Sample()
    //                      Trivial using approach above
    //                         Enqueue()
    //                      Use insert()
    // 2.4.22 see Chapter_2_4.MaxPQ (not tested...)
    // 2.4.23
    // 2.4.24 see PQWithExplicitLinks
    // 2.4.25
    // 2.4.26 see MaxPQ.sinkFast() and MaxPQ.swimFast()
    // 2.4.27
    // 2.4.28 see ClosestEuclideanPoints
    // 2.4.29   ... trivial
    // 2.4.30 see DynamicMedianFinding
    // 2.4.31
    // 2.4.32
    // 2.4.33 see IndexPQ
    // 2.4.34 see IndexPQ
    // 2.4.35
    // 2.4.36 see PerformanceDriver_I
    // 2.4.37
    // 2.4.38
    // 2.4.39 see HeapConstructionTime. Proportion of time allocated to heap construction seem to decrease with heap size.
    // 2.4.40
    // 2.4.41
    // 2.4.43




}

