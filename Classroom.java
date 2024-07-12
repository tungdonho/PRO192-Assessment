import java.util.ArrayList;
import java.util.List;

public class Classroom {
    private int classroomId;
    private String name;
    private String description;
    private List<User> students;
    private User teacher;

    // Constructor
    public Classroom(int classroomId, String name, String description, User teacher) {
        this.classroomId = classroomId;
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
        this.teacher = teacher;
    }

    // Getters and Setters
    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getStudents() {
        return students;
    }

    public void addStudent(User student) {
        this.students.add(student);
    }

    public void removeStudent(User student) {
        this.students.remove(student);
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    // Method to display classroom information
    public void displayInfo() {
        System.out.println("Classroom ID: " + classroomId);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Teacher: " + teacher.getUsername());
        System.out.println("Students:");
        for (User student : students) {
            student.displayInfo();
        }
    }
}
