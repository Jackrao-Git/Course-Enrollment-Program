
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable{
	private static final long serialVersionUID = 8265325947824393500L;
	private String courseId;
    private String courseName;
    private int maximumStudents;
    private int currentStudents;
    List<String> listOfNames = new ArrayList<>();
    private String courseInstructor;
    private int courseSectionNumber;
    private String courseLocation;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getMaximumStudents() {
        return maximumStudents;
    }

    public void setMaximumStudents(int maximumStudents) {
        this.maximumStudents = maximumStudents;
    }

    public int getCurrentStudents() {
        return currentStudents;
    }

    public void setCurrentStudents(int currentStudents) {
        this.currentStudents = currentStudents;
    }

    public List<String> getListOfNames() {
        return listOfNames;
    }

    public void setListOfNames(List<String> listOfNames) {
        this.listOfNames = listOfNames;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public int getCourseSectionNumber() {
        return courseSectionNumber;
    }

    public void setCourseSectionNumber(int courseSectionNumber) {
        this.courseSectionNumber = courseSectionNumber;
    }

    public String getCourseLocation() {
        return courseLocation;
    }

    public void setCourseLocation(String courseLocation) {
        this.courseLocation = courseLocation;
    }

    @Override
    public String toString() {
        return "[" +
                "courseId:'" + courseId + '\'' +
                ", courseName:'" + courseName + '\'' +
                ", maximumStudents:" + maximumStudents +
                ", currentStudents:" + currentStudents +
                ", courseInstructor:'" + courseInstructor + '\'' +
                ", courseSectionNumber:" + courseSectionNumber +
                ", courseLocation:'" + courseLocation + '\'' +
                ']';
    }
}
