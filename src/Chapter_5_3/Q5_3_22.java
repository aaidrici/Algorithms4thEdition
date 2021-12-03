package Chapter_5_3;

public class Q5_3_22 {

// To evaluate the hash using the Rabin-karp method for an w * h matrix,
//  The first h element - making up the first row - could be multiplied by <R^0, R^1, ... R^(h-1)>
//  while the next h element could be multiplied by <R^h, R^(h+1), ... , R^(2h-1)>
//  The matrix's hash consist of the sum of hash for each row. 
//
//  To compute the hash for a matrix using the Rabin-Karp trick:
//
//  When translating the w*h matrix from left to right, w * 7 operation would be needed to discard the leftmost
//  elements and append an element to the right for each of the w rows. RM for row 1 is R^(M-1)%Q, RM for row 2
//  is R^(2M-1)%Q, and so forth.
//
//  When translating the w*h matrix from downwards
//
//
}
