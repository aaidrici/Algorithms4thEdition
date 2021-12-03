package Chapter_1_2;

public class Transaction {

    double amount;
    Date when;
    String who;


    public Transaction(String who, Date when, double amount){
        this.amount = amount;
        this.when = when;
        this.who = who;
    }
    public Transaction(String transaction){

    }

    String who(){ return who; }
    Date when(){ return when; }
    double amount(){return amount; }


    public boolean equals(Transaction x){
        if (this == x) return true;
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        if (x.amount() != amount) return false;
        if (x.who() != who) return false;
        if (this.when.equals(x.when()));
        return true;
    }

}


class Date
{
    private final int month;
    private final int day;
    private final int year;
    public Date(int m, int d, int y)
    { month = m; day = d; year = y; }
    public int month()
    { return month; }
    public int day()
    { return day; }
    public int year()
    { return day; }
    public String toString()
    { return month() + "/" + day()
            + "/" + year(); }


    public boolean equals(Object x)
    {
        if (this == x) return true;
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Date that = (Date) x;
        if (this.day != that.day) return false;
        if (this.month != that.month) return false;
        if (this.year != that.year) return false;
        return true;
    }
}