package by.epam.buber.service;

import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order makeOrder(int userId, String address, String carClass, String comment) throws ServiceException;
    Order takeCurrentOrderForDriver(int id) throws ServiceException;
    Order takeCurrentOrderForUser(int id) throws ServiceException;
    boolean orderMade(int userId) throws ServiceException;
    boolean cancelOrder(int orderId) throws ServiceException;
    void setOrderPrice(BigDecimal price, int id) throws ServiceException;
    List<BigDecimal> calculateOrderPricePerDriver(List<Driver> drivers, Order order);
    List<Order> seeDriverOrders(int driveId) throws ServiceException;
    boolean acceptedOrder(int driverId) throws ServiceException;
    boolean startedOrder(int orderId) throws ServiceException;
    void startTrip(int orderId) throws ServiceException;
    void acceptOrder(int orderId, int driverId) throws ServiceException;
    void completeOrder(int orderId, int driverId) throws ServiceException;
}
