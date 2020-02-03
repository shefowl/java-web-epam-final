package by.epam.buber.repository;

import by.epam.buber.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderCrudRepository extends CrudRepository<Integer, Order> {
    List<Order> getOrdersByParticipantId(Integer id);
    void setAccepted(Integer driverId, Integer orderId);
    void clearDriverOrderListExceptAccepted(Integer orderId);
    void deleteFromDriverList(Integer orderId);
    void setPrice(BigDecimal price, Integer orderId);
    void setCompleted(boolean completed, Integer orderId);
    List<Order> getCurrentFromDriverList(Integer driveId);
    boolean orderStarted(Integer orderId);
    void setStartedById(Integer orderId, boolean started);
    boolean driverAccepted(Integer driverId);
    boolean driverRequested(Integer driverId);
    Order getCurrentByDriverId(Integer driverId);
    List<Order> getCurrentOrdersByDriverId(Integer driverId);
    Order getCurrentByUserId(Integer userId);
    List<Order> getByUserId(Integer userId);
}
