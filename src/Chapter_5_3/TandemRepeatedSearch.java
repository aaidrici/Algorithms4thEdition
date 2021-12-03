package Chapter_5_3;

public class TandemRepeatedSearch {

    String pat;
    int[][] dfa;


    public TandemRepeatedSearch(String t){
        { // Build DFA from pattern.
            this.pat = t;
            int M = pat.length();
            int R = 256;
            dfa = new int[R][M];
            dfa[pat.charAt(0)][0] = 1;
            for (int X = 0, j = 1; j < M; j++)
            { // Compute dfa[][j].
                for (int c = 0; c < R; c++)
                    dfa[c][j] = dfa[c][X]; // Copy mismatch cases.


                // bottom line modified to ensure the multiple overlapping patterns can be obtained.
                // dfa[pat.charAt(j)][j] = j+1; // Set match case.
                if (j+1 != M) dfa[pat.charAt(j)][j] = j+1;

                X = dfa[pat.charAt(j)][X]; // Update restart state.
            }
        }
    }

    public int TandemSearch(String s){

        int j = 0;
        char charLast = pat.charAt(pat.length()-1);
        int count = 0;
        int maxTendem = 0;
        int id_start = -1;

        for (int i = 0; i<s.length(); i++){
            char c = s.charAt(i);

            if (j == pat.length()-1){ // about to read the last character of pattern.
                if (c == charLast){
                    count++;
                    if (count > maxTendem){
                        maxTendem = count;
                        id_start = i - count * pat.length() + 1;
                    }

                    if (i == s.length()-1) continue;

                    if (s.charAt(i+1) == pat.charAt(0)){
                        j = 0;
                    }
                    else{
                        j = dfa[c][j];
                        count = 0;
                    }
                }
                else{
                    j = dfa[c][j];
                }
            }
            else{
                j = dfa[c][j];
            }
        }

        System.out.println(maxTendem);
        if (maxTendem < 2) return -1;
        else return id_start;

    }


    public static void main(String[] args){

        TandemRepeatedSearch tsr = new TandemRepeatedSearch("catl");
        String s = "kjlascatlcatldufponcatlqfhalkcatlcatlcatlcatlncxmakcatljhsdpq";
        System.out.println(tsr.TandemSearch(s));


    }
}
