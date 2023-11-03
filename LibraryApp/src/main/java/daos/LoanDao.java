package daos;

import Business.loans;
import exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanDao extends Dao {
    public List<loans> viewActiveLoans(int memberID) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<loans> activeLoans = new ArrayList<>();

        try {
            con = this.getConnection();

            String query = "SELECT * FROM loans WHERE MemberID = ? AND ReturnDate IS NULL AND DueDate > CURDATE()";
            ps = con.prepareStatement(query);
            ps.setInt(1, memberID);

            rs = ps.executeQuery();

            while (rs.next()) {
                int loanID = rs.getInt("LoanID");
                int bookID = rs.getInt("BookID");
                Date loanDate = rs.getDate("LoanDate");
                Date dueDate = rs.getDate("DueDate");
                double lateFee = rs.getDouble("LateFee");

                loans loan = new loans(loanID, memberID, bookID, loanDate, dueDate, null, lateFee);
                activeLoans.add(loan);
            }
        } catch (SQLException e) {
            throw new DaoException("viewActiveLoans() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }

        return activeLoans;
    }

    public List<loans> getLoansForMember(int memberId) throws DaoException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        con = getConnection();
        List<loans> loans = new ArrayList<>();


        String sql = "SELECT LoanID, MemberID, BookID, LoanDate, DueDate, ReturnDate, LateFee " +
                "FROM loans WHERE MemberID = ? AND ReturnDate IS NULL";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int loanId = resultSet.getInt("LoanID");
                    int bookId = resultSet.getInt("BookID");
                    Date loanDate = resultSet.getDate("LoanDate");
                    Date dueDate = resultSet.getDate("DueDate");
                    Date returnDate = resultSet.getDate("ReturnDate");
                    double lateFee = resultSet.getDouble("LateFee");

                    // Create a Loan object with the retrieved details
                    loans loan = new loans(loanId, memberId, bookId, loanDate, dueDate, returnDate, lateFee);
                    loans.add(loan);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving loans: " + e.getMessage());
        }

        return loans;
    }

    }

