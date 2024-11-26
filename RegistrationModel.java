import java.util.*;

public class RegistrationModel extends Observable {
    private final Map<String, User> users; // Map for faster user lookup
    private final Map<String, Course> courses; // Map for faster course lookup
    private final List<String> registrations; // Simple list for registrations

    public RegistrationModel() {
        users = new HashMap<>();
        courses = new HashMap<>();
        registrations = new ArrayList<>();
    }

    public void addUser(User user) {
        if (users.containsKey(user.getUsername())) {
            System.out.println("Error: User with this username already exists.");
            return;
        }
        users.put(user.getUsername(), user);
        FileDatabase.getInstance().writeToFile("students.txt", user.getRole() + "," + user.getUsername(), true);
        notifyObservers("New user added: " + user.getUsername());
    }

    public void addCourse(Course course) {
        if (courses.containsKey(course.getCourseId())) {
            System.out.println("Error: Course with this ID already exists.");
            return;
        }
        courses.put(course.getCourseId(), course);
        FileDatabase.getInstance().writeToFile("courses.txt", course.getCourseId() + "," + course.getCourseName(), true);
        notifyObservers("New course added: " + course.getCourseName());
    }

    public Course getCourseById(String courseId) {
        return courses.get(courseId);
    }

    public User getUserByName(String username) {
        return users.get(username);
    }

    public void registerForCourse(String studentName, String courseId) {
        Course course = getCourseById(courseId);
        if (course == null) {
            System.out.println("Error: Course not found. Registration failed.");
            return;
        }

        User student = getUserByName(studentName);
        if (student == null || !"Student".equalsIgnoreCase(student.getRole())) {
            System.out.println("Error: Student not found or invalid role. Registration failed.");
            return;
        }

        registrations.add(studentName + "," + courseId);
        FileDatabase.getInstance().writeToFile("registrations.txt", studentName + "," + courseId, true);
        notifyObservers("Registration successful: " + studentName + " -> " + courseId);
    }

    public List<String> getCoursesForStudent(String studentName) {
        List<String> studentCourses = new ArrayList<>();
        for (String reg : registrations) {
            String[] parts = reg.split(",");
            if (parts.length == 2 && parts[0].equalsIgnoreCase(studentName)) {
                studentCourses.add(parts[1]);
            }
        }
        return studentCourses;
    }

    public void assignInstructorToCourse(String courseId, String instructorName) {
        Course course = getCourseById(courseId);
        User instructor = getUserByName(instructorName);

        if (course != null && instructor != null && "Instructor".equalsIgnoreCase(instructor.getRole())) {
            course.setInstructorName(instructorName);
            notifyObservers("Instructor " + instructorName + " assigned to course " + courseId);
        } else {
            System.out.println("Error: Course or Instructor not found, or invalid role.");
        }
    }
}
