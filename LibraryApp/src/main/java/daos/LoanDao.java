package daos;

import Business.loans;
import exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author leo, destiny.
 */
public class LoanDao extends Dao {
    private int memberID;
    private int bookid;

    /**
     * Constructs a new BookDao with the specified database name.
     *
     * @param databaseName The name of the database to be used by the BookDao.
     */
    public LoanDao(String databaseName) {
        super(databaseName);
    }



    /**
     * gets the list of all loans for member
     *
     * the method gets a list of loans from the database for the member
     * if the return date is null then it is active
     *
     * @param memberID the id of the member to get the active loans for them
     * @return a list of the active loans for the member
     * @throws DaoException if error occurs while getting the active loans or connecting to
     * the database.
     */
    public List<loans> viewActiveLoans(int memberID) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<loans> activeLoans = new ArrayList<>();

        try {
            con = getConnection();

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

    /**
     * gets list of loans for member
     *
     * the method gets a list of the loans from the database that are
     * linked to the members id
     *
     * @param memberId the id of the member
     * @return a list of loans linked to the member
     * @throws DaoException if error occurs while getting the loans or connecting to database.
     */
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

    /**
     * Making a borrow books array list
     */
    public List<Integer> borrowedBooks = new ArrayList<>();

    /**
     *
     * borrowing a certain book
     *
     * @param memberID member id is the member that is being borrrowing the book
     * @param bookid the id of the book is being borrowed
     * @return it is true if the book was successfully borrowed and false otherwise
     * @throws DaoException if there is any error during the borrowing process
     */

    public boolean BorrowBook(int memberID, int bookid) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            if (canBorrowBook(memberID, bookid)) {
                String sql = "INSERT INTO loans (MemberID, BookID) VALUES (?, ?, NOW())";
                ps = con.prepareStatement(sql);
                ps.setInt(1, memberID);
                ps.setInt(2, bookid);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    borrowedBooks.add(bookid);
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println("error borrowing from book");
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();

            } catch (SQLException e) {
                System.out.println("handiling exceptions");

            }
        }
        return false;
    }

    /**
     *
     * adds to see if you can check if the book needs to be borrowed
     *
     * @param memberID the id of the member
     * @param bookid the id of thew book to be checking if it is being borrowed
     * @return true if the book can be borrowed false otherwise
     * @throws DaoException if there is an error during the check
     */
   public boolean canBorrowBook(int memberID, int bookid) throws DaoException {
       this.memberID = memberID;
       this.bookid = bookid;

       if(bookAlreadyBorrowed(memberID, bookid)) {
            return false;
        }
       return !bookAlreadyBorrowed(memberID, bookid);
   }
    /**
     *
     * adds the returning of a book
     *
     * @param memberID The ID of the member returning the book
     * @param bookid the results of the id of the book is being returned
     * @return it is true if the book successfully returned false otherwise
     * @throws DaoException if there is an error during the returning process
     */
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

               return rows > 0;

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

    /**
     * checks if a book is overdue the date for member
     *
     * method calls to the database to see if the book with the passed Bookid
     * loaned by member is overdue based on the duedate
     *
     * @param memberID id of member who borrowed book
     * @param bookid the id of the book
     * @return boolean if book is overdue return true otherwise false
     * @throws RuntimeException if error occurs while checking book overdue
     */
   public boolean isLate(int memberID,int bookid){
       Connection con = null;
       PreparedStatement ps = null;
       ResultSet rs = null;

       try {
           con = getConnection();

           String sql = "SELECT DueDate, ReturnDate FROM loans WHERE MemberID = ? AND Bookid = ?";
           ps = con.prepareStatement(sql);
           ps.setInt(1, memberID);
           ps.setInt(2, bookid);

           rs = ps.executeQuery();
           if (rs.next()) {

               Date dueDate = rs.getDate("DueDate");
               Date returnDate = rs.getDate("ReturnDate");

               if(returnDate != null){
                   return returnDate.after(dueDate);
               }
           }
           return false;
       } catch (SQLException e) {
           throw new RuntimeException("Error checking if book is late: " + e.getMessage());
       }
   }

    /**
     *adds a late fee to specific loan in database
     *
     * this method updates the late fee for specific loan thats
     * linked to the member and book
     *
     * @param memberID id of the member linked to the loan
     * @param bookid id of the book linked with the loan
     * @throws RuntimeException if an error occurs when adding late fee or database connection
     */
    public void addLateFeeToLoan(int memberID, int bookid) {
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
                System.out.println("Problem occured when adding fee");
            }
        } catch (SQLException e) {
            // Handle SQL-related exceptions here, log them, or throw an exception
            throw new RuntimeException("Error adding late fee to loan: " + e.getMessage(), e);
        }
    }


    /**
     *
     * to check if a book with the bookid that is given is being borrowed by a member already
     *
     * @param memberID the id of the member is to check for the book borrorwed
     * @param bookid the id of the book to be checked for the borrowing progress
     * @return true if the book has already been borrowed by the member false otherwise
     */
    public boolean bookAlreadyBorrowed(int memberID,int bookid) {

        boolean check = false;
        if (borrowedBooks.contains(bookid)) {
            check = true;
            return check;
        } else {
            return check;
        }
    }

    /**
     * validates the credit card number using Luhn algorithm
     *
     * this checks credit length and removes any spaces or anything user might put in
     *
     *
     * @param cardNumber the credit card number to validate
     * @return if credit card number is valid return true otherwise return false
     */
    public  boolean isCreditCardValid(String cardNumber) {

        cardNumber = cardNumber.replaceAll("[^0-9]", "");

        int length = cardNumber.length();
        if (length < 13 || length > 19) {
            return false;
        }
        int sum = 0;
        boolean doubleDigit = false;

        //Luhn algorithm
        for (int i = length - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }

        return (sum % 10 == 0);
    }

    /**
     * validates the format and checks if date is correct
     *
     * this method checks the format of the date, and it
     * expires in the future cant be less than the current date
     *
     * @param expiryDate
     * @return
     */
    public boolean isValidExpiryDate(String expiryDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
            Date currentDate = new Date();
            Date parsedExpiryDate = dateFormat.parse(expiryDate);

            return currentDate.before(parsedExpiryDate);
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * checks and processes the payment for a late fee for a member
     *
     * this method validates credit card info calling the 2 other methods to check.
     * once valid then processes payment for late fee. if both are true proceed onwards
     * otherwise return false.
     *
     *
     * @param memberId id of the member making payment for late fee
     * @param cardNumber the members credit card number
     * @param expiryDate the expiry date of the credit card
     * @return true if the payment was successful and credit card info is correct
     * @throws RuntimeException if error occurs with payment or database connection
     */
    public boolean payLateFeeValidate(int memberId,String cardNumber,String expiryDate) {

        if (!isCreditCardValid(cardNumber)) {
            return false;
        }
        if(!isValidExpiryDate(expiryDate)){
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

            return rows>0;

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





