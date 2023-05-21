package library;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/library"; // Replace with your database URL
    private static final String USERNAME = "root"; // Replace with your database username
    private static final String PASSWORD = ""; // Replace with your database password

    public static void insertCredentials(String name, String password) {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "INSERT INTO login (username, password) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, password);
            statement.executeUpdate();
            conn.close();
            System.out.println("Credentials inserted into the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
