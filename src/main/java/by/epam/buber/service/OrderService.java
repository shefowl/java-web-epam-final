package by.epam.buber.service;

import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order makeOrder(int userId, String address, String carClass, String comment);
    Order takeCurrentOrderForDriver(int id);
    Order takeCurrentOrderForUser(int id);
    boolean orderMade(int userId);
    boolean cancelOrder(int orderId);
    void setOrderPrice(BigDecimal price, int id);
    List<BigDecimal> calculateOrderPricePerDriver(List<Driver> drivers, Order order);
    List<Order> seeDriverOrders(int driveId);
    boolean acceptedOrder(int driverId);
    void startTrip(int orderId);
    void acceptOrder(int orderId, int driverId);
    void completeOrder(int orderId, int driverId);
}
