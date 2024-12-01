import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date enrollmentDate;

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    // Method to collect student details from user input
    public static Student createNewStudent() {
        Scanner scanner = new Scanner(System.in);

        Student student = new Student();
        System.out.print("Enter First Name: ");
        student.setFirstName(scanner.nextLine());

        System.out.print("Enter Last Name: ");
        student.setLastName(scanner.nextLine());

        System.out.print("Enter Email: ");
        student.setEmail(scanner.nextLine());

        System.out.print("Enter Phone Number: ");
        student.setPhoneNumber(scanner.nextLine());

        System.out.print("Enter Enrollment Date (YYYY-MM-DD): ");
        student.setEnrollmentDate(Date.valueOf(scanner.nextLine()));

        return student;
    }

    // Method to save student details to the database
    public boolean saveToDatabase() {
        String sql = "INSERT INTO students (first_name, last_name, email, phone_number, enrollment_date) VALUES (?, ?, ?, ?, ?)";
        List<Object> params = Arrays.asList(firstName, lastName, email, phoneNumber, enrollmentDate);

        int result = DBQueryUtil.executeUpdate(sql, params);
        return result > 0;
    }

    // Method to retrieve all students from the database
    public static List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();

        try (QueryResult queryResult = DBQueryUtil.executeQuery(sql, new ArrayList<>())) {
            if (queryResult != null) {
                ResultSet resultSet = queryResult.getResultSet();
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setStudentId(resultSet.getInt("student_id"));
                    student.setFirstName(resultSet.getString("first_name"));
                    student.setLastName(resultSet.getString("last_name"));
                    student.setEmail(resultSet.getString("email"));
                    student.setPhoneNumber(resultSet.getString("phone_number"));
                    student.setEnrollmentDate(resultSet.getDate("enrollment_date"));
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
}
