package daos;

import Business.members;
import exceptions.DaoException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

 class MembersDaoTest {

    @Test
    public void testInsertMember() throws SQLException {
        members testMember = new members("testUser", "testPassword", "John", "Doe", "john@example.com",
                "123 Main St", "Apt 4B", "12345", "555-555-5555", Date.valueOf("2023-11-02").toLocalDate());

        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);


        when(dbConn.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        MembersDao membersDao = new MembersDao("testLibraryApp");
        boolean result = MembersDao.insertMember(dbConn, testMember);

        assertTrue(result);
    }

    }

