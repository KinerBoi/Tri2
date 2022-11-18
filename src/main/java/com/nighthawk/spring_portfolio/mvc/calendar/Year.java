package com.nighthawk.spring_portfolio.mvc.calendar;

/**
 * Simple POJO
 * Used to Interface with APCalendar
 * The toString method(s) prepares object for JSON serialization
 * Note... this is NOT an entity, just an abstraction
 */
class Year {
   private int year;
   private int month;
   private int day;
   private boolean isLeapYear;
   private int firstDayOfYear;
   private int dayOfYear;
   private int dayOfWeek;

   // zero argument constructor
   public Year() {
   }

   /* year getter/setters */
   public int getYear() {
      return year;
   }

   public void setYear(int year) {
      this.year = year;
      this.setIsLeapYear(year);
      this.setFirstDayOfYear(year);
   }

   /* date getter/setters */
   public int getDate() {
      return year;
   }

   public void setDate(int month, int day, int year) {
      this.month = month;
      this.day = day;
      this.year = year;
      this.setIsLeapYear(year);
      this.setFirstDayOfYear(year);
      this.setDayOfYear(month, day, year);
      this.setDayOfWeek(month, day, year);
   }

   /* isLeapYear getter/setters */
   public boolean getIsLeapYear(int year) {
      return APCalendar.isLeapYear(year);
   }

   private void setIsLeapYear(int year) { // this is private to avoid tampering
      this.isLeapYear = APCalendar.isLeapYear(year);
   }

   /* isLeapYearToString formatted to be mapped to JSON */
   public String isLeapYearToString() {
      return ("{ \"year\": " + this.year + ", " + "\"isLeapYear\": " + this.isLeapYear + " }");
   }

   /* firstDayOfYear getter/setters */
   public int getFirstDayOfYear(int year) {
      return APCalendar.firstDayOfYear(year);
   }

   private void setFirstDayOfYear(int year) { // this is private to avoid tampering
      this.firstDayOfYear = APCalendar.firstDayOfYear(year);
   }

   /* firstDayOfYearToString formatted to be mapped to JSON */
   public String firstDayOfYearToString() {
      return ("{ \"year\": " + this.year + ", " + "\"firstDayOfYear\": " + this.firstDayOfYear + " }");
   }

   /* dayOfYear getter/setters */
   public int getDayOfYear(int month, int day, int year) {
      return APCalendar.dayOfYear(month, day, year);
   }

   private void setDayOfYear(int month, int day, int year) { // this is private to avoid tampering
      this.dayOfYear = APCalendar.dayOfYear(month, day, year);
   }

   /* firstDayOfYearToString formatted to be mapped to JSON */
   public String dayOfYearToString() {
      return ("{ \"month\": " + this.month + ", " + "\"day\": " + this.day + ", " + "\"year\": " + this.year + ", "
            + "\"dayOfYear\": " + this.dayOfYear + " }");
   }

   /* dayOfYear getter/setters */
   public int getNumberOfLeapYears(int year1, int year2) {
      return APCalendar.numberOfLeapYears(year1, year2);
   }

   private void setNumberOfLeapYears(int year1, int year2) { // this is private to avoid tampering
      this.dayOfYear = APCalendar.numberOfLeapYears(year1, year2);
   }

   /* firstDayOfYearToString formatted to be mapped to JSON */
   public String numberOfLeapYearsToString(int year1, int year2) {
      return ("{ \"year1\": " + year1 + ", " + "\"year2\": " + year2 + ", " + "\"numberOfLeapYears\": "
            + getNumberOfLeapYears(year1, year2) + " }");
   }

   /* dayOfWeek getter/setters */
   public int getDayOfWeek(int month, int day, int year) {
      return APCalendar.dayOfWeek(month, day, year);
   }

   private void setDayOfWeek(int month, int day, int year) { // this is private to avoid tampering
      this.dayOfWeek = APCalendar.dayOfWeek(month, day, year);
   }

   /* firstDayOfYearToString formatted to be mapped to JSON */
   public String dayOfWeekToString() {
      return ("{ \"month\": " + this.month + ", " + "\"day\": " + this.day + ", " + "\"year\": " + this.year + ", "
            + "\"dayOfWeek\": " + this.dayOfWeek + " }");
   }

   /* standard toString placeholder until class is extended */
   public String toString() {
      return dayOfWeekToString();
   }

   public static void main(String[] args) {
      Year year = new Year();
      year.setYear(2022);
      year.setDate(3, 5, 2028);
      System.out.println(year.dayOfWeekToString());
   }
}