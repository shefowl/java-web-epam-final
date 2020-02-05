package by.epam.buber.service;

import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.exception.ServiceException;

import java.util.List;

public interface AdminService {
    void ban(Integer participantId, boolean ban) throws ServiceException;
    List<TaxiParticipant> getAllParticipants() throws ServiceException;
    void registrateDriver(Driver driver) throws ServiceException;
    List<TaxiParticipant> getUsersByName(String name) throws ServiceException;
    TaxiParticipant getParticipantById(Integer participantId) throws ServiceException;
    List<TaxiParticipant> getUsersForDiscount() throws ServiceException;
    void setDiscount(int userId, int discount) throws ServiceException;
}
