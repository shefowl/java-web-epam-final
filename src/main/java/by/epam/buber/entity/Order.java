package by.epam.buber.entity;

import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int userId;
    private int driverId;
    private int coordinates;
    private String destinationPoint;
    private int destinationCoordinates;
    private BigDecimal price;
    private String comment;
    private CarClass carClass;
    private boolean completed;
    private List<Driver> ableDrivers;

    public Order() {
    }

    public Order(Order order) {
        this.id = order.id;
        this.coordinates = order.coordinates;
        this.destinationPoint = order.destinationPoint;
        this.destinationCoordinates = order.destinationCoordinates;
        this.price = new BigDecimal(order.price.doubleValue());
        this.comment = order.comment;
        this.carClass = order.carClass;
        this.completed = order.completed;
        this.ableDrivers = new ArrayList<>(order.ableDrivers);
    }

    public Order(int userId,String destinationPoint, String comment, CarClass carClass) {
        this.userId = userId;
        this.destinationPoint = destinationPoint;
        this.comment = comment;
        this.carClass = carClass;
    }

    public Order(int id, int coordinates, String destinationPoint) {
        this.id = id;
        this.coordinates = coordinates;
        this.destinationPoint = destinationPoint;
        User user = new User();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int coordinates) {
        this.coordinates = coordinates;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public int getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public void setDestinationCoordinates(int destinationCoordinates) {
        this.destinationCoordinates = destinationCoordinates;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<Driver> getAbleDrivers() {
        return ableDrivers;
    }

    public void setAbleDrivers(List<Driver> ableDrivers) {
        this.ableDrivers = ableDrivers;
    }
}
