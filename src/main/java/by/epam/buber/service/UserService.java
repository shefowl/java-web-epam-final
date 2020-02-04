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
    void sendDriverRequest(int driverId, int userId);
    boolean driverRequested(int orderId);
    List<Driver> showAbleDrivers(long destinationCoordinates, CarClass carClass);
    void setDriverUnactive(int driverId);
}
