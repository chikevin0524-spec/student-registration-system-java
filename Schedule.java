package project1;

/**
 * Schedule class is a resizable-array implementation to hold a list of sections.
 * 
 * @author Your Name
 */
public class Schedule {
    private Section[] sections;
    private int numSections;
    private static final int CAPACITY = 4;
    
    /**
     * Constructor to create a Schedule with initial capacity.
     */
    public Schedule() {
        this.sections = new Section[CAPACITY];
        this.numSections = 0;
    }
    
    /**
     * Finds a section in the array.
     * 
     * @param section the section to find
     * @return the index if found, -1 otherwise
     */
    private int find(Section section) {
        for (int i = 0; i < numSections; i++) {
            if (sections[i].equals(section)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Grows the array by CAPACITY when it's full.
     */
    private void grow() {
        Section[] newSections = new Section[sections.length + CAPACITY];
        for (int i = 0; i < numSections; i++) {
            newSections[i] = sections[i];
        }
        sections = newSections;
    }
    
    /**
     * Adds a section to the schedule.
     * 
     * @param section the section to add
     */
    public void add(Section section) {
        if (numSections == sections.length) {
            grow();
        }
        sections[numSections] = section;
        numSections++;
    }
    
    /**
     * Removes a section from the schedule by replacing it with the last element.
     * 
     * @param section the section to remove
     */
    public void remove(Section section) {
        int index = find(section);
        if (index != -1) {
            sections[index] = sections[numSections - 1];
            sections[numSections - 1] = null;
            numSections--;
        }
    }
    
    /**
     * Enrolls a student in a section.
     * 
     * @param section the section
     * @param student the student to enroll
     */
    public void enroll(Section section, Student student) {
        int index = find(section);
        if (index != -1) {
            sections[index].enroll(student);
        }
    }
    
    /**
     * Drops a student from a section.
     * 
     * @param section the section
     * @param student the student to drop
     */
    public void drop(Section section, Student student) {
        int index = find(section);
        if (index != -1) {
            sections[index].drop(student);
        }
    }
    
    /**
     * Checks if the schedule contains a section.
     * 
     * @param section the section to check
     * @return true if found, false otherwise
     */
    public boolean contains(Section section) {
        return find(section) != -1;
    }
    
    /**
     * Gets a section by course and period.
     * 
     * @param course the course
     * @param period the period number
     * @return the section if found, null otherwise
     */
    public Section getSection(Course course, int period) {
        Time time = Time.fromPeriod(period);
        if (time == null) {
            return null;
        }
        Section target = new Section(course, null, null, time);
        int index = find(target);
        if (index != -1) {
            return sections[index];
        }
        return null;
    }
    
    /**
     * Gets all sections for a specific instructor.
     * 
     * @param instructor the instructor
     * @return array of sections (may contain nulls if not full)
     */
    public Section[] getSectionsByInstructor(Instructor instructor) {
        Section[] result = new Section[numSections];
        int count = 0;
        for (int i = 0; i < numSections; i++) {
            if (sections[i].getInstructor() == instructor) {
                result[count++] = sections[i];
            }
        }
        return result;
    }
    
    /**
     * Gets all sections for a specific classroom.
     * 
     * @param classroom the classroom
     * @return array of sections (may contain nulls if not full)
     */
    public Section[] getSectionsByClassroom(Classroom classroom) {
        Section[] result = new Section[numSections];
        int count = 0;
        for (int i = 0; i < numSections; i++) {
            if (sections[i].getClassroom() == classroom) {
                result[count++] = sections[i];
            }
        }
        return result;
    }
    
    /**
     * Gets all sections for a specific student.
     * 
     * @param student the student
     * @return array of sections (may contain nulls if not full)
     */
    public Section[] getSectionsByStudent(Student student) {
        Section[] result = new Section[numSections];
        int count = 0;
        for (int i = 0; i < numSections; i++) {
            if (sections[i].contains(student)) {
                result[count++] = sections[i];
            }
        }
        return result;
    }
    
    /**
     * Gets all sections for a specific course.
     * 
     * @param course the course
     * @return array of sections (may contain nulls if not full)
     */
    public Section[] getSectionsByCourse(Course course) {
        Section[] result = new Section[numSections];
        int count = 0;
        for (int i = 0; i < numSections; i++) {
            if (sections[i].getCourse() == course) {
                result[count++] = sections[i];
            }
        }
        return result;
    }
    
    /**
     * Sorts sections by campus, then by building using selection sort.
     */
    private void sortByClassroom() {
        for (int i = 0; i < numSections - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numSections; j++) {
                Section s1 = sections[j];
                Section s2 = sections[minIndex];
                
                // Compare by campus
                int campusCompare = s1.getClassroom().getCampus().compareTo(s2.getClassroom().getCampus());
                if (campusCompare < 0) {
                    minIndex = j;
                } else if (campusCompare == 0) {
                    // If same campus, compare by building
                    int buildingCompare = s1.getClassroom().getBuilding().compareTo(s2.getClassroom().getBuilding());
                    if (buildingCompare < 0) {
                        minIndex = j;
                    }
                }
            }
            // Swap
            Section temp = sections[i];
            sections[i] = sections[minIndex];
            sections[minIndex] = temp;
        }
    }
    
    /**
     * Sorts sections by course number, then by period using selection sort.
     */
    private void sortByCourse() {
        for (int i = 0; i < numSections - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numSections; j++) {
                Section s1 = sections[j];
                Section s2 = sections[minIndex];
                
                // Compare by course number
                int courseCompare = s1.getCourse().getCourseNumber().compareTo(s2.getCourse().getCourseNumber());
                if (courseCompare < 0) {
                    minIndex = j;
                } else if (courseCompare == 0) {
                    // If same course, compare by period
                    if (s1.getTime().getPeriod() < s2.getTime().getPeriod()) {
                        minIndex = j;
                    }
                }
            }
            // Swap
            Section temp = sections[i];
            sections[i] = sections[minIndex];
            sections[minIndex] = temp;
        }
    }
    
    /**
     * Prints the schedule sorted by campus and building.
     */
    public void printByClassroom() {
        if (numSections == 0) {
            System.out.println("Schedule is empty!");
            return;
        }
        System.out.println("* List of sections ordered by campus, building *");
        sortByClassroom();
        for (int i = 0; i < numSections; i++) {
            sections[i].printWithRoster();
        }
        System.out.println("* end of list **");
    }
    
    /**
     * Prints the schedule sorted by course number and period.
     */
    public void printByCourse() {
        if (numSections == 0) {
            System.out.println("Schedule is empty!");
            return;
        }
        System.out.println("* List of sections ordered by course number, section time *");
        sortByCourse();
        for (int i = 0; i < numSections; i++) {
            sections[i].printWithRoster();
        }
        System.out.println("* end of list *");
    }
}
