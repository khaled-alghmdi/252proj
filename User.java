abstract class User {
    protected String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public abstract String getRole();
}

class Student extends User {
    public Student(String username) {
        super(username);
    }

    @Override
    public String getRole() {
        return "Student";
    }
}

class Instructor extends User {
    public Instructor(String username) {
        super(username);
    }

    @Override
    public String getRole() {
        return "Instructor";
    }
}

class UserFactory {
    public static User createUser(String role, String username) {
        return switch (role.toLowerCase()) {
            case "student" -> new Student(username);
            case "instructor" -> new Instructor(username);
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };
    }
}
