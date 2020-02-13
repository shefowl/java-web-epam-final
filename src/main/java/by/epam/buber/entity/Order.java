package by.epam.buber.entity;

import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private int id;
    private int userId;
    private int driverId;
    private long coordinates;
    private String destinationPoint;
    private long destinationCoordinates;
    private BigDecimal price;
    private String comment;
    private CarClass carClass;
    private boolean started;
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
        this.started = order.started;
        this.completed = order.completed;
        this.ableDrivers = new ArrayList<>(order.ableDrivers);
    }

    public Order(int userId, String destinationPoint, String comment, CarClass carClass) {
        this.userId = userId;
        this.destinationPoint = destinationPoint;
        this.comment = comment;
        this.carClass = carClass;
    }

    public Order(int userId, long coordinates, String destinationPoint, String comment, CarClass carClass,
                 long destinationCoordinates) {
        this.userId = userId;
        this.coordinates = coordinates;
        this.destinationPoint = destinationPoint;
        this.comment = comment;
        this.carClass = carClass;
        this.destinationCoordinates = destinationCoordinates;
        this.started = false;
        this.completed = false;
    }

    public Order(int id, long coordinates, String destinationPoint) {
        this.id = id;
        this.coordinates = coordinates;
        this.destinationPoint = destinationPoint;
        User user = new User();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                userId == order.userId &&
                driverId == order.driverId &&
                coordinates == order.coordinates &&
                destinationCoordinates == order.destinationCoordinates &&
                started == order.started &&
                completed == order.completed &&
                Objects.equals(destinationPoint, order.destinationPoint) &&
                Objects.equals(price, order.price) &&
                Objects.equals(comment, order.comment) &&
                carClass == order.carClass &&
                Objects.equals(ableDrivers, order.ableDrivers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, driverId, coordinates, destinationPoint, destinationCoordinates, price, comment, carClass, started, completed, ableDrivers);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", driverId=" + driverId +
                ", coordinates=" + coordinates +
                ", destinationPoint='" + destinationPoint + '\'' +
                ", destinationCoordinates=" + destinationCoordinates +
                ", price=" + price +
                ", comment='" + comment + '\'' +
                ", carClass=" + carClass +
                ", started=" + started +
                ", completed=" + completed +
                ", ableDrivers=" + ableDrivers +
                '}';
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

    public long getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(long coordinates) {
        this.coordinates = coordinates;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public long getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public void setDestinationCoordinates(long destinationCoordinates) {
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

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
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
