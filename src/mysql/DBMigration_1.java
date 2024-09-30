package mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMigration_1 {
    private static final MySQLConnector connector = new MySQLConnector("jdbc:mysql://localhost:3306/student_csv", "", "");
    private static final Connection conn = connector.getConnection();

    public static void createTable() {
        String createTableQuery = "CREATE TABLE student_details ("
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


        try {
            Statement statement = conn.createStatement();
            statement.execute(createTableQuery);
            System.out.println("Table 'student_details' created successfully.");

        } catch (SQLException e) {
            System.out.println("An error occurred while creating the table: " + e.getMessage());
        }
    }

}
