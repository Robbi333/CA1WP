package daos;

import exceptions.DaoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanDaoTest {

    @Test
    void viewActiveLoans() {
    }

    @Test
    void getLoansForMember() {
    }

    //my database is the same as the infromation i dont know my error but i attempted it :(
    @Test
    void BorrowBook() throws DaoException {

        LoanDao library = new LoanDao("librarytest");
        int memberID = 1;
        int bookid = 2;

        boolean result = library.BorrowBook(memberID, bookid);

        boolean expectedResult = true;

        assertEquals(expectedResult, result);
    }
    //my database is the same as the infromation i dont know my error but i attempted it :(
    @Test
    void testBorrowBook_Failed() throws DaoException {
        LoanDao library = new LoanDao("librarytest");

        int memberID = 2;
        int bookid = 102;

        boolean result = library.BorrowBook(memberID, bookid) ;

        boolean expectedResult = false;

        assertEquals(expectedResult, result);

    }


    @Test
    void canBorrowBook() throws DaoException {

        LoanDao library = new LoanDao("librarytest");

        int memberID = 1;
        int bookid = 101;



        boolean result = library.canBorrowBook(memberID, bookid);

        boolean expectedResult = true;

        assertEquals(expectedResult, result);

    }
    @Test
    void BookalreadyBorrowed() throws DaoException {
        LoanDao library = new LoanDao("librarytest");
        int memberID = 5;
        int bookid = 5;


        boolean result = library.canBorrowBook(memberID, bookid);

        boolean expectedResult = true;

        assertEquals(expectedResult, result);

    }

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

    @Test
    void bookAlreadyBorrowed() {
        LoanDao library = new LoanDao("librarytest");

        int member = 2;
        int bookid = 5;

        boolean result = library.bookAlreadyBorrowed(member,bookid);

        boolean expectedResult = true;
    }
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