package project1;

/**
 * Section class represents a course section with instructor, classroom, time, and student roster.
 * 
 * @author Your Name
 */
public class Section {
    private Course course;
    private Instructor instructor;
    private Classroom classroom;
    private Time time;
    private Student[] roster;
    private int numStudents;
    private static final int CAPACITY = 4;
    
    /**
     * Constructor to create a Section object.
     * 
     * @param course the course
     * @param instructor the instructor
     * @param classroom the classroom
     * @param time the time period
     */
    public Section(Course course, Instructor instructor, Classroom classroom, Time time) {
        this.course = course;
        this.instructor = instructor;
        this.classroom = classroom;
        this.time = time;
        this.roster = new Student[CAPACITY];
        this.numStudents = 0;
    }
    
    /**
     * Gets the course.
     * 
     * @return the course
     */
    public Course getCourse() {
        return course;
    }
    
    /**
     * Gets the instructor.
     * 
     * @return the instructor
     */
    public Instructor getInstructor() {
        return instructor;
    }
    
    /**
     * Gets the classroom.
     * 
     * @return the classroom
     */
    public Classroom getClassroom() {
        return classroom;
    }
    
    /**
     * Gets the time period.
     * 
     * @return the time
     */
    public Time getTime() {
        return time;
    }
    
    /**
     * Gets the number of enrolled students.
     * 
     * @return the number of students
     */
    public int getNumStudents() {
        return numStudents;
    }
    
    /**
     * Enrolls a student in this section.
     * 
     * @param student the student to enroll
     */
    public void enroll(Student student) {
        if (!isFull() && !contains(student)) {
            roster[numStudents] = student;
            numStudents++;
        }
    }
    
    /**
     * Drops a student from this section.
     * 
     * @param student the student to drop
     */
    public void drop(Student student) {
        for (int i = 0; i < numStudents; i++) {
            if (roster[i].equals(student)) {
                // Replace with last student
                roster[i] = roster[numStudents - 1];
                roster[numStudents - 1] = null;
                numStudents--;
                return;
            }
        }
    }
    
    /**
     * Checks if a student is enrolled in this section.
     * 
     * @param student the student to check
     * @return true if enrolled, false otherwise
     */
    public boolean contains(Student student) {
        for (int i = 0; i < numStudents; i++) {
            if (roster[i].equals(student)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the section is full.
     * 
     * @return true if full, false otherwise
     */
    public boolean isFull() {
        return numStudents >= CAPACITY;
    }
    
    /**
     * Checks if the section has any enrolled students.
     * 
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return numStudents == 0;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Section section = (Section) obj;
        return course == section.course && time == section.time;
    }
    
    @Override
    public String toString() {
        return "[" + course.getCourseNumber() + " " + time.getStartTime() + "] " +
               "[" + instructor.getLastName().toUpperCase() + "] " +
               "[" + classroom.getRoomNumber() + ", " + classroom.getBuilding() + ", " + classroom.getCampus() + "]";
    }
    
    /**
     * Prints the section information.
     */
    public void print() {
        System.out.println(toString());
    }

    /** Tab character for roster indentation (must be \\t not spaces for grader). */
    private static final char TAB = '\t';

    /**
     * Prints the section line and roster (for PC/PL output).
     */
    public void printWithRoster() {
        System.out.println(toString());
        if (numStudents == 0) {
            System.out.println(TAB + "**No students enrolled**");
        } else {
            System.out.println(TAB + "**Roster**");
            for (int i = 0; i < numStudents; i++) {
                Student s = roster[i];
                System.out.println(TAB + "[" + s.getProfile().getFname() + " " + s.getProfile().getLname() + " " + s.getProfile().getDob() + "]");
            }
        }
    }
}
