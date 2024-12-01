import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // SQL query to insert a new project
        String sql = "INSERT INTO projects (project_name, start_date, end_date, status, description) VALUES (?, ?, ?, ?, ?)";

        // List of parameters to be inserted into the query
        List<Object> params = Arrays.asList("Project A", Date.valueOf("2024-01-01"), Date.valueOf("2024-12-31"), "In Progress", "Description of Project A");

        // Execute the insert query
        int result = DBQueryUtil.executeUpdate(sql, params);
        if (result > 0) {
            System.out.println("Project added successfully.");
        } else {
            System.out.println("Failed to add project.");
        }

        // SQL query to select projects with 'In Progress' status
        String sql1 = "SELECT * FROM projects WHERE status = ?";


        // List of parameters (in this case, the status)
        List<Object> params1 = Arrays.asList("In Progress");

        // Execute the select query
        try (QueryResult queryResult = DBQueryUtil.executeQuery(sql1, params1)) {
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
