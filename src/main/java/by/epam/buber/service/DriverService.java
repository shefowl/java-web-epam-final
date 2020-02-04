package by.epam.buber.service;

import by.epam.buber.entity.Order;

import java.util.List;

public interface DriverService {
    void setBusy(int id);
    void setFree(int id);
    boolean isBusy(int id);
    void setActive(int id);
    void setUnactive(int id);
}
