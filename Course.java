package project1;

/**
 * Course enum represents the courses available in the registration system.
 * 
 * @author Your Name
 */
public enum Course {
    CS100("CS100", 4, Standing.FRESHMAN, null),
    CS200("CS200", 4, Standing.SOPHOMORE, null),
    CS300("CS300", 4, Standing.JUNIOR, Major.CS),
    CS400("CS400", 4, Standing.JUNIOR, Major.CS),
    CS442("CS442", 3, Standing.JUNIOR, null),
    PHY100("PHY100", 5, Standing.FRESHMAN, null),
    PHY200("PHY200", 5, Standing.SOPHOMORE, null),
    ECE300("ECE300", 4, Standing.JUNIOR, Major.ECE),
    ECE400("ECE400", 4, Standing.SENIOR, Major.ECE),
    CCD("CCD", 4, Standing.FRESHMAN, null),
    HST("HST", 3, Standing.FRESHMAN, null);
    
    private final String courseNumber;
    private final int creditHours;
    private final Standing requiredStanding;
    private final Major requiredMajor;
    
    /**
     * Constructor for Course enum.
     * 
     * @param courseNumber the course number
     * @param creditHours the credit hours
     * @param requiredStanding the required standing
     * @param requiredMajor the required major (null if no major requirement)
     */
    Course(String courseNumber, int creditHours, Standing requiredStanding, Major requiredMajor) {
        this.courseNumber = courseNumber;
        this.creditHours = creditHours;
        this.requiredStanding = requiredStanding;
        this.requiredMajor = requiredMajor;
    }
    
    /**
     * Gets the course number.
     * 
     * @return the course number
     */
    public String getCourseNumber() {
        return courseNumber;
    }
    
    /**
     * Gets the credit hours.
     * 
     * @return the credit hours
     */
    public int getCreditHours() {
        return creditHours;
    }
    
    /**
     * Gets the required standing.
     * 
     * @return the required standing
     */
    public Standing getRequiredStanding() {
        return requiredStanding;
    }
    
    /**
     * Gets the required major.
     * 
     * @return the required major, or null if no major requirement
     */
    public Major getRequiredMajor() {
        return requiredMajor;
    }
    
    /**
     * Returns the Course enum from a string course number (case-insensitive).
     * 
     * @param courseNumber the course number
     * @return the Course enum, or null if not found
     */
    public static Course fromString(String courseNumber) {
        for (Course course : Course.values()) {
            if (course.courseNumber.equalsIgnoreCase(courseNumber)) {
                return course;
            }
        }
        return null;
    }
}

