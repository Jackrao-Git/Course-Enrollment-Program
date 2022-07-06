
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements StudentInterface, Serializable {

	private static final long serialVersionUID = 697981629471090832L;
	
	private List<Course> courseList = new ArrayList<>();

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public void viewAllCourse() {
        for (Course course : School.courseList) {
            System.out.println(course.toString());
        }
    }

    @Override
    public void viewNotFullCourse() {
        for (Course course : School.courseList) {
            if (course.getCurrentStudents() < course.getMaximumStudents()) {
                System.out.println(course);
            }
        }
    }

    @Override
    public void registerCourse(String courseName, int section, String fullName) {
        boolean existCourse = false;
        for (Course course : School.courseList) {
            if (courseName.equals(course.getCourseName())) {
                existCourse = true;
                if (course.getCurrentStudents() < course.getMaximumStudents()) {
                    course.setCurrentStudents(course.getCurrentStudents() + 1);
                    course.getListOfNames().add(fullName);
                    courseList.add(course);
                } else {
                    System.out.println("The number of registered students has reached its maximum!");
                }
            }
        }
        if (!existCourse) {
            System.out.println("The course doesn't exist!");
        }

    }

    @Override
    public void withdrawCourse(String courseName, String fullName) {
        if (courseName == null) {
            System.out.println("Course Name must be not null!");
            return;
        }
        for (Course course : courseList) {
            if (courseName.equals(course.getCourseName())) {
                courseList.remove(course);
                for (Course schoolCourse : School.courseList) {
                    if (courseName.equals(schoolCourse.getCourseName())) {
                        schoolCourse.setCurrentStudents(schoolCourse.getCurrentStudents() - 1);
                        schoolCourse.getListOfNames().remove(fullName);
                    }
                }
                break;
            }
        }
    }

    @Override
    public void viewAllRegisterCourse() {
        for (Course course : courseList) {
            System.out.println(course.toString());
        }
    }
}
