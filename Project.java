import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Project {
    private int id;
    private String projectName;
    private Date startDate;
    private Date endDate;
    private String status;
    private String description;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("ID must be a positive integer.");
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        if (projectName == null || projectName.isEmpty()) {
            throw new IllegalArgumentException("Project name cannot be null or empty.");
        }
        this.projectName = projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null.");
        }
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        if (endDate != null && endDate.before(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty.");
        }
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Save project to the database
    public boolean saveToDatabase() {
        String sql = "INSERT INTO projects (project_name, start_date, end_date, status, description) VALUES (?, ?, ?, ?, ?)";
        List<Object> params = Arrays.asList(projectName, startDate, endDate, status, description);

        int result = DBQueryUtil.executeUpdate(sql, params);
        return result > 0;
    }

    // Update project in the database
    public boolean updateInDatabase() {
        if (id <= 0) {
            throw new IllegalStateException("Project ID must be set for update.");
        }

        String sql = "UPDATE projects SET project_name = ?, start_date = ?, end_date = ?, status = ?, description = ? WHERE id = ?";
        List<Object> params = Arrays.asList(projectName, startDate, endDate, status, description, id);

        int result = DBQueryUtil.executeUpdate(sql, params);
        return result > 0;
    }

    // Delete project from the database
    public boolean deleteFromDatabase() {
        if (id <= 0) {
            throw new IllegalStateException("Project ID must be set for deletion.");
        }

        String sql = "DELETE FROM projects WHERE id = ?";
        List<Object> params = Arrays.asList(id);

        int result = DBQueryUtil.executeUpdate(sql, params);
        return result > 0;
    }

    // Retrieve all projects from the database
    public static List<Project> getAllProjects() {
        String sql = "SELECT * FROM projects";
        List<Project> projects = new ArrayList<>();

        QueryResult queryResult = DBQueryUtil.executeQuery(sql, new ArrayList<>());
        if (queryResult != null) {
            ResultSet resultSet = queryResult.getResultSet();
            try {
                while (resultSet.next()) {
                    projects.add(mapResultSetToProject(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error reading projects from the database: " + e.getMessage(), e);
            }
        }

        return projects;
    }

    // Helper method: Map a ResultSet row to a Project object
    private static Project mapResultSetToProject(ResultSet resultSet) {
        try {
            Project project = new Project();
            project.setId(resultSet.getInt("id"));
            project.setProjectName(resultSet.getString("project_name"));
            project.setStartDate(resultSet.getDate("start_date"));
            project.setEndDate(resultSet.getDate("end_date"));
            project.setStatus(resultSet.getString("status"));
            project.setDescription(resultSet.getString("description"));
            return project;
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping ResultSet to Project: " + e.getMessage(), e);
        }
    }
}
