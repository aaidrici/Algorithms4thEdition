package Chapter_5_2;

public class TypingMonkey {




    // The question is ill posed. The frequency of the word length will depend on the size
    // of the sample. Small words will be be over-represented for small sample size.
    // However, long words will tend to have higher frequency of occurrence for large
    // samples a they far less likely to be repeated. i.e. for near infinite sample size,
    // Only very very large words have not been repeated before, and will thus be more likely
    // to occur.
    //
    //
    // The question can easily be answered for the case repeated words are still counted:
    //
    // I. given p < 1/26, the odds the monkey stops typing a word is 1-26p and the
    // odds the monkeys adds a character is 26p.
    //
    // II. Assuming words can be repeated:
    // P(word length = 0) = 1-26*p
    // P(word length = 1) = (1-26*p)*(26p)
    // P(word length = 2) = (1-26*p)*(26p)^2
    // P(word length = 3) = (1-26*p)*(26p)^3
    // P(word length = 4) = (1-26*p)*(26p)^4
    //     ...
    // P(word length = n) = (1-26*p)*(26p)^n
    // with sum(P(word length = i)) for i=[0,inf[ -> 1.0
    //



    public static void main(String[] args){

        int N = 10;


        double p = 0.03;
        double sum = 0;
        double P = 1-26*p;
        sum += P;

        System.out.printf("P(length = %d) = %.8f\n", 0, P);
        for (int i= 1; i< N;i++){
            P *= (26*p);
            System.out.printf("P(length = %d) = %.8f\n", i, P);
            sum += P;
        }

        System.out.println("sum = " + sum);

    }

}
