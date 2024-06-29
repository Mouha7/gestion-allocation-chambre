package ucad.glrs.core;

import java.util.List;

public interface Repository<T> {
    boolean insert(T object);
    List<T> selectAll();
    boolean update(T object);
    T selectBy(String value);
    T selectBy(int value);
    int count();
}
