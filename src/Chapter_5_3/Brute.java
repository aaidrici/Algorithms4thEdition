package Chapter_5_3;

import Chapter_1_3.Queue;

public class Brute {

    private String pat;
    private Queue<Integer> q;

    public Brute(String pattern){ this.pat = pattern; }

    public int search(String txt){
        q = new Queue<Integer>();

        int firstOccurence = -1;
        for (int i = 0; i < txt.length() - pat.length() + 1; i++){
            for (int j = 0; j < pat.length(); j++){
                if (txt.charAt(i+j) != pat.charAt(j)) break;
                else if (j == pat.length() - 1){
                    if (q.isEmpty()) firstOccurence = i;
                    q.enqueue(i);
                    break;
                }
            }
        }
        return firstOccurence;
    }

    public int count(){
        return q.size();
    }

    public Iterable<Integer> searchAll(){
        return q;
    }


    public static void main(String[] args){

        Brute brute = new Brute("needle");
        System.out.println(brute.search("This is a needle and this is another needle because I think you don't like needle"));

        for(Integer x : brute.searchAll()) System.out.println(x);
    }
}
