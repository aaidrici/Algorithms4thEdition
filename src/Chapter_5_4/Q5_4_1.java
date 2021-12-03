package Chapter_5_4;

public class Q5_4_1 {


    // Only one one instance of 4 consecutive As and no other As
    // [^A]*A{4}[^A]*

    // No more than 4 consecutive As
    // (.{0-3}*[^A])*A{4}([^A].{0-3})*

    // At least one instance of 4 consecutive As (concatenate the previous two answers)
    // [^A]*A{4}[^A]*(.{0-3}*[^A])*A{4}([^A].{0-3})*


}
