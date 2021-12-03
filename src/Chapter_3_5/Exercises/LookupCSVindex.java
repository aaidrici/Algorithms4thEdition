package Chapter_3_5.Exercises;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;


// a slightly more elegant/efficient solution could be made by re-using the code
// generated in the first question of chapter 3.5 where STs are modified to support
// index features.

public class LookupCSVindex {
        public static void main(String[] args)
        {
            In in = new In(args[0]);
            int keyField = Integer.parseInt(args[1]);
            int valField = Integer.parseInt(args[2]);
            ST<String, ArrayList<String>> st = new ST<String, ArrayList<String>>();
            while (in.hasNextLine())
            {
                String line = in.readLine();
                String[] tokens = line.split(",");
                String key = tokens[keyField];
                String val = tokens[valField];
                if (!st.contains(key)){
                    ArrayList<String> t = new ArrayList<>();
                    t.add(val);
                    st.put(key, t);
                }
                else{
                    ArrayList<String> t = st.get(key);
                    t.add(val);
                    st.put(key, t);
                }
            }
            while (!StdIn.isEmpty())
            {
                String query = StdIn.readString();
                if (st.contains(query)){
                    ArrayList<String> t = st.get(query);
                    for (String x : t) System.out.print(x + " ");
                }
                System.out.println();
            }
        }
    }