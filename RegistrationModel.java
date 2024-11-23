
import java.util.ArrayList;
import java.util.List;

public class RegistrationModel extends Observable {
    private final List<User> users;
    private final List<Course> courses;
    private final List<String> registrations;

    public RegistrationModel() {
        users = new ArrayList<>();
        courses = new ArrayList<>();
        registrations = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
        FileDatabase.writeToFile("students.txt", user.getRole() + "," + user.getUsername(), true);
        notifyObservers("New user added: " + user.getUsername());
    }

public void registerForCourse(String studentName, String courseId) {
    registrations.add(studentName + "," + courseId);
    FileDatabase.writeToFile("registrations.txt", studentName + "," + courseId, true);
    notifyObservers("Registration successful: " + studentName + " -> " + courseId);
}
public void addCourse(Course course) {
    courses.add(course); // إضافة الدورة إلى القائمة
    FileDatabase.writeToFile("courses.txt", course.getCourseId() + "," + course.getCourseName(), true);
    notifyObservers("New course added: " + course.getCourseName());
}



public Course getCourseById(String courseId) {
    for (Course course : courses) {
        if (course.getCourseId().equalsIgnoreCase(courseId)) {
            return course;
        }
    }
    return null;
}


    public User getUserByName(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }
    public List<String> getCoursesForStudent(String studentName) {
    List<String> studentCourses = new ArrayList<>();
    for (String reg : registrations) {
        String[] parts = reg.split(",");
        if (parts[0].equalsIgnoreCase(studentName)) {
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
