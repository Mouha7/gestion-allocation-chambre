package ucad.glrs.core;

import java.util.List;

public interface Service<T> {
    boolean save(T objet);
    List<T> show();
    int count();
    T getBy(int value);
    T getBy(String value);
}
