package by.epam.buber.service.impl;

import by.epam.buber.repository.OrderCrudRepository;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.repository.impl.OrderCrudRepositoryImpl;
import by.epam.buber.repository.impl.UserCrudRepositoryImpl;
import by.epam.buber.service.DriverService;

public class DriverServiceImpl implements DriverService {
    private OrderCrudRepository orderCrudRepository = new OrderCrudRepositoryImpl();
    private UserCrudRepository userCrudRepository = new UserCrudRepositoryImpl();
    @Override
    public void setBusy(int id){
        userCrudRepository.setBusyById(id, true);
    }

    @Override
    public void setFree(int id){
        userCrudRepository.setBusyById(id, false);
    }

    @Override
    public void setActive(int id) {
        userCrudRepository.setDriverActive(id,true);
    }

    @Override
    public void setUnactive(int id) {
        userCrudRepository.setDriverActive(id,false);
    }

    @Override
    public boolean isBusy(int id) {
        return userCrudRepository.isDriverBusy(id);
    }

}
