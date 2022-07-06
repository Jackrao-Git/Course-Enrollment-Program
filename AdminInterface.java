
public interface AdminInterface {

    void createCourse(Course course);

    void deleteCourse(String courseId);

    void editCourse(Course newCourse);

    void displayCourseInfo(String courseId);

    void registerStudent(Student student);

    void viewAllCourses();

    void viewAllFullCourse();

    void writeFullCourse();

    void viewStudentNames(String courseName);

    void viewStudentCourse(String firstName, String lastName);

    void sortCourses();
}
