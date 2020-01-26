package by.epam.buber.entity;

public class Order {
    private int id;
    private int coordinates;
    private String destinationPoint;

    public Order() {
    }

    public Order(int id, int coordinates, String destinationPoint) {
        this.id = id;
        this.coordinates = coordinates;
        this.destinationPoint = destinationPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
