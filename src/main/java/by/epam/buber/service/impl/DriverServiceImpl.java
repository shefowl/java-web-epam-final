package by.epam.buber.service.impl;

import by.epam.buber.repository.DriverCrudRepository;
import by.epam.buber.repository.RepositoryFactory;
import by.epam.buber.service.DriverService;

public class DriverServiceImpl implements DriverService {
    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private DriverCrudRepository driverCrudRepository = repositoryFactory.getDriverCrudRepository();


    @Override
    public void setBusy(int id){
        driverCrudRepository.setBusyById(id, true);
    }

    @Override
    public void setFree(int id){
        driverCrudRepository.setBusyById(id, false);
    }

    @Override
    public void setActive(int id) {
        driverCrudRepository.setDriverActive(id,true);
    }

    @Override
    public void setUnactive(int id) {
        driverCrudRepository.setDriverActive(id,false);
    }

    @Override
    public boolean isBusy(int id) {
        return driverCrudRepository.isDriverBusy(id);
    }

}
