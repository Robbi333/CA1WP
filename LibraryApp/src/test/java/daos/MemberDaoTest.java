import Business.members;
import daos.MembersDao;
import exceptions.DaoException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MembersDaoTest {

    private TestMembersDao testMembersDao;

    @BeforeAll
    public static void setupDatabase() throws SQLException {
        // Set up your in-memory database (e.g., H2) and create necessary tables
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testLibraryApp;DB_CLOSE_DELAY=-1");
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE members ("
                + "MemberID INT AUTO_INCREMENT PRIMARY KEY,"
                + "Username VARCHAR(255),"
                + "Password VARCHAR(255),"
                + "First_Name VARCHAR(255),"
                + "Last_Name VARCHAR(255),"
                + "Email VARCHAR(255),"
                + "Address1 VARCHAR(255),"
                + "Address2 VARCHAR(255),"
                + "Eircode VARCHAR(255),"
                + "Phone_Number VARCHAR(255),"
                + "Registration_Date DATE,"
                + "Admin BOOLEAN"
                + ")");
        connection.close();
    }

    @BeforeEach
    public void setUp() {
        // Initialize your test DAO using the test database configuration
        testMembersDao = new TestMembersDao();
    }

    @Test
    public void testInsertMember() throws DaoException {
        // Create a test member
        members member = new members("testUser", "testPassword", "John", "Doe", "john@example.com",
                "123 Main St", "Apt 4B", "12345", "555-555-5555", Date.valueOf("2023-11-02"));

        // Insert the member
        boolean result = testMembersDao.insertMember(testMembersDao.getConnection(), member);

        // Assert that the insertion was successful
        assertEquals(true, result);
    }
}
