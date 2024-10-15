package mysql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class DBMigration_1 {
    private static final MySQLConnector connector = new MySQLConnector("jdbc:mysql://localhost:3306/student_csv", "root", "");

    public static void createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS student_details ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(255) NOT NULL, "
                + "nationality VARCHAR(100), "
                + "city VARCHAR(100), "
                + "latitude DECIMAL(10, 8), "
                + "longitude DECIMAL(11, 8), "
                + "gender ENUM('Male', 'Female', 'Other'), "
                + "ethnic_group VARCHAR(100), "
                + "age INT, "
                + "english_grade DECIMAL(5, 2), "
                + "math_grade DECIMAL(5, 2), "
                + "sciences_grade DECIMAL(5, 2), "
                + "language_grade DECIMAL(5, 2), "
                + "portfolio_rating DECIMAL(5, 2), "
                + "coverletter_rating DECIMAL(5, 2), "
                + "refletter_rating DECIMAL(5, 2)"
                + ");";

        try (Connection conn = connector.getConnection();
             Statement statement = conn.createStatement()) {
            statement.execute(createTableQuery);
            System.out.println("Table 'student_details' created successfully or already exists.");
        } catch (SQLException e) {
            System.out.println("An error occurred while creating the table: " + e.getMessage());
        }
    }

    public static void readCSVAndInsertIntoDB(String csvFilePath) {
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            while ((line = br.readLine()) != null) {
                String[] studentData = line.split(csvSplitBy);
                if (studentData.length != 16) {
                    System.err.println("Skipping line due to incorrect number of fields: " + studentData.length);
                    continue;
                }

                try {
                    String name = studentData[1];
                    String nationality = studentData[2];
                    String city = studentData[3];
                    double latitude = Double.parseDouble(studentData[4]);
                    double longitude = Double.parseDouble(studentData[5]);
                    String gender = studentData[6];
                    String ethnicGroup = studentData[7];
                    int age = Integer.parseInt(studentData[8]);
                    double englishGrade = Double.parseDouble(studentData[9]);
                    double mathGrade = Double.parseDouble(studentData[10]);
                    double sciencesGrade = Double.parseDouble(studentData[11]);
                    double languageGrade = Double.parseDouble(studentData[12]);
                    double portfolioRating = Double.parseDouble(studentData[13]);
                    double coverLetterRating = Double.parseDouble(studentData[14]);
                    double refLetterRating = Double.parseDouble(studentData[15]);

                    System.out.println("Adding student: " + name + ", Age: " + age);

                    StudentAction.addStudent(name, nationality, city, latitude, longitude, gender, ethnicGroup, age,
                            englishGrade, mathGrade, sciencesGrade, languageGrade, portfolioRating, coverLetterRating,
                            refLetterRating);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing student data: " + e.getMessage() + " in line: " + line);
                } catch (Exception e) {
                    System.err.println("Error adding student: " + e.getMessage() + " with data: " + Arrays.toString(studentData));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }


    public static void updateStudent(Scanner scanner, int studentId) {
        String updateQuery = "UPDATE student_details SET name = ?, nationality = ?, city = ?, latitude = ?, longitude = ?, "
                + "gender = ?, ethnic_group = ?, age = ?, english_grade = ?, math_grade = ?, sciences_grade = ?, "
                + "language_grade = ?, portfolio_rating = ?, coverletter_rating = ?, refletter_rating = ? WHERE id = ?";

        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
            System.out.print("Enter new name: ");
            pstmt.setString(1, scanner.next());
            System.out.print("Enter new nationality: ");
            pstmt.setString(2, scanner.next());
            System.out.print("Enter new city: ");
            pstmt.setString(3, scanner.next());
            System.out.print("Enter new latitude: ");
            pstmt.setDouble(4, scanner.nextDouble());
            System.out.print("Enter new longitude: ");
            pstmt.setDouble(5, scanner.nextDouble());
            System.out.print("Enter new gender: ");
            pstmt.setString(6, scanner.next());
            System.out.print("Enter new ethnic group: ");
            pstmt.setString(7, scanner.next());
            System.out.print("Enter new age: ");
            pstmt.setInt(8, scanner.nextInt());
            System.out.print("Enter new English grade: ");
            pstmt.setDouble(9, scanner.nextDouble());
            System.out.print("Enter new Math grade: ");
            pstmt.setDouble(10, scanner.nextDouble());
            System.out.print("Enter new Sciences grade: ");
            pstmt.setDouble(11, scanner.nextDouble());
            System.out.print("Enter new Language grade: ");
            pstmt.setDouble(12, scanner.nextDouble());
            System.out.print("Enter new Portfolio rating: ");
            pstmt.setDouble(13, scanner.nextDouble());
            System.out.print("Enter new Cover letter rating: ");
            pstmt.setDouble(14, scanner.nextDouble());
            System.out.print("Enter new Reference letter rating: ");
            pstmt.setDouble(15, scanner.nextDouble());
            pstmt.setInt(16, studentId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("No student found with ID: " + studentId);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while updating student: " + e.getMessage());
        }
    }
}
