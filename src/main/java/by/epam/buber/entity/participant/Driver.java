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
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
