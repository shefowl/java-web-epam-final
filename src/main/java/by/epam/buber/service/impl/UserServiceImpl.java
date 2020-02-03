package by.epam.buber.service.impl;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.Role;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.entity.participant.User;
import by.epam.buber.repository.OrderCrudRepository;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.repository.impl.OrderCrudRepositoryImpl;
import by.epam.buber.repository.impl.UserCrudRepositoryImpl;
import by.epam.buber.service.UserService;
import by.epam.buber.service.impl.util.CoordinatesGenerator;
import by.epam.buber.service.impl.util.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserServiceImpl implements UserService {
    private UserCrudRepository userCrudRepository = new UserCrudRepositoryImpl();
    private OrderCrudRepository orderCrudRepository = new OrderCrudRepositoryImpl();
    private PasswordEncoder passwordEncoder = new PasswordEncoder();

    @Override
    public TaxiParticipant signIn(String name, String password) {
        String hash = passwordEncoder.encode(password);
        TaxiParticipant taxiParticipant = userCrudRepository.getByName(name);
        if (taxiParticipant != null) {
            if (!taxiParticipant.isBanned()) {
                if (passwordEncoder.checkPassword(password, taxiParticipant.getPassword())) {
                    if (taxiParticipant.getRole() == Role.DRIVER) {
                        userCrudRepository.setDriverActive(taxiParticipant.getId(), true);
                        // userCrudRepository.setDriverCoordinates(taxiParticipant.getId(), CoordinatesGenerator.generate()); // uncheсk foreign keys
                    }
                    return taxiParticipant;
                }
            }
        } else {
            return null;
        }
        return null;
    }

    @Override
    public void setDriverUnactive(int driverId) {
        userCrudRepository.setDriverActive(driverId, false);
    }

    @Override
    public TaxiParticipant signUp(String name, String password, String email, String phoneNumber) {
        User user = (User) userCrudRepository.getByName(name);
        if (user == null) {
            String hashPassword = passwordEncoder.encode(password);
            user = new User(name, hashPassword, email, phoneNumber);
            userCrudRepository.save(user);
        }
        return user;
    }

    @Override
    public TaxiParticipant changeName(int id, String name) {
        TaxiParticipant taxiParticipant = userCrudRepository.getById(id);
        if (taxiParticipant != null) {
            taxiParticipant.setName(name);
            userCrudRepository.save(id, taxiParticipant);
            return taxiParticipant;
        }
        return null;
    }

    @Override
    public TaxiParticipant changePassword(int id, String current, String newPassword, String repeatNewPassword) {
        TaxiParticipant taxiParticipant = userCrudRepository.getById(id);
        if (taxiParticipant != null) {
            if (passwordEncoder.checkPassword(current, taxiParticipant.getPassword())) {
                if (newPassword.equals(repeatNewPassword)) {
                    String hashPassword = passwordEncoder.encode(newPassword);
                    taxiParticipant.setPassword(hashPassword);
                    userCrudRepository.save(id, taxiParticipant);
                    return taxiParticipant;
                }
            }
        }
        return null;
    }

    @Override
    public void sendDriverRequest(int driverId, int userId) {
        Order order = orderCrudRepository.getCurrentByUserId(userId);
        userCrudRepository.setOrderToDriver(order, driverId);
    }

    @Override
    public boolean driverRequested(int orderId) {
        return orderCrudRepository.driverRequested(orderId);
    }

    @Override
    public List<Driver> showAbleDrivers(long destinationCoordinates, CarClass carClass) {
        List<Driver> drivers = userCrudRepository.getAbleDriversByCarClass(carClass);
        List<Driver> ableDrivers = new ArrayList<>();
        for (Driver driver : drivers) {
            if (Math.abs(driver.getCoordinates() - destinationCoordinates) < 400000000) {
                ableDrivers.add(driver);
            }
        }
        return ableDrivers;
    }
}

