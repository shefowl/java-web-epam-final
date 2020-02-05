package by.epam.buber.service.impl;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.exception.DaoException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.repository.DriverCrudRepository;
import by.epam.buber.repository.OrderCrudRepository;
import by.epam.buber.repository.RepositoryFactory;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.service.OrderService;
import by.epam.buber.service.impl.util.CoordinatesGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private UserCrudRepository userCrudRepository = repositoryFactory.getUserCrudRepository();
    private OrderCrudRepository orderCrudRepository = repositoryFactory.getOrderCrudRepository();
    private DriverCrudRepository driverCrudRepository =repositoryFactory.getDriverCrudRepository();

    @Override
    public List<Order> seeDriverOrders(int driveId) throws ServiceException {
        try {
            return orderCrudRepository.getCurrentFromDriverList(driveId);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean acceptedOrder(int driverId) throws ServiceException{
        try {
            return orderCrudRepository.driverAccepted(driverId);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Order takeCurrentOrderForDriver(int id) throws ServiceException{
        try {
            return orderCrudRepository.getCurrentByDriverId(id);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void startTrip(int orderId) throws ServiceException{
        try {
            orderCrudRepository.setStartedById(orderId, true);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void acceptOrder(int orderId, int driverId) throws ServiceException{
        try {
            orderCrudRepository.setAccepted(driverId, orderId);
            driverCrudRepository.setBusyById(driverId, true);
            orderCrudRepository.clearDriverOrderListExceptAccepted(orderId);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void completeOrder(int orderId, int driverId) throws ServiceException{
        try {
            orderCrudRepository.setCompleted(true, orderId);
            driverCrudRepository.setBusyById(driverId, false);
            orderCrudRepository.deleteFromDriverList(orderId);
            Order order = orderCrudRepository.getById(orderId);
            driverCrudRepository.setDriverCoordinates(driverId, order.getDestinationCoordinates());
            userCrudRepository.setDiscount(order.getUserId(), 0);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Order makeOrder(int userId,String address, String carClass, String comment) throws ServiceException{
        try {
            long coordinates = CoordinatesGenerator.generate();
            long destinationCoordinates = CoordinatesGenerator.generate();
            Order order = new Order(userId, coordinates, address, comment, CarClass.valueOf(carClass.toUpperCase()),
                    destinationCoordinates);
            orderCrudRepository.save(order);
            return order;
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }


    @Override
    public Order takeCurrentOrderForUser(int id) throws ServiceException{
        try {
            return orderCrudRepository.getCurrentByUserId(id);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean orderMade(int userId) throws ServiceException{
        try {
            return orderCrudRepository.getCurrentByUserId(userId) != null;
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean cancelOrder(int orderId) throws ServiceException{
        try {
            if (!orderCrudRepository.orderStarted(orderId)) {
                orderCrudRepository.delete(orderId);
                orderCrudRepository.deleteFromDriverList(orderId);
                return true;
            } else {
                return false;
            }
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void setOrderPrice(BigDecimal price, int id) throws ServiceException {
        try {
            orderCrudRepository.setPrice(price, id);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BigDecimal> calculateOrderPricePerDriver(List<Driver> drivers, Order order){
            List<BigDecimal> prices = new ArrayList<>();
            for (Driver driver : drivers) {
                double price = Math.abs(driver.getCoordinates() - order.getCoordinates()) / 100000000.0;
                price *= driver.getPricePerKm().doubleValue();
                BigDecimal bigDecimal = BigDecimal.valueOf(price);
                prices.add(bigDecimal);
            }
            return prices;
    }
}
