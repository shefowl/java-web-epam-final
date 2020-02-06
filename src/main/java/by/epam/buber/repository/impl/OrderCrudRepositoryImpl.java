package by.epam.buber.repository.impl;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.exception.DaoException;
import by.epam.buber.repository.OrderCrudRepository;
import by.epam.buber.repository.impl.util.ResultSetConverter;
import by.epam.buber.repository.pool.ConnectionPool;
import by.epam.buber.repository.pool.ConnectionPoolException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderCrudRepositoryImpl implements OrderCrudRepository {

    private ResultSetConverter converter = ResultSetConverter.getInstance();

    public static final String SQL_ORDER_REQUEST = "SELECT  * FROM carOrder";
    public static final String SQL_ORDER_REQUEST_BY_ID = "SELECT  * FROM carOrder WHERE id=?";
    public static final String SQL_ORDER_REQUEST_BY_USER_ID = "SELECT  * FROM carOrder WHERE userId=?";
    public static final String SQL_ORDER_REQUEST_BY_DRIVER_ID = "SELECT  * FROM carOrder WHERE driverId=?";
    public static final String SQL_ORDER_REQUEST_STARTED_BY_ID = "SELECT started FROM carOrder WHERE id=?";
    public static final String SQL_CHECK_DRIVER_REQUEST = "SELECT  * FROM driverOrderlist WHERE orderId=?";
    public static final String SQL_CHECK_DRIVER_ACCEPT = "SELECT  * FROM carorder WHERE driverId=?";
    public static final String SQL_ORDER_UPDATE = "INSERT INTO carOrder(userId, coordinates, destinationPoint," +
            " price, orderComment, carClass, completed, destinationCoordinates," +
            " driverId, started)VALUES (?,?,?,?,?,?,?,?,?,?)";
    public static final String SQL_ORDER_UPDATE_BY_ID = "UPDATE carOrder SET price=?, completed=? WHERE id=?";
    public static final String SQL_ORDER_UPDATE_ACCEPTED_BY_ID = "UPDATE carOrder SET driverId=? WHERE id=?";
    public static final String SQL_ORDER_UPDATE_COMPLETED_BY_ID = "UPDATE carOrder SET completed=? WHERE id=?";
    public static final String SQL_ORDER_UPDATE_STARTED_BY_ID = "UPDATE carOrder SET started=? WHERE id=?";
    public static final String SQL_ORDER_UPDATE_PRICE_BY_ID = "UPDATE carOrder SET price=? WHERE id=?";
    public static final String SQL_ORDER_DELETE_BY_ID = "DELETE FROM carOrder WHERE id=?";
    public static final String DELETE_FROM_DRIVER_ORDER_LIST_EXCEPT_ORDER_ID =
            "DELETE FROM driverOrderList WHERE orderId!=?";
    public static final String SQL_DRIVER_ORDER_LIST_DELETE_BY_ORDER_ID = "DELETE FROM driverOrderList WHERE orderId=?";
    public static final String SQL_ORDER_REQUEST_FROM_DRIVER_LIST = "SELECT c.* FROM carorder c INNER JOIN " +
            "driverOrderList d ON c.id=d.orderId WHERE d.driverId=?";
    public static final String SET_FOREIGN_KEY_CHECKS_0 = "SET FOREIGN_KEY_CHECKS = 0";
    public static final String SET_FOREIGN_KEY_CHECKS_1 = "SET FOREIGN_KEY_CHECKS = 1";


    @Override
    public List<Order> getOrdersByParticipantId(Integer id) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Order order;
                while (resultSet.next()) {
                    order = converter.convertOrderFromResultSet(resultSet);
                    orders.add(order);
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public List<Order> getAll() throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_ORDER_REQUEST)) {
            Order order;
            while(resultSet.next()) {
                order = converter.convertOrderFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public Order getById(Integer id) throws DaoException {
        Order order = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                order = converter.convertOrderFromResultSet(resultSet);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    @Override
    public List<Order> getByUserId(Integer userId) throws DaoException {
        Order order = null;
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_USER_ID)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    orders.add(converter.convertOrderFromResultSet(resultSet));
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public Order getCurrentByUserId(Integer userId) throws DaoException {
        Order order = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_USER_ID)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    if(!resultSet.getBoolean("completed")) {
                        order = converter.convertOrderFromResultSet(resultSet);
                    }
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    @Override
    public List<Order> getCurrentOrdersByDriverId(Integer driverId) throws DaoException {
        Order order = null;
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_DRIVER_ID)) {
            statement.setInt(1, driverId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    if(!resultSet.getBoolean("completed")) {
                        orders.add(converter.convertOrderFromResultSet(resultSet));
                    }
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public Order getCurrentByDriverId(Integer driverId) throws DaoException {
        Order order = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_DRIVER_ID)) {
            statement.setInt(1, driverId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    if(!resultSet.getBoolean("completed")) {
                        order = converter.convertOrderFromResultSet(resultSet);
                    }
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    @Override
    public boolean driverRequested(Integer driverId) throws DaoException{
        boolean requested = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_CHECK_DRIVER_REQUEST)) {
            statement.setInt(1, driverId);
            try(ResultSet resultSet = statement.executeQuery()) {
                requested = resultSet.next();
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
        return requested;
    }

    @Override
    public boolean driverAccepted(Integer driverId) throws DaoException{
        boolean requested = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_CHECK_DRIVER_ACCEPT)) {
            statement.setInt(1, driverId);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (!resultSet.getBoolean("completed")) {
                        requested = resultSet.next();
                    }
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
        return requested;
    }

    @Override
    public void setStartedById(Integer orderId, boolean started) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE_STARTED_BY_ID)) {
            statement.setBoolean(1, started);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean orderStarted(Integer orderId) throws DaoException{
        boolean started = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_REQUEST_STARTED_BY_ID)) {
            statement.setInt(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    started = resultSet.getBoolean("started");
                }
            }
        }
        catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
        return started;
    }

    @Override
    public List<Order> getCurrentFromDriverList(Integer driveId) throws DaoException {
        Order order = null;
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_FROM_DRIVER_LIST)) {
            statement.setInt(1, driveId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                        orders.add(converter.convertOrderFromResultSet(resultSet));
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public void save(Integer id, Order order) throws DaoException { // вынести принятие в отдельный метод
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE_BY_ID)) {
            statement.setBigDecimal(1, order.getPrice());
            statement.setBoolean(2, order.isCompleted());
            statement.setInt(3, id);
            statement.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void save(Order order) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statementChecks0 = connection.createStatement();
             Statement statementChecks1 = connection.createStatement();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE)) {
            statementChecks0.executeQuery(SET_FOREIGN_KEY_CHECKS_0); // поместить в очередь?
            statement.setInt(1, order.getUserId());
            statement.setLong(2, order.getCoordinates());
            statement.setString(3, order.getDestinationPoint());
            statement.setBigDecimal(4, order.getPrice());
            statement.setString(5, order.getComment());
            statement.setString(6, order.getCarClass().name());
            statement.setBoolean(7, order.isCompleted());
            statement.setLong(8, order.getDestinationCoordinates());
            statement.setInt(9, order.getDriverId());
            statement.setBoolean(10, order.isStarted());
            statement.executeUpdate();
            statementChecks0.executeQuery(SET_FOREIGN_KEY_CHECKS_1);
        }
        catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void setAccepted(Integer driverId, Integer orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE_ACCEPTED_BY_ID)) {
            statement.setInt(1, driverId);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void setCompleted(boolean completed, Integer orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE_COMPLETED_BY_ID)) {
            statement.setBoolean(1, completed);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void setPrice(BigDecimal price, Integer orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE_PRICE_BY_ID)) {
            statement.setBigDecimal(1, price);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statementChecks0 = connection.createStatement();
             Statement statementChecks1 = connection.createStatement();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_DELETE_BY_ID)) {
            statementChecks0.executeQuery(SET_FOREIGN_KEY_CHECKS_0); // поместить в очередь?
            statement.setInt(1, id);
            statement.executeUpdate();
            statementChecks1.executeQuery(SET_FOREIGN_KEY_CHECKS_1);
        }
        catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteFromDriverList(Integer orderId) throws DaoException{
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_ORDER_LIST_DELETE_BY_ORDER_ID)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
    }
    @Override
    public void clearDriverOrderListExceptAccepted(Integer orderId) throws DaoException{
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FROM_DRIVER_ORDER_LIST_EXCEPT_ORDER_ID)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
    }
}

