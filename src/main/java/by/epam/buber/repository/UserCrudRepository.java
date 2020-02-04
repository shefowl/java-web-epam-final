package by.epam.buber.repository;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.TaxiParticipant;

import java.util.List;

public interface UserCrudRepository extends CrudRepository<Integer, TaxiParticipant> {
    TaxiParticipant getByName(String name);
    void ban(Integer participantId, boolean ban);
    void setDiscount(Integer userId, int discount);
}
