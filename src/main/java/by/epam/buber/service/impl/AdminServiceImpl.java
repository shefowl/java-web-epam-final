package by.epam.buber.service.impl;

import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.exception.DaoException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.repository.DriverCrudRepository;
import by.epam.buber.repository.OrderCrudRepository;
import by.epam.buber.repository.RepositoryFactory;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.repository.impl.UserCrudRepositoryImpl;
import by.epam.buber.service.AdminService;
import by.epam.buber.service.impl.util.CoordinatesGenerator;
import by.epam.buber.service.impl.util.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private final static Logger logger = LogManager.getLogger(AdminServiceImpl.class);

    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private UserCrudRepository userCrudRepository = repositoryFactory.getUserCrudRepository();
    private OrderCrudRepository orderCrudRepository = repositoryFactory.getOrderCrudRepository();
    private DriverCrudRepository driverCrudRepository = repositoryFactory.getDriverCrudRepository();
    private PasswordEncoder passwordEncoder = new PasswordEncoder();


    @Override
    public void ban(Integer participantId, boolean ban) throws ServiceException {
        try {
            userCrudRepository.ban(participantId, ban);
            if(ban) {
                logger.info("Participant has been banned");
            }
            else {
                logger.info("Participant has been unbanned");
            }
        }
        catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TaxiParticipant> getAllParticipants() throws ServiceException{
        try{
            return userCrudRepository.getAll();
        }
        catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TaxiParticipant> getUsersByName(String name) throws ServiceException {
        List<TaxiParticipant> participants = getAllParticipants();
        List<TaxiParticipant> participantsByName = new ArrayList<>();
        for (TaxiParticipant participant: participants){
            if(participant.getName().contains(name)){
                participantsByName.add(participant);
            }
        }
        return participantsByName;
    }

    @Override
    public List<TaxiParticipant> getUsersForDiscount() throws ServiceException {
        List<TaxiParticipant> participantsForDiscount = new ArrayList<>();

        try {
            List<TaxiParticipant> participants = getAllParticipants();
            List<Order> orders;
            for (TaxiParticipant participant : participants) {
                orders = orderCrudRepository.getCompletedByUserId(participant.getId());
                BigDecimal price = new BigDecimal(0);
                for (Order order : orders) {
                    price = price.add(order.getPrice());
                }
                if (price.doubleValue() > 1000) {
                    participantsForDiscount.add(participant);
                }
            }
        }
        catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
        return participantsForDiscount;
    }

    @Override
    public void setDiscount(int userId, int discount) throws ServiceException {
        try {
            userCrudRepository.setDiscount(userId, discount);
            logger.info("User get discount");
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public TaxiParticipant getParticipantById(Integer participantId) throws ServiceException {
        try {
            return userCrudRepository.getById(participantId);
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void registrateDriver(Driver driver) throws ServiceException {
        try {
            driver.setPassword(passwordEncoder.encode(driver.getPassword()));
            driver.setCoordinates(CoordinatesGenerator.generate());
            driverCrudRepository.save(driver);
            logger.info("New driver has been signed up");
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
