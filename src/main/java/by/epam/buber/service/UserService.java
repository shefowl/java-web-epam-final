package by.epam.buber.service;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.TaxiParticipant;

import java.util.List;

public interface UserService {
    TaxiParticipant signIn(String name, String password);
    TaxiParticipant signUp(String name, String password, String email, String phoneNumber);
    TaxiParticipant changeName(int id, String name);
    TaxiParticipant changePassword(int id, String current, String newPassword, String repeatNewPassword);
    //Order makeOrder(int userId, String address, String carClass, String comment);
    void sendDriverRequest(int driverId, int userId);
    //Order takeCurrentOrder(int id);
    boolean driverRequested(int orderId);
    //boolean orderMade(int userId);
    //boolean cancelOrder(int orderId);
    //void setOrderPrice(BigDecimal price, int id);
    List<Driver> showAbleDrivers(long destinationCoordinates, CarClass carClass);
    void setDriverUnactive(int driverId);
    //List<BigDecimal> calculateOrderPricePerDriver(List<Driver> drivers, Order order);
}
