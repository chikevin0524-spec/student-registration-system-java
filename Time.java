package project1;

/**
 * Time enum represents the class periods and their start times.
 * 
 * @author Your Name
 */
public enum Time {
    PERIOD1(1, "8:30"),
    PERIOD2(2, "10:20"),
    PERIOD3(3, "12:10"),
    PERIOD4(4, "14:00"),
    PERIOD5(5, "15:50"),
    PERIOD6(6, "17:40");
    
    private final int period;
    private final String startTime;
    
    /**
     * Constructor for Time enum.
     * 
     * @param period the period number (1-6)
     * @param startTime the start time in HH:mm format
     */
    Time(int period, String startTime) {
        this.period = period;
        this.startTime = startTime;
    }
    
    /**
     * Gets the period number.
     * 
     * @return the period number
     */
    public int getPeriod() {
        return period;
    }
    
    /**
     * Gets the start time.
     * 
     * @return the start time
     */
    public String getStartTime() {
        return startTime;
    }
    
    /**
     * Returns the Time enum from a period number.
     * 
     * @param period the period number
     * @return the Time enum, or null if not found
     */
    public static Time fromPeriod(int period) {
        for (Time time : Time.values()) {
            if (time.period == period) {
                return time;
            }
        }
        return null;
    }
}
