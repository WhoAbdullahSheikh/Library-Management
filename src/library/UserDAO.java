package library;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO 
{
    private Connection connection;

    public UserDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public User getUserByUsernameAndPassword(String username, String password) { User user = null;
        try {
            String query = "SELECT * FROM login WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
