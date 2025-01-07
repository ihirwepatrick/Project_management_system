import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Date;

public class AddProjectUI {
    public static void showAddProjectDialog(Stage owner) {
        Stage dialog = new Stage();
        dialog.initOwner(owner);
        dialog.setTitle("Add New Project");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Form fields
        Label nameLabel = new Label("Project Name:");
        TextField nameField = new TextField();

        Label startDateLabel = new Label("Start Date (YYYY-MM-DD):");
        TextField startDateField = new TextField();

        Label endDateLabel = new Label("End Date (YYYY-MM-DD):");
        TextField endDateField = new TextField();

        Label statusLabel = new Label("Status:");
        TextField statusField = new TextField();

        Label descriptionLabel = new Label("Description:");
        TextField descriptionField = new TextField();

        Button submitButton = new Button("Add Project");
        submitButton.setOnAction(e -> {
            // Handle adding the project
            Project newProject = new Project();
            newProject.setProjectName(nameField.getText());
            newProject.setStartDate(Date.valueOf(startDateField.getText()));
            newProject.setEndDate(Date.valueOf(endDateField.getText()));
            newProject.setStatus(statusField.getText());
            newProject.setDescription(descriptionField.getText());

            if (newProject.saveToDatabase()) {
                System.out.println("Project added successfully.");
            } else {
                System.out.println("Failed to add project.");
            }
            dialog.close();
        });

        gridPane.addRow(0, nameLabel, nameField);
        gridPane.addRow(1, startDateLabel, startDateField);
        gridPane.addRow(2, endDateLabel, endDateField);
        gridPane.addRow(3, statusLabel, statusField);
        gridPane.addRow(4, descriptionLabel, descriptionField);
        gridPane.addRow(5, submitButton);

        Scene scene = new Scene(gridPane, 400, 300);
        dialog.setScene(scene);
        dialog.show();
    }
}
