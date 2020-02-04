package by.epam.buber.repository.impl.util;

import by.epam.buber.entity.Car;
import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.Role;
import by.epam.buber.entity.participant.TaxiParticipant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetConverter {

    private static final ResultSetConverter instance = new ResultSetConverter();

    private ResultSetConverter(){
    }

    public static ResultSetConverter getInstance(){
        return instance;
    }

    public Order convertOrderFromResultSet(ResultSet resultSet){
        Order order = new Order();
        try {
            order.setId(resultSet.getInt("id"));
            order.setCoordinates(resultSet.getLong("coordinates"));
            order.setDestinationPoint(resultSet.getString("destinationPoint"));
            order.setDestinationCoordinates(resultSet.getLong("destinationCoordinates"));
            order.setPrice(resultSet.getBigDecimal("price"));
            order.setComment(resultSet.getString("orderComment"));
            order.setCarClass(CarClass.valueOf(resultSet.getString("carClass")));
            order.setCompleted(resultSet.getBoolean("completed"));
            order.setUserId(resultSet.getInt("userId"));
            order.setDriverId(resultSet.getInt("driverId"));
            order.setStarted(resultSet.getBoolean("started"));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return order;
    }

    public TaxiParticipant convertParticipantFromResultSet(ResultSet resultSet, TaxiParticipant participant){
        try {
            participant.setId(resultSet.getInt("id"));
            participant.setName(resultSet.getString("name"));
            participant.setEmail(resultSet.getString("email"));
            participant.setRole(Role.valueOf(resultSet.getString("role")));
            participant.setPhoneNumber(resultSet.getString("phone"));
            participant.setPassword(resultSet.getString("password"));
            participant.setBanned(resultSet.getBoolean("banned"));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return participant;
    }

    public Driver convertDriverFromResultSet(ResultSet resultSet, Driver driver){
        try {
            driver = (Driver)convertParticipantFromResultSet(resultSet, driver);
            driver.setActive(resultSet.getBoolean("active"));
            driver.setBusy(resultSet.getBoolean("busy"));
            driver.setCoordinates(resultSet.getLong("coordinates"));
            driver.setPricePerKm(resultSet.getBigDecimal("pricePerKm"));
            Car car = new Car();
            car.setMark(resultSet.getString("mark"));
            car.setModel(resultSet.getString("model"));
            car.setCarClass(CarClass.valueOf(resultSet.getString("carClass").toUpperCase()));
            driver.setCar(car);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return driver;
    }

    public void statementSetParticipant(PreparedStatement statement, TaxiParticipant participant){
        try {
            statement.setString(1, participant.getName());
            statement.setString(2, participant.getPassword());
            statement.setString(3, participant.getEmail());
            statement.setString(4, participant.getRole().name());
            statement.setString(5, participant.getPhoneNumber());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void statementSetBooleanById(PreparedStatement statement, boolean bool, int id){
        try {
            statement.setBoolean(1, bool);
            statement.setInt(2, id);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
