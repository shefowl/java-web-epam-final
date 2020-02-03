package by.epam.buber.service.impl;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.repository.OrderCrudRepository;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.repository.impl.OrderCrudRepositoryImpl;
import by.epam.buber.repository.impl.UserCrudRepositoryImpl;
import by.epam.buber.service.OrderService;
import by.epam.buber.service.impl.util.CoordinatesGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderCrudRepository orderCrudRepository = new OrderCrudRepositoryImpl();
    private UserCrudRepository userCrudRepository = new UserCrudRepositoryImpl();

    @Override
    public List<Order> seeDriverOrders(int driveId){
        return orderCrudRepository.getCurrentFromDriverList(driveId);
    }

    @Override
    public boolean acceptedOrder(int driverId){
        return orderCrudRepository.driverAccepted(driverId);
    }

    @Override
    public Order takeCurrentOrderForDriver(int id){
        return orderCrudRepository.getCurrentByDriverId(id);
    }

    @Override
    public void startTrip(int orderId){
        orderCrudRepository.setStartedById(orderId, true);
    }

    @Override
    public void acceptOrder(int orderId, int driverId){
        orderCrudRepository.setAccepted(driverId, orderId);
        userCrudRepository.setBusyById(driverId, true);
        orderCrudRepository.clearDriverOrderListExceptAccepted(orderId);
    }

    @Override
    public void completeOrder(int orderId, int driverId){
        orderCrudRepository.setCompleted(true, orderId);
        userCrudRepository.setBusyById(driverId, false);
        orderCrudRepository.deleteFromDriverList(orderId);
        Order order = orderCrudRepository.getById(orderId);
        userCrudRepository.setDriverCoordinates(driverId, order.getDestinationCoordinates());
        userCrudRepository.setDiscount(order.getUserId(), 0);
    }

    @Override
    public Order makeOrder(int userId,String address, String carClass, String comment){
        long coordinates = CoordinatesGenerator.generate();
        long destinationCoordinates = CoordinatesGenerator.generate();
        Order order = new Order(userId, coordinates, address, comment, CarClass.valueOf(carClass.toUpperCase()),
                destinationCoordinates);
        orderCrudRepository.save(order);
        return order;
    }


    @Override
    public Order takeCurrentOrderForUser(int id){
        return orderCrudRepository.getCurrentByUserId(id);
    }

    @Override
    public boolean orderMade(int userId){
        return orderCrudRepository.getCurrentByUserId(userId) != null;
    }

    @Override
    public boolean cancelOrder(int orderId){
        if(!orderCrudRepository.orderStarted(orderId)) {
            orderCrudRepository.delete(orderId);
            orderCrudRepository.deleteFromDriverList(orderId);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void setOrderPrice(BigDecimal price, int id){
        orderCrudRepository.setPrice(price, id);
    }

    @Override
    public List<BigDecimal> calculateOrderPricePerDriver(List<Driver> drivers, Order order){
        List<BigDecimal> prices = new ArrayList<>();
        for (Driver driver: drivers) {
            double price = Math.abs(driver.getCoordinates() - order.getCoordinates()) / 100000000.0;
            price *= driver.getPricePerKm().doubleValue();
            BigDecimal bigDecimal = BigDecimal.valueOf(price);
            prices.add(bigDecimal);
        }
        return prices;
    }
}
