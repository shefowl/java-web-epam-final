package by.epam.buber.repository;

import by.epam.buber.repository.impl.DriverCrudRepositoryImpl;
import by.epam.buber.repository.impl.OrderCrudRepositoryImpl;
import by.epam.buber.repository.impl.UserCrudRepositoryImpl;

public class RepositoryFactory {

    public static final RepositoryFactory instance = new RepositoryFactory();

    private final by.epam.buber.repository.UserCrudRepository userCrudRepository = new UserCrudRepositoryImpl();
    private final DriverCrudRepository driverCrudRepository = new DriverCrudRepositoryImpl();
    private final OrderCrudRepository orderCrudRepository = new OrderCrudRepositoryImpl();

    private RepositoryFactory(){

    }

    public static RepositoryFactory getInstance(){
        return instance;
    }

    public by.epam.buber.repository.UserCrudRepository getUserCrudRepository() {
        return userCrudRepository;
    }

    public DriverCrudRepository getDriverCrudRepository() {
        return driverCrudRepository;
    }

    public OrderCrudRepository getOrderCrudRepository() {
        return orderCrudRepository;
    }
}
