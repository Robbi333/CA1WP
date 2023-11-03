package daos;

import Business.book;
import exceptions.DaoException;
import java.util.List;

public interface BookDaoInterface {
    List<Book> findAllBooks() throws DaoException
}

