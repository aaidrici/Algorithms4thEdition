//package Chapter_1_4;
//
//import edu.princeton.cs.algs4.In;
//
//public class MatrixLocalMinimum {
//
//
//
//
//
//
//
//
//
//
//
//
//
//    public static int MatrixLocalMin(int[][] a, int x1, int x2, int y1, int y2){
//        // x1/x2 = leftmost/rightmost accessible index
//        // y1/y2 = bottom-most/upper-most accessible;
//
//        int height = y2-y1+1;
//        int width = x2-x1+1;
//
//
//        if (size == 1) return a[0][0];
//        if (size == 2){
//
//        }
//        else {
//
//            if (height == width){ // sweep through middle row
//                int mid = y1 + height/2;
//
//                if (a[mid][x1] < a[mid][x1+1] && a[mid][x1] < a[mid+1][x1] && a[mid][x1] < a[mid-1][x1] ) return a[mid][x1];
//                if (a[mid][x2] < a[mid][x2-1] && a[mid][x2] < a[mid+1][x2] && a[mid][x2] < a[mid-1][x2] ) return a[mid][x1];
//
//                int SmallestCellId = 0;
//                for (int column = 0; column < width; column++){
//
//                    if ()
//                    if (a[mid][column] < a[mid+1][column]  &&  a[mid][x2] < a[mid][column+1]  &&  a[mid][x2] < a[mid-1][column]  &&  a[mid][x2] < a[mid][column-1]) return cellVal;
//                    else if (cellVal < temp) temp = cellVal;
//
//
//                }
//                if (a[][] > a[][])
//                    return MatrixLocalMin(a, x1, x2, mid+1, y2);
//                else
//                    return MatrixLocalMin(a, x1, x2, mid+1, y2);
//            }
//            else { // sweep through the middle column
//                int mid = x1 + width/2;
//                int temp = a[0][mid];
//                for (int row = 1; row<height; row++){
//                    int cellVal = a[row][mid];
//                    if (cellVal < a[row+1][mid] && cellVal < a[row][mid+1] && cellVal < a[row-1][mid] && cellVal < a[row][mid-1]) return cellVal;
//                    else if (cellVal < temp) temp = cellVal;
//                }
//                return MatrixLocalMin(a, x1, x2, y1, y2);
//            }
//
//
//
//
//        }
//
//
//
//
//    }
//
//
//
//
//    public static void main(String[] args){
//
//        String filename = "src/Chapter1_4_20/inputFiles/Q20.txt";
//
//
//        In file = new In(filename);
//        int[] oneDimArray = file.readAllInts();
//        int size = (int)Math.ceil(Math.sqrt(oneDimArray.length));
//        int[][] twoDimArray = new int[size][size];
//        int ind;
//        for (int row = 0; row < size; row ++)
//            for (int column = 0; column < size; column++){
//                ind = row*size + column;
//                twoDimArray[row][column] = oneDimArray[ind];
//                if (ind == oneDimArray.length-1) break;
//            }
//
//
//
//    }
//
//
//
//
//
//
//}
