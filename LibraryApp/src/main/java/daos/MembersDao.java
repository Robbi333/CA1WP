package daos;


import Business.members;
import exceptions.DaoException;
import java.sql.*;

/**
 * @author roberts.
 */
public class MembersDao extends Dao {


    public MembersDao(String databaseName) {
        super(databaseName);
    }

    /**
     * Inserts a new member into the database with the provided information.
     *
     * @param con    A valid database connection.
     * @param member The member object containing user information to be inserted.
     * @return True if the insertion was successful, false otherwise.
     * @throws DaoException If an error occurs during the database operation.
     */
    public static boolean insertMember(Connection con, members member) throws DaoException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String insertQuery = "INSERT INTO members (Username, Password, First_Name, Last_Name, Email, Address1, Address2, Eircode, Phone_Number, Registration_Date, Admin) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = con.prepareStatement(insertQuery);
            ps.setString(1, member.getUsername());
            ps.setString(2, member.getPassword());
            ps.setString(3, member.getFirst_Name());
            ps.setString(4, member.getLast_Name());
            ps.setString(5, member.getEmail());
            ps.setString(6, member.getAddress1());
            ps.setString(7, member.getAddress2());
            ps.setString(8, member.getEircode());
            ps.setString(9, member.getPhone_Number());
            ps.setDate(10, Date.valueOf(member.getRegistration_Date()));
            ps.setBoolean(11, member.isAdmin());

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DaoException("Error inserting member: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                freeConnection(con);
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
    }
}