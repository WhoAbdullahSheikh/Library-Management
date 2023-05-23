package library;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class UserDAO {

    private Connection connection;

    public UserDAO() {
        try {
            String URL = "jdbc:mysql://localhost:3306/library";
            String USERNAME = "root";
            String PASSWORD = "";

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean createUser(String username, String password, String userId, String adminChoice) {
        String query = "INSERT INTO users (username, password, userId, admin) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, userId);
            statement.setString(4, adminChoice);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean validateUser(String username, String password) {
        String query = "SELECT username, password FROM users WHERE username = ?";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try ( ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String dbPassword = rs.getString("password");
                    return password.equals(dbPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean resetDatabase() {
        String query = "DELETE FROM Users";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // If rows are affected, data deletion is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred during the database query
        }
    }
    
    private boolean addBookToDatabase(Book book) {
    // Modify the database connection details according to your configuration
    String url = "jdbc:mysql://localhost:3306/library";
    String username = "root";
    String password = "";

    try (Connection conn = DriverManager.getConnection(url, username, password)) {
        String sql = "INSERT INTO Books (book_id, book_name, genre, price) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, book.getBookID());
        stmt.setString(2, book.getBookName());
        stmt.setString(3, book.getGenre());
        stmt.setString(4, book.getPrice());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
    }

}
