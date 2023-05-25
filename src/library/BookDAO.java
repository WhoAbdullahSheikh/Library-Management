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

        try ( PreparedStatement statement = connection.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {

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

    public Book getBookByID(int bookID) {
        Book book = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish a connection to the database
            String url = "jdbc:mysql://localhost:3306/library"; // Replace with your database URL
            String username = "root"; // Replace with your database username
            String password = ""; // Replace with your database password
            connection = DriverManager.getConnection(url, username, password);

            // Prepare the SQL statement
            String sql = "SELECT * FROM books WHERE BID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, bookID);

            // Execute the query
            resultSet = statement.executeQuery();

            // If a book is found, create a Book object and populate its fields
            if (resultSet.next()) {
                String bookName = resultSet.getString("BName");
                String genre = resultSet.getString("genre");
                String price = resultSet.getString("price");

                book = new Book(bookID, bookName, genre, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions here
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return book;
    }

}
