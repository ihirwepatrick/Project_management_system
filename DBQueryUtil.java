import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBQueryUtil {

    // Method to execute an update query (INSERT, UPDATE, DELETE)
    public static int executeUpdate(String sql, List<Object> params) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set parameters dynamically
            setParameters(statement, params);

            // Execute the update query and return the number of affected rows
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;  // Return 0 if execution fails
        }
    }

    // Method to execute a select query and return a QueryResult
    public static QueryResult executeQuery(String sql, List<Object> params) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            // Set parameters dynamically
            setParameters(statement, params);

            // Execute the query and return the result as a QueryResult object
            return new QueryResult(statement.executeQuery(), statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Return null if query execution fails
        }
    }

    // Utility method to set parameters in a PreparedStatement
    private static void setParameters(PreparedStatement statement, List<Object> params) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            statement.setObject(i + 1, params.get(i));  // Set each parameter dynamically
        }
    }
}
