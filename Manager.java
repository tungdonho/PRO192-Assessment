import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Manager {
    private List<User> users = new ArrayList<>();
    private List<Classroom> classrooms = new ArrayList<>();

    public boolean login() {
        String username = Inputter.getString("Enter username: ");
        String password = Inputter.getString("Enter password: ");
        if (username.equals("admin") && password.equals("password")) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }

    public void createUser() {
        String username;
        String password;
        int roleId;

        // Validate username
        while (true) {
            username = Inputter.getString("Enter username: ");
            if (!username.contains(" ") && !isUsernameExists(username)) {
                break;
            } else if (isUsernameExists(username)) {
                System.out.println("Username already exists. Please enter a different username.");
            } else {
                System.out.println("Username cannot contain spaces.");
            }
        }

        // Validate password
        while (true) {
            password = Inputter.getString("Enter password: ");
            if (!password.contains(" ")) {
                break;
            } else {
                System.out.println("Password cannot contain spaces.");
            }
        }

        // Get role ID
        roleId = Inputter.getInteger("Enter role ID (1 for Teacher, 2 for User): ");
        while (roleId != 1 && roleId != 2) {
            System.out.println("Invalid role ID. Please enter 1 or 2.");
            roleId = Inputter.getInteger("Enter role ID (1 for Teacher, 2 for User): ");
        }

        User newUser = new User(username, password, roleId);
        users.add(newUser);
        System.out.println("User created successfully!");
    }

    public void updateUser() {
        displayUsers();
        String usernameToUpdate = Inputter.getString("Enter the username you want to update: ");
        User userToUpdate = findUserByUsername(usernameToUpdate);
        if (userToUpdate == null) {
            System.out.println("User not found.");
            return;
        }

        String newPassword = Inputter.getString("Enter new password: ");
        int newRoleId = Inputter.getInteger("Enter new role ID (1 for Teacher, 2 for User): ");
        while (newRoleId != 1 && newRoleId != 2) {
            System.out.println("Invalid role ID. Please enter 1 or 2.");
            newRoleId = Inputter.getInteger("Enter new role ID (1 for Teacher, 2 for User): ");
        }

        userToUpdate.setPassword(newPassword);
        userToUpdate.setRoleId(newRoleId);
        System.out.println("User information updated successfully.");
    }

    private boolean isUsernameExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void displayUsers() {
        System.out.println("Select what to display:");
        System.out.println("1. Display all users");
        System.out.println("2. Display all teachers");
        int choice = Inputter.getInteger("Enter your choice (1-2): ");

        System.out.format("%-20s %-20s %-20s%n", "Username", "Role", "Role ID");
        System.out.println("-".repeat(60));

        switch (choice) {
            case 1:
                for (User user : users) {
                    user.displayInfo();
                }
                break;
            case 2:
                for (User user : users) {
                    if (user.getRoleId() == 1) {
                        user.displayInfo();
                    }
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void createClassroom() {
        String name = Inputter.getString("Enter classroom name: ");
        String description = Inputter.getString("Enter classroom description: ");
        // Find a random teacher from the list of users
        List<User> teachers = getUsersByRole(1);
        if (teachers.isEmpty()) {
            System.out.println("No teachers available. Cannot create a classroom.");
            return;
        }
        User teacher = teachers.get(new Random().nextInt(teachers.size()));
        // Add 10 random students to the classroom, or all students if there are less
        // than 10
        List<User> students = getUsersByRole(2);
        List<User> selectedStudents = new ArrayList<>();
        if (students.size() >= 10) {
            for (int i = 0; i < 10; i++) {
                selectedStudents.add(students.get(new Random().nextInt(students.size())));
            }
        } else {
            selectedStudents.addAll(students);
        }
        Classroom newClassroom = new Classroom(classrooms.size() + 1, name, description, teacher);
        for (User student : selectedStudents) {
            newClassroom.addStudent(student);
        }
        classrooms.add(newClassroom);
        System.out.println("Classroom created successfully!");
    }

    private List<User> getUsersByRole(int roleId) {
        List<User> usersWithRole = new ArrayList<>();
        for (User user : users) {
            if (user.getRoleId() == roleId) {
                usersWithRole.add(user);
            }
        }
        return usersWithRole;
    }

    public void updateClassroom() {
        displayClassrooms();
        int classroomIdToUpdate = Inputter.getInteger("Enter the classroom ID you want to update: ");
        Classroom classroomToUpdate = findClassroomById(classroomIdToUpdate);
        if (classroomToUpdate == null) {
            System.out.println("Classroom not found.");
            return;
        }
        String newName = Inputter.getString("Enter new classroom name: ");
        String newDescription = Inputter.getString("Enter new classroom description: ");
        String newTeacherUsername = Inputter.getString("Enter new teacher username: ");
        User newTeacher = findUserByUsername(newTeacherUsername);
        if (newTeacher == null) {
            System.out.println("Teacher not found.");
            return;
        }
        classroomToUpdate.setName(newName);
        classroomToUpdate.setDescription(newDescription);
        classroomToUpdate.setTeacher(newTeacher);
        System.out.println("Classroom information updated successfully.");
    }

    private void displayClassrooms() {
        System.out.format("%-5s %-20s %-40s %-10s %-20s%n", "ID", "Name", "Description", "Students", "Teacher");
        System.out.println("-".repeat(100));
        for (Classroom classroom : classrooms) {
            System.out.format("%-5d %-20s %-40s %-10d %-20s%n", classroom.getClassroomId(), classroom.getName(),
                    classroom.getDescription(), classroom.getStudents().size(), classroom.getTeacher().getUsername());
        }
    }

    private Classroom findClassroomById(int classroomId) {
        for (Classroom classroom : classrooms) {
            if (classroom.getClassroomId() == classroomId) {
                return classroom;
            }
        }
        return null;
    }

    public void deleteClassroom() {
        displayClassrooms();
        int classroomIdToDelete = Inputter.getInteger("Enter the classroom ID you want to delete: ");
        Classroom classroomToDelete = findClassroomById(classroomIdToDelete);
        if (classroomToDelete == null) {
            System.out.println("Classroom not found.");
            return;
        }
        classrooms.remove(classroomToDelete);
        System.out.println("Classroom deleted successfully.");
    }

    public void deleteUser() {
        displayUsers();
        String usernameToDelete = Inputter.getString("Enter the username you want to delete: ");
        if (usernameToDelete.equals("admin")) {
            System.out.println("Cannot delete the admin user.");
            return;
        }
        User userToDelete = findUserByUsername(usernameToDelete);
        if (userToDelete == null) {
            System.out.println("User not found.");
            return;
        }
        users.remove(userToDelete);
        System.out.println("User deleted successfully.");
    }

    public void addStudentToClassroom() {
        displayClassrooms();
        int classroomId = Inputter.getInteger("Enter the classroom ID you want to add a student to: ");
        Classroom classroom = findClassroomById(classroomId);
        if (classroom == null) {
            System.out.println("Classroom not found.");
            return;
        }
        List<User> studentsNotInClassroom = getUsersNotInClassroom(classroom);
        if (studentsNotInClassroom.isEmpty()) {
            System.out.println("All students are already in the classroom.");
            return;
        }
        System.out.println("Select a student to add to the classroom:");
        for (int i = 0; i < studentsNotInClassroom.size(); i++) {
            System.out.println((i + 1) + ". " + studentsNotInClassroom.get(i).getUsername());
        }
        int studentChoice = Inputter.getInteger("Enter your choice (1-" + studentsNotInClassroom.size() + "): ");
        if (studentChoice < 1 || studentChoice > studentsNotInClassroom.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        User studentToAdd = studentsNotInClassroom.get(studentChoice - 1);
        classroom.addStudent(studentToAdd);
        System.out.println("Student added to the classroom successfully.");
    }

    private List<User> getUsersNotInClassroom(Classroom classroom) {
        List<User> usersNotInClassroom = new ArrayList<>();
        for (User user : users) {
            if (user.getRoleId() == 2 && !classroom.getStudents().contains(user)) {
                usersNotInClassroom.add(user);
            }
        }
        return usersNotInClassroom;
    }

    public void deleteStudentFromClassroom() {
        displayClassrooms();
        int classroomId = Inputter.getInteger("Enter the classroom ID you want to remove a student from: ");
        Classroom classroom = findClassroomById(classroomId);
        if (classroom == null) {
            System.out.println("Classroom not found.");
            return;
        }
        System.out.println("Students in the classroom:");
        for (User student : classroom.getStudents()) {
            student.displayInfo();
        }
        String usernameToDelete = Inputter.getString("Enter the username of the student you want to remove: ");
        User studentToDelete = findUserByUsername(usernameToDelete);
        if (studentToDelete == null) {
            System.out.println("Student not found.");
            return;
        }
        classroom.removeStudent(studentToDelete);
        System.out.println("Student removed from the classroom successfully.");
    }

    public void searchUserByUsername() {
        String usernameToSearch = Inputter.getString("Enter the username to search for: ");
        List<User> matchingUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getUsername().toLowerCase().contains(usernameToSearch.toLowerCase())) {
                matchingUsers.add(user);
            }
        }
        if (matchingUsers.isEmpty()) {
            System.out.println("No users found with the given username.");
        } else {
            System.out.println("Users found:");
            System.out.format("%-20s %-20s %-20s%n", "Username", "Role", "Role ID");
            System.out.println("-".repeat(60));
            for (User user : matchingUsers) {
                user.displayInfo();
            }
        }
    }

    public void searchClassroom() {
        String searchTerm = Inputter.getString("Enter the classroom name or ID to search for: ");
        List<Classroom> matchingClassrooms = new ArrayList<>();
        for (Classroom classroom : classrooms) {
            if (classroom.getName().toLowerCase().contains(searchTerm.toLowerCase())
                    || Integer.toString(classroom.getClassroomId()).contains(searchTerm)) {
                matchingClassrooms.add(classroom);
            }
        }
        if (matchingClassrooms.isEmpty()) {
            System.out.println("No classrooms found with the given search term.");
        } else {
            System.out.println("Classrooms found:");
            System.out.format("%-5s %-20s %-40s %-10s %-20s%n", "ID", "Name", "Description", "Students", "Teacher");
            System.out.println("-".repeat(100));
            for (Classroom classroom : matchingClassrooms) {
                System.out.format("%-5d %-20s %-40s %-10d %-20s%n", classroom.getClassroomId(), classroom.getName(),
                        classroom.getDescription(), classroom.getStudents().size(),
                        classroom.getTeacher().getUsername());
            }
        }
    }

    public void displayAllClassrooms() {
        System.out.format("%-5s %-20s %-40s %-10s %-20s%n", "ID", "Name", "Description", "Students", "Teacher");
        System.out.println("-".repeat(100));
        for (Classroom classroom : classrooms) {
            System.out.format("%-5d %-20s %-40s %-10d %-20s%n", classroom.getClassroomId(), classroom.getName(),
                    classroom.getDescription(), classroom.getStudents().size(), classroom.getTeacher().getUsername());
        }
    }
}
