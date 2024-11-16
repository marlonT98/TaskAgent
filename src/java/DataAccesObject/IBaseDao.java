package DataAccesObject;

import java.util.List;

public interface IBaseDao<T> {

    List<T> readAll();

    boolean delete(String id);

    boolean update(T item);

    boolean insert(T item);

    T readById(String c);
}
