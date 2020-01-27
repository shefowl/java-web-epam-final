package by.epam.buber.repository;

import by.epam.buber.entity.participant.TaxiParticipant;

public interface UserCrudRepository extends CrudRepository<Integer, TaxiParticipant> {
    TaxiParticipant getByName(String name);
}
