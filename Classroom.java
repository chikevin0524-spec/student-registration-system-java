package project1;

/**
 * Classroom enum represents the classroom locations in the registration system.
 * 
 * @author Your Name
 */
public enum Classroom {
    HIL114("HIL114", "Hill Center", "Busch"),
    ARC103("ARC103", "Allison Road Classroom", "Busch"),
    BEAUD("BEAUD", "Beck Hall", "Livingston"),
    TIL232("TIL232", "Tillett Hall", "Livingston"),
    AB2225("AB2225", "Academic Building", "College Avenue"),
    MU302("MU302", "Murray Hall", "College Avenue");
    
    private final String roomNumber;
    private final String building;
    private final String campus;
    
    /**
     * Constructor for Classroom enum.
     * 
     * @param roomNumber the room number
     * @param building the building name
     * @param campus the campus name
     */
    Classroom(String roomNumber, String building, String campus) {
        this.roomNumber = roomNumber;
        this.building = building;
        this.campus = campus;
    }
    
    /**
     * Gets the room number.
     * 
     * @return the room number
     */
    public String getRoomNumber() {
        return roomNumber;
    }
    
    /**
     * Gets the building name.
     * 
     * @return the building name
     */
    public String getBuilding() {
        return building;
    }
    
    /**
     * Gets the campus name.
     * 
     * @return the campus name
     */
    public String getCampus() {
        return campus;
    }
    
    /**
     * Returns the Classroom enum from a string room number (case-insensitive).
     * 
     * @param roomNumber the room number
     * @return the Classroom enum, or null if not found
     */
    public static Classroom fromString(String roomNumber) {
        for (Classroom classroom : Classroom.values()) {
            if (classroom.roomNumber.equalsIgnoreCase(roomNumber)) {
                return classroom;
            }
        }
        return null;
    }
}
