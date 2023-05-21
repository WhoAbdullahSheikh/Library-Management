package library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class UserTableExample extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public UserTableExample() {
        initComponents();
    }

    private void initComponents() {
        // Create JTable and table model
        table = new JTable();
        tableModel = new DefaultTableModel();
        table.setModel(tableModel);

        // Set column headers
        tableModel.addColumn("Username");
        tableModel.addColumn("Password");
        tableModel.addColumn("UserID");
        tableModel.addColumn("Admin");

        // Add JTable to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void fetchDataFromDatabase(String username, String password, String userID, String adminChoice) {
        // Replace with your database configuration
        String URL = "jdbc:mysql://localhost:3306/library";
        String USERNAME = "root";
        String PASSWORD = "";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // Construct the SQL query based on user input
            String query = "SELECT username, password FROM Users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, userID);
            statement.setString(4, adminChoice);

            // Execute the query and retrieve the result set
            ResultSet resultSet = statement.executeQuery();

            // Clear existing data in the table model
            tableModel.setRowCount(0);

            // Iterate over the result set and populate the table model
            while (resultSet.next()) {
                String fetchedUsername = resultSet.getString("username");
                String fetchedPassword = resultSet.getString("password");
                String fetchedUserID = resultSet.getString("userID");
                String fetchedAdmin = resultSet.getString("admin");

                // Add a row to the table model
                tableModel.addRow(new Object[]{fetchedUsername, fetchedPassword, fetchedUserID, fetchedAdmin});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserTableExample example = new UserTableExample();
            example.fetchDataFromDatabase("John", "password123", "12345", "Yes");
        });
    }
}
