package by.epam.buber.service;

import by.epam.buber.entity.Order;
import by.epam.buber.repository.impl.OrderCrudRepositoryImpl;
import by.epam.buber.repository.impl.UserCrudRepositoryImpl;

import java.util.List;

public class DriverService {
    private OrderCrudRepositoryImpl orderCrudRepository = new OrderCrudRepositoryImpl();
    private UserCrudRepositoryImpl userCrudRepository = new UserCrudRepositoryImpl();

    public List<Order> seeOrders(int id){
        return orderCrudRepository.getCurrentFromDriverList(id);
    }

    public void setBusy(int id){
        userCrudRepository.setBusyById(id, true);
    }

    public void setFree(int id){
        userCrudRepository.setBusyById(id, false);
    }

    public boolean acceptedOrder(int driverId){
        return orderCrudRepository.driverAccepted(driverId);
    }

    public Order takeCurrentOrder(int id){
        return orderCrudRepository.getCurrentByDriverId(id);
    }

    public void startTrip(int id){
        orderCrudRepository.setStartedById(id, true);
    }

    public void acceptOrder(int orderId, int driverId){
        orderCrudRepository.setAccepted(driverId, orderId);
        userCrudRepository.setBusyById(driverId, true);
    }

    public void completeOrder(int orderId, int driverId){
        orderCrudRepository.setCompleted(true, orderId);
        userCrudRepository.setBusyById(driverId, false);
    }
}
