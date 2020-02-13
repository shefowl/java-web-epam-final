package by.epam.buber.repository;

import by.epam.buber.entity.Order;
import by.epam.buber.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;

public interface OrderCrudRepository extends CrudRepository<Integer, Order> {
    List<Order> getCompletedByUserId(Integer userId) throws DaoException;
    List<Order> getOrdersByParticipantId(Integer id) throws DaoException;
    void setAccepted(Integer driverId, Integer orderId) throws DaoException;
    void clearDriverOrderListExceptAccepted(Integer orderId) throws DaoException;
    void deleteFromDriverList(Integer orderId) throws DaoException;
    void setPrice(BigDecimal price, Integer orderId) throws DaoException;
    void setCompleted(boolean completed, Integer orderId) throws DaoException;
    List<Order> getCurrentFromDriverList(Integer driveId) throws DaoException;
    boolean orderStarted(Integer orderId) throws DaoException;
    void setStartedById(Integer orderId, boolean started) throws DaoException;
    boolean driverAccepted(Integer driverId) throws DaoException;
    boolean driverRequested(Integer driverId) throws DaoException;
    Order getCurrentByDriverId(Integer driverId) throws DaoException;
    List<Order> getCurrentOrdersByDriverId(Integer driverId) throws DaoException;
    Order getCurrentByUserId(Integer userId) throws DaoException;
    List<Order> getByUserId(Integer userId) throws DaoException;
}
