package by.epam.buber.entity.participant;

import by.epam.buber.entity.Car;

public class Driver extends TaxiParticipant {
    private Car car;
    private String coordinates;
    private boolean active;
    private boolean busy;

    public Driver() {
        role = Role.DRIVER;
    }

    public Car getCar() {
        return new Car(car);
    }

    public void setCar(Car car) {
        this.car = new Car(car);
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
