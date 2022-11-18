package com.nighthawk.spring_portfolio.mvc.calendar;

// Prototype Implementation

public class APCalendar {

    /** Returns true if year is a leap year and false otherwise.
     * isLeapYear(2019) returns False
     * isLeapYear(2016) returns True
     */          
    public static boolean isLeapYear(int year) {
        if(year % 4 == 0){
            return true;
        } else{
            return false;
        }
    }
        
    /** Returns the value representing the day of the week 
     * 0 denotes Sunday, 
     * 1 denotes Monday, ..., 
     * 6 denotes Saturday. 
     * firstDayOfYear(2019) returns 2 for Tuesday.
    */
    private static int firstDayOfYear(int year) {
        // implementation not shown
        
        int yearCode = (year + (year/4)) % 7;

        int January = 0;

        int dayNumber = 1;
        
        int centuryCode;

        if (year/100 == 17) {
            centuryCode = 4;
            return centuryCode;
        }
        else if (year/100 == 18) {
            centuryCode = 2;
            return centuryCode;

        }
        else if (year/100 == 19){
            centuryCode = 0;
            return centuryCode;

        }
        else if (year/100 == 20) {
            centuryCode = 6;
            return centuryCode;

        }
        else if (year/100 == 21) {
            centuryCode = 4;
            return centuryCode;

        }
        else if (year/100 == 22) {
            centuryCode = 2;
            return centuryCode;

        }
        else if (year/100 == 23) {
            centuryCode = 0;
            return centuryCode;

        }
        

        if (isLeapYear(year) == true) {
            int day = yearCode + 1 + centuryCode + 1;
            int num = day % 7;
            return num;
        }
        else {
            int day = yearCode + 0 + centuryCode + 1;
            int num = day % 7;
            return num;
        }

        if (num == 0) {
            System.out.println(num + ": Sunday");
        }
        else if (num == 1 ) {
            System.out.println(num + ": Monday");
        }
        else if (num == 2) {
            System.out.println(num + ": Tuesday");
        }
        else if (num == 3) {
            System.out.println(num + ": Wednesday");
        }
        else if (num == 4) {
            System.out.println(num + ": Thursday");
        }
        else if (num == 5) {
            System.out.println(num + ": Friday");
        }
        else if (num == 6) {
            System.out.println(num + ": Saturday");
        }


    }


    /** Returns n, where month, day, and year specify the nth day of the year.
     * This method accounts for whether year is a leap year. 
     * dayOfYear(1, 1, 2019) return 1
     * dayOfYear(3, 1, 2017) returns 60, since 2017 is not a leap year
     * dayOfYear(3, 1, 2016) returns 61, since 2016 is a leap year. 
    */ 
    private static int dayOfYear(int month, int day, int year) {
        // implementation not shown

        return 1;
        }

    /** Returns the number of leap years between year1 and year2, inclusive.
     * Precondition: 0 <= year1 <= year2
    */ 
    public static int numberOfLeapYears(int year1, int year2) {
         // to be implemented in part (a)

        return 0;
        }

    /** Returns the value representing the day of the week for the given date
     * Precondition: The date represented by month, day, year is a valid date.
    */
    public static int dayOfWeek(int month, int day, int year) { 
        // to be implemented in part (b)
        return 0;
        }

    /** Tester method */
    public static void main(String[] args) {
        // Private access modifiers
        System.out.println("firstDayOfYear: " + APCalendar.firstDayOfYear(2022));
        System.out.println("dayOfYear: " + APCalendar.dayOfYear(1, 1, 2022));

        // Public access modifiers
        System.out.println("isLeapYear: " + APCalendar.isLeapYear(2022));
        System.out.println("numberOfLeapYears: " + APCalendar.numberOfLeapYears(2000, 2022));
        System.out.println("dayOfWeek: " + APCalendar.dayOfWeek(1, 1, 2022));
    }

}