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

    public static members authenticateMember(Connection con, String username, String password) throws DaoException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM members WHERE Username = ? AND Password = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                // User found, create a members object with the details
                members authenticatedMember = new members();
                authenticatedMember.setMemberID(rs.getInt("MemberID"));
                authenticatedMember.setUsername(rs.getString("Username"));
                authenticatedMember.setPassword(rs.getString("Password"));
                authenticatedMember.setFirst_Name(rs.getString("First_Name"));
                authenticatedMember.setLast_Name(rs.getString("Last_Name"));
                authenticatedMember.setEmail(rs.getString("Email"));
                authenticatedMember.setAddress1(rs.getString("Address1"));
                authenticatedMember.setAddress2(rs.getString("Address2"));
                authenticatedMember.setEircode(rs.getString("Eircode"));
                authenticatedMember.setPhone_Number(rs.getString("Phone_Number"));
                authenticatedMember.setRegistration_Date(rs.getDate("Registration_Date").toLocalDate());
                authenticatedMember.setAdmin(rs.getInt("Admin"));

                return authenticatedMember;
            }
        } catch (SQLException e) {
            throw new DaoException("Error authenticating member: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
        return null; // User not found
    }

}