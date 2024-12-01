import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryResult implements AutoCloseable {
    private final ResultSet resultSet;
    private final PreparedStatement statement;
    private final Connection connection;

    public QueryResult(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        this.resultSet = resultSet;
        this.statement = statement;
        this.connection = connection;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    @Override
    public void close() throws SQLException {
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (connection != null) connection.close();
    }
}
