import java.util.List;

public class RegistrationController {
    private RegistrationModel model;
    private RegistrationView view;

    public RegistrationController(RegistrationModel model, RegistrationView view) {
        this.model = model;
        this.view = view;
        model.addObserver(view);
    }

    public void addStudent(String username) {
        User student = UserFactory.createUser("student", username);
        model.addUser(student);
    }

    public void addInstructor(String username) {
        User instructor = UserFactory.createUser("instructor", username);
        model.addUser(instructor);
    }

    public void addCourse(String courseId, String courseName, String instructorName) {
        // تحقق من وجود المدرس
        User instructor = model.getUserByName(instructorName);
        if (instructor != null && "Instructor".equalsIgnoreCase(instructor.getRole())) {
            // إذا كان المدرس موجودًا، أضف الدورة
            Course course = CourseFactory.createCourse(courseId, courseName);
            model.addCourse(course);
            model.assignInstructorToCourse(courseId, instructorName);
        } else {
            // إذا لم يكن المدرس موجودًا، لا تضف الدورة وأبلغ المستخدم
            System.out.println("Error: Instructor not found or invalid role. Course not added.");
        }
    }

public void registerForCourse(String studentName, String courseId) {
    // التحقق من وجود الدورة
    Course course = model.getCourseById(courseId);
    if (course == null) {
        System.out.println("Error: Course not found. Registration failed.");
        return;
    }

    // التحقق من وجود الطالب
    User student = model.getUserByName(studentName);
    if (student == null || !"Student".equalsIgnoreCase(student.getRole())) {
        System.out.println("Error: Student not found or invalid role. Registration failed.");
        return;
    }

    // إذا كان الطالب والدورة موجودين، قم بالتسجيل
    model.registerForCourse(studentName, courseId);
}


    public void showCoursesForStudent(String studentName) {
        List<String> courses = model.getCoursesForStudent(studentName);
        System.out.println("Courses registered by " + studentName + ":");
        for (String course : courses) {
            System.out.println(course);
        }
    }


public class CourseFactory {
    public static Course createCourse(String courseId, String courseName) {
        return new Course(courseId, courseName);
    }
}

}
