package by.epam.buber.service;

import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.repository.impl.OrderCrudRepositoryImpl;
import by.epam.buber.repository.impl.UserCrudRepositoryImpl;

import java.util.List;

public class DriverService {
    private OrderCrudRepositoryImpl orderCrudRepository = new OrderCrudRepositoryImpl();
    private UserCrudRepositoryImpl userCrudRepository = new UserCrudRepositoryImpl();

    public List<Order> seeOrders(){
        return orderCrudRepository.getAll();
    }

    public void setBusy(int id){
        userCrudRepository.setBusyById(id, true);
    }

    public void setFree(int id){
        userCrudRepository.setBusyById(id, false);
    }

    public void acceptOrder(int orderId, int driverId){
        //order.setDriverId(driverId);
        //orderCrudRepository.save(order.getId(), order);
        orderCrudRepository.setAccepted(driverId, orderId);
        userCrudRepository.setBusyById(driverId, true);
        //return order;
    }

    public Order completeOrder(Order order){
        order.setCompleted(true);
        orderCrudRepository.save(order.getId(), order);
        return order;
    }
}
