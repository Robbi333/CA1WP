package daos;

import Business.book;
import exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends Dao{

    public List<book> findAllBooks() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<book> books = new ArrayList<>();

        try
        {
            con = this.getConnection();

            String query = "Select * from book";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()) {
                int bookID = rs.getInt("BookID");
                String title = rs.getString("Title");
                int authorID = rs.getInt("AuthorID");
                int ISBN = rs.getInt("ISBN");
                int publicationYear = rs.getInt("PublicationYear");
                int genreID = rs.getInt("GenreID");
                int totalCopies = rs.getInt("TotalCopies");
                String description = rs.getString("Description");

                book book = new book(bookID, title, authorID, ISBN, publicationYear, genreID, totalCopies, description);
                books.add(book);
            }
        }catch (SQLException e) {
            throw new DaoException("findAllBooks() " + e.getMessage());
        }finally {
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
        return books;
    }


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

        if(bookAlreadyBorrowed(memberID, bookid)) {
            return false;
        }
        if(!bookAlreadyBorrowed(bookid)) {
            return true;
        }

    }
    boolean returnbook(int memberID, int bookid) throws DaoException {

        if(bookAlreadyBorrowed(memberID, bookid)) {
            borrrowBook.remove(bookid);
            return true;
        } else {

            return false;
        }

    }

    private boolean bookAlreadyBorrowed(int memberID, int bookid) {
        return borrrowBook.contains(bookid);
    }


}





}
