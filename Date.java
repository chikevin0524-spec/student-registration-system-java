package project1;

import java.util.Calendar;

/**
 * Date class represents a calendar date with year, month, and day.
 * Implements Comparable interface for date comparison.
 * 
 * @author Your Name
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    
    // Constants for leap year calculation
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int JAN = Calendar.JANUARY + 1; // Calendar.JANUARY is 0
    public static final int FEB = Calendar.FEBRUARY + 1;
    public static final int MAR = Calendar.MARCH + 1;
    public static final int APR = Calendar.APRIL + 1;
    public static final int MAY = Calendar.MAY + 1;
    public static final int JUN = Calendar.JUNE + 1;
    public static final int JUL = Calendar.JULY + 1;
    public static final int AUG = Calendar.AUGUST + 1;
    public static final int SEP = Calendar.SEPTEMBER + 1;
    public static final int OCT = Calendar.OCTOBER + 1;
    public static final int NOV = Calendar.NOVEMBER + 1;
    public static final int DEC = Calendar.DECEMBER + 1;
    
    // Days in each month (non-leap year)
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    /**
     * Constructor to create a Date object.
     * 
     * @param month the month (1-12)
     * @param day the day
     * @param year the year
     */
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }
    
    /**
     * Constructor to create a Date from a string in mm/dd/yyyy format.
     * 
     * @param dateStr the date string in mm/dd/yyyy format
     */
    public Date(String dateStr) {
        String[] parts = dateStr.split("/");
        this.month = Integer.parseInt(parts[0]);
        this.day = Integer.parseInt(parts[1]);
        this.year = Integer.parseInt(parts[2]);
    }
    
    /**
     * Checks if a year is a leap year.
     * 
     * @param year the year to check
     * @return true if the year is a leap year, false otherwise
     */
    private boolean isLeap(int year) {
        // Step 1: If the year is evenly divisible by 4, go to step 2. Otherwise, go to step 5.
        if (year % QUADRENNIAL == 0) {
            // Step 2: If the year is evenly divisible by 100, go to step 3. Otherwise, go to step 4.
            if (year % CENTENNIAL == 0) {
                // Step 3: If the year is evenly divisible by 400, go to step 4. Otherwise, go to step 5.
                if (year % QUATERCENTENNIAL == 0) {
                    // Step 4: The year is a leap year.
                    return true;
                } else {
                    // Step 5: The year is not a leap year.
                    return false;
                }
            } else {
                // Step 4: The year is a leap year.
                return true;
            }
        } else {
            // Step 5: The year is not a leap year.
            return false;
        }
    }
    
    /**
     * Checks if the date is a valid calendar date.
     * 
     * @return true if the date is valid, false otherwise
     */
    public boolean isValid() {
        // Check if month is valid (1-12)
        if (month < JAN || month > DEC) {
            return false;
        }
        
        // Check if year is valid (positive)
        if (year <= 0) {
            return false;
        }
        
        // Check if day is valid
        int maxDays = DAYS_IN_MONTH[month - 1];
        
        // Adjust for February in leap years
        if (month == FEB && isLeap(year)) {
            maxDays = 29;
        }
        
        if (day < 1 || day > maxDays) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Checks if this date is today or a future date.
     * 
     * @return true if this date is today or in the future, false otherwise
     */
    public boolean isTodayOrFuture() {
        Calendar today = Calendar.getInstance();
        Calendar thisDate = Calendar.getInstance();
        thisDate.set(year, month - 1, day); // Calendar months are 0-based
        
        return !thisDate.before(today);
    }
    
    /**
     * Calculates the age in years based on this date of birth.
     * 
     * @return the age in years
     */
    public int getAge() {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(year, month - 1, day);
        
        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        
        // Adjust if birthday hasn't occurred this year
        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        
        return age;
    }
    
    /**
     * Gets the year.
     * 
     * @return the year
     */
    public int getYear() {
        return year;
    }
    
    /**
     * Gets the month.
     * 
     * @return the month
     */
    public int getMonth() {
        return month;
    }
    
    /**
     * Gets the day.
     * 
     * @return the day
     */
    public int getDay() {
        return day;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Date date = (Date) obj;
        return year == date.year && month == date.month && day == date.day;
    }
    
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }
    
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        }
        if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        }
        return Integer.compare(this.day, other.day);
    }
    
    /**
     * Testbed main() method to test the isValid() method.
     * Tests 4 invalid cases and 2 valid cases.
     */
    public static void main(String[] args) {
        System.out.println("Testing Date.isValid() method:");
        System.out.println("=================================");
        
        // Test case 1: Invalid - month out of range (0)
        Date date1 = new Date(0, 15, 2000);
        System.out.println("Test 1: Date " + date1 + " - isValid(): " + date1.isValid() + " (Expected: false)");
        
        // Test case 2: Invalid - day out of range (32 for January)
        Date date2 = new Date(1, 32, 2000);
        System.out.println("Test 2: Date " + date2 + " - isValid(): " + date2.isValid() + " (Expected: false)");
        
        // Test case 3: Invalid - February 30 in non-leap year
        Date date3 = new Date(2, 30, 2001);
        System.out.println("Test 3: Date " + date3 + " - isValid(): " + date3.isValid() + " (Expected: false)");
        
        // Test case 4: Invalid - February 30 in leap year (still invalid)
        Date date4 = new Date(2, 30, 2000);
        System.out.println("Test 4: Date " + date4 + " - isValid(): " + date4.isValid() + " (Expected: false)");
        
        // Test case 5: Valid - normal date
        Date date5 = new Date(5, 15, 2000);
        System.out.println("Test 5: Date " + date5 + " - isValid(): " + date5.isValid() + " (Expected: true)");
        
        // Test case 6: Valid - February 29 in leap year
        Date date6 = new Date(2, 29, 2000);
        System.out.println("Test 6: Date " + date6 + " - isValid(): " + date6.isValid() + " (Expected: true)");
    }
}
