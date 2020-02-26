package by.epam.buber.service.impl;

import by.epam.buber.exception.DaoException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.repository.DriverCrudRepository;
import by.epam.buber.repository.RepositoryFactory;
import by.epam.buber.service.DriverService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DriverServiceImpl implements DriverService {
    private final static Logger logger = LogManager.getLogger(DriverServiceImpl.class);

    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private DriverCrudRepository driverCrudRepository = repositoryFactory.getDriverCrudRepository();


    @Override
    public void setBusy(int id) throws ServiceException{
        try {
            driverCrudRepository.setBusyById(id, true);
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void setFree(int id) throws ServiceException{
        try {
            driverCrudRepository.setBusyById(id, false);
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void setActive(int id) throws ServiceException {
        try {
            driverCrudRepository.setDriverActive(id, true);
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void setUnactive(int id) throws ServiceException {
        try {
            driverCrudRepository.setDriverActive(id, false);
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isBusy(int id) throws ServiceException {
        try {
            return driverCrudRepository.isDriverBusy(id);
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }


}
