package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentAction {
    private static final MySQLConnector connector = new MySQLConnector("jdbc:mysql://localhost:3306/student_csv", "root", "");
    private static final Connection conn = connector.getConnection();

    public static void addStudent(String name, String nationality, String city, double latitude, double longitude,
                           String gender, String ethnic_group, int age, double english_grade,
                           double math_grade, double sciences_grade, double language_grade,
                           double portfolio_rating, double coverletter_rating, double refletter_rating) {
        String insertQuery = "INSERT INTO student_details (name, nationality, city, latitude, longitude, gender, ethnic_group, age, english_grade, math_grade, sciences_grade, language_grade, portfolio_rating, coverletter_rating, refletter_rating) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            // Set the parameters for the prepared statement
            stmt.setString(1, name);
            stmt.setString(2, nationality);
            stmt.setString(3, city);
            stmt.setDouble(4, latitude);
            stmt.setDouble(5, longitude);
            stmt.setString(6, gender);
            stmt.setString(7, ethnic_group);
            stmt.setInt(8, age);
            stmt.setDouble(9, english_grade);
            stmt.setDouble(10, math_grade);
            stmt.setDouble(11, sciences_grade);
            stmt.setDouble(12, language_grade);
            stmt.setDouble(13, portfolio_rating);
            stmt.setDouble(14, coverletter_rating);
            stmt.setDouble(15, refletter_rating);
            stmt.executeUpdate();
            System.out.println("Student added successfully!");

        } catch (SQLException e) {
            System.err.println("An error occurred while adding the student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static   void updateStudent(int studentId, String name, String nationality, String city, double latitude, double longitude,
                              String gender, String ethnic_group, int age, double english_grade,
                              double math_grade, double sciences_grade, double language_grade,
                              double portfolio_rating, double coverletter_rating, double refletter_rating) {
        String updateQuery = "UPDATE student_details SET name=?, nationality=?, city=?, latitude=?, longitude=?, gender=?, ethnic_group=?, age=?, english_grade=?, math_grade=?, sciences_grade=?, language_grade=?, portfolio_rating=?, coverletter_rating=?, refletter_rating=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            // Set the parameters for the prepared statement
            stmt.setString(1, name);
            stmt.setString(2, nationality);
            stmt.setString(3, city);
            stmt.setDouble(4, latitude);
            stmt.setDouble(5, longitude);
            stmt.setString(6, gender);
            stmt.setString(7, ethnic_group);
            stmt.setInt(8, age);
            stmt.setDouble(9, english_grade);
            stmt.setDouble(10, math_grade);
            stmt.setDouble(11, sciences_grade);
            stmt.setDouble(12, language_grade);
            stmt.setDouble(13, portfolio_rating);
            stmt.setDouble(14, coverletter_rating);
            stmt.setDouble(15, refletter_rating);
            stmt.setInt(16, studentId);
            stmt.executeUpdate();
            System.out.println("Student updated successfully!");

        } catch (SQLException e) {
            System.err.println("An error occurred while updating the student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void deleteStudent(int studentId) {
        String deleteQuery = "DELETE FROM student_details WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
            stmt.setInt(1, studentId);
            stmt.executeUpdate();
            System.out.println("Student deleted successfully!");

        } catch (SQLException e) {
            System.err.println("An error occurred while deleting the student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void getStudentById(int studentId) {
        String selectQuery = "SELECT * FROM student_details WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Student Details:");
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Nationality: " + rs.getString("nationality"));
                System.out.println("City: " + rs.getString("city"));
                System.out.println("Latitude: " + rs.getDouble("latitude"));
                System.out.println("Longitude: " + rs.getDouble("longitude"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("Ethnic Group: " + rs.getString("ethnic_group"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("English Grade: " + rs.getDouble("english_grade"));
                System.out.println("Math Grade: " + rs.getDouble("math_grade"));
                System.out.println("Sciences Grade: " + rs.getDouble("sciences_grade"));
                System.out.println("Language Grade: " + rs.getDouble("language_grade"));
                System.out.println("Portfolio Rating: " + rs.getDouble("portfolio_rating"));
                System.out.println("Cover Letter Rating: " + rs.getDouble("coverletter_rating"));
                System.out.println("Reference Letter Rating: " + rs.getDouble("refletter_rating"));
            } else {
                System.out.println("Student with ID " + studentId + " not found.");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while retrieving the student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void getAllStudents() {
        String selectAllQuery = "SELECT * FROM student_details";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(selectAllQuery);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
                // You can print more details as needed
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while retrieving all students: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
