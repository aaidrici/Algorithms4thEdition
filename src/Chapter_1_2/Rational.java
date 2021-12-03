package Chapter_1_2;

public class Rational {
        // overflow are handle with exceptions here...


        long num;
        long den;

        public Rational(int numerator, int denominator){
                num = numerator; den = denominator;
                normalize();
        }

        public Rational plus(Rational b){
                num = num * b.den + b.num * den;
                den = den * b.den;
                normalize();
                return this;
        }
        public Rational minus(Rational b){
                num = num * b.den - b.num * den;
                den = den * b.den;
                normalize();
                return this;
        }
        public Rational times(Rational b){

                num *= b.num;
                den *= b.den;
                normalize();
                return this;
        }
        public Rational divides(Rational b){
                num *= b.den;
                den *= b.num;
                long GCD = gcd(num, den);
                num /= GCD;
                den /= GCD;
                return this;
        }
        boolean equals(Rational that){
                if (this == that) return true;
                if (this.getClass() != that.getClass()) return false;

                this.normalize();
                that.normalize();

                if (this.num != that.num) return false;
                if (this.den != that.den) return false;

                return true;
        }

        public String toString(){
                return Long.toString(num) + "/" + Long.toString(den);
        }

        private void normalize(){
                long GCD = gcd(num, den);
                num /= GCD;
                den /= GCD;
        }

        private static long gcd(long p, long q){
                if (q == 0) return p;
                long r = p % q;
                return gcd(q, r);
        }

        static void main(String[] args){
                int a = Integer.parseInt(args[0]);
                int b = Integer.parseInt(args[1]);
                int c = Integer.parseInt(args[2]);
                int d = Integer.parseInt(args[3]);

        }
}


