package Chapter_5_4;

import Chapter_1_3.Queue;
import Chapter_1_3.Stack;

import Chapter_3_5.Exercises.SET;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.LinearProbingHashST;

public class NFA
{
    private char[] re; // match transitions
    private Digraph G; // epsilon transitions
    private int M; // number of states


    // Strategy:
    //  if a set is encountered while scanning the RE string, save its string definition in a hash table and
    //  associate it to the index where it was found. Also, consider the set as if it was a single letter
    //  when building the NFA.

    private LinearProbingHashST<Integer, String> sets;


    public NFA(String regexp)
    { // Create the NFA for the given regular expression.
        Stack<Integer> ops = new Stack<Integer>();
        re = regexp.toCharArray();
        M = re.length;
        G = new Digraph(M+1);

        Queue<Integer> ors;
        sets = new LinearProbingHashST<>();

        for (int i = 0; i < M; i++)
        {
            int lp = i;

            // add logic to handle sets
            if (i < M-1 && re[i] == '[') {
                int k = 0;
                while (re[i+k] != ']') k++;
                String s = regexp.substring(i+1, i+k);
                System.out.println();
                sets.put(i, s);
                i += k;
            }

            if (re[i] == '(' || re[i] == '|')
                ops.push(i);

            else if (re[i] == ')')
            {
                int or = ops.pop();

                if (re[or] == '|'){
                    ors = new Queue<Integer>();
                    while (re[or] == '|'){
                        ors.enqueue(or);
                        or = ops.pop();
                    }
                    lp = or;
                    for (int x : ors){
                        G.addEdge(x, i);
                        G.addEdge(lp, x+1);
                    }
                }
                else lp = or;

            }
            if (i < M-1 && re[i+1] == '*') // look ahead - since we look ahead, the check cannot made at i == M-1
            {
                G.addEdge(lp, i+1);
                G.addEdge(i+1, lp);
            }
            else if (i < M-1 && re[i+1] == '+')
            {
                G.addEdge(i+1, lp);
            }



            if (re[i] == '(' || re[i] == '*' || re[i] == ')' || re[i] == '+')
                G.addEdge(i, i+1);

        }
    }
    public boolean recognizes(String txt)
    { // Does the NFA recognize txt?
        Bag<Integer> pc = new Bag<Integer>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int v = 0; v < G.V(); v++)
            if (dfs.marked(v)) pc.add(v);
        for (int i = 0; i < txt.length(); i++)
        { // Compute possible NFA states for txt[i+1].
            Bag<Integer> match = new Bag<Integer>();
            for (int v : pc){
                if (v < M){
                    if (sets.contains(v)){
                        if (setMatch(txt.charAt(i), sets.get(v)))
                            match.add(v + sets.get(v).length() + 2);
                    }
                    else if (re[v] == txt.charAt(i) || re[v] == '.')
                        match.add(v+1);
                }
            }
            pc = new Bag<Integer>();
            dfs = new DirectedDFS(G, match);
            for (int v = 0; v < G.V(); v++)
                if (dfs.marked(v)) pc.add(v);
        }
        for (int v : pc) if (v == M) return true;
        return false;
    }

    private boolean setMatch(char c, String set){

        if (set.charAt(0) != '^'){
            for (int i = 0; i < set.length(); i++){
                if (i < set.length() - 2 && set.charAt(i+1) == '-'){
                    if (c >= set.charAt(i) && c <= set.charAt(i+2)) return true;
                    i+=2;
                }
                else{
                    if (c == set.charAt(i)) return true;
                }
            }
        }
        else{
            for (int i = 0; i < set.length(); i++){
                if (c == set.charAt(i)) return false;
            }
            return true;
        }

        return false;
    }



    public static void main(String[] args){
        String re = "([^ABC])*";
        NFA nfa = new NFA(re);
        boolean ans = nfa.recognizes("SDF");
        System.out.println(ans);
    }


}



