package by.epam.buber.service.impl;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.Role;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.entity.participant.User;
import by.epam.buber.exception.DaoException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.repository.DriverCrudRepository;
import by.epam.buber.repository.OrderCrudRepository;
import by.epam.buber.repository.RepositoryFactory;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.service.UserService;
import by.epam.buber.service.impl.util.CoordinatesGenerator;
import by.epam.buber.service.impl.util.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private UserCrudRepository userCrudRepository = repositoryFactory.getUserCrudRepository();
    private OrderCrudRepository orderCrudRepository = repositoryFactory.getOrderCrudRepository();
    private DriverCrudRepository driverCrudRepository =repositoryFactory.getDriverCrudRepository();
    private PasswordEncoder passwordEncoder = new PasswordEncoder();

    @Override
    public TaxiParticipant signIn(String name, String password) throws ServiceException {
        try {
            String hash = passwordEncoder.encode(password);
            TaxiParticipant taxiParticipant = userCrudRepository.getByName(name);
            if (taxiParticipant != null) {
                    if (passwordEncoder.checkPassword(password, taxiParticipant.getPassword())) {
                        if (taxiParticipant.getRole() == Role.DRIVER) {
                            driverCrudRepository.setDriverActive(taxiParticipant.getId(), true);
                             driverCrudRepository.setDriverCoordinates(taxiParticipant.getId(),
                                     CoordinatesGenerator.generate());
                             logger.info("Driver signed in");
                        }
                        logger.info("User signed in");
                        return taxiParticipant;
                    }
            } else {
                return null;
            }
            return null;
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public TaxiParticipant signUp(String name, String password, String email, String phoneNumber)
            throws ServiceException {
        try {
            User user = (User) userCrudRepository.getByName(name);
            if (user == null) {
                String hashPassword = passwordEncoder.encode(password);
                user = new User(name, hashPassword, email, phoneNumber);
                userCrudRepository.save(user);
                logger.info("User signed up");
            }
            else {
                return null;
            }
            return user;
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public TaxiParticipant changeName(int id, String name) throws ServiceException {
        try {
            TaxiParticipant taxiParticipant = userCrudRepository.getById(id);
            if (taxiParticipant != null) {
                if(userCrudRepository.getByName(name) == null) {
                    taxiParticipant.setName(name);
                    userCrudRepository.save(id, taxiParticipant);
                    logger.info("Participant changed name");
                    return taxiParticipant;
                }
            }
            return null;
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public TaxiParticipant changePassword(int id, String current, String newPassword, String repeatNewPassword)
            throws ServiceException {
        try {
            TaxiParticipant taxiParticipant = userCrudRepository.getById(id);
            if (taxiParticipant != null) {
                if (passwordEncoder.checkPassword(current, taxiParticipant.getPassword())) {
                    if (newPassword.equals(repeatNewPassword)) {
                        String hashPassword = passwordEncoder.encode(newPassword);
                        taxiParticipant.setPassword(hashPassword);
                        userCrudRepository.save(id, taxiParticipant);
                        logger.info("Participant changed password");
                        return taxiParticipant;
                    }
                }
            }
            return null;
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void sendDriverRequest(int driverId, int userId) throws ServiceException {
        try {
            Order order = orderCrudRepository.getCurrentByUserId(userId);
            driverCrudRepository.setOrderToDriver(order, driverId);
            logger.info("Driver requested");
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean driverRequested(int orderId) throws ServiceException {
        try {
            return orderCrudRepository.driverRequested(orderId);
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Driver> showAbleDrivers(long destinationCoordinates, CarClass carClass) throws ServiceException {
        try {
            List<Driver> drivers = driverCrudRepository.getAbleDriversByCarClass(carClass);
            List<Driver> ableDrivers = new ArrayList<>();
            for (Driver driver : drivers) {
                if (Math.abs(driver.getCoordinates() - destinationCoordinates) < 400000000) {
                    ableDrivers.add(driver);
                }
            }
            return ableDrivers;
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}

