package daos;

import Business.book;
import exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends Dao {

    public List<book> findAllBooks() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<book> books = new ArrayList<>();

        try {
            con = this.getConnection();

            String query = "Select * from book";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
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
        } catch (SQLException e) {
            throw new DaoException("findAllBooks() " + e.getMessage());
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
        return books;
    }
}




