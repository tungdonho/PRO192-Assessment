public class User {
    private String username;
    private String password;
    private int roleId;

    // Constructor
    public User(String username, String password, int roleId) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    // Method to display user information
    public void displayInfo() {
        System.out.format("%-20s %-20s %-20d%n", username, getRoleName(), roleId);
    }

    // Method to get the role name based on roleId
    private String getRoleName() {
        switch (roleId) {
            case 0:
                return "Admin";
            case 1:
                return "Teacher";
            case 2:
                return "Student";
            default:
                return "Unknown";
        }
    }
}
