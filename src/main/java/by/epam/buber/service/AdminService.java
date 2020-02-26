package by.epam.buber.service;

import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.exception.ServiceException;

import java.util.List;

/**
 * Service interface represents functionality for administrators
 */
public interface AdminService {
    /**
     * Ban or unban user by id
     * @param participantId
     * @param ban
     * @throws ServiceException
     */
    void ban(Integer participantId, boolean ban) throws ServiceException;

    /**
     * @return all application participants
     * @throws ServiceException
     */
    List<TaxiParticipant> getAllParticipants() throws ServiceException;

    /**
     * Add's new driver to system
     * @param driver
     * @throws ServiceException
     */
    void signUpDriver(Driver driver) throws ServiceException;

    /**
     * Show all participants by nme
     * @param name
     * @return all participants whose names matches entered one
     * @throws ServiceException
     */
    List<TaxiParticipant> getUsersByName(String name) throws ServiceException;

    /**
     * Get participant by id
     * @param participantId
     * @return participant
     * @throws ServiceException
     */
    TaxiParticipant getParticipantById(Integer participantId) throws ServiceException;

    /**
     * Show list of users are able to get discount
     * @return users are able to get discount
     * @throws ServiceException
     */
    List<TaxiParticipant> getUsersForDiscount() throws ServiceException;

    /**
     * Give discount to user
     * @param userId
     * @param discount
     * @throws ServiceException
     */
    void setDiscount(int userId, int discount) throws ServiceException;
}
