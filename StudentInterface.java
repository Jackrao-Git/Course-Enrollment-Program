
public interface StudentInterface {

    void viewAllCourse();

    void viewNotFullCourse();

    void registerCourse(String courseName, int section, String fullName);

    void withdrawCourse(String courseName, String fullName);

    void viewAllRegisterCourse();
}
