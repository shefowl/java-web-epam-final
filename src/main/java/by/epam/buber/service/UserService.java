package by.epam.buber.service;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.exception.ServiceException;

import java.util.List;

public interface UserService {
    TaxiParticipant signIn(String name, String password) throws ServiceException;
    TaxiParticipant signUp(String name, String password, String email, String phoneNumber) throws ServiceException;
    TaxiParticipant changeName(int id, String name) throws ServiceException;
    TaxiParticipant changePassword(int id, String current, String newPassword, String repeatNewPassword)
            throws ServiceException;
    void sendDriverRequest(int driverId, int userId) throws ServiceException;
    boolean driverRequested(int orderId) throws ServiceException;
    List<Driver> showAbleDrivers(long destinationCoordinates, CarClass carClass) throws ServiceException;
    void setDriverUnactive(int driverId) throws ServiceException;
}
