package project1;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Frontend class is the user interface that processes command lines from the terminal.
 *
 * @author Your Name
 */
public class Frontend {
    private StudentList studentList;
    private Schedule schedule;

    public Frontend() {
        this.studentList = new StudentList();
        this.schedule = new Schedule();
    }

    public void run() {
        System.out.println("Registration System is running.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine().trim();

            if (line.length() == 0) {
                continue;
            }

            if (line.equals("Q")) {
                System.out.println("Registration System is terminated.");
                break;
            }

            processCommand(line);
        }

        scanner.close();
    }

    private void processCommand(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line);
        if (!tokenizer.hasMoreTokens()) {
            return;
        }

        String command = tokenizer.nextToken();

        switch (command) {
            case "A":
                handleAddStudent(tokenizer);
                break;
            case "R":
                handleRemoveStudent(tokenizer);
                break;
            case "O":
                handleOfferSection(tokenizer);
                break;
            case "C":
                handleCloseSection(tokenizer);
                break;
            case "E":
                handleEnrollStudent(tokenizer);
                break;
            case "D":
                handleDropStudent(tokenizer);
                break;
            case "PS":
                handlePrintStudents();
                break;
            case "PL":
                handlePrintScheduleByLocation();
                break;
            case "PC":
                handlePrintScheduleByCourse();
                break;
            default:
                System.out.println(command + " is an invalid command!");
                break;
        }
    }

    private void handleAddStudent(StringTokenizer tokenizer) {
        if (!tokenizer.hasMoreTokens()) return;
        String fname = tokenizer.nextToken();
        if (!tokenizer.hasMoreTokens()) return;
        String lname = tokenizer.nextToken();
        if (!tokenizer.hasMoreTokens()) return;
        String dateStr = tokenizer.nextToken();
        Date dob = new Date(dateStr);
        if (!dob.isValid()) {
            System.out.println("INVALID: " + dateStr + " is not a valid calendar date!");
            return;
        }
        if (dob.isTodayOrFuture()) {
            System.out.println("INVALID: " + dateStr + " cannot be today or a future day.");
            return;
        }
        if (dob.getAge() < 16) {
            System.out.println("INVALID: " + dateStr + " younger than 16 years old.");
            return;
        }
        if (!tokenizer.hasMoreTokens()) return;
        String majorStr = tokenizer.nextToken();
        Major major = Major.fromString(majorStr);
        if (major == null) {
            System.out.println("INVALID: " + majorStr + " major does not exist.");
            return;
        }
        if (!tokenizer.hasMoreTokens()) return;
        int credits;
        try {
            credits = Integer.parseInt(tokenizer.nextToken());
        } catch (NumberFormatException e) {
            return;
        }
        if (credits <= 0) {
            System.out.println("INVALID: " + credits + " credit is negative!");
            return;
        }
        Profile profile = new Profile(fname, lname, dob);
        Student student = new Student(profile, major, credits);
        String nameStr = "[" + fname + " " + lname + " " + dateStr + "]";
        if (studentList.contains(student)) {
            System.out.println(nameStr + " student is already in the list.");
            return;
        }
        studentList.add(student);
        System.out.println(nameStr + " added to the list.");
    }

    private void handleRemoveStudent(StringTokenizer tokenizer) {
        if (!tokenizer.hasMoreTokens()) return;
        String fname = tokenizer.nextToken();
        if (!tokenizer.hasMoreTokens()) return;
        String lname = tokenizer.nextToken();
        if (!tokenizer.hasMoreTokens()) return;
        String dateStr = tokenizer.nextToken();
        Date dob = new Date(dateStr);
        Profile profile = new Profile(fname, lname, dob);
        Student student = studentList.getStudent(profile);
        String nameStr = "[" + fname + " " + lname + " " + dateStr + "]";
        if (student == null) {
            System.out.println(nameStr + " is not in the student list.");
            return;
        }
        Section[] enrolled = schedule.getSectionsByStudent(student);
        for (Section section : enrolled) {
            if (section != null && section.contains(student)) {
                return; // enrolled, cannot remove - no message
            }
        }
        studentList.remove(student);
        System.out.println(nameStr + " removed from the list.");
    }

    private void handleOfferSection(StringTokenizer tokenizer) {
        if (!tokenizer.hasMoreTokens()) return;
        String courseStr = tokenizer.nextToken();
        Course course = Course.fromString(courseStr);
        if (course == null) {
            System.out.println("INVALID: course name " + courseStr + " does not exist.");
            return;
        }
        if (!tokenizer.hasMoreTokens()) return;
        int period;
        try {
            period = Integer.parseInt(tokenizer.nextToken());
        } catch (NumberFormatException e) {
            return;
        }
        Time time = Time.fromPeriod(period);
        if (time == null) {
            System.out.println("INVALID: period " + period + " does not exist.");
            return;
        }
        if (schedule.getSection(course, period) != null) {
            System.out.println("INVALID: " + course.getCourseNumber() + " period " + period + " already exists.");
            return;
        }
        if (!tokenizer.hasMoreTokens()) return;
        String instructorStr = tokenizer.nextToken();
        Instructor instructor = Instructor.fromString(instructorStr);
        if (instructor == null) {
            System.out.println("INVALID: instructor " + instructorStr + " does not exist.");
            return;
        }
        Section[] instSections = schedule.getSectionsByInstructor(instructor);
        for (Section s : instSections) {
            if (s != null && s.getTime() == time) {
                System.out.println("INVALID: " + instructor.getLastName().toUpperCase() + " time conflict.");
                return;
            }
        }
        if (!tokenizer.hasMoreTokens()) return;
        String classroomStr = tokenizer.nextToken();
        Classroom classroom = Classroom.fromString(classroomStr);
        if (classroom == null) {
            System.out.println("INVALID: classroom " + classroomStr + " does not exist.");
            return;
        }
        Section[] roomSections = schedule.getSectionsByClassroom(classroom);
        for (Section s : roomSections) {
            if (s != null && s.getTime() == time) {
                System.out.println("INVALID: [" + classroom.getRoomNumber() + ", " + classroom.getBuilding() + ", " + classroom.getCampus() + "] not available.");
                return;
            }
        }
        Section section = new Section(course, instructor, classroom, time);
        schedule.add(section);
        System.out.println(section.toString() + " added to the schedule.");
    }

    private void handleCloseSection(StringTokenizer tokenizer) {
        if (!tokenizer.hasMoreTokens()) return;
        String courseStr = tokenizer.nextToken();
        Course course = Course.fromString(courseStr);
        if (course == null) {
            System.out.println("INVALID: course name " + courseStr + " does not exist.");
            return;
        }
        if (!tokenizer.hasMoreTokens()) return;
        int period;
        try {
            period = Integer.parseInt(tokenizer.nextToken());
        } catch (NumberFormatException e) {
            return;
        }
        Time time = Time.fromPeriod(period);
        if (time == null) {
            System.out.println("INVALID: period " + period + " does not exist.");
            return;
        }
        Section section = schedule.getSection(course, period);
        if (section == null) {
            System.out.println(course.getCourseNumber() + " " + time.getStartTime() + " does not exist.");
            return;
        }
        if (!section.isEmpty()) {
            System.out.println(course.getCourseNumber() + " " + time.getStartTime() + " cannot be removed [" + section.getNumStudents() + " student(s) enrolled]");
            return;
        }
        schedule.remove(section);
        System.out.println(course.getCourseNumber() + " " + time.getStartTime() + " removed.");
    }

    private void handleEnrollStudent(StringTokenizer tokenizer) {
        if (!tokenizer.hasMoreTokens()) return;
        String fname = tokenizer.nextToken();
        if (!tokenizer.hasMoreTokens()) return;
        String lname = tokenizer.nextToken();
        if (!tokenizer.hasMoreTokens()) return;
        String dateStr = tokenizer.nextToken();
        Date dob = new Date(dateStr);
        Profile profile = new Profile(fname, lname, dob);
        Student student = studentList.getStudent(profile);
        String nameStr = "[" + fname + " " + lname + " " + dateStr + "]";
        if (student == null) {
            System.out.println("INVALID: " + nameStr + " does not exist.");
            return;
        }
        if (!tokenizer.hasMoreTokens()) return;
        String courseStr = tokenizer.nextToken();
        Course course = Course.fromString(courseStr);
        if (!tokenizer.hasMoreTokens()) return;
        int period;
        try {
            period = Integer.parseInt(tokenizer.nextToken());
        } catch (NumberFormatException e) {
            return;
        }
        Time time = Time.fromPeriod(period);
        if (course == null) {
            System.out.println("INVALID: course name " + courseStr + " does not exist.");
            return;
        }
        if (time == null) {
            System.out.println("INVALID: period " + period + " does not exist.");
            return;
        }
        Section section = schedule.getSection(course, period);
        if (section == null) {
            System.out.println("INVALID: " + course.getCourseNumber() + " " + time.getStartTime() + " does not exist.");
            return;
        }
        Section[] courseSections = schedule.getSectionsByCourse(course);
        for (Section s : courseSections) {
            if (s != null && s.contains(student)) {
                System.out.println(nameStr + " already enrolled in " + course.getCourseNumber());
                return;
            }
        }
        Standing required = course.getRequiredStanding();
        if (student.getStanding().ordinal() < required.ordinal()) {
            System.out.println("Prereq: " + required.getDisplayName() + " - " + nameStr + " [" + student.getStanding().getDisplayName() + "]");
            return;
        }
        Major reqMajor = course.getRequiredMajor();
        if (reqMajor != null && student.getMajor() != reqMajor) {
            System.out.println("Prereq: major only - " + nameStr + " [" + student.getMajor().getCode() + "]");
            return;
        }
        Section[] studentSections = schedule.getSectionsByStudent(student);
        for (Section s : studentSections) {
            if (s != null && s.getTime() == time) {
                System.out.println("Time conflict: " + nameStr + " enrolled in another class at period " + period);
                return;
            }
        }
        int currentCredits = 0;
        for (Section s : studentSections) {
            if (s != null) currentCredits += s.getCourse().getCreditHours();
        }
        if (currentCredits + course.getCreditHours() > 18) {
            System.out.println("Cannot enroll " + nameStr + "; now has " + currentCredits + " will exceeds credit limit of 18.");
            return;
        }
        if (section.isFull()) {
            System.out.println("Cannot enroll " + nameStr + ", " + course.getCourseNumber() + " " + time.getStartTime() + " is full.");
            return;
        }
        schedule.enroll(section, student);
        System.out.println(nameStr + " added to " + course.getCourseNumber() + " " + time.getStartTime());
    }

    private void handleDropStudent(StringTokenizer tokenizer) {
        if (!tokenizer.hasMoreTokens()) return;
        String fname = tokenizer.nextToken();
        if (!tokenizer.hasMoreTokens()) return;
        String lname = tokenizer.nextToken();
        if (!tokenizer.hasMoreTokens()) return;
        String dateStr = tokenizer.nextToken();
        Date dob = new Date(dateStr);
        Profile profile = new Profile(fname, lname, dob);
        Student student = studentList.getStudent(profile);
        String nameStr = "[" + fname + " " + lname + " " + dateStr + "]";
        if (student == null) {
            System.out.println("INVALID: " + nameStr + " does not exist.");
            return;
        }
        if (!tokenizer.hasMoreTokens()) return;
        String courseStr = tokenizer.nextToken();
        Course course = Course.fromString(courseStr);
        if (!tokenizer.hasMoreTokens()) return;
        int period;
        try {
            period = Integer.parseInt(tokenizer.nextToken());
        } catch (NumberFormatException e) {
            return;
        }
        Time time = Time.fromPeriod(period);
        if (course == null) {
            System.out.println("INVALID: course name " + courseStr + " does not exist.");
            return;
        }
        if (time == null) {
            System.out.println("INVALID: period " + period + " does not exist.");
            return;
        }
        Section section = schedule.getSection(course, period);
        if (section == null) {
            System.out.println(course.getCourseNumber() + " " + time.getStartTime() + " does not exist.");
            return;
        }
        if (!section.contains(student)) {
            System.out.println(nameStr + " is not enrolled in this section.");
            return;
        }
        schedule.drop(section, student);
        System.out.println(nameStr + " dropped from " + course.getCourseNumber() + " " + time.getStartTime());
    }

    private void handlePrintStudents() {
        studentList.print();
    }

    private void handlePrintScheduleByLocation() {
        schedule.printByClassroom();
    }

    private void handlePrintScheduleByCourse() {
        schedule.printByCourse();
    }
}
