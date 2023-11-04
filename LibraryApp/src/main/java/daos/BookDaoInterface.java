package daos;

import Business.book;
import exceptions.DaoException;
import java.util.List;

/**
 * @author leo.
 */
public interface BookDaoInterface {
    List<book> findAllBooks() throws DaoException;
}

