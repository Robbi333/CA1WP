package exceptions;

import java.sql.SQLException;

/**
 * @author leo.
 */
public class DaoException extends SQLException{

    public DaoException() {
    }

    public DaoException(String aMessage) {
        super(aMessage);
    }
}
