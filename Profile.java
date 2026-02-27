package project1;

/**
 * Profile class represents a student profile with first name, last name, and date of birth.
 * Implements Comparable interface for profile comparison.
 * 
 * @author Your Name
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;
    
    /**
     * Constructor to create a Profile object.
     * 
     * @param fname the first name
     * @param lname the last name
     * @param dob the date of birth
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }
    
    /**
     * Gets the first name.
     * 
     * @return the first name
     */
    public String getFname() {
        return fname;
    }
    
    /**
     * Gets the last name.
     * 
     * @return the last name
     */
    public String getLname() {
        return lname;
    }
    
    /**
     * Gets the date of birth.
     * 
     * @return the date of birth
     */
    public Date getDob() {
        return dob;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Profile profile = (Profile) obj;
        return fname.equalsIgnoreCase(profile.fname) &&
               lname.equalsIgnoreCase(profile.lname) &&
               dob.equals(profile.dob);
    }
    
    @Override
    public String toString() {
        return fname + " " + lname + " " + dob.toString();
    }
    
    @Override
    public int compareTo(Profile other) {
        // Compare by last name first (case-insensitive)
        int lastNameCompare = this.lname.compareToIgnoreCase(other.lname);
        if (lastNameCompare != 0) {
            return lastNameCompare;
        }
        
        // If last names are equal, compare by first name (case-insensitive)
        int firstNameCompare = this.fname.compareToIgnoreCase(other.fname);
        if (firstNameCompare != 0) {
            return firstNameCompare;
        }
        
        // If both names are equal, compare by date of birth
        return this.dob.compareTo(other.dob);
    }
    
    /**
     * Testbed main() method to test the compareTo() method.
     * Tests 3 cases returning -1, 3 cases returning 1, and 1 case returning 0.
     */
    public static void main(String[] args) {
        System.out.println("Testing Profile.compareTo() method:");
        System.out.println("====================================");
        
        // Test cases returning -1 (this < other)
        Profile p1 = new Profile("John", "Doe", new Date(1, 1, 2000));
        Profile p2 = new Profile("Jane", "Doe", new Date(1, 1, 2000));
        System.out.println("Test 1: " + p1 + " vs " + p2 + " - compareTo(): " + p1.compareTo(p2) + " (Expected: -1, different last name)");
        
        Profile p3 = new Profile("Alice", "Smith", new Date(1, 1, 2000));
        Profile p4 = new Profile("Bob", "Smith", new Date(1, 1, 2000));
        System.out.println("Test 2: " + p3 + " vs " + p4 + " - compareTo(): " + p3.compareTo(p4) + " (Expected: -1, same last name, different first name)");
        
        Profile p5 = new Profile("John", "Doe", new Date(1, 1, 2000));
        Profile p6 = new Profile("John", "Doe", new Date(1, 2, 2000));
        System.out.println("Test 3: " + p5 + " vs " + p6 + " - compareTo(): " + p5.compareTo(p6) + " (Expected: -1, same names, different DOB)");
        
        // Test cases returning 1 (this > other)
        Profile p7 = new Profile("Jane", "Doe", new Date(1, 1, 2000));
        Profile p8 = new Profile("John", "Doe", new Date(1, 1, 2000));
        System.out.println("Test 4: " + p7 + " vs " + p8 + " - compareTo(): " + p7.compareTo(p8) + " (Expected: 1, different last name)");
        
        Profile p9 = new Profile("Bob", "Smith", new Date(1, 1, 2000));
        Profile p10 = new Profile("Alice", "Smith", new Date(1, 1, 2000));
        System.out.println("Test 5: " + p9 + " vs " + p10 + " - compareTo(): " + p9.compareTo(p10) + " (Expected: 1, same last name, different first name)");
        
        Profile p11 = new Profile("John", "Doe", new Date(1, 2, 2000));
        Profile p12 = new Profile("John", "Doe", new Date(1, 1, 2000));
        System.out.println("Test 6: " + p11 + " vs " + p12 + " - compareTo(): " + p11.compareTo(p12) + " (Expected: 1, same names, different DOB)");
        
        // Test case returning 0 (this == other)
        Profile p13 = new Profile("John", "Doe", new Date(1, 1, 2000));
        Profile p14 = new Profile("John", "Doe", new Date(1, 1, 2000));
        System.out.println("Test 7: " + p13 + " vs " + p14 + " - compareTo(): " + p13.compareTo(p14) + " (Expected: 0, identical profiles)");
    }
}
