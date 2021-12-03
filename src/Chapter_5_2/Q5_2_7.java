package Chapter_5_2;

public class Q5_2_7 {


    // problem: the current implementation of TST doesn't handle empty string because empty
    // strings do no correspond to one of the allowed 256 characters.

    // A solution could be to:
    // 1. add the two member variables `emptyStringPresent` and `emptyStringValue`
    // 2. modify put() such that empty String can be added
    // 3. modify delete() to account for empty string deletion
    // 4. modify size() to account for empty strings presence
    // X. other changes may be needed, e.g. Iterable<String> keys()...


//          // Change #1
//          private boolean emptyStringPresent = false;
//          private Value emptyStringValue = null;
//
//          // Change #2
//          public void put(String key, Value val)
//           {
//              if (key == ""){
//                  emptyStringPresent = true;
//                  emptyStringValue = val;
//              }
//
//              root = put(root, key, val, 0);
//           }
//
//           // Change #3
//           public void delete(String s){
//              if (s == "" && emptyStringPresent){
//                  emptyStringPresent = false;
//                  emptyStringValue = null;
//              }
//           ...
//           }
//
//           // Change #4
//           public int size(){
//               if (root == null) return 0;
//               int size = root.size;
//               if (emptyStringPresent) size += 1;
//               return size;
//           }
//
//
//
//
//
    public static void main(String[] args){

        TST<Integer> tst = new TST<Integer>();
        tst.put(" ",23);


        System.out.println("".charAt(0));

    }

}
