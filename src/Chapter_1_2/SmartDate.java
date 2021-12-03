package Chapter_1_2;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class SmartDate
{
    // the original code had the variable value set to private
    private int value;

    String[] weekdays = {"Monday", "Tuesday", "Wednesday","Thursday", "Friday","Saturday", "Sunday"};

    public SmartDate(int m, int d, int y)
    {
        try{
            value = y*512 + m*32 + d;
            checksDate(m,d,y);
        }
        catch(IncorrectDateInput e){
            System.out.println("Ensure year value is positive, month value is no more than 12 and day value 31 of less");
        }
    }
    public SmartDate(String stringDate){
        String[] splitString = stringDate.split("/");
        int d = Integer.parseInt(splitString[0]);
        int m = Integer.parseInt(splitString[1]);
        int y = Integer.parseInt(splitString[2]);

        try{
            value = y*512 + m*32 + d;
            checksDate(m,d,y);
        }
        catch(IncorrectDateInput e){
            System.out.println("Ensure year value is positive, month value is no more than 12 and day value 31 of less");
        }

    }


    public String dayOfTheWeek()
    {
        // assume every year start on mondays... life is too short
        int m = (value / 32) % 16;
        int d = value % 32;
        int y = value / 512;
        return weekdays[(31*m + d + 365*y) % 7];
    }

    public int month()
    { return (value / 32) % 16; }

    public int day()
    { return value % 32; }

    public int year()
    { return value / 512; }

    public String toString()
    { return month() + "/" + day()
            + "/" + year(); }

    public static void main(String[] args){

        int m = Integer.parseInt(args[0]);
        int d = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);

        SmartDate disDate = new SmartDate(m,d,y);
        System.out.println(disDate.dayOfTheWeek());

    }


    // function that will "throw new exception" if the conditions it defines are met
    // It is really just a method/function with a "throws expression" appended to its signature
    static void checksDate(int m, int d, int y) throws IncorrectDateInput{
        if ((m > 12) | (d>31) | (y < 0)){
            throw new IncorrectDateInput(m, d, y) ;
            //System.out.println("incorrect date format");
        }
    }
}

// exception definition  - it only contain the values that are used to assess if a exception must be raised
//    and not the logic used to determine if an exception must be raised.
class IncorrectDateInput extends Exception {
    int m,d,y;
    public IncorrectDateInput(int m,int d,int y){
        this.m = m;
        this.y = y;
        this.d = d;
    }
}




