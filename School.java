
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class School {
    public static List<Course> courseList = new ArrayList<>();
    public static List<Student> studentList = new ArrayList<>();

    //this will create ser files 
    static {
        try {
            File file = new File("file/courses.ser");
            if (file.exists()){
                FileInputStream cfis = new FileInputStream(file);
                ObjectInputStream cois = new ObjectInputStream(cfis);
                courseList = (List<Course>) cois.readObject();
                cfis.close();
                cois.close();

                FileInputStream sfis = new FileInputStream("file/students.ser");
                ObjectInputStream sois = new ObjectInputStream(sfis);
                studentList = (List<Student>) sois.readObject();
                sfis.close();
                sois.close();
            }else {
                BufferedReader reader = new BufferedReader(new FileReader("file/MyUniversityCoursesFile.csv"));
                reader.readLine();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] item = line.split(",");
                    Course course = new Course();
                    course.setCourseName(item[0]);
                    course.setCourseId(item[1]);
                    course.setMaximumStudents(Integer.parseInt(item[2]));
                    course.setCurrentStudents(Integer.parseInt(item[3]));
                    course.setCourseInstructor(item[5]);
                    course.setCourseSectionNumber(Integer.parseInt(item[6]));
                    course.setCourseLocation(item[7]);
                    courseList.add(course);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input login username (No matter for admin or student):");
        String username = scanner.nextLine();
        if ("Admin".equals(username)) {
            System.out.println("Please input password:");
            String password = scanner.nextLine();
            if ("Admin001".equals(password)) {
                Admin admin = new Admin();
                admin.setUserName(username);
                admin.setPassword(password);
                showAdminMenu(admin);
            } else {
                System.out.println("Error password!");
                login();
            }
        } else {
            boolean existUsername = false;
            for (Student stu : studentList) {
                if (username.equals(stu.getUserName())) {
                    existUsername = true;
                    System.out.println("Please input password:");
                    String password = scanner.nextLine();
                    if (stu.getPassword().equals(password)) {
                        showStudentMenu(stu);
                    } else {
                        System.out.println("Error password!");
                        login();
                    }
                }
            }
            if (!existUsername) {
                System.out.println("Username is not exist!");
                login();
            }
        }
    }

    public static void exit() {
        try {
            FileOutputStream cfos = new FileOutputStream("file/courses.ser");
            ObjectOutputStream coos = new ObjectOutputStream(cfos);
            coos.writeObject(courseList);
            cfos.close();
            coos.close();
            FileOutputStream sfos = new FileOutputStream("file/students.ser");
            ObjectOutputStream soos = new ObjectOutputStream(sfos);
            soos.writeObject(studentList);
            sfos.close();
            soos.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }

    private static void showStudentMenu(Student stu) {
        System.out.println("1.View all courses");
        System.out.println("2.View all courses that are not FULL");
        System.out.println("3.Register on a course");
        System.out.println("4.Withdraw from a course");
        System.out.println("5.View all courses that the current student is being registered in");
        System.out.println("6.Exit");
        System.out.println("Please select your operation:");
        Scanner scanner = new Scanner(System.in);
        int op = scanner.nextInt();
        switch (op) {
            case 1:
                viewAllCourse(stu);
                showStudentMenu(stu);
                break;
            case 2:
                viewNotFullCourse(stu);
                showStudentMenu(stu);
                break;
            case 3:
                registerCourse(stu);
                showStudentMenu(stu);
                break;
            case 4:
                withdrawCourse(stu);
                showStudentMenu(stu);
                break;
            case 5:
                viewAllRegisterCourse(stu);
                showStudentMenu(stu);
                break;
            case 6:
                exit();
                break;
            default:
                System.out.println("Input error, please re-enter!");
                showStudentMenu(stu);
        }
    }

    private static void viewAllRegisterCourse(Student stu) {
        stu.viewAllRegisterCourse();
    }

    private static void withdrawCourse(Student stu) {
        System.out.println("Please entry withdraw course name:");
        Scanner scanner = new Scanner(System.in);
        String courseName = scanner.nextLine();
        stu.withdrawCourse(courseName, stu.getFirstName() + " " + stu.getLastName());
    }

    private static void registerCourse(Student stu) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the course you wish to register for:");
        String courseName = scanner.nextLine();
        System.out.println("Please enter the section of the course you wish to register for:");
        int section = scanner.nextInt();
        stu.registerCourse(courseName, section, stu.getFirstName() + " " + stu.getLastName());
    }

    private static void viewNotFullCourse(Student stu) {
        stu.viewNotFullCourse();
    }

    private static void viewAllCourse(Student stu) {
        stu.viewAllCourse();
    }

    private static void showAdminMenu(Admin admin) {
        System.out.println("1.Courses Management");
        System.out.println("2.Reports");
        System.out.println("Please select your operation:");
        Scanner scanner = new Scanner(System.in);
        int op = scanner.nextInt();
        if (op == 1) {
            showCoursesManagementMenu(admin);
        } else if (op == 2) {
            showReportsMenu(admin);
        } else {
            System.out.println("Input error, please re-enter!");
            showAdminMenu(admin);
        }
    }

    private static void showCoursesManagementMenu(Admin admin) {
        System.out.println("1.Create a new course");
        System.out.println("2.Delete a course");
        System.out.println("3.Edit a course");
        System.out.println("4.Display information for a given course");
        System.out.println("5.Register a student");
        System.out.println("6.Exit");
        System.out.println("Please select your operation:");
        Scanner scanner = new Scanner(System.in);
        int op = scanner.nextInt();
        switch (op) {
            case 1:
                createCourse(admin);
                showCoursesManagementMenu(admin);
                break;
            case 2:
                deleteCourse(admin);
                showCoursesManagementMenu(admin);
                break;
            case 3:
                editCourse(admin);
                showCoursesManagementMenu(admin);
                break;
            case 4:
                displayCourseInfo(admin);
                showCoursesManagementMenu(admin);
                break;
            case 5:
                registerStudent(admin);
                showCoursesManagementMenu(admin);
                break;
            case 6:
                exit();
                break;
            default:
                System.out.println("Input error, please re-enter!");
                showCoursesManagementMenu(admin);
        }
    }

    private static void registerStudent(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        System.out.println("Please enter the student's username:");
        student.setUserName(scanner.nextLine());
        System.out.println("Please enter the student's password:");
        student.setPassword(scanner.nextLine());
        System.out.println("Please enter the student's first name:");
        student.setFirstName(scanner.nextLine());
        System.out.println("Please enter the student's last name:");
        student.setLastName(scanner.nextLine());
        admin.registerStudent(student);
        System.out.println("Registered student information successfully!");
    }

    private static void displayCourseInfo(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the ID of the course you want to display:");
        String courseID = scanner.nextLine();
        admin.displayCourseInfo(courseID);
    }

    //for edit; I guess course name could not be editted so I did not provide the option to edit coursename
    private static void editCourse(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        Course course = new Course();
        System.out.println("Please enter the ID of the course you want to modify:");
        course.setCourseId(scanner.nextLine());
        System.out.println("Please input maximum number of students registered in the course:");
        course.setMaximumStudents(Integer.parseInt(scanner.nextLine()));
        System.out.println("Please input course instructor:");
        course.setCourseInstructor(scanner.nextLine());
        System.out.println("Please input course section number:");
        course.setCourseSectionNumber(Integer.parseInt(scanner.nextLine()));
        System.out.println("Please input course location:");
        course.setCourseLocation(scanner.nextLine());
        admin.editCourse(course);
        System.out.println("Successfully edit the course!");
    }

    private static void deleteCourse(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the ID of the course you want to delete:");
        String courseId = scanner.nextLine();
        admin.deleteCourse(courseId);
        System.out.println("Successfully delete the course");
    }

    private static void createCourse(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        Course course = new Course();
        System.out.println("Please input course id:");
        course.setCourseId(scanner.nextLine());
        System.out.println("Please input course name:");
        course.setCourseName(scanner.nextLine());
        System.out.println("Please input maximum number of students registered in the course:");
        course.setMaximumStudents(Integer.parseInt(scanner.nextLine()));
        System.out.println("Please input course instructor:");
        course.setCourseInstructor(scanner.nextLine());
        System.out.println("Please input course section number:");
        course.setCourseSectionNumber(Integer.parseInt(scanner.nextLine()));
        System.out.println("Please input course location:");
        course.setCourseLocation(scanner.nextLine());
        admin.createCourse(course);
        System.out.println("Successfully create a new course!");
    }

    private static void showReportsMenu(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.View all courses");
        System.out.println("2.View all courses that are Full");
        System.out.println("3.Write to a file the list of course that are Full");
        System.out.println("4.View the names of the students being registered in a specific course");
        System.out.println("5.View the list of courses that a given student is being registered on");
        System.out.println("6.Sort courses based on the current number of student registers");
        System.out.println("7.Exit");
        System.out.println("Please select your operation:");
        int op = scanner.nextInt();
        switch (op) {
            case 1:
                viewAllCourses(admin);
                showReportsMenu(admin);
                break;
            case 2:
                viewAllFullCourse(admin);
                showReportsMenu(admin);
                break;
            case 3:
                writeFullCourse(admin);
                showReportsMenu(admin);
                break;
            case 4:
                viewStudentNames(admin);
                showReportsMenu(admin);
                break;
            case 5:
                viewStudentCourse(admin);
                showReportsMenu(admin);
                break;
            case 6:
                sortCourses(admin);
                showReportsMenu(admin);
                break;
            case 7:
                exit();
                break;
            default:
                System.out.println("Input error, please re-enter!");
                showReportsMenu(admin);
        }
    }

    private static void sortCourses(Admin admin) {
        admin.sortCourses();
    }

    private static void viewStudentCourse(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the student's first name:");
        String firstName = scanner.nextLine();
        System.out.println("Please enter the student's last name:");
        String lastName = scanner.nextLine();
        admin.viewStudentCourse(firstName, lastName);
    }

    private static void viewStudentNames(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the course name:");
        admin.viewStudentNames(scanner.nextLine());
    }

    private static void writeFullCourse(Admin admin) {
        admin.writeFullCourse();
    }

    private static void viewAllFullCourse(Admin admin) {
        admin.viewAllFullCourse();
    }

    private static void viewAllCourses(Admin admin) {
        admin.viewAllCourses();
    }

    public static void main(String[] args) {
        login();
    }


}
