
import mysql.DBMigration_1;
import mysql.MySQLConnector;
import mysql.StudentAction;

import java.util.Scanner;

public class Main {

    private static final MySQLConnector connector = new MySQLConnector("jdbc:mysql://localhost:3306/student_csv", "root", "");

    public static void main(String[] args) {
        DBMigration_1.createTable();

        String csvFilePath = "src/student-dataset.csv"; // Specify your CSV file path here
        DBMigration_1.readCSVAndInsertIntoDB(csvFilePath);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nOptions:");
            System.out.println("1. Display all students");
            System.out.println("2. Update a student");
            System.out.println("3. Delete a student");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    StudentAction.getAllStudents();
                    break;
                case 2:
                    System.out.print("Enter student ID to update: ");
                    int studentIdToUpdate = scanner.nextInt();
                    DBMigration_1.updateStudent(scanner, studentIdToUpdate);
                    break;
                case 3:
                    System.out.print("Enter student ID to delete: ");
                    int studentIdToDelete = scanner.nextInt();
                    StudentAction.deleteStudent(studentIdToDelete);
                    break;
                case 4:
                    // Exit the program
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Program exited.");
    }
}
