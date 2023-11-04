package daos;

import exceptions.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author leo.
 */
public class Dao {

    public String databaseName;

    /**
     * creates a new Dao instance with the passed database name.
     *
     * @param databaseName the name of the database linked with this Dao instance.
     */
    public Dao(String databaseName){
        this.databaseName = databaseName;
    }


    /**
     * creates a database connection and returns the connection object.
     *
     * @return a connection object which represents the database connection.
     * @throws DaoException if there is an error in creating the database connection.
     */
    public static Connection getConnection() throws DaoException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/libraryapp";
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
            System.exit(2);
        }
        return con;
    }

    /**
     * frees the database connection by closing the passed connection object.
     *
     * @param con connection object to be closed and freed.
     * @throws DaoException if there is an error in closing the database connection.
     */
    public static void freeConnection(Connection con) throws DaoException {
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
