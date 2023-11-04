package daos;

import Business.book;
import Business.loans;
import exceptions.DaoException;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoanDaoTest {

    /**
     * test to see if it gets the active loans for a member
     * @throws SQLException
     */
    @Test
    void viewActiveLoans() throws SQLException {
        int memberID = 1;

        loans loan1 = new loans(1, 1, 1, Date.valueOf("2023-11-02"), Date.valueOf("2023-11-16"), null, 0.0);
        loans loan2 = new loans(2, 1, 2, Date.valueOf("2023-11-03"), Date.valueOf("2023-11-17"), null, 0.0);
        List<loans> expectedResults = new ArrayList<>();
        expectedResults.add(loan1);
        expectedResults.add(loan2);

        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(dbConn.prepareStatement("SELECT * from loans")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true,false);

        when(rs.getInt("LoanID")).thenReturn(1, 2);
        when(rs.getInt("MemberID")).thenReturn(1, 1);
        when(rs.getInt("BookID")).thenReturn(1, 2);
        when(rs.getDate("LoanDate")).thenReturn(Date.valueOf("2023-11-02"), Date.valueOf("2023-11-03"));
        when(rs.getDate("DueDate")).thenReturn(Date.valueOf("2023-11-16"), Date.valueOf("2023-11-17"));
        when(rs.getDate("ReturnDate")).thenReturn(null, null);
        when(rs.getDouble("LateFee")).thenReturn(0.0, 0.0);

        LoanDao loanDao = new LoanDao("librarytest");
        List<loans> result = loanDao.viewActiveLoans(memberID);

        System.out.println("Expected Results:");
        System.out.println(expectedResults);
        System.out.println("Actual Results:");
        System.out.println(result);

        assertArrayEquals(expectedResults.toArray(),result.toArray());


    }

    /**
     * Checks to see if correctly see's if a memeber has no active loans
     * @throws SQLException
     */
    @Test
    void viewActiveLoansEmpty() throws SQLException {
        int memberID = 5;

        List<loans> expectedResults = new ArrayList<>();

        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(dbConn.prepareStatement("SELECT * from loans")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(false);

        LoanDao loanDao = new LoanDao("librarytest");
        List<loans> result = loanDao.viewActiveLoans(memberID);

        System.out.println("Expected Results (Empty):");
        System.out.println(expectedResults);
        System.out.println("Actual Results:");
        System.out.println(result);

        assertTrue(result.isEmpty());


    }

    /**
     * this is essentially the same as active loans but instead just gets all loans,
     * the active checks
     */
    @Test
    void getLoansForMember() throws SQLException {
        int memberID = 1;

        loans loan1 = new loans(1, 1, 1, Date.valueOf("2023-11-02"), Date.valueOf("2023-11-16"), null, 0.0);
        loans loan2 = new loans(2, 1, 2, Date.valueOf("2023-11-03"), Date.valueOf("2023-11-17"), null, 0.0);
        List<loans> expectedResults = new ArrayList<>();
        expectedResults.add(loan1);
        expectedResults.add(loan2);

        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(dbConn.prepareStatement("SELECT * from loans")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true,false);

        when(rs.getInt("LoanID")).thenReturn(1, 2);
        when(rs.getInt("MemberID")).thenReturn(1, 1);
        when(rs.getInt("BookID")).thenReturn(1, 2);
        when(rs.getDate("LoanDate")).thenReturn(Date.valueOf("2023-11-02"), Date.valueOf("2023-11-03"));
        when(rs.getDate("DueDate")).thenReturn(Date.valueOf("2023-11-16"), Date.valueOf("2023-11-17"));
        when(rs.getDate("ReturnDate")).thenReturn(null, null);
        when(rs.getDouble("LateFee")).thenReturn(0.0, 0.0);

        LoanDao loanDao = new LoanDao("librarytest");
        List<loans> result = loanDao.viewActiveLoans(memberID);

        System.out.println("Expected Results:");
        System.out.println(expectedResults);
        System.out.println("Actual Results:");
        System.out.println(result);

        assertArrayEquals(expectedResults.toArray(),result.toArray());
    }

    
    //my database is the same as the infromation i dont know my error but i attempted it :(
    @Test
    /**
     * Test case to verify the success of borrowing a book
     */
    void BorrowBook() throws DaoException {

        LoanDao library = new LoanDao("librarytest");
        int memberID = 1;
        int bookid = 2;

        boolean result = library.BorrowBook(memberID, bookid);

        boolean expectedResult = true;


        assertEquals(expectedResult, result);
    }

    /**
     * test case to verify its failure of borrrowing a book
     * @throws DaoException if there is an issue with the data access
     */
    @Test
    void testBorrowBook_Failed() throws DaoException {
        LoanDao library = new LoanDao("librarytest");

        int memberID = 2;
        int bookid = 102;

        boolean result = library.BorrowBook(memberID, bookid) ;

        boolean expectedResult = false;

        assertEquals(expectedResult, result);

    }

    /**
     * test the case to check if the book can be borrowed
     * @throws DaoException if there is an issue with the data access
     */
    @Test
    void canBorrowBook() throws DaoException {

        LoanDao library = new LoanDao("librarytest");

        int memberID = 1;
        int bookid = 101;



        boolean result = library.canBorrowBook(memberID, bookid);

        boolean expectedResult = true;

        assertEquals(expectedResult, result);

    }

    /**
     * test to see if the book is already borrowed
     * @throws DaoException if there is an issue with the data access
     */
    @Test
    void BookalreadyBorrowed() throws DaoException {
        LoanDao library = new LoanDao("librarytest");
        int memberID = 5;
        int bookid = 5;


        boolean result = library.canBorrowBook(memberID, bookid);

        boolean expectedResult = true;

        assertEquals(expectedResult, result);

    }

    /**
     * to test if you can return a book
     * @throws DaoException if there is an issue with the data access
     */
    @Test
    void returnbook() throws DaoException {
        LoanDao library = new LoanDao("librarytest");

        int memberID = 1;
        int bookid = 101;

        boolean result;
        result = library.returnbook(memberID, bookid);

    }

    @Test
    void isLate() {
    }

    @Test
    void isCreditCardValid() {
    }

    @Test
    void payLateFeeValidate() {
    }

    @Test
    void addLateFeeToLoan() {
    }

    /**
     * To test the case to check if a book is already borrowed by a member
     */
    @Test
    void bookAlreadyBorrowed() {
        LoanDao library = new LoanDao("librarytest");

        int member = 2;
        int bookid = 5;

        boolean result = library.bookAlreadyBorrowed(member,bookid);

        boolean expectedResult = true;
    }

    /**
     * To test the case to check if a book is not borrowed by a member
     */
    @Test
    void bookAlreadyBorrowed_notBorrowed() {
        LoanDao library = new LoanDao("librarytest");

        int member = 2;
        int bookid = 5;


        boolean result = library.bookAlreadyBorrowed(member,bookid);

        boolean expectedResult = false;
    }

    @Test
    void isValidExpiryDate() {
    }
}