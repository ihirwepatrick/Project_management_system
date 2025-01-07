import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectStudent {
    private int projectId;
    private int studentId;
    private String role;

    // Getters and Setters
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Method to add a student to a project
    public boolean addStudentToProject() {
        String query = "INSERT INTO project_students (project_id, student_id, role) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.projectId);
            stmt.setInt(2, this.studentId);
            stmt.setString(3, this.role);
            int result = stmt.executeUpdate();
            return result > 0; // Returns true if the student was successfully added
        } catch (SQLException e) {
            System.out.println("Error adding student to project: " + e.getMessage());
            return false;
        }
    }

    // Method to retrieve all students working on a specific project
    public static List<ProjectStudent> getStudentsByProject(int projectId) {
        List<ProjectStudent> students = new ArrayList<>();
        String query = "SELECT s.student_id, s.first_name, s.last_name, s.email, ps.role " +
                "FROM students s " +
                "JOIN project_students ps ON s.student_id = ps.student_id " +
                "WHERE ps.project_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, projectId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProjectStudent projectStudent = new ProjectStudent();
                projectStudent.setStudentId(rs.getInt("student_id"));
                projectStudent.setRole(rs.getString("role"));
                // Optionally, you can create a student object here as well and set its properties
                // projectStudent.setStudent(new Student(rs.getInt("student_id"), rs.getString("first_name"), ...));

                students.add(projectStudent);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving students for project: " + e.getMessage());
        }
        return students;
    }

    // Method to remove a student from a project
    public boolean removeStudentFromProject() {
        String query = "DELETE FROM project_students WHERE project_id = ? AND student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.projectId);
            stmt.setInt(2, this.studentId);
            int result = stmt.executeUpdate();
            return result > 0; // Returns true if the student was successfully removed
        } catch (SQLException e) {
            System.out.println("Error removing student from project: " + e.getMessage());
            return false;
        }
    }
}
