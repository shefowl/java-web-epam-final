package by.epam.buber.repository.impl;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.repository.OrderCrudRepository;
import by.epam.buber.repository.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderCrudRepositoryImpl implements OrderCrudRepository {
    public static final String SQL_ORDER_REQUEST = "SELECT  * FROM carOrder";
    public static final String SQL_ORDER_REQUEST_BY_ID = "SELECT  * FROM carOrder WHERE id=?";
    public static final String SQL_ORDER_REQUEST_BY_USER_ID = "SELECT  * FROM carOrder WHERE userId=?";
    public static final String SQL_ORDER_REQUEST_BY_DRIVER_ID = "SELECT  * FROM carOrder WHERE driverId=?";
    public static final String SQL_CHECK_DRIVER_REQUEST = "SELECT  * FROM driverOrderlist WHERE orderId=?";
    public static final String SQL_CHECK_DRIVER_ACCEPT = "SELECT  * FROM carorder WHERE driverId=?";
    public static final String SQL_ORDER_UPDATE = "INSERT INTO carOrder(userId, coordinates, destinationPoint," +
            " price, orderComment, carClass, completed, destinationCoordinates," +
            " driverId)VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String SQL_ORDER_UPDATE_BY_ID = "UPDATE carOrder SET price=?, completed=? WHERE id=?";
    public static final String SQL_ORDER_UPDATE_ACCEPTED_BY_ID = "UPDATE carOrder SET driverId=? WHERE id=?";
    public static final String SQL_ORDER_UPDATE_COMPLETED_BY_ID = "UPDATE carOrder SET completed=? WHERE id=?";
    public static final String SQL_ORDER_UPDATE_STARTED_BY_ID = "UPDATE carOrder SET started=? WHERE id=?";
    public static final String SQL_ORDER_UPDATE_PRICE_BY_ID = "UPDATE carOrder SET price=? WHERE id=?";
    public static final String SQL_ORDER_DELETE_BY_ID = "DELETE FROM carOrder WHERE id=?";
    public static final String SQL_DRIVER_ORDER_LIST_DELETE_BY_ORDER_ID = "DELETE FROM driverOrderList WHERE orderId=?";
    public static final String SQL_ORDER_REQUEST_FROM_DRIVER_LIST = "SELECT c.* FROM carorder c INNER JOIN " +
            "driverOrderList d ON c.id=d.orderId WHERE d.driverId=?";
    public static final String SET_FOREIGN_KEY_CHECKS_0 = "SET FOREIGN_KEY_CHECKS = 0";
    public static final String SET_FOREIGN_KEY_CHECKS_1 = "SET FOREIGN_KEY_CHECKS = 1";

    @Override
    public List<Order> getOrdersByParticipantId(int id) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Order order;
                while (resultSet.next()) {
                    order = convertFromResultSet(resultSet);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_ORDER_REQUEST)) {
            Order order;
            while(resultSet.next()) {
                order = convertFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order getById(int id) {
        Order order = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                order = convertFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public List<Order> getByUserId(int id) {
        Order order = null;
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_USER_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    orders.add(convertFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order getCurrentByUserId(int id) {
        Order order = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_USER_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    if(!resultSet.getBoolean("completed")) {
                        order = convertFromResultSet(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public List<Order> getCurrentOrdersByDriverId(int id) {
        Order order = null;
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_DRIVER_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    if(!resultSet.getBoolean("completed")) {
                        orders.add(convertFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public Order getCurrentByDriverId(int id) {
        Order order = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_USER_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    if(!resultSet.getBoolean("completed")) {
                        order = convertFromResultSet(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public boolean driverRequested(int id){
        boolean requested = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_CHECK_DRIVER_REQUEST)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                requested = resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requested;
    }

    public boolean driverAccepted(int id){
        boolean requested = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_CHECK_DRIVER_ACCEPT)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                requested = resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requested;
    }

    public void setStartedById(int id, boolean started) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE_STARTED_BY_ID)) {
            statement.setBoolean(1, started);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean orderStarted(int id){
        boolean started = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE_STARTED_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    started = resultSet.getBoolean("started");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return started;
    }


    public List<Order> getCurrentFromDriverList(int id) {
        Order order = null;
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_FROM_DRIVER_LIST)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                        orders.add(convertFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }


    private Order convertFromResultSet(ResultSet resultSet){
        Order order = new Order();
        try {
            order.setId(resultSet.getInt("id"));
            order.setCoordinates(resultSet.getInt("coordinates"));
            order.setDestinationPoint(resultSet.getString("destinationPoint"));
            order.setDestinationCoordinates(resultSet.getInt("destinationCoordinates"));
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

    @Override
    public void save(Integer id, Order order) { // вынести принятие в отдельный метод
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE_BY_ID)) {
            //statement.setString(1, order.get());
            statement.setBigDecimal(1, order.getPrice());
            statement.setBoolean(2, order.isCompleted());
            statement.setInt(3, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Order order) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statementCheks0 = connection.createStatement();
             Statement statementCheks1 = connection.createStatement();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE)) {
            statementCheks0.executeQuery(SET_FOREIGN_KEY_CHECKS_0); // поместить в очередь?
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getCoordinates());
            statement.setString(3, order.getDestinationPoint());
            statement.setBigDecimal(4, order.getPrice());
            statement.setString(5, order.getComment());
            statement.setString(6, order.getCarClass().name());
            statement.setBoolean(7, order.isCompleted());
            statement.setInt(8, order.getDestinationCoordinates());
            statement.setInt(9, order.getDriverId());
            statement.executeUpdate();
            statementCheks0.executeQuery(SET_FOREIGN_KEY_CHECKS_1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAccepted(int driverId, int orderId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE_ACCEPTED_BY_ID)) {
            statement.setInt(1, driverId);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCompleted(boolean completed, int orderId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE_COMPLETED_BY_ID)) {
            statement.setBoolean(1, completed);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPrice(BigDecimal price, int orderId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE_PRICE_BY_ID)) {
            statement.setBigDecimal(1, price);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDriverList(Integer id){
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_ORDER_LIST_DELETE_BY_ORDER_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
