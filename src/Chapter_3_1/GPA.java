package Chapter_3_1;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

public class GPA {



    public static void main(String[] args){

        ST<String, Double> st = new ST<String, Double>();
        st.put("A+",4.33);
        st.put("A",4.0);
        st.put("A-",3.67);
        st.put("B+",3.33);
        st.put("B",3.0);
        st.put("B-",2.67);
        st.put("C+",2.33);
        st.put("C",2.0);
        st.put("C-",1.67);
        st.put("D",1.0);
        st.put("F",0.0);


        double cummulativeGrades = 0.0;
        int n_grade = 0;

        String grade;
        while(!StdIn.isEmpty()){

            grade = StdIn.readString();
            if(st.contains(grade)){
                cummulativeGrades += st.get(grade);
                n_grade++;
            }

        }
        System.out.print("Resulting GPA = " + cummulativeGrades / n_grade);

    }


}
