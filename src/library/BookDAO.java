package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO() {
        connectToDatabase();
    }

    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/library"; // Replace with your database URL
        String username = "root"; // Replace with your database username
        String password = ""; // Replace with your database password

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();

        String query = "SELECT * FROM books";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int bookID = resultSet.getInt("BID");
                String bookName = resultSet.getString("BName");
                String genre = resultSet.getString("genre");
                String price = resultSet.getString("price");

                Book book = new Book(bookID, bookName, genre, price);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }
}
