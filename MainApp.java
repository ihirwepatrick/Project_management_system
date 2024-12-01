import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // Add a new student
        System.out.println("=== Add New Student ===");
        Student newStudent = Student.createNewStudent(); // Collect student details
        if (newStudent.saveToDatabase()) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Failed to add student.");
        }

        // Retrieve and display student information
        System.out.println("\n=== Retrieve Students ===");
        List<Student> students = Student.getAllStudents();
        System.out.println("List of Students:");
        for (Student student : students) {
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Phone: " + student.getPhoneNumber());
            System.out.println("Enrollment Date: " + student.getEnrollmentDate());
            System.out.println("-------------------------");
        }
        String studentQuery = "SELECT * FROM students WHERE email LIKE ?";
        List<Object> studentParams = Arrays.asList("%@example.com%"); // Example filter for email domain

        try (QueryResult queryResult = DBQueryUtil.executeQuery(studentQuery, studentParams)) {
            if (queryResult != null) {
                ResultSet resultSet = queryResult.getResultSet();
                while (resultSet.next()) {
                    System.out.println("Student ID: " + resultSet.getInt("student_id"));
                    System.out.println("First Name: " + resultSet.getString("first_name"));
                    System.out.println("Last Name: " + resultSet.getString("last_name"));
                    System.out.println("Email: " + resultSet.getString("email"));
                    System.out.println("Phone Number: " + resultSet.getString("phone_number"));
                    System.out.println("Enrollment Date: " + resultSet.getDate("enrollment_date"));
                    System.out.println("-------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add a new project
        System.out.println("\n=== Add New Project ===");
        String projectSql = "INSERT INTO projects (project_name, start_date, end_date, status, description) VALUES (?, ?, ?, ?, ?)";
        List<Object> projectParams = Arrays.asList(
                "Project A",
                Date.valueOf("2024-01-01"),
                Date.valueOf("2024-12-31"),
                "In Progress",
                "Description of Project A"
        );

        int projectResult = DBQueryUtil.executeUpdate(projectSql, projectParams);
        if (projectResult > 0) {
            System.out.println("Project added successfully.");
        } else {
            System.out.println("Failed to add project.");
        }

        // Retrieve and display project information
        System.out.println("\n=== Retrieve Projects ===");
        String projectQuery = "SELECT * FROM projects WHERE status = ?";
        List<Object> projectParams1 = Arrays.asList("In Progress");

        try (QueryResult queryResult = DBQueryUtil.executeQuery(projectQuery, projectParams1)) {
            if (queryResult != null) {
                ResultSet resultSet = queryResult.getResultSet();
                while (resultSet.next()) {
                    System.out.println("Project Name: " + resultSet.getString("project_name"));
                    System.out.println("Start Date: " + resultSet.getDate("start_date"));
                    System.out.println("End Date: " + resultSet.getDate("end_date"));
                    System.out.println("Status: " + resultSet.getString("status"));
                    System.out.println("Description: " + resultSet.getString("description"));
                    System.out.println("-------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
