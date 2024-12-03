
public class Course {
    private String courseId;
    private String courseName;
    private String instructorName; 

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructorName = null; 
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
  public class CourseFactory {
    public static Course createCourse(String courseId, String courseName) {
        return new Course(courseId, courseName);
    }
}


}
