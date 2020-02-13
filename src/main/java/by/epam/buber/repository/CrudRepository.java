package by.epam.buber.repository;

import by.epam.buber.exception.DaoException;

import java.util.List;

public interface CrudRepository<T,E> {
    List<E> getAll() throws DaoException;
    E getById(T id) throws DaoException;
    void save(T id, E e) throws DaoException;
    void save(E e) throws DaoException;
    void delete(T id) throws DaoException;
}
