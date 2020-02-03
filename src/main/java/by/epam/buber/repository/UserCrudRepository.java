package by.epam.buber.repository;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.TaxiParticipant;

import java.util.List;

public interface UserCrudRepository extends CrudRepository<Integer, TaxiParticipant> {
    TaxiParticipant getByName(String name);
    void setBusyById(Integer id, boolean busy);
    List<Driver> getAllDrivers();
    List<Driver> getAbleDriversByCarClass(CarClass carClass);
    void setOrderToDriver(Order order, Integer id);
    boolean isDriverBusy(Integer driverId);
    void ban(Integer participantId, boolean ban);
    void saveDriver(Driver driver);
    void setDriverCoordinates(Integer driverId, long coordinates);
    void setDriverActive(Integer driverId, boolean active);
    void setDiscount(Integer userId, int discount);
}
