package by.epam.buber.service;

import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.TaxiParticipant;

import java.util.List;

public interface AdminService {
    void ban(Integer participantId);
    void unban(Integer participantId);
    List<TaxiParticipant> getAllParticipants();
    void registrateDriver(Driver driver);
    List<TaxiParticipant> getUsersByName(String name);
    TaxiParticipant getParticipantById(Integer participantId);
    List<TaxiParticipant> getUsersForDiscount();
    void setDiscount(int userId, int discount);
}
