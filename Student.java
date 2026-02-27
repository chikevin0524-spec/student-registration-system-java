package project1;

/**
 * Student class represents a student in the registration system.
 * Implements Comparable interface for student comparison.
 * 
 * @author Your Name
 */
public class Student implements Comparable<Student> {
    private Profile profile;
    private Major major;
    private int creditCompleted;
    
    /**
     * Constructor to create a Student object.
     * 
     * @param profile the student's profile
     * @param major the student's major
     * @param creditCompleted the number of completed credits
     */
    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }
    
    /**
     * Gets the student's profile.
     * 
     * @return the profile
     */
    public Profile getProfile() {
        return profile;
    }
    
    /**
     * Gets the student's major.
     * 
     * @return the major
     */
    public Major getMajor() {
        return major;
    }
    
    /**
     * Gets the number of completed credits.
     * 
     * @return the completed credits
     */
    public int getCreditCompleted() {
        return creditCompleted;
    }
    
    /**
     * Gets the student's standing based on completed credits.
     * 
     * @return the standing
     */
    public Standing getStanding() {
        return Standing.fromCredits(creditCompleted);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student student = (Student) obj;
        return profile.equals(student.profile);
    }
    
    @Override
    public String toString() {
        Standing standing = getStanding();
        return "[" + profile.getFname() + " " + profile.getLname() + " " + profile.getDob() + "] " +
               "[" + major.getCode() + "," + major.getSchool() + "] " +
               "credits earned: " + creditCompleted + " [" + standing.getDisplayName() + "]";
    }
    
    @Override
    public int compareTo(Student other) {
        return this.profile.compareTo(other.profile);
    }
}
