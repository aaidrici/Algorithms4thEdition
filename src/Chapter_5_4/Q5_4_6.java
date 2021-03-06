package Chapter_5_4;

public class Q5_4_6 {


    //sequence: A B B A C E F G E F G C A A B


    // A {7, 8, 3, 4, \6, 9, 17, 18, 2, 10, 14, 19, \20, 1, 21, 22, 23*} <- states that can be reached after reading `A`
    // B {7, 8, 3, 4, \6, 9, 17, 18, 2, 10, 14, 19, \20, 1, 21, 22, 23*}
    // B {7, 8, 3, \4, 6, 9, 17, 18, 2, 10, 14, 19, \20, 1, 21, 22, 23*}
    // A {7, 8, 3, 4, 6, 9, 17, 18, 2, \10, 14, 19, \20, 1, 21, 22, 23*}
    // C {7, 8, 3, 4, 6, 9, 17, 18, 2, 10, \14, 19, \20, 1, 21, 22, 23*}
    // E {\15, 22, 23*}
    // F {\16}
    // G {7, 8, 3, 4, 9, 17, 18, 2, 10, \14, 19, \20, 1, 21, 22, 23*}
    // E {\15, 22, 23*}
    // F {\16}
    // G {7, 8, 3, 4, 9, 17, 18, 2, \10, 14, 19, \20, 1, 21, 22, 23*}
    // C {7, 8, 3, \4, 6, 9, 17, 18, 2, 10, 14, 19, \20, 1, 21, 22, 23*}
    // A {7, 8, 3, \4, 6, 9, 17, 18, 2, 10, 14, 19, \20, 1, 21, 22, 23*}
    // A {7, 8, 3, \4, 6, 9, 17, 18, 2, 10, 14, 19, \20, 1, 21, 22, 23*}
    // B {7, 8, 3, 4, 6, 9, 17, 18, 2, 10, 14, 19, 20, 1, 21, 22, 23*} <- final state is still accessible. String matches the RE.


    // note: 23* = final state
    // \XX : state from which the next character can be reached.
}
