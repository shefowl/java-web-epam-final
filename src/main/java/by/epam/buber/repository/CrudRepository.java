package by.epam.buber.repository;

import java.util.List;

public interface CrudRepository<T,E> {
    List<E> getAll();
    E getById(int id);
    void save(T id, E e);
    void save(E e);
    void delete(T id);
}
