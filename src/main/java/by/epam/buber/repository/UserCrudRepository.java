package by.epam.buber.repository;

import by.epam.buber.entity.User;

public interface UserCrudRepository extends CrudRepository<Integer, User> {
    User getByName(String name);
}
