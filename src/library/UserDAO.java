package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;

public class UserDAO {

    private Connection connection; // Initialize your database connection

    public UserDAO() {
        try {
            // Replace the databaseUrl, username, and password with your MySQL configuration
            String URL = "jdbc:mysql://localhost:3306/library"; // Replace with your database URL
            String USERNAME = "root"; // Replace with your database username
            String PASSWORD = ""; // Replace with your database password

            // Establish the database connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean createUser(String username, String password) {
        String query = "INSERT INTO users (username, password, UserID, Admin) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
           
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // If a row is affected, user creation is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred during the database query
        }
    }

}
