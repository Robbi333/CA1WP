package daos;

import Business.book;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookDaoTest {


    /**
     * test of findallbooks method, og class bookdao
     *
     * @throws SQLException
     */
    @Test
    public void testFindAllBooks() throws SQLException {

        book book1 = new book(1, "Harry Potter and the Philosopher's Stone", 1, 2147483647, 1997, 1, 1200000, "An 11-year-old orphan living with his unwelcoming aunt, uncle, and cousin, who learns of his own fame as a wizard known to have survived his parents' murder at the hands of the dark wizard Lord Voldemort as an infant when he is accepted to Hogwarts School");
        book book2 = new book(2, "Harry Potter and the Chamber of Secrets", 1, 123589026, 1998, 2, 770000, "Throughout the summer holidays after his first year at Hogwarts School of Witchcraft and Wizardry, Harry Potter has been receiving sinister warnings from a house-elf called Dobby. Now, back at school to start his second year, Harry hears unintelligible wh");
        book book3 = new book(3, "Harry Potter and the Prisoner of Azkaban", 1, 830827838, 1999, 3, 650000, "Harry Potter & The Prisoner of Azkaban is about Harry's 3rd year at Hogwarts. Along with friends Ron and Hermione, Harry investigates the case of Sirius Black, an escaped prisoner from Azkaban, the wizard prison.");
        book book4 = new book(4, "Harry Potter and the Goblet of Fire", 1, 387086705, 2000, 4, 40000, "It follows Harry Potter, a wizard in his fourth year at Hogwarts School of Witchcraft and Wizardry, and the mystery surrounding the entry of Harry's name into the Triwizard Tournament, in which he is forced to compete. The book was published in the United");
        book book5 = new book(5, "Harry Potter and the Order of the Phoenix", 1, 691461178, 2003, 5, 660000, "Dark times have come to Hogwarts. After the Dementors' attack on his cousin Dudley, Harry Potter knows that Voldemort will stop at nothing to find him. There are many who deny the Dark Lord's return, but Harry is not alone: a secret order gathers at Grimm");
        book book6 = new book(6, "Charlie and the Chocolate Factory", 6, 927491739, 1964, 6, 2600000, "Charlie And The Chocolate Factory is a delightful children's book by Roald Dahl. It tells the story of a young boy named Charlie Bucket, who wins a golden ticket and gets the opportunity to tour the mysterious and magical chocolate factory owned by the ec");
        ArrayList<book> expectedResults = new ArrayList<>();
        expectedResults.add(book1);
        expectedResults.add(book2);
        expectedResults.add(book3);
        expectedResults.add(book4);
        expectedResults.add(book5);
        expectedResults.add(book6);

        //create mock objects
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        //fill mock objects with dummy
        when(dbConn.prepareStatement("SELECT * from book")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        //want 6 results in result set, so 6 true
        when(rs.next()).thenReturn(true,true,true,true,true,true,false);

        //fill result set
        when(rs.getInt("Bookid")).thenReturn(book1.getBookid(), book2.getBookid(), book3.getBookid(), book4.getBookid(), book5.getBookid(), book6.getBookid());
        when(rs.getString("Title")).thenReturn(book1.getTitle(), book2.getTitle(), book3.getTitle(), book4.getTitle(), book5.getTitle(), book6.getTitle());
        when(rs.getInt("AuthorID")).thenReturn(book1.getAuthorID(), book2.getAuthorID(), book3.getAuthorID(), book4.getAuthorID(), book5.getAuthorID(), book6.getAuthorID());
        when(rs.getInt("ISBN")).thenReturn(book1.getISBN(), book2.getISBN(), book3.getISBN(), book4.getISBN(), book5.getISBN(), book6.getISBN());
        when(rs.getInt("PublicationYear")).thenReturn(book1.getPublicationYear(), book2.getPublicationYear(), book3.getPublicationYear(), book4.getPublicationYear(), book5.getPublicationYear(), book6.getPublicationYear());
        when(rs.getInt("GenreID")).thenReturn(book1.getGenreID(), book2.getGenreID(), book3.getGenreID(), book4.getGenreID(), book5.getGenreID(), book6.getGenreID());
        when(rs.getInt("TotalCopies")).thenReturn(book1.getTotalCopies(), book2.getTotalCopies(), book3.getTotalCopies(), book4.getTotalCopies(), book5.getTotalCopies(), book6.getTotalCopies());
        when(rs.getString("Description")).thenReturn(book1.getDescription(), book2.getDescription(), book3.getDescription(), book4.getDescription(), book5.getDescription(), book6.getDescription());

        BookDao bookDao = new BookDao("librarytest");
        List<book> result = bookDao.findAllBooks();

        System.out.println("Expected Results:");
        System.out.println(expectedResults);
        System.out.println("Actual Results:");
        System.out.println(result);

        //checks if created array list is expected
        assertArrayEquals(expectedResults.toArray(),result.toArray());
        System.out.println(result);

    }
}