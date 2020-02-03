package by.epam.buber.service;

import by.epam.buber.entity.Order;

import java.util.List;

public interface DriverService {
    //List<Order> seeOrders(int driveId);
    void setBusy(int id);
    void setFree(int id);
    boolean isBusy(int id);
    void setActive(int id);
    void setUnactive(int id);
    //boolean acceptedOrder(int driverId);
    //Order takeCurrentOrder(int id);
//    void startTrip(int orderId);
//    void acceptOrder(int orderId, int driverId);
//    void completeOrder(int orderId, int driverId);
}
