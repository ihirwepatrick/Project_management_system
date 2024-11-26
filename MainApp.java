import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import kotlin.*;

public class MainApp {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            // Example of adding a project
            String sql = "INSERT INTO projects (project_name, start_date, end_date, status, description) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "Project A");
                statement.setDate(2,  java.sql.Date.valueOf("2024-01-01"));
                statement.setDate(3,  java.sql.Date.valueOf("2024-01-01"));
                statement.setString(4, "In Progress");
                statement.setString(5, "Description of Project A");
                int modifiedRows = statement.executeUpdate();
                System.out.println(modifiedRows);
            }

            // Add more database operations as needed

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
