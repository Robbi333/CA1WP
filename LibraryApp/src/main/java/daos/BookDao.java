package daos;

import Business.book;
import exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author roberts, leo.
 */
public class BookDao extends Dao {

    //leo
    /**
     * Constructs a new BookDao with the specified database name.
     *
     * @param databaseName The name of the database to be used by the BookDao.
     */
    public BookDao(String databaseName) {
        super(databaseName);
    }

    /**
     * Retrieves a list of all books from the database
     *
     * @return A list of book objects containing info about all the books
     * @throws DaoException if an error occurs in the database operation
     */
    //leo
    public List<book> findAllBooks() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<book> books = new ArrayList<>();

        try {
            con = getConnection();

            String query = "Select * from book";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int bookID = rs.getInt("Bookid");
                String title = rs.getString("Title").trim();
                int authorID = rs.getInt("AuthorID");
                int ISBN = rs.getInt("ISBN");
                int publicationYear = rs.getInt("PublicationYear");
                int genreID = rs.getInt("GenreID");
                int totalCopies = rs.getInt("TotalCopies");
                String description = rs.getString("Description").trim();

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
    //roberts
    /**
     * Inserts a new book into the library's database.
     *
     * @param book The book object to be inserted.
     * @return True if the insertion was successful, false otherwise.
     * @throws DaoException If an error occurs during the database operation.
     */
    public boolean insertBook(book book) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String insertQuery = "INSERT INTO book (Title, AuthorID, ISBN, PublicationYear, GenreID, TotalCopies, Description) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            ps = con.prepareStatement(insertQuery);
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getAuthorID());
            ps.setInt(3, book.getISBN());
            ps.setInt(4, book.getPublicationYear());
            ps.setInt(5, book.getGenreID());
            ps.setInt(6, book.getTotalCopies());
            ps.setString(7, book.getDescription());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DaoException("Error inserting book: " + e.getMessage());
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
    }
    //roberts
    /**
     * Updates the total number of copies for a book with the specified BookID.
     *
     * @param bookID       The unique identifier of the book to update.
     * @param changeAmount The amount to change the total copies by. Positive values add copies, negative values remove copies.
     * @return True if the update was successful, false otherwise.
     * @throws DaoException If an error occurs during the database operation.
     */
    public boolean updateBookCopies(int bookID, int changeAmount) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = this.getConnection();
            String updateQuery = "UPDATE book SET TotalCopies = TotalCopies + ? WHERE BookID = ?";
            ps = con.prepareStatement(updateQuery);
            ps.setInt(1, changeAmount);
            ps.setInt(2, bookID);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DaoException("Error updating book copies: " + e.getMessage());
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
    }
}



