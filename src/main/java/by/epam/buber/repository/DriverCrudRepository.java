package by.epam.buber.repository;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.exception.DaoException;

import java.util.List;

public interface DriverCrudRepository extends CrudRepository<Integer, Driver> {
    void setBusyById(Integer id, boolean busy) throws DaoException;
    void setOrderToDriver(Order order, Integer id) throws DaoException;
    boolean isDriverBusy(Integer driverId) throws DaoException;
    void setDriverCoordinates(Integer driverId, long coordinates) throws DaoException;
    void setDriverActive(Integer driverId, boolean active) throws DaoException;
    List<Driver> getAbleDriversByCarClass(CarClass carClass) throws DaoException;

}
