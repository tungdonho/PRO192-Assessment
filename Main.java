public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        boolean loggedIn = false;
        while (true) {
            if (!loggedIn) {
                System.out.println("Welcome to the application!");
                loggedIn = manager.login();
                if (!loggedIn) {
                    System.out.println("Login failed. Please try again.");
                    continue;
                }
            }
            System.out.println("What would you like to do?");
            System.out.println("1. Create a new user");
            System.out.println("2. Display all users");
            System.out.println("3. Update user information");
            System.out.println("4. Delete a user");
            System.out.println("5. Create a new classroom");
            System.out.println("6. Update a classroom");
            System.out.println("7. Delete a classroom");
            System.out.println("8. Add a student to a classroom");
            System.out.println("9. Remove a student from a classroom");
            System.out.println("10. Search for a user by username");
            System.out.println("11. Search for a classroom");
            System.out.println("12. Display all classrooms");
            System.out.println("13. Logout");
            System.out.println("14. Exit");
            int choice = Inputter.getInteger("Enter your choice (1-14): ");
            switch (choice) {
                case 1:
                    manager.createUser();
                    break;
                case 2:
                    manager.displayUsers();
                    break;
                case 3:
                    manager.updateUser();
                    break;
                case 4:
                    manager.deleteUser();
                    break;
                case 5:
                    manager.createClassroom();
                    break;
                case 6:
                    manager.updateClassroom();
                    break;
                case 7:
                    manager.deleteClassroom();
                    break;
                case 8:
                    manager.addStudentToClassroom();
                    break;
                case 9:
                    manager.deleteStudentFromClassroom();
                    break;
                case 10:
                    manager.searchUserByUsername();
                    break;
                case 11:
                    manager.searchClassroom();
                    break;
                case 12:
                    manager.displayAllClassrooms();
                    break;
                case 13:
                    loggedIn = false;
                    System.out.println("Logged out successfully.");
                    break;
                case 14:
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
