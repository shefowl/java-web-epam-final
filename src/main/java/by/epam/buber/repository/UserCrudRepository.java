package by.epam.buber.repository;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.exception.DaoException;

import java.util.List;

public interface UserCrudRepository extends CrudRepository<Integer, TaxiParticipant> {
    TaxiParticipant getByName(String name) throws DaoException;
    void ban(Integer participantId, boolean ban) throws DaoException;
    void setDiscount(Integer userId, int discount) throws DaoException;
}
