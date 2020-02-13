package by.epam.buber.entity;

public class Car {
    private String mark;
    private String model;
    private CarClass carClass;

    public Car() {
    }

    public Car(Car car) {
        this.mark = car.mark;
        this.model = car.model;
        this.carClass = car.carClass;
    }

    public Car(String mark, String model, CarClass carClass) {
        this.mark = mark;
        this.model = model;
        this.carClass = carClass;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }
}
