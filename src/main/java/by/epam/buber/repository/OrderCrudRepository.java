package by.epam.buber.repository;

import by.epam.buber.entity.Order;

import java.util.List;

public interface OrderCrudRepository extends CrudRepository<Integer, Order> {
    List<Order> getOrdersByParticipantId(int id);
}
