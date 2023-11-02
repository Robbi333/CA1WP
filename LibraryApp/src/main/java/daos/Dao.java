package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

    private final String libraryapp;

    public Dao(String libraryapp){
        this.libraryapp = libraryapp;
    }


    public Connection getConnection(){
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/" + libraryapp;
        String username = "root";
        String password = "";
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex1) {
            System.out.println("Failed to find driver class " + ex1.getMessage());
            System.exit(1);
        } catch (SQLException ex2) {
            System.out.println("Connection failed " + ex2.getMessage());
        }
        return con;
    }

    public void freeConnection(Connection con){
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to free connection: " + e.getMessage());
            System.exit(1);
        }
    }
}
