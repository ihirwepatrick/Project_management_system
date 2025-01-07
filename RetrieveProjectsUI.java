import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class RetrieveProjectsUI {
    public static void showRetrieveProjectsDialog(Stage owner) {
        Stage dialog = new Stage();
        dialog.initOwner(owner);
        dialog.setTitle("Retrieve All Projects");

        TableView<Project> tableView = new TableView<>();

        // Define table columns
        TableColumn<Project, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Project, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));

        TableColumn<Project, String> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn<Project, String> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        TableColumn<Project, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Project, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Add columns to the table
        tableView.getColumns().addAll(idColumn, nameColumn, startDateColumn, endDateColumn, statusColumn, descriptionColumn);

        // Fetch projects from the database and populate the table
        List<Project> projects = Project.getAllProjects();
        ObservableList<Project> observableProjects = FXCollections.observableArrayList(projects);
        tableView.setItems(observableProjects);

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox, 800, 400);
        dialog.setScene(scene);
        dialog.show();
    }
}
