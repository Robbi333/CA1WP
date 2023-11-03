package daos;

import Business.book;
import exceptions.DaoException;
import java.util.List;

public interface BookDaoInterface {



    boolean borrrowBook(int MemberID, int bookid) throws DaoException;

    boolean returnBook(int MemberID, int bookid) throws DaoExcecption;

    boolean canBorrowBooks(int MemberID, int bookid) throws DaoException;

    public List<book> findAllBooks() throws DaoException;



}
