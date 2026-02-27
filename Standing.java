package project1;

/**
 * Standing enum represents student standing based on completed credits.
 * 
 * @author Your Name
 */
public enum Standing {
    FRESHMAN,  // Credits < 30
    SOPHOMORE, // Credits < 60
    JUNIOR,    // Credits < 90
    SENIOR;    // Credits >= 90
    
    /**
     * Gets the standing based on completed credits.
     * 
     * @param credits the number of completed credits
     * @return the standing
     */
    public static Standing fromCredits(int credits) {
        if (credits < 30) {
            return FRESHMAN;
        } else if (credits < 60) {
            return SOPHOMORE;
        } else if (credits < 90) {
            return JUNIOR;
        } else {
            return SENIOR;
        }
    }

    /**
     * Returns display name with capital first letter (e.g. Senior).
     */
    public String getDisplayName() {
        String n = name();
        return n.charAt(0) + n.substring(1).toLowerCase();
    }
}
