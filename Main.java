
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RegistrationModel model = new RegistrationModel();
        RegistrationView view = new RegistrationView();
        RegistrationController controller = new RegistrationController(model, view);

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Registration System");

        while (true) {
            System.out.println("""
    1. Add Student
    2. Add Instructor
    3. Add Course 
    4. Register for Course
    5. View Courses for Student
    6. Exit
""");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter student name: ");
                    String studentName = sc.nextLine();
                    controller.addStudent(studentName);
                }
                case 2 -> {
                    System.out.print("Enter instructor name: ");
                    String instructorName = sc.nextLine();
                    controller.addInstructor(instructorName);
                }
                case 3 -> {
                    System.out.print("Enter course ID: ");
                    String courseId = sc.nextLine();
                    System.out.print("Enter course name: ");
                    String courseName = sc.nextLine();
                    System.out.print("Enter instructor name: ");
                    String instructorName = sc.nextLine();
                    controller.addCourse(courseId, courseName, instructorName);
                }
                case 4 -> {
                    System.out.print("Enter student name: ");
                    String studentName = sc.nextLine();
                    System.out.print("Enter course ID: ");
                    String courseId = sc.nextLine();
                    controller.registerForCourse(studentName, courseId);
                }
                case 5 -> {
                    System.out.print("Enter student name: ");
                    String studentName = sc.nextLine();
                    controller.showCoursesForStudent(studentName);
                }
                case 6 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
