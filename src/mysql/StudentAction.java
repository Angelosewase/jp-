package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentAction {
    private static final MySQLConnector connector = new MySQLConnector("jdbc:mysql://localhost:3306/student_csv", "", "");
    private static final Connection conn = connector.getConnection();

    public void addStudent(String name, String nationality, String city, double latitude, double longitude,
                           String gender, String ethnic_group, int age, double english_grade,
                           double math_grade, double sciences_grade, double language_grade,
                           double portfolio_rating, double coverletter_rating, double refletter_rating) {
        String insertQuery = "INSERT INTO student_details (name, nationality, city, latitude, longitude, gender, ethnic_group, age, english_grade, math_grade, sciences_grade, language_grade, portfolio_rating, coverletter_rating, refletter_rating) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            // Set the parameters for the prepared statement
            stmt.setString(1, name);                    // Name
            stmt.setString(2, nationality);             // Nationality
            stmt.setString(3, city);                    // City
            stmt.setDouble(4, latitude);                // Latitude (DECIMAL)
            stmt.setDouble(5, longitude);               // Longitude (DECIMAL)
            stmt.setString(6, gender);                  // Gender
            stmt.setString(7, ethnic_group);            // Ethnic Group
            stmt.setInt(8, age);                        // Age
            stmt.setDouble(9, english_grade);           // English Grade (DECIMAL)
            stmt.setDouble(10, math_grade);             // Math Grade (DECIMAL)
            stmt.setDouble(11, sciences_grade);         // Sciences Grade (DECIMAL)
            stmt.setDouble(12, language_grade);         // Language Grade (DECIMAL)
            stmt.setDouble(13, portfolio_rating);       // Portfolio Rating (DECIMAL)
            stmt.setDouble(14, coverletter_rating);     // Cover Letter Rating (DECIMAL)
            stmt.setDouble(15, refletter_rating);       // Reference Letter Rating (DECIMAL)

            // Execute the insert statement
            stmt.executeUpdate();
            System.out.println("Student added successfully!");

        } catch (SQLException e) {
            System.err.println("An error occurred while adding the student: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
