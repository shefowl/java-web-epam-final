package by.epam.buber.service;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.entity.participant.User;
import by.epam.buber.repository.impl.OrderCrudRepositoryImpl;
import by.epam.buber.repository.impl.UserCrudRepositoryImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserService {
    private UserCrudRepositoryImpl userCrudRepository = new UserCrudRepositoryImpl();
    private OrderCrudRepositoryImpl orderCrudRepository = new OrderCrudRepositoryImpl();


    public TaxiParticipant login(String name, String password){
        TaxiParticipant taxiParticipant = userCrudRepository.getByName(name);
        if(taxiParticipant != null){
            if(taxiParticipant.getPassword().equals(password)){
                return taxiParticipant;
            }
        }
        else{
            return null;
        }
        return null;
    }

    public TaxiParticipant signUp(String name, String password, String email, String phoneNumber){
        User user = (User) userCrudRepository.getByName(name);
        if(user == null){
            user = new User(name, password, email, phoneNumber);
            userCrudRepository.save(user);
        }
        return user;
    }

    public TaxiParticipant changeName(int id, String name) {
        TaxiParticipant taxiParticipant = userCrudRepository.getById(id);
        if (taxiParticipant != null) {
            taxiParticipant.setName(name);
            userCrudRepository.save(id, taxiParticipant);
            return taxiParticipant;
        }
        return null;
    }

    public TaxiParticipant changePassword(int id, String current, String newPassword, String repeatNewPassword) {
        TaxiParticipant taxiParticipant = userCrudRepository.getById(id);
        if (taxiParticipant != null) {
            if(taxiParticipant.getPassword().equals(current)){
                if(newPassword.equals(repeatNewPassword)) {
                    taxiParticipant.setPassword(newPassword);
                    userCrudRepository.save(id, taxiParticipant);
                    return taxiParticipant;
                }
            }
        }
        return null;
    }

    public Order makeOrder(int userId,String address, String carClass, String comment){
        Random random = new Random();
        int coordinates = random.nextInt(999000000 + 1);
        coordinates +=1000000;
        int destinationCoordinates = random.nextInt(999000000 + 1);
        destinationCoordinates +=1000000;
        Order order = new Order(userId, coordinates, address, comment,CarClass.valueOf(carClass.toUpperCase()),
                destinationCoordinates);
        orderCrudRepository.save(order);
        List<Driver> ableDrivers = userCrudRepository.getAllDrivers();
        order.setAbleDrivers(ableDrivers);
        return order;
    }

    public void sendDriverRequest(int driverId, int userId){
        Order order = orderCrudRepository.getCurrentByUserId(userId);
        userCrudRepository.setOrderToDriver(order, driverId);
    }

    public Order takeCurrentOrder(int id){
        return orderCrudRepository.getCurrentByUserId(id);
    }

    public boolean driverRequested(int orderId){
        return orderCrudRepository.driverRequested(orderId);
    }

    public boolean orderMade(int userId){
        return orderCrudRepository.getCurrentByUserId(userId) != null;
    }

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

    public void setOrderPrice(BigDecimal price, int id){
        orderCrudRepository.setPrice(price, id);
    }

    public List<Driver> showAbleDrivers(long destinationCoordinates, CarClass carClass){
        List<Driver> drivers = userCrudRepository.getAbleDriversByCarClass(carClass);
        List<Driver> ableDrivers = new ArrayList<>();
        for (Driver driver: drivers){
            //long i = Math.abs(driver.getCoordinates() - destinationCoordinates);
            //boolean g = i > 35000000;
            if(Math.abs(driver.getCoordinates() - destinationCoordinates) < 400000000){
                ableDrivers.add(driver);
            }
        }
        return ableDrivers;
    }
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
