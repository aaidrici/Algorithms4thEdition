package Chapter_1_5;

public class Chapter_1_5 {


    // QA
    // 1. Would require more information, otherwise the outcome is too arbitrary. i.e. in case we are using
    //   quick find, do we simply isolate one of two given connected nodes, or do we randomly split their component
    //   in two separate component and each half contains one of the two specified nodes.
    // 2. A model of computation where we only count accesses to a random-access memory large enough to hold the input and consider all other operations to be free.
    //
    // EXERICES
    // 1.5.1 - see page_1 of src/Chapter_1_5/writtenExercises
    // 1.5.2 - see page_1 of src/Chapter_1_5/writtenExercises
    // 1.5.3 - see page_2 of src/Chapter_1_5/writtenExercises
    // 1.5.4 - see page_3 of src/Chapter_1_5/writtenExercises
    // 1.5.5     since we have 1e3 times more sites and pairs, we can assume pID != qID almost always
    //           we perform the inner loop for every input pair
    //           -> per input pair, we have:
    //                  2 comparison instruction (~negligible)
    //                  1 comparison/return instruction if they are part of the same component (~negligible)
    //                  10 N instruction to go through the loop
    //           10^6 inputs pairs x 10^9 loop iteration/input pair x 10 instruction / loop iteration x 1e-9 sec / instruction
    //             = ... 10^7 seconds  = 115 days
    // 1.5.6      since we have 1e3 times more sites and than input pairs, we can assume find() will almost always take only one step
    //            and also that the inputs will almost always belong to different component:
    //            -> per input pair, we have:
    //                 2 fct call (for find()), each of then involving 4 instruction ( 2 * 4 = 8 instr. )
    //                 2 variable declaration (i,j) + 2 variable assignment ( 4 instr.)
    //                 4 sz[] array access + if statement +  '<' operator + var assignment  + '+= assignment'
    //                 2 id[] array access + count decrement
    //             we have ~23 instruction/pair (or some slightly higher constant...)  * 10^6 pairs * 1e-9 sec/instruction
    //             ...less than one second.
    //  1.5.7
    //     see QuickFindUF and QuickUnionUF in Exercise (un-tested)
    //  1.5.8
    //    ...
    //  1.5.9
    //    It is impossible. In QuickUnionWeighted, when there is only one link at the deepest level, it must
    //    belong to the smaller sized component that was union-ed to the main node. hence we can posit {6,5,9,4,8}
    //    were in a separate component prior to the last union.
    //    The {6,5,9,4,8} tree cannot be generate using QuickUnionWeighted because the root nodes of the previous
    //    union had to be 6 and 5. 5 belonged to a component of smaller size, yet it is the root the union. This is
    //    impossible.
    //  1.5.10
    //     resulting algorithm would be valid, but the performance benefit of QuickUnionWeighted would be lost
    //  1.5.11
    //     (answer not implemented) - there would be a performance loss compared to Quick Find, since there
    //      would still be a need to loop through the whole id[] array to update the component values. There would
    //      also be a need to keep track of the size of the component by updating sz[] (like in
    //      weightedQuickUnion)
    //  1.5.12
    //      see CreativeProblems/QuickUnionUF
    //      (0,1) -> (1,2) -> (2,3) -> (3,4) .. with (p,q) and p pointing at q
    //  1.5.13
    //       see CreativeProblems/WeightedQuickUnionUFwithCompressedPath
    //       this was done in 17 unions, see the main function for union values.
    //  1.5.14
    //       see WeightedQuickUnionUFwithHeight (untested)
    //       -> a component has its size incremented iff both merged components have the same height
    //       -> at every union with a height increase (H-1 -> H), the total number of component equals at least 2H.
    //       -> Hence, H^2 + 1 is a lower bound to the total number of element in a component (or site n)
    //       ->    H^2 + 1  < N
    //       ->    log_2{H^ 2} < log_2{H^ 2 + 1} <  log_2{N}
    //       ->    H <  log_2{N}
    //  1.5.15
    //       ...
    //  1.5.16
    //      QuickFindUF
    //  1.5.17
    //
    //  1.5.18
    //  1.5.19
    //  1.5.20
    //  1.5.21
    //  1.5.22
    //  1.5.23
    //  1.5.24
    //  1.5.25
    //  1.5.26
    public static void main(String[] args){



    }
}
