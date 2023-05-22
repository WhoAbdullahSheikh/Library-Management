package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserTable extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private Connection connection;

    public UserTable() {
        initComponents();
        connectToDatabase();
        populateTable();
    }

    private void initComponents() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Username");
        tableModel.addColumn("Password");
        tableModel.addColumn("Admin");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("User Table Example");
        setSize(500, 400);
        setLocationRelativeTo(null);
    }

    private void connectToDatabase() {
        String URL = "jdbc:mysql://localhost:3306/library"; // Replace with your database URL
        String USERNAME = "root"; // Replace with your database username
        String PASSWORD = ""; // Replace with your database password

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateTable() {
        String query = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String userId = resultSet.getString("userId");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String adminChoice = resultSet.getString("admin");

                tableModel.addRow(new Object[]{userId, username, password, adminChoice});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserTable example = new UserTable();
        example.setVisible(true);
    }
}
