import Business.members;
import daos.MembersDao;
import exceptions.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class MembersDaoTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @BeforeEach
    void setUp() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    @Test
    public void testInsertMember() throws SQLException, DaoException {
        members member = new members("testUser", "testPassword", "John", "Doe", "john@example.com",
                "123 Main St", "Apt 4B", "12345", "555-555-5555", Date.valueOf("2023-11-02"));

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean result = MembersDao.insertMember(mockConnection, member);

        verify(mockConnection).prepareStatement(anyString());
        verify(mockPreparedStatement).setString(1, "testUser");
        verify(mockPreparedStatement).setString(2, "testPassword");
        verify(mockPreparedStatement).setString(3, "John");
        verify(mockPreparedStatement).setString(4, "Doe");
        verify(mockPreparedStatement).setString(5, "john@example.com");
        verify(mockPreparedStatement).setString(6, "123 Main St");
        verify(mockPreparedStatement).setString(7, "Apt 4B");
        verify(mockPreparedStatement).setString(8, "12345");
        verify(mockPreparedStatement).setString(9, "555-555-5555");
        verify(mockPreparedStatement).setDate(10, Date.valueOf("2023-11-02"));
        verify(mockPreparedStatement).setBoolean(11, false); // Assuming default is not an admin

        assertEquals(true, result);
    }
}
