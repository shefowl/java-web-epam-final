package by.epam.buber.service;

import by.epam.buber.exception.ServiceException;

public interface DriverService {
    void setBusy(int id) throws ServiceException;
    void setFree(int id) throws ServiceException;
    boolean isBusy(int id) throws ServiceException;
    void setActive(int id) throws ServiceException;
    void setUnactive(int id) throws ServiceException;
    void setDriverUnactive(int driverId) throws ServiceException;

}
