
import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Admin extends User implements AdminInterface, Serializable {

    @Override
    public void createCourse(Course course) {
        School.courseList.add(course);
    }

    @Override
    public void deleteCourse(String courseId) {
        if (courseId == null) {
            System.out.println("courseId must be not null!");
            return;
        }
        for (Course course : School.courseList) {
            if (courseId.equals(course.getCourseId())) {
                School.courseList.remove(course);
                return;
            }
        }
    }

    @Override
    
    public void editCourse(Course newCourse) {
        if (newCourse == null) {
            return;
        }
        for (Course course : School.courseList) {
            if (course.getCourseId().equals(newCourse.getCourseId())) {
                if (!course.getCourseName().equals(newCourse.getCourseName())) {
                    System.out.println("Course name can't be edited");
                }
                course.setCourseInstructor(newCourse.getCourseInstructor());
                course.setMaximumStudents(newCourse.getMaximumStudents());
                course.setCurrentStudents(newCourse.getCurrentStudents());
                course.setCourseLocation(newCourse.getCourseLocation());
                course.setCourseSectionNumber(newCourse.getCourseSectionNumber());
                course.setListOfNames(newCourse.getListOfNames());
            }
        }
    }

    @Override
    public void displayCourseInfo(String courseId) {
        if (courseId == null) {
            System.out.println("courseId must be not null!");
            return;
        }
        for (Course course : School.courseList) {
            if (courseId.equals(course.getCourseId())) {
                System.out.println(course.toString());
                return;
            }
        }
    }

    @Override
    public void registerStudent(Student student) {
        School.studentList.add(student);
    }

    @Override
    public void viewAllCourses() {
        for (Course course : School.courseList) {
            System.out.println(course.toString());
        }
    }

    @Override
    public void viewAllFullCourse() {
        for (Course course : School.courseList) {
            if (course.getCurrentStudents() == course.getMaximumStudents()) {
                System.out.println(course);
            }
        }
    }

    @Override
    public void writeFullCourse() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("file/MyFullCourse.csv", true));
            for (Course course : School.courseList) {
                if (course.getCurrentStudents() == course.getMaximumStudents()) {
                    String fullCourse = course.getCourseName() + "," + course.getCourseId() + "," + course.getMaximumStudents() +
                            "," + course.getCurrentStudents() + "," + course.getCourseInstructor() + "," + course.getCourseSectionNumber()
                            + "," + course.getCourseLocation();
                    bw.write(fullCourse);
                    bw.newLine();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void viewStudentNames(String courseName) {
        if (courseName == null) {
            System.out.println("courseName must be not null!");
            return;
        }
        for (Course course : School.courseList) {
            if (courseName.equals(course.getCourseName())) {
                List<String> studentNames = course.getListOfNames();
                for (String name : studentNames) {
                    System.out.println(name);
                }
            }
        }
    }

    @Override
    public void viewStudentCourse(String firstName, String lastName) {
        for (Student student : School.studentList) {
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                List<Course> studentCourseList = student.getCourseList();
                for (Course course : studentCourseList) {
                    System.out.println(course);
                }
            }
        }
    }

    @Override
    public void sortCourses() {
        Collections.sort(School.courseList, new Comparator<Course>() {
            @Override
            public int compare(Course c1, Course c2) {
                return c2.getCurrentStudents() - c1.getCurrentStudents();
            }
        });
    }

    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.viewAllCourses();
    }
}
