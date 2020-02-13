package by.epam.buber.repository.impl;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.exception.DaoException;
import by.epam.buber.repository.DriverCrudRepository;
import by.epam.buber.repository.impl.util.ResultSetConverter;
import by.epam.buber.repository.pool.ConnectionPool;
import by.epam.buber.repository.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverCrudRepositoryImpl extends AbstractRepository implements DriverCrudRepository {

    private final static Logger logger = LogManager.getLogger(DriverCrudRepositoryImpl.class);
    private ResultSetConverter converter = ResultSetConverter.getInstance();

    private final String SQL_SAVE_DRIVER = "INSERT INTO participant (name, password, email, role, phone, banned) " +
            "VALUES (?, ?, ?, ?, ?, ?); SELECT LAST_INSERT_ID() " +
            "INTO @mysql_variable_here; INSERT INTO driver " +
            "(participantId, active, busy, coordinates, pricePerKm) VALUES " +
            "(@mysql_variable_here, ?, ?, ?, ?); INSERT INTO car (driverId, mark, model, carClass) " +
            "VALUES(@mysql_variable_here, ?, ?, ?)";
    private final String SQL_SAVE_DRIVER_BY_ID = "INSERT INTO participant (id, name, password, role, email, phone, banned) " +
            "VALUES (?, ?, ?, ?, ?, ?); INSERT INTO driver " +
            "(participantId, active, busy, coordinates, pricePerKm) VALUES " +
            "(?, ?, ?, ?, ?); INSERT INTO car (driverId, mark, model, carClass) " +
            "?, ?, ?, ?)";

    private final String SQL_DRIVER_GET_ALL_BY_ID = "SELECT * FROM participant JOIN driver ON participantId=id" +
            " JOIN car ON driverId=id";
    private final String SQL_DRIVER_GET_BY_ID = "SELECT * FROM participant JOIN driver ON participantId=id" +
            " JOIN car ON driverId=id";
    public static final String SQL_DRIVER_GET_ABLE_BY_COORDINATES_AND_CAR_CLASS =
            "SELECT * FROM(SELECT * FROM (SELECT * FROM(SELECT active, busy, coordinates, pricePerKm, driverId, mark," +
                    " model, carClass FROM driver JOIN car ON participantId=driverId WHERE carCLass=?) AS T " +
                    "WHERE active=1) AS D WHERE busy =0) AS P JOIN participant ON id=driverId;";

    private final String SQL_DRIVER_UPDATE_ORDERS = "INSERT INTO driverOrderList(orderId, driverId)VALUES (?,?)";
    private final String SQL_DRIVER_IS_BUSY_BY_ID = "SELECT  busy FROM driver WHERE participantId=?";
    private final String SQL_DRIVER_UPDATE_BUSY_BY_ID = "UPDATE driver SET busy=? WHERE participantId=?";
    private final String SQL_DRIVER_UPDATE_ACTIVE_BY_ID = "UPDATE driver SET active=? WHERE participantId=?";
    private final String SQL_DRIVER_UPDATE_COORDINATES_BY_ID = "SET FOREIGN_KEY_CHECKS=0; UPDATE driver SET" +
            " coordinates=? WHERE participantId=?; SET FOREIGN_KEY_CHECKS=1";

    private final String SQL_DRIVER_DELETE_BY_ID = "SET FOREIGN_KEY_CHECKS = 0; DELETE p, d, c FROM " +
            "participant p JOIN driver d ON d.participantId=p.id JOIN car c ON c.driverId=p.id WHERE p.id=?;" +
            " SET FOREIGN_KEY_CHECKS = 1";

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
        } catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
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
        } catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
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
        } catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
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
        catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
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
        catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public void setBusyById(Integer id, boolean busy) throws DaoException {
        setBooleanById(busy, id, SQL_DRIVER_UPDATE_BUSY_BY_ID);
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
        catch (ConnectionPoolException | SQLException e){
            logger.error(e);
            throw new DaoException(e);
        }
        return busy;
    }

    @Override
    public void setDriverActive(Integer driverId, boolean active) throws DaoException {
        setBooleanById(active, driverId, SQL_DRIVER_UPDATE_ACTIVE_BY_ID);
    }

    @Override
    public void save(Driver driver) throws DaoException{
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SAVE_DRIVER)) {
            converter.statementSetParticipant(statement, driver);
            statement.setBoolean(7, driver.isActive());
            statement.setBoolean(8, driver.isBusy());
            statement.setLong(9, driver.getCoordinates());
            statement.setBigDecimal(10, driver.getPricePerKm());
            statement.setString(11, driver.getCar().getMark());
            statement.setString(12, driver.getCar().getModel());
            statement.setString(13, driver.getCar().getCarClass().name());
            statement.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public void save(Integer id, Driver driver) throws DaoException{
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            connection.setAutoCommit(false);
             try(PreparedStatement statement = connection.prepareStatement(SQL_SAVE_DRIVER_BY_ID)) {
                 statement.setInt(1, driver.getId());
                 statement.setString(2, driver.getName());
                 statement.setString(3, driver.getPassword());
                 statement.setString(4, driver.getEmail());
                 statement.setString(5, driver.getRole().name());
                 statement.setString(6, driver.getPhoneNumber());
                 statement.setBoolean(7, false);
                 statement.setInt(8, driver.getId());
                 statement.setBoolean(9, driver.isActive());
                 statement.setBoolean(10, driver.isBusy());
                 statement.setLong(11, driver.getCoordinates());
                 statement.setBigDecimal(12, driver.getPricePerKm());
                 statement.setInt(13, driver.getId());
                 statement.setString(14, driver.getCar().getMark());
                 statement.setString(15, driver.getCar().getModel());
                 statement.setString(16, driver.getCar().getCarClass().name());
                 statement.executeUpdate();
                 connection.commit();
                 connection.setAutoCommit(true);
             }
             catch (SQLException e){
                 connection.rollback();
                 logger.error(e);
                 throw new DaoException(e);
             }
        }
        catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        deleteById(id, SQL_DRIVER_DELETE_BY_ID);
    }

}
