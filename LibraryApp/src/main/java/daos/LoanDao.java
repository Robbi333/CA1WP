package daos;

import Business.loans;
import exceptions.DaoException;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanDao extends Dao {
    private int memberID;
    private int bookid;

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
                "FROM loans WHERE MemberID = ?" ;
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


                    loans loan = new loans(loanId, memberId, bookId, loanDate, dueDate, returnDate, lateFee);
                    loans.add(loan);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving loans: " + e.getMessage());
        }

        return loans;
    }

    private List<Integer> borrowedBooks = new ArrayList<>();
    public boolean BorrowBook(int memberID, int bookid) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            if (canBorrowBook(memberID, bookid)) {
                String sql = "INSERT INTO loans (MemberID, Bookid) VALUES (?, ?, NOW())";
                ps = con.prepareStatement(sql);
                ps.setInt(1, memberID);
                ps.setInt(2, bookid);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    return true;
                } else if (!bookAlreadyBorrowed(memberID, bookid)) {

                    borrowedBooks.add(bookid);
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new DaoException("There is an Error Borrowing a Book:" + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();

            } catch (SQLException e) {

            }
        }
    }


   public boolean canBorrowBook(int memberID, int bookid) throws DaoException {
       this.memberID = memberID;
       this.bookid = bookid;

       if(bookAlreadyBorrowed(memberID, bookid)) {
            return false;
        }
        if(!bookAlreadyBorrowed(memberID, bookid)) {
            return true;
        }
        return false;
    }
   public boolean returnbook(int memberID, int bookid) throws DaoException {
       Connection con = null;
       PreparedStatement ps = null;

       try {
           con = getConnection();

           if (bookAlreadyBorrowed(memberID, bookid)) {
               borrowedBooks.remove(bookid);

               if (isLate(memberID, bookid)) {
                   addLateFeeToLoan(memberID, bookid);
               }

               String sql = "DELETE FROM BorrowedBooks Where MemberID = ? AND bookid = ?";

               ps = con.prepareStatement(sql);
               ps.setInt(1, memberID);
               ps.setInt(2,bookid);

               int rows = ps.executeUpdate();

               if (rows > 0) {
                   return true;
               }

           }
           return false;
       } catch (SQLException e) {
           throw new DaoException("Error Retuning a Book:" + e.getMessage());
       } finally {
           try {
               if (ps != null) {
                   ps.close();
               }
               try {
                   if (con != null) {
                       con.close();
                   }
               } catch(SQLException e){
                   throw new RuntimeException(e);

               }

           } catch (SQLException e) {
               throw new RuntimeException(e);

           }
       }
   }

   public boolean isLate(int memberID,int bookid){
       Connection con = null;
       PreparedStatement ps = null;
       ResultSet rs = null;

       try {
           con = getConnection();

           String sql = "SELECT DueDate FROM loans WHERE MemberID = ? AND Bookid = ?";
           ps = con.prepareStatement(sql);
           ps.setInt(1, memberID);
           ps.setInt(2, bookid);

           rs = ps.executeQuery();
           if (rs.next()) {

               Date dueDate = rs.getDate("DueDate");

               Date currentDate = new Date();

               if (currentDate.after(dueDate)) {
                   return true;
               }
           }
           return false;
       } catch (SQLException e) {
           throw new RuntimeException("Error checking if book is late: " + e.getMessage());
       }
   }

    private void addLateFeeToLoan(int memberID, int bookid) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            String sql = "UPDATE loans SET LateFee = 10 WHERE MemberID = ? AND Bookid = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, memberID);
            ps.setInt(2, bookid);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                //fee added
            } else {
                //problem adding fee
            }
        } catch (SQLException e) {
            // Handle SQL-related exceptions here, log them, or throw an exception
            throw new RuntimeException("Error adding late fee to loan: " + e.getMessage(), e);
        }
    }




    private boolean bookAlreadyBorrowed(int memberID,int bookid) {


        return borrowedBooks.contains(bookid);
    }

    public static boolean isCreditCardValid(String cardNumber, String expiryDate) {

        cardNumber = cardNumber.replaceAll("[^0-9]", "");

        int length = cardNumber.length();
        if (length < 13 || length > 19) {
            return false;
        }

        if (!isValidExpiryDate(expiryDate)) {
            return false;
        }
        //just checking for lenght and valid date time
        return true;
    }

    private static boolean isValidExpiryDate(String expiryDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
            Date currentDate = new Date();
            Date parsedExpiryDate = dateFormat.parse(expiryDate);

            return currentDate.before(parsedExpiryDate);
        } catch (ParseException e) {
            return false;
        }
    }


    public boolean payLateFeeValidate(int memberId, double lateFee,String cardNumber,String expiryDate) {

        if (!isCreditCardValid(cardNumber, expiryDate)) {
            return false;
        }
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String sql = "UPDATE loans Set LateFee = 0.00 where memberId =?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, memberId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                return true;
            } else {
                return false;
            }

        } catch (DaoException e) {
            throw new RuntimeException("Error paying late fee: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error occurred");
            }


        }
    }

    }





