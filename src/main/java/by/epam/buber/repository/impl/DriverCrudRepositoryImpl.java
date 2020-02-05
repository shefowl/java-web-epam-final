package by.epam.buber.repository.impl;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.exception.DaoException;
import by.epam.buber.repository.DriverCrudRepository;
import by.epam.buber.repository.impl.util.ResultSetConverter;
import by.epam.buber.repository.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverCrudRepositoryImpl implements DriverCrudRepository {

    private ResultSetConverter converter = ResultSetConverter.getInstance();

    private final String SQL_SAVE_DRIVER = "INSERT INTO participant (name, password, role, email, phone) " +
            "VALUES (?, ?, 'DRIVER', ?, ?); SELECT LAST_INSERT_ID() " +
            "INTO @mysql_variable_here; INSERT INTO driver " +
            "(participantId, active, busy, coordinates, pricePerKm) VALUES " +
            "(@mysql_variable_here, ?, ?, ?, ?); INSERT INTO car (driverId, mark, model, carClass) " +
            "VALUES(@mysql_variable_here, ?, ?, ?)";
    private final String SQL_SAVE_DRIVER_BY_ID = "INSERT INTO participant (id, name, password, role, email, phone) " +
            "VALUES (?, ?, ?, 'DRIVER', ?, ?); INSERT INTO driver " +
            "(participantId, active, busy, coordinates, pricePerKm) VALUES " +
            "(?, ?, ?, ?, ?); INSERT INTO car (driverId, mark, model, carClass) " +
            "?, ?, ?, ?)";
    private final String SQL_DRIVER_GET_ALL_BY_ID = "SELECT * FROM participant JOIN driver ON participantId=id" +
            " JOIN car ON driverId=id";
    private final String SQL_DRIVER_GET_BY_ID = "SELECT * FROM participant JOIN driver ON participantId=id" +
            " JOIN car ON driverId=id";
    private final String SQL_DRIVER_UPDATE_ORDERS = "INSERT INTO driverOrderList(orderId, driverId)VALUES (?,?)";
    private final String SQL_DRIVER_IS_BUSY_BY_ID = "SELECT  busy FROM driver WHERE participantId=?";
    private final String SQL_DRIVER_UPDATE_BUSY_BY_ID = "UPDATE driver SET busy=? WHERE participantId=?";
    private final String SQL_DRIVER_UPDATE_ACTIVE_BY_ID = "UPDATE driver SET active=? WHERE participantId=?";
    private final String SQL_DRIVER_DELETE_BY_ID = "SET FOREIGN_KEY_CHECKS = 0; DELETE participant, driver, car FROM " +
            "participant JOIN driver ON participantId=id JOIN car ON driverId=id WHERE id=?; SET FOREIGN_KEY_CHECKS = 1";
    public static final String SQL_DRIVER_GET_ABLE_BY_COORDINATES_AND_CAR_CLASS =
            " SELECT * FROM (SELECT * FROM (SELECT * FROM (SELECT * FROM driver JOIN car ON " +
                    "participantId=driverId WHERE carClass=?) AS T WHERE active=1)" +
                    " AS D WHERE busy=0) AS P JOIN participant ON id=driverId;";
    private final String SQL_DRIVER_UPDATE_COORDINATES_BY_ID = "SET FOREIGN_KEY_CHECKS=0; UPDATE driver SET" +
            " coordinates=? WHERE participantId=?; SET FOREIGN_KEY_CHECKS=1";
//    private final String SET_FOREIGN_KEY_CHECKS_0 = "SET FOREIGN_KEY_CHECKS = 0";
//    private final String SET_FOREIGN_KEY_CHECKS_1 = "SET FOREIGN_KEY_CHECKS = 1";

    public List<Driver> getAll() throws DaoException{
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_DRIVER_GET_ALL_BY_ID)) {
            while(resultSet.next()) {
                Driver driver = new Driver();
                driver = converter.convertDriverFromResultSet(resultSet, driver);
                drivers.add(driver);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return drivers;
    }

    @Override
    public Driver getById(Integer id) throws DaoException {
        Driver driver = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_GET_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    driver = (Driver)converter.convertParticipantFromResultSet(resultSet, new Driver());
                    //return driver;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return driver;
    }

    @Override
    public List<Driver> getAbleDriversByCarClass(CarClass carClass) throws DaoException{
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_GET_ABLE_BY_COORDINATES_AND_CAR_CLASS)) {
            statement.setString(1, carClass.name());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Driver driver = new Driver();
                driver = converter.convertDriverFromResultSet(resultSet, driver);
                drivers.add(driver);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return drivers;
    }

    @Override
    public void setOrderToDriver(Order order, Integer id) throws DaoException{
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_UPDATE_ORDERS)) {
            statement.setInt(1, order.getId());
            statement.setInt(2, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void setDriverCoordinates(Integer driverId, long coordinates) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_UPDATE_COORDINATES_BY_ID)) {
            statement.setLong(1, coordinates);
            statement.setInt(2, driverId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void setBusyById(Integer id, boolean busy) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_UPDATE_BUSY_BY_ID)) {
            converter.statementSetBooleanById(statement, busy, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean isDriverBusy(Integer driverId) throws DaoException{
        boolean busy = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_DRIVER_IS_BUSY_BY_ID)) {
            statement.setInt(1, driverId);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()) {
                    busy = resultSet.getBoolean("busy");
                }
            }
        }
        catch (SQLException e){
            throw new DaoException(e);
        }
        return busy;
    }

    @Override
    public void setDriverActive(Integer driverId, boolean active) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_UPDATE_ACTIVE_BY_ID)) {
            converter.statementSetBooleanById(statement, active, driverId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void save(Driver driver) throws DaoException{
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SAVE_DRIVER)) {
            converter.statementSetParticipant(statement, driver);
            statement.setBoolean(6, driver.isActive());
            statement.setBoolean(7, driver.isBusy());
            statement.setLong(8, driver.getCoordinates());
            statement.setBigDecimal(9, driver.getPricePerKm());
            statement.setString(10, driver.getCar().getMark());
            statement.setString(11, driver.getCar().getModel());
            statement.setString(12, driver.getCar().getCarClass().name());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void save(Integer id, Driver driver) throws DaoException{
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SAVE_DRIVER_BY_ID)) {
            statement.setInt(1,driver.getId());
            statement.setString(2, driver.getName());
            statement.setString(3, driver.getPassword());
            statement.setString(4, driver.getEmail());
            statement.setString(5, driver.getRole().name());
            statement.setString(6, driver.getPhoneNumber());
            statement.setInt(7,driver.getId());
            statement.setBoolean(8, driver.isActive());
            statement.setBoolean(9, driver.isBusy());
            statement.setLong(10, driver.getCoordinates());
            statement.setBigDecimal(11, driver.getPricePerKm());
            statement.setInt(12,driver.getId());
            statement.setString(13, driver.getCar().getMark());
            statement.setString(14, driver.getCar().getModel());
            statement.setString(15, driver.getCar().getCarClass().name());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
