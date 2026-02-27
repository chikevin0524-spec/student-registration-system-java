package project1;

/**
 * Major enum represents the 5 majors available in the registration system.
 * 
 * @author Your Name
 */
public enum Major {
    CS("CS", "School of Arts & Sciences"),
    ECE("ECE", "School of Engineering"),
    MATH("MATH", "School of Arts & Sciences"),
    ITI("ITI", "School of Communication and Information"),
    BAIT("BAIT", "Rutgers Business School");
    
    private final String code;
    private final String school;
    
    /**
     * Constructor for Major enum.
     * 
     * @param code the major code
     * @param school the school name
     */
    Major(String code, String school) {
        this.code = code;
        this.school = school;
    }
    
    /**
     * Gets the major code.
     * 
     * @return the major code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * Gets the school name.
     * 
     * @return the school name
     */
    public String getSchool() {
        return school;
    }
    
    /**
     * Returns the Major enum from a string code (case-insensitive).
     * 
     * @param code the major code
     * @return the Major enum, or null if not found
     */
    public static Major fromString(String code) {
        for (Major major : Major.values()) {
            if (major.code.equalsIgnoreCase(code)) {
                return major;
            }
        }
        return null;
    }
}
