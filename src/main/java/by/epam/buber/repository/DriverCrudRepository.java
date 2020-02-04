package by.epam.buber.repository;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;

import java.util.List;

public interface DriverCrudRepository extends CrudRepository<Integer, Driver> {
    void setBusyById(Integer id, boolean busy);
    void setOrderToDriver(Order order, Integer id);
    boolean isDriverBusy(Integer driverId);
    void setDriverCoordinates(Integer driverId, long coordinates);
    void setDriverActive(Integer driverId, boolean active);
    List<Driver> getAbleDriversByCarClass(CarClass carClass);

}
