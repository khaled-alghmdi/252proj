
import java.util.List;

public class RegistrationView implements Observer {

    @Override
    public void update(String message) {
        System.out.println("Update: " + message);
    }

    public void displayStudents(List<User> students) {
        System.out.println("Current Students:");
        for (User student : students) {
            System.out.println(student.getRole() + ": " + student.getUsername());
        }
    }

    public void displayCourses(List<Course> courses) {
        System.out.println("Current Courses:");
        for (Course course : courses) {
            System.out.println(course.getCourseId() + ": " + course.getCourseName());
        }
    }

    public void displayRegistrations(List<String> registrations) {
        System.out.println("Current Registrations:");
        for (String registration : registrations) {
            System.out.println(registration);
        }
    }
}
