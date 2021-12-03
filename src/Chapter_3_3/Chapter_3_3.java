package Chapter_3_3;

public class Chapter_3_3 {

    // 3.3.1 see Q_3_3_1-2-3.jpg
    // 3.3.2 see Q_3_3_1-2-3.jpg
    // 3.3.3 see Q_3_3_1-2-3.jpg
    // 3.3.4 see Q_3_3_4.jpg
    // 3.3.5 see Q_3_3_5.jpg
    // 3.3.6
    // 3.3.7 trivial
    // 3.3.8 see Q_3_3_8.jpg
    // 3.3.9 iii) and iv)
    // 3.3.10 refer to Q_3_3_1-2-3.jpg
    // 3.3.11 refer to Q_3_3_1-2-3.jpg
    // 3.3.12 see Q_3_3_12-14-15.jpg
    // 3.3.13 True. Nodes are always added at the bottom with a red link, which can only alter
    //        locally the 2-3 tree, and sometime increment the tree height.
    // 3.3.14 see Q_3_3_12-14-15.jpg
    // 3.3.15 see Q_3_3_12-14-15.jpg
    // 3.3.16 see Q_3_3_12-14-15.jpg
    // 3.3.17
    // 3.3.18
    // 3.3.19
    // 3.3.20 when N = k^2 - 1 (i.e. a power of two minus one), the internal path length equals 'k'
    // 3.3.21
    // 3.3.22 This is not possible. For a given combination of keys put in a BST, the smallest height
    //        can only be achieved when the BST is either perfectly balanced (i.e. when N = 2^height - 1)
    //        or when all the leaves span 2 consecutive rows (when N != 2^height - 1).
    //        Since red-black BSTs are always balanced by definition and can hold more than one key per
    //        node, this makes it impossible for them to have a height strictly smaller than that of a
    //        BST with the same input key sequence.
    //
    // 3.3.23 see TwoThreeTreeNoBalancing
    // 3.3.24
    // 3.3.25 see TwoThreeTreeWithBalancing
    // 3.3.26 see TwoThreeSingleTopDown
    // 3.3.27 see TwoThreeRightRedLinkAllowed
    // 3.3.28
    // 3.3.29
    // 3.3.30 see RedBlackBST
    // 3.3.31 most previous questions already had it implemented. e.g. 3.3.25
    // 3.3.32
    // 3.3.33 see isRedBlackBST() method in RedBlackBST class
    // 3.3.34 see StructDiff23tree (*** not sure why result obtained is different.)
    // 3.3.35
    // 3.3.36 2-3 trees can be generalized to 2-3-4-5-6-7-8 trees:
    //        SEARCH: method is identical 2-3 but generalized:
    //        1. set root as the `currentNode`
    //        2.0 if current node is null, return null.
    //        2.1 elseif input key equals any of the 1 to 7 keys of the current node, return the value associated to the key.
    //        2.2 elseif input key < minKey(currentNode), then set current node as currentNode.left
    //        3.3 else if input key >  maxKey(currentNode), then set current node as currentNode.right
    //        3.4 if input key is between minKey() and maxKey() of the current Node, find the two consecutive key pair in the
    //            node bounding the input key and set currentNode as the child associated to that key pair.
    //        3. repeat step 2
    //        SUPPRESS:
    //        1. set currentNode as root
    //        2. Use the SEARCH method described above to find the input key to suppress, until the
    //           current node's target child is a 2-node.
    //        3.1 if the key was found, suppress the node.
    //        3.2 if currentNode's target child is a two-node child, use one of the two method described here:
    //            -> if the target 2-node child has a sibling that is at least 3-node, transfer of the
    //               immediate child's key to the target children.
    //            -> if the target 2-node child only has 2-node siblings. Drop transfer one of the parent key
    //               such that the children becomes a 3-node or a 4-node.
    //
    // 3.3.37 see NotMemoryLess
    // 3.3.38
    // 3.3.39 see RedBlackBST's deleteMin()
    // 3.3.40 see RedBlackBST's deleteMax()
    // 3.3.41

    // 3.3.42
    // 3.3.43
    // 3.3.44
    // 3.3.45
    // 3.3.46


}
