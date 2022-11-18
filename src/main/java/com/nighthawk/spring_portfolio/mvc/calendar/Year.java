package com.nighthawk.spring_portfolio.mvc.calendar;

/** Simple POJO 
 * Used to Interface with APCalendar
 * The toString method(s) prepares object for JSON serialization
 * Note... this is NOT an entity, just an abstraction
 */
class Year {
   private int year;
   private boolean isLeapYear;
   private int firstDayOfYear;
   private int dayOfYear;
   private int numberOfLeapYears;
   private int dayOfWeek;
   ;



   // zero argument constructor
   public Year() {} 

   /* year getter/setters */
   public int getYear() {
      return year;
   }
   public void setYear(int year) {
      this.year = year;
      this.setIsLeapYear(year);
   }


   public boolean getisLeapYear(int year) {
      return APCalendar.isLeapYear(year);
   }
   private void setisLeapYear(int year) {  // this is private to avoid tampering
      this.isLeapYear = APCalendar.isLeapYear(year);
   }

   /* isLeapYearToString formatted to be mapped to JSON */
   public String isLeapYearToString(){
      return ( "{ \"year\": "  +this.year+  ", " + "\"isLeapYear\": "  +this.isLeapYear+ " }" );
   }	


   /* isLeapYear getter/setters */
   public int getfirstDayOfYear(int year) {
      return APCalendar.firstDayOfYear(year);
   }
   private void setfirstDayOfYear(int year) {  // this is private to avoid tampering
      this.firstDayOfYear = APCalendar.firstDayOfYear(year);
   }

   public String firstDayOfYearToString (int year) {
      return ( "{ \"year\": "  +this.year+  ", " + "\"firstDayOfYear\": "  +this.firstDayOfYear+ " }" );
     }
  

   public int getdayOfYear(int month, int day, int year) {
      return APCalendar.dayOfYear(month, day, year);
   }
   private void setdayOfYear(int month, int day, int year) {  // this is private to avoid tampering
      this.dayOfYear = APCalendar.dayOfYear(month, day, year);
   }

   public String dayOfYearToString(int year1, int year2) {
      return ( "{ \"year1\": "  + year1 +  ", " + "\"year2\": "  + year2 + ", " +  "\"numberOfLeapYears\": "  + APCalendar.numberOfLeapYears(year1, year2)+ " }" );
   }

   public int getnumberOfLeapYears(int year1, int year2) {
      return APCalendar.numberOfLeapYears(year1,year2);
   }
   private void setnumberOfLeapYears(int year1, int year2) {  // this is private to avoid tampering
      this.numberOfLeapYears = APCalendar.numberOfLeapYears(year1,year2);
   }

   

   public int getdayOfWeek(int month, int day, int year) {
      return APCalendar.dayOfWeek(month,day,year);
   }
   private void setdayOfWeek(int month, int day, int year) {  // this is private to avoid tampering
      this.dayOfWeek = APCalendar.dayOfWeek(month, day,year);
   }

   /* isLeapYearToString formatted to be mapped to JSON */

   /* standard toString placeholder until class is extended */
   public String toString() { 
      return isLeapYearToString(); 
   }

   public boolean getIsLeapYear(int year) {
      return APCalendar.isLeapYear(year);
   }
   private void setIsLeapYear(int year) {  // this is private to avoid tampering
      this.isLeapYear = APCalendar.isLeapYear(year);
   }


   public static void main(String[] args) {
      Year year = new Year();
      year.setYear(2023);
      System.out.println(year);
   }
}