import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Project Management System ===");
            System.out.println("1. Add New Project");
            System.out.println("2. Retrieve All Projects");
            System.out.println("3. Update Project");
            System.out.println("4. Delete Project");
            System.out.println("5. Add Student to Project");
            System.out.println("6. Retrieve Students in a Project");
            System.out.println("7. Exit");
            System.out.print("Select an option (1-7): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
                continue;
            }

            switch (choice) {
                case 1:
                    addNewProject(scanner);
                    break;
                case 2:
                    retrieveAllProjects();
                    break;
                case 3:
                    updateProject(scanner);
                    break;
                case 4:
                    deleteProject(scanner);
                    break;
                case 5:
                    addStudentToProject(scanner);
                    break;
                case 6:
                    retrieveStudentsInProject(scanner);
                    break;
                case 7:
                    System.out.println("Exiting the application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }

    // Add a new project
    private static void addNewProject(Scanner scanner) {
        System.out.println("\n=== Add New Project ===");
        Project newProject = new Project();
        System.out.print("Enter Project Name: ");
        newProject.setProjectName(scanner.nextLine());
        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        newProject.setStartDate(Date.valueOf(scanner.nextLine()));
        System.out.print("Enter End Date (YYYY-MM-DD): ");
        newProject.setEndDate(Date.valueOf(scanner.nextLine()));
        System.out.print("Enter Status: ");
        newProject.setStatus(scanner.nextLine());
        System.out.print("Enter Description: ");
        newProject.setDescription(scanner.nextLine());

        if (newProject.saveToDatabase()) {
            System.out.println("Project added successfully.");
        } else {
            System.out.println("Failed to add project.");
        }
    }

    // Retrieve and display all projects
    private static void retrieveAllProjects() {
        System.out.println("\n=== Retrieve Projects ===");
        List<Project> projects = Project.getAllProjects();

        if (projects.isEmpty()) {
            System.out.println("No projects found.");
            return;
        }

        System.out.println("==================================================================================================");
        System.out.printf("| %-5s | %-20s | %-12s | %-12s | %-15s | %-30s |\n",
                "ID", "Name", "Start Date", "End Date", "Status", "Description");
        System.out.println("==================================================================================================");

        for (Project project : projects) {
            System.out.printf("| %-5d | %-20s | %-12s | %-12s | %-15s | %-30s |\n",
                    project.getId(),
                    project.getProjectName(),
                    project.getStartDate(),
                    project.getEndDate(),
                    project.getStatus(),
                    project.getDescription());
        }
        System.out.println("==================================================================================================");
    }

    // Update an existing project
    private static void updateProject(Scanner scanner) {
        System.out.println("\n=== Update Project ===");
        System.out.print("Enter Project ID to Update: ");
        int updateId;

        try {
            updateId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a valid number.");
            return;
        }

        Project updateProject = new Project();
        updateProject.setId(updateId);
        System.out.print("Enter New Project Name: ");
        updateProject.setProjectName(scanner.nextLine());
        System.out.print("Enter New Start Date (YYYY-MM-DD): ");
        updateProject.setStartDate(Date.valueOf(scanner.nextLine()));
        System.out.print("Enter New End Date (YYYY-MM-DD): ");
        updateProject.setEndDate(Date.valueOf(scanner.nextLine()));
        System.out.print("Enter New Status: ");
        updateProject.setStatus(scanner.nextLine());
        System.out.print("Enter New Description: ");
        updateProject.setDescription(scanner.nextLine());

        if (updateProject.updateInDatabase()) {
            System.out.println("Project updated successfully.");
        } else {
            System.out.println("Failed to update project. Ensure the ID exists.");
        }
    }

    // Delete a project
    private static void deleteProject(Scanner scanner) {
        System.out.println("\n=== Delete Project ===");
        System.out.print("Enter Project ID to Delete: ");
        int deleteId;

        try {
            deleteId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a valid number.");
            return;
        }

        Project deleteProject = new Project();
        deleteProject.setId(deleteId);

        if (deleteProject.deleteFromDatabase()) {
            System.out.println("Project deleted successfully.");
        } else {
            System.out.println("Failed to delete project. Ensure the ID exists.");
        }
    }

    // Add a student to a project
    private static void addStudentToProject(Scanner scanner) {
        System.out.println("\n=== Add Student to Project ===");
        ProjectStudent projectStudent = new ProjectStudent();

        System.out.print("Enter Project ID: ");
        projectStudent.setProjectId(Integer.parseInt(scanner.nextLine()));

        System.out.print("Enter Student ID: ");
        projectStudent.setStudentId(Integer.parseInt(scanner.nextLine()));

        System.out.print("Enter Role (e.g., Developer, Designer): ");
        projectStudent.setRole(scanner.nextLine());

        if (projectStudent.addStudentToProject()) {
            System.out.println("Student added to project successfully.");
        } else {
            System.out.println("Failed to add student to project.");
        }
    }

    // Retrieve students working on a project based on Project ID
    private static void retrieveStudentsInProject(Scanner scanner) {
        System.out.println("\n=== Retrieve Students in a Project ===");
        System.out.print("Enter Project ID: ");
        int projectId = Integer.parseInt(scanner.nextLine());

        List<ProjectStudent> students = ProjectStudent.getStudentsByProject(projectId);

        if (students.isEmpty()) {
            System.out.println("No students found for this project.");
            return;
        }

        System.out.println("==================================================================================================");
        System.out.printf("| %-5s | %-15s |\n", "Student ID", "Role");
        System.out.println("==================================================================================================");

        for (ProjectStudent student : students) {
            System.out.printf("| %-5d | %-15s |\n", student.getStudentId(), student.getRole());
        }
        System.out.println("==================================================================================================");
    }
}
