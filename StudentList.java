package project1;

/**
 * StudentList class is a resizable-array implementation to hold a list of student objects.
 * 
 * @author Your Name
 */
public class StudentList {
    private static final int NOT_FOUND = -1;
    private final static int CAPACITY = 4;
    private Student[] list;
    private int size;
    
    /**
     * Constructor to create a StudentList with initial capacity.
     */
    public StudentList() {
        this.list = new Student[CAPACITY];
        this.size = 0;
    }
    
    /**
     * Finds a student in the array.
     * 
     * @param student the student to find
     * @return the index if found, NOT_FOUND otherwise
     */
    private int find(Student student) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(student)) {
                return i;
            }
        }
        return NOT_FOUND;
    }
    
    /**
     * Grows the array by CAPACITY when it's full.
     */
    private void grow() {
        Student[] newList = new Student[list.length + CAPACITY];
        for (int i = 0; i < size; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }
    
    /**
     * Adds a student to the end of the array.
     * 
     * @param student the student to add
     */
    public void add(Student student) {
        if (size == list.length) {
            grow();
        }
        list[size] = student;
        size++;
    }
    
    /**
     * Removes a student from the array by replacing it with the last element.
     * 
     * @param student the student to remove
     */
    public void remove(Student student) {
        int index = find(student);
        if (index != NOT_FOUND) {
            list[index] = list[size - 1];
            list[size - 1] = null;
            size--;
        }
    }
    
    /**
     * Checks if the list contains a student.
     * 
     * @param student the student to check
     * @return true if found, false otherwise
     */
    public boolean contains(Student student) {
        return find(student) != NOT_FOUND;
    }
    
    /**
     * Gets a student by profile.
     * 
     * @param profile the profile to search for
     * @return the student if found, null otherwise
     */
    public Student getStudent(Profile profile) {
        for (int i = 0; i < size; i++) {
            if (list[i].getProfile().equals(profile)) {
                return list[i];
            }
        }
        return null;
    }
    
    /**
     * Gets the size of the list.
     * 
     * @return the size
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Sorts the list using selection sort by last name, first name, then date of birth.
     */
    private void sort() {
        // Selection sort
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (list[j].compareTo(list[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // Swap
            Student temp = list[i];
            list[i] = list[minIndex];
            list[minIndex] = temp;
        }
    }
    
    /**
     * Prints the list of students, sorted by last name, first name, then date of birth.
     */
    public void print() {
        if (size == 0) {
            System.out.println("Student list is empty!");
            return;
        }
        System.out.println("* Student list ordered by last, first name, DOB *");
        sort();
        for (int i = 0; i < size; i++) {
            System.out.println(list[i].toString());
        }
        System.out.println("* end of list **");
    }
}
