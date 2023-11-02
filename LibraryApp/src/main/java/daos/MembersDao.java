package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MembersDao extends Dao {

    private final String libraryapp;

    public MembersDao(String libraryapp){

        this.libraryapp = libraryapp;
    };

    public Connection getConnection() throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/" + libraryapp;
        String username = "root";
        String password = "";

        try {
            Class.forName(driver); // You might not need this if you're using a recent JDBC driver.
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex1) {
            throw new SQLException("Failed to find driver class: " + ex1.getMessage());
        } catch (SQLException ex2) {
            throw new SQLException("Connection failed: " + ex2.getMessage());
        }
    }}


