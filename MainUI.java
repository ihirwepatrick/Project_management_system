import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainUI {
    public static VBox createMainUI(Stage stage) {
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));

        Label titleLabel = new Label("Project Management System");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Buttons for each action
        Button addProjectButton = new Button("Add New Project");
        Button retrieveProjectsButton = new Button("Retrieve All Projects");
        Button updateProjectButton = new Button("Update Project");
        Button deleteProjectButton = new Button("Delete Project");
        Button addStudentButton = new Button("Add Student to Project");
        Button retrieveStudentsButton = new Button("Retrieve Students in a Project");

        // Event handlers for buttons
        addProjectButton.setOnAction(e -> AddProjectUI.showAddProjectDialog(stage));
        retrieveProjectsButton.setOnAction(e -> RetrieveProjectsUI.showRetrieveProjectsDialog(stage));
        // Add similar handlers for other buttons...

        // ListView for displaying project data
        ListView<String> listView = new ListView<>();
        listView.setPrefHeight(300);

        HBox buttonBox = new HBox(10, addProjectButton, retrieveProjectsButton, updateProjectButton, deleteProjectButton, addStudentButton, retrieveStudentsButton);
        buttonBox.setPadding(new Insets(10));

        mainLayout.getChildren().addAll(titleLabel, buttonBox, listView);
        return mainLayout;
    }
}
