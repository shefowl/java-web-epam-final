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
            order.setId(resultSet.getInt(OrderField.ID));
            order.setCoordinates(resultSet.getLong(OrderField.COORDINATES));
            order.setDestinationPoint(resultSet.getString(OrderField.DESTINATION_POINT));
            order.setDestinationCoordinates(resultSet.getLong(OrderField.DESTINATION_COORDINATES));
            order.setPrice(resultSet.getBigDecimal(OrderField.PRICE));
            order.setComment(resultSet.getString(OrderField.ORDER_COMMENT));
            order.setCarClass(CarClass.valueOf(resultSet.getString(OrderField.CAR_CLASS)));
            order.setCompleted(resultSet.getBoolean(OrderField.COMPLETED));
            order.setUserId(resultSet.getInt(OrderField.USER_ID));
            order.setDriverId(resultSet.getInt(OrderField.DRIVER_ID));
            order.setStarted(resultSet.getBoolean(OrderField.STARTED));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return order;
    }

    public TaxiParticipant convertParticipantFromResultSet(ResultSet resultSet, TaxiParticipant participant){
        try {
            participant.setId(resultSet.getInt(TaxiParticipantField.ID));
            participant.setName(resultSet.getString(TaxiParticipantField.NAME));
            participant.setEmail(resultSet.getString(TaxiParticipantField.EMAIL));
            participant.setRole(Role.valueOf(resultSet.getString(TaxiParticipantField.ROLE)));
            participant.setPhoneNumber(resultSet.getString(TaxiParticipantField.PHONE));
            participant.setPassword(resultSet.getString(TaxiParticipantField.PASSWORD));
            participant.setBanned(resultSet.getBoolean(TaxiParticipantField.BANNED));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return participant;
    }

    public Driver convertDriverFromResultSet(ResultSet resultSet, Driver driver){
        try {
            driver = (Driver)convertParticipantFromResultSet(resultSet, driver);
            driver.setActive(resultSet.getBoolean(DriverField.ACTIVE));
            driver.setBusy(resultSet.getBoolean(DriverField.BUSY));
            driver.setCoordinates(resultSet.getLong(DriverField.COORDINATES));
            driver.setPricePerKm(resultSet.getBigDecimal(DriverField.PRICE_PER_KM));
            Car car = new Car();
            car.setMark(resultSet.getString(CarField.MARK));
            car.setModel(resultSet.getString(CarField.MODEL));
            car.setCarClass(CarClass.valueOf(resultSet.getString(CarField.CAR_CLASS).toUpperCase()));
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
            statement.setString(4, participant.getRole().name().toUpperCase());
            statement.setString(5, participant.getPhoneNumber());
            statement.setBoolean(6, false);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
