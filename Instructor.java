package project1;

/**
 * Instructor enum represents the 9 instructors in the registration system.
 * 
 * @author Your Name
 */
public enum Instructor {
    PATEL("Patel"),
    LIM("Lim"),
    ZIMNES("Zimnes"),
    HARPER("Harper"),
    KAUR("Kaur"),
    TAYLOR("Taylor"),
    RAMESH("Ramesh"),
    CERAVOLO("Ceravolo"),
    BROWN("Brown");
    
    private final String lastName;
    
    /**
     * Constructor for Instructor enum.
     * 
     * @param lastName the instructor's last name
     */
    Instructor(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Gets the instructor's last name.
     * 
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Returns the Instructor enum from a string last name (case-insensitive).
     * 
     * @param lastName the last name
     * @return the Instructor enum, or null if not found
     */
    public static Instructor fromString(String lastName) {
        for (Instructor instructor : Instructor.values()) {
            if (instructor.lastName.equalsIgnoreCase(lastName)) {
                return instructor;
            }
        }
        return null;
    }
}
