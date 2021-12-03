package Chapter_5_5;

public class Q5_5_10 {

    // refer to image Q5_5_10.jpg for the tree
    //
    // 1. to trie code takes 104 + 24 + 13 = 141 bits:
    //    13*8bits to encode the 13 ascii character = 104 bits
    //    24 links in the trie -> 24 zeros
    //    13 leafs in the trie -> 13 ones
    // 2. After the trie definition, it takes another 103 bits to code the message:
    //           n g l w i t a f h o e s sp
    //      x = [1 1 1 1 2 2 2 2 2 3 3 4 5]   <- code length
    //      y = [5 5 5 5 4 4 4 4 4 3 4 3 2]   <- instances of the characters
    //      dot(x,y) = 103
    //
    // grand total is 103 + 141 bits = 244 bits
    //




}
