package database_controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_Manager {
    private Connection connection;

    public Database_Manager() {
        try {
            // Register the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            // Connect to the database. If the database file doesn't exist, it will be created.
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Connection to SQLite has been established.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        Database_Manager db1 = new Database_Manager();
    }
}
