import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection; // Initialize your database connection

    public boolean createUser(String username, String password) {
        String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
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
    
    public boolean addUser(String username, String password) {
    String query = "INSERT INTO users (username, password) VALUES (?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, username);
        statement.setString(2, password);
        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0; // If one or more rows were affected, user addition is successful
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Error occurred during the database operation
    }
}
    
}
