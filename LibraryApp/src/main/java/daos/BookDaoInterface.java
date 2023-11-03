package daos;

import Business.book;
import exceptions.DaoException;
import java.util.List;

public interface BookDaoInterface {



   public boolean borrrowBook(int MemberID, int bookid) throws DaoException {
       Connection con = null;
       PreparedStatement ps = null;

       try {
           connection =

           if (canBorrowBook(memberID, bookid)) {
               String sql = "INSERT INTO BorrowedBooks (MemberID, Bookid, BorrowDate) VALUES (?, ?, NOW())";
               ps = con.prepareStatement(sql);
               ps.setInt(1, memberID);
               ps.setInt(2, bookid);

               int rows = ps.executeUpdate();

               if (rows > 0) {
                   return true;
               }
           }
           return false;
       } catch (SQLException e) {
           throw new DaoException("There is an Error Borrowing a Book:" + e.getMessage(), e);
       } finally {
           try {
               if(ps != null) ps.close();
               if (con != null) con.close();

           } catch (SQLException e) {

           }
       }
       }
       boolean canBorrowBook(int memberID, int bookid) throws DaoException {

       }
       boolean returnbook(int memberID, int bookid) throws DaoException {

       }
       List<Book> findAllBooks() throws DaoException

       }


